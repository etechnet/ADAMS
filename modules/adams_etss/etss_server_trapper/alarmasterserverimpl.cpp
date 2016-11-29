/***************************************************************************
                          processmonitorserverimpl.cpp  -  description
                             -------------------
    begin                : Tue Jan 21 2003
    copyright            : (C) 2003 by Raffaele Ficcadenti
    email                : raffaele.ficcadenti@e-tech.net
 ***************************************************************************/


#include "alarmmasterserverimpl.h"
#include <Qt/qtextstream.h>
#include <Qt/q3strlist.h>
#include <Qt/qsemaphore.h>
#include <Qt/qwaitcondition.h>
#include <Qt/qdir.h>
#include <Qt/qfileinfo.h>
#include <Qt/qxmlstream.h>
#include <stdlib.h>

#if defined(P_USE_TAO)
#include "orbsvcs/CosNamingC.h"
#endif

#include "ntmshared/storenode.h"
#include "applogger.h"
#include "ntmshared/ntmglobals.h"
#include "globalobjs.h"
#include <ntmshared/definealarmrelation.h>
#include <ntmshared/xmlio.h>

#define	MAX_START_TIMING	(60*1000)	// half a minute to start a dataserver !
#define WATCHDOG_TIMEOUT	(60*30 * 1)	// one half hour to watchdog intervention
// #define LONG_WATCHDOG_TIMEOUT	(60*20)		// long watchdog delay

static PluginFactory pluginFactory;		// a proxy factory for traffic elements plugins
static QMutex mutex_ini;
static QSemaphore semaphore ( MAX_CONCURRENT_ALARM_REQUESTS );
static bool initRunning = false;

extern bool globalTerminationRequest;
extern pid_t my_pid;

/**
 * Costruttore della classe AlarmMasterServerImpl.
 */
AlarmMasterServerImpl::AlarmMasterServerImpl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy )
{

	num_alarms = 0;
	last_run_time_loaded = false;
	serverID = idServer;

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}

	orb = GlobalObjs::orb();

	config_name = cnfparser.locateFile ( "alarms.ini", StoreNode::myExeName() );
	NOME_CENTRALE = StoreNode::get();


	lout << "************************************************" << endl;
	lout << "FILE DI CONFIGURAZIONE	= " << config_name << endl;
	lout << "NOME_CENTRALE		= " << NOME_CENTRALE << endl;
	lout << "SERVER_ID              = " << serverID << endl;
	lout << "************************************************" << endl << endl;

	status_file_name = cnfparser.parQTxtGetValue ( "MasterStatusFile", "AlarmMasterServer_Env", config_name );
	QString status_ext = QString::number ( serverID ) + ".xml";
	status_file_name.replace ( ".xml", status_ext );

	// Initialize watchdog
	timeout.setProcess ( getpid() );
	timeout.setTimeOut ( WATCHDOG_TIMEOUT );
	timeout.setORB ( orb );
	timeout.setExitEnforced();

	timeout.start();		// watchdog active
	lout2 << "watchdog started" << endl;

	alarmMap = new AlarmDataMap;
	change_day_time = QDateTime::currentDateTime();
	int pwrc;

#ifdef TEST_PLAN
	db_nome_db = "DBRDAT";
	db_nome_nodo = "RDA";
	db_type_pswd = "ORACLE";
	db_nome_utente = "ops$rdaexec";
#else
	db_nome_db = "DBRDAP";
	db_nome_nodo = "RDA";
	db_type_pswd = "ORACLE";
	db_nome_utente = "ops$rdaexec";
#endif

	/** Got paaword for SFTP session */
	lout1 << "Get password for node " << db_nome_nodo << endl;
	if ( ( pwrc = pwdcrypt_getpasswd ( db_nome_nodo, db_type_pswd, db_nome_utente, remote_password ) ) ) {
		switch ( pwrc ) {
			case 1:
				lout1 << "CRYPTED FILE OPEN ERROR!!" << endl;
				break;
			case 2:
				lout1 << "Password for user " << db_nome_utente << ", type " << db_type_pswd << " not found!!" << endl;
				break;
		}
	}
// 	lout3 << "LOGIN=[" << "ops$rdaexec" << "]" << endl;
// 	lout3 << "PASSWD=[" << remote_password << "]" << endl;

	// initialize initRunner
	m_initRunner.setAMSInstance ( this );

	lout2 << "Server instance created..." << endl;
}


/**
 * Distruttore della classe DispatcherserverImpl.
 */
AlarmMasterServerImpl::~AlarmMasterServerImpl()
{
}


// UNIX_STD=1995 ps -ae -o pid,ppid,comm | grep alarmmasterserverimpl

/**
 * Metodo invocato dal client per fare lo shutdown
 */
void AlarmMasterServerImpl::ShutDown() throw ( CORBA::SystemException )
{
	dout << "ShutDown()" << endl;
	if ( QFile::exists ( status_file_name ) ) {
		dout << "** Removing status file..." << endl;
		QFile::remove ( status_file_name );
	}
	globalTerminationRequest = true;
	waitGenerator();
	orb->shutdown ( 0 );
// 	::exit(0);
}

PortableServer::POA_ptr AlarmMasterServerImpl::_default_POA()
{
	return PortableServer::POA::_duplicate ( m_poa_hierarchy->CoreProcessing() );
}


void AlarmMasterServerImpl::checkRestart()
{
	if ( !last_run_time_loaded ) {
		QDateTime now = QDateTime::currentDateTime();
		if ( !readStatus ( ) ) {
			last_run_time = now;
			lout << "** Could not check XML date from file." << endl;
			cleanTemporary();
		} else {
			lout << "Reference date:" << now.date().toString ( "yyyyMMdd" )
			     << " - XML date:" << last_run_time.date().toString ( "yyyyMMdd" )
			     << endl;
			if ( now.date() == last_run_time.date() ) {	// restart in the same day
				lout << "** RELOADING ALARM STATUS **" << endl;
				if ( !readStatus() ) {
					last_run_time = now;
					lout << "** Could not re-load status from file. Exiting..." << endl;
					::exit ( 1 );
				}
			} else
				cleanTemporary();

		}

		last_run_time_loaded = true;
	}
}

bool AlarmMasterServerImpl::checkConfigChanges()
{
// 	ntm.alarmRelationInterface->setTrafficElementReference ( ntm.trafficElementInterface );
	AlarmRelationsIterator it = ntm.alarmRelationInterface->getIterator();

	API_DataBase_alarmmasterserver db ( db_nome_db, "ops$rdaexec", remote_password );
	if ( db.getExitStatusDB() != DB_NOERROR ) {
		lout << endl << endl << "** Initialization failure. Cannot proceed further..." << endl;
		return true;		// no db conn.
	}

	int m_num_alarms = 0;
	int * m_alarm_vector = new int [MAX_ALARMS];

	while ( it.current() ) {
		if ( db.checkIdRelationOnServer ( serverID, it.current()->data.idAlarmRelation ) == true ) {
			m_alarm_vector[m_num_alarms] = it.current()->data.idAlarmRelation;
			lout3 << "ID REL da processare: " << m_alarm_vector[m_num_alarms] << endl;
			++m_num_alarms;
		} else {
			lout3 << "ID REL scartata: " << it.current()->data.idAlarmRelation << endl;
		}
		++it;
	}

	bool changed = false;
	if ( m_num_alarms != num_alarms )
		changed = true;

	for ( int i = 0; i < m_num_alarms; i++ ) {
		if ( m_alarm_vector[i] != alarm_vector[i] ) {
			changed = true;
			break;
		}
	}

	delete [] m_alarm_vector;

	if ( changed )
		dout << "*** Alarm configuration has changed ***" << endl;

	return changed;
}


void AlarmMasterServerImpl::cleanTemporary()
{

	QDir d = QDir::current();	// all data where program run

// 	d.setFilter ( QDir::Files );
// 	d.setNameFilter ( "helper_*.so" );
// 	d.setSorting ( QDir::Size | QDir::Reversed );

	dout << "Removing RT collected data, and alarm status:" << endl;

	QString my_data_fname;

	for ( int i = 0; i < num_alarms; i++ ) {
		my_data_fname = QString ( RT_STATUS_BASENAME ) + QString::number ( alarm_vector[i] ) + ".bin";
		rlout1 << my_data_fname << endl;
		d.remove ( my_data_fname );
		my_data_fname = composeRelationStatusFileName ( alarm_vector[i] );
		rlout1 << my_data_fname << endl;
		d.remove ( my_data_fname );
	}


// 	QStringList name_filter_list;
// 	name_filter_list << ".rt_*" << ".ams_*";
//
// 	QFileInfoList f_list = d.entryInfoList ( name_filter_list, QDir::Files | QDir::Hidden );
//
// 	for ( int i = 0; i < f_list.size(); i++ ) {
// 		QFileInfo f_info = f_list.at ( i );
// 		rlout1 << f_info.fileName() << endl;
// 		d.remove ( f_info.fileName() );
// 	}

	dout << "Resetting thresholds map." << endl;
	thresholds.clear();

	dout << "Reloading thresholds..." << endl;

	API_DataBase_alarmmasterserver db ( db_nome_db, "ops$rdaexec", remote_password );
	if ( db.getExitStatusDB() != DB_NOERROR ) {
		lout << endl << endl << "** Initialization failure. Cannot proceed further..." << endl;
		return;		// no db conn.
	}

	QDateTime yesterday = QDateTime::currentDateTime().addDays ( -1 );
	db.getAllThresholds ( thresholds, yesterday, alarm_vector, num_alarms, ntm );

	dout << "Thresholds loaded." << endl;

}


void AMS_initRunner::run()
{
	lout3 << "initRunner started" << endl;
	if ( initRunning )
		return;
	initRunning = true;

	ams->initAll();
	ams->loadPolicies();

	initRunning = false;
	lout3 << "initRunner completed" << endl;
	return;
}

void AlarmMasterServerImpl::checkDup()
{
	pid_t c_pid = ( pid_t ) checkPid();

	if ( my_pid != c_pid ) {
		dout << "***** Server duplicate running: me " << my_pid << " new " << c_pid << endl;

		if ( c_pid != 0 ) {
			dout << "***** PID Reset..." << endl;
			XmlIO pid_file;
			// reset process pid
			pid_file.load ( m_pid_file() );
			pid_file.xmlClear();
			pid_file.addNodeSimple ( "PID", ( unsigned int ) 0 );
			if ( !pid_file.save() ) {
				dout << "*** WARNING: Could not reset PID..." << endl;
			}
		}

		::exit ( 1 );
	}

}


void AlarmMasterServerImpl::initAll()
{
	static bool db_clean_done = false;

	if ( !nodeConfigHandler.loaded() ) {
		lout << "Cant't load CENTRALI structure..." << endl;
		return;
	}

	for ( int i = 0; i < MAX_CEN; i++ ) {
		invokeStatus.percent[ i ] = 0;
		invokeStatus.desc[ i ][ 0 ] = '\0';
		invokeStatus.url[ i ][ 0 ] = '\0';
	}

	mutex_ini.lock();

	API_DataBase_alarmmasterserver db ( db_nome_db, "ops$rdaexec", remote_password );
	if ( db.getExitStatusDB() != DB_NOERROR ) {
		lout << endl << endl << "** Initialization failure. Cannot proceed further..." << endl;
		mutex_ini.unlock();
		return;		// no db conn.
	}

	if ( ! db_clean_done ) {
		lout1 << "Cleaning history tables older than '" << FINESTRA_GG_ALLARMI_STORICI << "' days." << endl;
		db.eliminaAllarmiStorici ( FINESTRA_GG_ALLARMI_STORICI );
	}

	if ( !readConfiguration ( &db ) ) {
		mutex_ini.unlock();
		return;
	}

	fake_params.init_fake ( NOME_CENTRALE, qPrintable ( config_name ) );

	ntm.alarmRelationInterface->setTrafficElementReference ( ntm.trafficElementInterface );
	AlarmRelationsIterator it = ntm.alarmRelationInterface->getIterator();
	num_alarms = 0;
	//TODO: what happens if num_alarms change ??

	while ( it.current() ) {
		if ( db.checkIdRelationOnServer ( serverID, it.current()->data.idAlarmRelation ) == true ) {
			alarm_vector[num_alarms] = it.current()->data.idAlarmRelation;
			lout << "ID REL da processare: " << alarm_vector[num_alarms] << endl;
			++num_alarms;
		} else {
			lout << "ID REL scartata: " << it.current()->data.idAlarmRelation << endl;
		}
		++it;
	}

	if ( helpDict.count() == 0 ) {
		lout << "Helper plugins registration...." << endl;
		registerHelperPlugins();
		lout << "done." << endl;
	}  		// scan helper plugins

	lout << "* " << num_alarms << " alarm relations to process." << endl;

	if ( initAlarmVector() ) {
		lout << endl << endl << "** Initialization failure. Cannot proceed further..." << endl;
		mutex_ini.unlock();
		return;
	} else
		lout1 << "Alarm vector initialization successfull." << endl;

	lout << "Thresholds initialization..." << endl;
	QDateTime now = QDateTime::currentDateTime();
	QDateTime yesterday = now.addDays ( -1 );
	db.getAllThresholds ( thresholds, yesterday, alarm_vector, num_alarms, ntm );
	lout << "Thresholds loaded." << endl;

	checkRestart();

	mutex_ini.unlock();
}

bool AlarmMasterServerImpl::unloadRelationStatus ( int reference_relation, BTreeAlarm& map, bool flush_map )
{
	QFile f_status ( composeRelationStatusFileName ( reference_relation ) );
	if ( ! f_status.open ( QIODevice::WriteOnly ) ) {
		dout << "** Error opening status file " << composeRelationStatusFileName ( reference_relation ) << endl;
		return false;
	}
	QDataStream ds ( &f_status );

	BTreeAlarmIterator map_iterator = map.begin();

	while ( map_iterator != map.end() ) {

		if ( map_iterator.key().size() ) {
			ds << map_iterator.key();
			ds << *map_iterator.data();
		}

		++map_iterator;
	}

	f_status.close();

	if ( flush_map ) {
		foreach ( AlarmStatusNode * ptr, map )
			delete ptr;
		map.clear();
		lout3 << "Current alarm node map destroyed." << endl;
	}

	return true;
}


bool AlarmMasterServerImpl::loadRelationStatus ( int reference_relation, BTreeAlarm& map )
{
	QString rel_file_name = composeRelationStatusFileName ( reference_relation );

	foreach ( AlarmStatusNode * ptr, map )
		delete ptr;
	map.clear();


	if ( ! QFile::exists ( rel_file_name ) )	// non-existent return an empty map
		return true;

	QFile f_status ( rel_file_name );
	if ( ! f_status.open ( QIODevice::ReadOnly ) ) {
		dout << "** Error opening status file " << rel_file_name << endl;
		return false;
	}
	QDataStream ds ( &f_status );

	QString key;

	while ( !ds.atEnd() ) {
		AlarmStatusNode * m_nodo = new AlarmStatusNode();

		ds >> key;
		ds >> *m_nodo;

		if ( key.size() )
			map.insert ( key, m_nodo );
	}

	f_status.close();

	return true;
}


bool AlarmMasterServerImpl::writeStatus ()
{
	QFile f_xml ( status_file_name );
	if ( ! f_xml.open ( QIODevice::WriteOnly | QIODevice::Text ) ) {
		dout << "** Error opening status file " << status_file_name << endl;
		return false;
	}

	QXmlStreamWriter w_xml ( &f_xml );

	w_xml.setAutoFormatting ( true );
	w_xml.writeStartDocument();
	w_xml.writeDTD ( "<!DOCTYPE alarm_status>" );
	w_xml.writeStartElement ( "alarm_master_status" );
	w_xml.writeAttribute ( "version", "1.0" );

	last_run_time = QDateTime::currentDateTime();
	w_xml.writeStartElement ( "last_run_time" );
	w_xml.writeTextElement ( "date", last_run_time.date().toString ( "yyyyMMdd" ) );
	w_xml.writeTextElement ( "time", last_run_time.time().toString ( "hhmmss" ) );
	w_xml.writeTextElement ( "num_rel", QString::number ( num_alarms ) );
	w_xml.writeEndElement();


// 	w_xml.writeEndElement();
	w_xml.writeEndDocument();
	f_xml.close();;

	return true;
}

bool AlarmMasterServerImpl::readStatus ()
{
	QFile f_xml ( status_file_name );
	if ( ! f_xml.open ( QIODevice::ReadOnly | QIODevice::Text ) ) {
		dout << "** Error opening status file " << status_file_name << endl;
		return 0;
	}

	QXmlStreamReader r_xml ( &f_xml );

	while ( !r_xml.atEnd() ) {

		r_xml.readNext();

		if ( r_xml.isStartElement() ) {
			if ( r_xml.name() == "alarm_master_status" ) {
				lout << "Loading status from XML version:" << r_xml.attributes().value ( "version" ).toString() << endl;
				continue;
			} else if ( r_xml.name() == "last_run_time" ) {
				do {
					r_xml.readNext();

					if ( r_xml.isStartElement() &&  r_xml.name() == "date" ) {
						QString d = r_xml.readElementText();
						lout1 << "xml date:" << d << endl;
						last_run_time.setDate ( QDate::fromString ( d, "yyyyMMdd" ) );
						lout1 << "date:" << last_run_time.date().toString ( "yyyyMMdd" ) << endl;
					} else if ( r_xml.isStartElement() &&  r_xml.name() == "time" ) {
						QString t = r_xml.readElementText();
						lout1 << "xml time:" << t << endl;
					} else if ( r_xml.isStartElement() &&  r_xml.name() == "num_rel" ) {
						int n_rel = r_xml.readElementText().toInt();
						lout1 << "num_rel:" << n_rel << endl;
					}
				} while ( ! ( r_xml.isEndElement() && r_xml.name() == "last_run_time" ) && !r_xml.atEnd() );

				continue;
			}
		}
	}

	if ( r_xml.hasError() ) {
		lout << "** Errors in XML file read" << endl;
		f_xml.close();
		f_xml.remove();
		dout << "Server Restart..." << endl;
		waitGenerator();
		orb->shutdown ( 0 );
		return false;
	}

	f_xml.close();
	return true;
}


void AlarmMasterServerImpl::waitGenerator()
{
	dout << "Waiting for running thread termination..." << endl;
	int nsec = 0;
	if ( isRunning() ) {
		while ( !isFinished() ) {
			QThread::sleep ( 1 );
			++nsec;
		}
	}

	dout << "timeout pending shutdown for " << nsec << " seconds." << endl;
}

/**
 * Metodo invocato dai client per effettuare il wakeup delle operazioni scedulate
 */
void AlarmMasterServerImpl::wakeup() throw ( CORBA::SystemException )
{
	lout3 << "** wakeup()" << endl;

	timeout.setiActive ( true );	// reset watchdog
	if ( timeout.running() == false )
		timeout.start();	// watchdog active

	if ( isRunning() ) {
		lout << "** wakeup() is ALREADY running..." << endl;
		return;
	}
	lout3 << "** wakeup() starting..." << endl;

	start();
	lout1 << "** wakeup() running..." << endl;
}


void AlarmMasterServerImpl::run()
{
	if ( globalTerminationRequest ) {
		lout << "*** wakeup() called in termination. Exiting." << endl;
		ShutDown();
		return;
	}

	dout << "External scheduler wake-up us..." << endl;

	int iCenExecute = 0;
	ExecutionThread * ExecThread[ MAX_CEN ];
	bool StartFailure[ MAX_CEN ];
	SafeCounter switch_count;

	// change day check
	QDateTime now = QDateTime::currentDateTime();
	lout << "Change day reference date:" << now.date().toString ( "yyyyMMdd" )
	     << " - running date:" << change_day_time.date().toString ( "yyyyMMdd" )
	     << endl;
	if ( now.date() != change_day_time.date() ) {
		cleanTemporary();
		change_day_time = now;
	}

	// Start watchdog
	timeout.setProcess ( getpid() );
	timeout.setTimeOut ( WATCHDOG_TIMEOUT );

	checkDup();
	if ( initRunning )
		return;

	if ( !last_run_time_loaded ) {
		initRunning = true;
		initAll();
		loadPolicies();
		initRunning = false;
	}

	if ( globalTerminationRequest ) {
		return;
	}

	// configuration change check
	if ( checkConfigChanges() ) {
		globalTerminationRequest = true;
		dout << "*** SCHEDULING SHUTDOWN ***" << endl;
		ShutDown();
		return;
	}

	try {
		/*
		** A seconda delle centrali richieste
		** vengono lanciati i threads relativi.
		*/
		int iNumCenToDo = 0;

		//
		// Start all requests in distinct threads, checking for (eventually) failures/timeouts
		//
		QStringList node_black_list;
// 		Q3StrList * nbl_strlist;
		QString my_bl_section = QString ( "Node Blacklist" ) + QString::number ( serverID );
		node_black_list = cnfparser.parQTxtGetSection ( my_bl_section, config_name );
// 		if ( nbl_strlist ) {
// 			for ( int i = 0; i < nbl_strlist->count(); i++ ) {
// 				node_black_list << nbl_strlist->at ( i );
// 			}
// 			delete nbl_strlist;
// 		}
// 		else {
// 			lout1 << "*** Unable to retrieve Node Blacklist ***" << endl;
// 		}

		QTime timer;
		for ( int iCen = 0; iCen < MAX_CEN; iCen++ ) {
			StartFailure[ iCen ] = false;
			ExecThread[ iCen ] = 0;
			if ( !nodeConfigHandler.getSwitchingList().at ( iCen ) ->iActive )
				continue;	// skip inactive nodes

			QString Centrale ( nodeConfigHandler.getSwitchingList().at ( iCen ) ->acName );
			bool skip_node = false;
			for ( int i = 0; i < node_black_list.count(); i++ ) {
				if ( Centrale == node_black_list.at ( i ) ) {
					lout << "Node " << Centrale << " blacklisted. (Skipped)" << endl;
					skip_node = true;
					break;
				}
			}
			if ( skip_node ) continue; // skip blacklisted

			iNumCenToDo++;
			ExecThread[ iCen ] = new ExecutionThread ( Centrale, &ntm, alarm_resource, num_alarms, &switch_count, serverID );
			ExecThread[ iCen ] ->setIndex ( iCen );
			ExecThread[ iCen ] ->useProgressCounters ( &invokeStatus );
			ExecThread[ iCen ] ->start();
			timer.start();
// 			timeout.setiActive ( true );	// reset watchdog
			while ( ExecThread[ iCen ] ->getStatus() == ExecutionThread::Starting ) {
				sleep ( 1 );
				if ( timer.elapsed() >= MAX_START_TIMING ) {
					lout << "FAILURE: node (" << iCen << ") failed to start after " << MAX_START_TIMING << " msecs" << endl;
					--iNumCenToDo;
					StartFailure[ iCen ] = true;
					invokeStatus.percent[ iCen ] = -1;
					break;
				}
			}
			iCenExecute = iCen;
		}

		while ( ! ( iNumCenToDo == switch_count.value() ) ) {
			bool doBreak = true;
			for ( int failcnt = 0; failcnt < MAX_CEN; failcnt++ ) {
				if ( !StartFailure[ failcnt ] && ExecThread[ failcnt ] )
					if ( ExecThread[ failcnt ] ->getStatus() == ExecutionThread::Running )
						doBreak = false;
			}
			if ( doBreak )
				break;
			sleep ( 1 );
		}

		if ( globalTerminationRequest ) {
			return;
		}

		// Whats goes wrong ?
		for ( int fl = 0; fl < MAX_CEN; fl++ ) {
			if ( !ExecThread[ fl ] )
				continue;
			if ( StartFailure[ fl ] || ExecThread[ fl ] ->getStatus() == ExecutionThread::Failure ) {
				invokeStatus.percent[ fl ] = -1;
			}
		}

		invokeStatus.percent[ 0 ] = 999;	// completed

		// Thread deletion
		for ( int iCen = 0; iCen < MAX_CEN; iCen++ ) {
			if ( ExecThread[ iCen ] ) {
				lout << "deleting thread " << iCen << endl;
				delete ExecThread[ iCen ];
			} else {
				lout << "skipping deletion of thread " << iCen << endl;
			}
		}

		dout1 << "+++ Alarm evaluation started" << endl;

		generateAlarms();

		dout1 << "--- Alarm evaluation end" << endl;

		return;
	} catch ( const CORBA::Exception & e ) {
		lout << "Unexpected exception:" << endl
		     << "name  : " << e._name() << endl
#ifdef P_USE_TAO
		     << "info: " << e._info().c_str() << endl;
#else
		     << "reason: " << e._it_reason_text() << endl;
#endif
		return;
	}

	return;

}


bool AlarmMasterServerImpl::loadPolicies()
{
	API_DataBase_alarmmasterserver db ( db_nome_db, "ops$rdaexec", remote_password );
	static bool first_time = true;

	if ( db.getExitStatusDB() != DB_NOERROR )
		return false;		// no db conn.

	// Manage alarm policies

	for ( int i = 0; i < num_alarms; i++ ) {
		ALARM_POLICY alarmPolicyCheck;
		bool policy_changed = false;

		db.getAlarmPolicy ( &alarmPolicyCheck, alarm_resource[i].id );
		if ( alarmPolicyCheck.iTraffId != alarm_resource[i].id ) {

			if ( !alarm_resource[i].policy_loaded ) {
				lout << "No Alarm policy defined for "
				     << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] )
				     << "... using defaults!!"
				     << endl;

				alarm_resource[i].policy.iTraffId = alarm_resource[i].id;
				alarm_resource[i].policy.cNewPolicy = 'P';
				alarm_resource[i].policy.iInterval = 1;
				alarm_resource[i].policy.iPersistence = 1;
				alarm_resource[i].policy.coeff = 1.0;
			} else
				lout << "No Alarm policy defined for "
				     << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] )
				     << "... still using defaults!!"
				     << endl;
		} else if ( alarmPolicyCheck.cNewPolicy		!= alarm_resource[i].policy.cNewPolicy   ||
		                alarmPolicyCheck.iInterval	!= alarm_resource[i].policy.iInterval    ||
		                alarmPolicyCheck.iPersistence	!= alarm_resource[i].policy.iPersistence ||
		                alarmPolicyCheck.coeff		!= alarm_resource[i].policy.coeff
		          ) {	/* policy changed !! */

			alarm_resource[i].policy = alarmPolicyCheck;
// 			alarm_resource[i].policy_loaded = false;
			if ( !first_time )
				policy_changed = true;
		}

		if ( !alarm_resource[i].policy_loaded || policy_changed ) {

			lout1 << "ALARM POLICY for "
			      << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] ) << " :" << endl
			      << "-----------------------------------------" << endl
			      << "TraffId      = " << alarm_resource[i].policy.iTraffId << endl
			      << "cNewPolicy   = " << alarm_resource[i].policy.cNewPolicy << endl
			      << "iInterval    = " << alarm_resource[i].policy.iInterval << endl
			      << "iPersistence = " << alarm_resource[i].policy.iPersistence << endl
			      << "Coeff        = " << alarm_resource[i].policy.coeff << endl
			      << "-----------------------------------------" << endl;

			if ( policy_changed ) {
				lout1 << "RESETTING ALARM STATUS."
				      << endl;

				if ( QFile::exists ( status_file_name ) ) {
					dout << "** Removing status file..." << endl;
					QFile::remove ( status_file_name );
				}
				cleanTemporary();

				dout << "Server Restart..." << endl;
				globalTerminationRequest = true;
				waitGenerator();
				orb->shutdown ( 0 );

				return false;
			}
		}

		alarm_resource[i].policy_loaded = true;
	}

	first_time = false;
	return true;
}

int AlarmMasterServerImpl::checkWorkingDay ( QDateTime & cur_date )
{
	API_DataBase_alarmmasterserver db ( db_nome_db, "ops$rdaexec", remote_password );

	if ( db.getExitStatusDB() != DB_NOERROR )
		return 0;		// no db conn.

	int type_day = db.checkWorkingDay ( cur_date );

	lout << cur_date.toString ( "dd-MM-yyyy  hh:mm:ss.zzz" ) << " Type Day:" << type_day << endl;

	return type_day;
}

bool AlarmMasterServerImpl::generateAlarms()
{
	AlarmGeneratorAPIData genAPI;

	// quarter by day
	QDateTime now = QDateTime::currentDateTime();
	int type_day = checkWorkingDay ( now );

	if ( globalTerminationRequest ) {
		return false;
	}

	if ( !loadPolicies() )
		return false;

	API_DataBase_alarmmasterserver db ( db_nome_db, "ops$rdaexec", remote_password );
	if ( db.getExitStatusDB() != DB_NOERROR )
		return false;		// no db conn.

	// create a map from relation id to alarm vector
	QMap<int, int> id_to_vect;

	for ( int i = 0; i < num_alarms; i++ ) {
		if ( id_to_vect.find ( alarm_resource[i].id ) == id_to_vect.end() ) {  // avoid dups
			id_to_vect.insert ( alarm_resource[i].id, i );
			lout1 << i << " -> " << alarm_resource[i].id << endl;
		} else
			continue;
	}

	int quart_num = now.time().hour() * 4;
	div_t div_tDiv = div ( now.time().minute(), 15 );

	if ( ( now.time().minute() <= 14 ) && ( now.time().hour() == 0 ) )
		quart_num = 0;
	else
		quart_num += div_tDiv.quot;


	// now the scan for alarms...

	int current_relation = -1;
// 	AlarmDataMap * new_alarmMap = new AlarmDataMap;
	int count_on = 0, count_off = 0, count_pre = 0;

	for ( int r_cnt = 0; r_cnt < num_alarms; r_cnt++ ) {

		BTreeAlarm relation_alarm_map;
		RTNodeMap rt_map;
		current_relation = alarm_resource[r_cnt].id;
		int a_idx = id_to_vect.value ( current_relation );
		lout3 << "Processing rel " << current_relation << " a_idx " << a_idx << endl;

		// load alarm status

		loadRelationStatus ( current_relation, relation_alarm_map );
		dout3 << "Loaded status for " << current_relation
		      << " size: " << relation_alarm_map.size()
		      << endl;

		// merge or add rt data node

		rt_map.loadRTRelationMap ( current_relation, alarm_resource, num_alarms );
		RTNodeMapIterator rt_itr = rt_map.begin();
		BTreeAlarmIterator ai;

		int count_intree = 0, count_new = 0;

		while ( rt_itr != rt_map.end() ) {

			ai = relation_alarm_map.find ( rt_itr.key() );
			if ( ai == relation_alarm_map.end() ) {
				if ( rt_itr.key().size() == 0 ) {
					++rt_itr;
					continue;
				}

				AlarmStatusNode * n_asd = new AlarmStatusNode ( num_alarms );
				n_asd->nodo = new Nodo ( alarm_resource[a_idx].n_int, alarm_resource[a_idx].n_dbl );
				n_asd->nodo->setupCounters();
				* ( n_asd->nodo ) = *rt_itr.data();
				relation_alarm_map.insert ( rt_itr.key(), n_asd );
				++count_new;
			} else {
				AlarmStatusNode * m_asd = ai.data();
				if ( m_asd->nodo )
					m_asd->nodo->reset();
				else {
					m_asd->nodo = new Nodo ( alarm_resource[a_idx].n_int, alarm_resource[a_idx].n_dbl );
					m_asd->nodo->setupCounters();
				}
				* ( m_asd->nodo ) = *rt_itr.data();

				++count_intree;
			}

			++rt_itr;
		}

		rt_map.flushMap();
		// Clear rt data
		QString status_file_name ( RT_STATUS_BASENAME );
		status_file_name += QString::number ( current_relation ) + ".bin";
		QFile::remove ( status_file_name );
		lout3 << "Erased RT data: " << status_file_name << endl;


		dout2 << "Generating alarms for rel " << current_relation
		      << " " << ntm.alarmRelationInterface->decodeRel ( current_relation )
		      << ". " << relation_alarm_map.size() << " nodes to process..." << endl
		      << "(" << count_new << " new nodes, and " << count_intree << " in tree nodes, "
		      << count_new + count_intree << " total)"
		      << endl;

		dout2 << "Thresholds checking... " << thresholds.size() << " keys in list." << endl;
		int progress = 0, n_query = 0;
		int k_found = 0, k_notfound = 0;
		ai = relation_alarm_map.begin();
		int cycle_c = 0;
		while ( ai != relation_alarm_map.end() ) {
			// get thresholds for this relation
			QString DirectKey = ai.key();
// 			if ( cycle_c < 20 ) lout3 << cycle_c << ": " << DirectKey << endl;
			int rel_id = btree_alarm_key_rel_id ( DirectKey );
			DirectKey.remove ( 0, ALARM_RELATION_INKEY_DIGITS );
// 			if ( cycle_c < 20 ) lout3 << cycle_c << ": " << DirectKey << endl;
			++cycle_c;
			QDateTime yesterday = now.addDays ( -1 );
			QString hour_str = now.time().toString ( "hh" );
			THRESHOLD_VALUE thv;

			QMap<QString, int>::iterator tit = alarm_resource[a_idx].threshold_ids.begin();
			while ( tit != alarm_resource[a_idx].threshold_ids.end() ) {
				QString th_key = DirectKey + hour_str + QChar ( '_' ) + tit.key();

				if ( ! thresholds.contains ( th_key ) ) {	// already loaded ?
					thv = db.getThresholds ( type_day, rel_id, yesterday, DirectKey, tit.data() );
					if ( thv.value < 0.0 )	// if no errors and found
						++k_notfound;
					thresholds.insert ( th_key, thv );
// 					lout1 << "th_key=[" << th_key << "]" << " th_value=" << thv.value << endl;
					++n_query;
				} else
					++k_found;

				++tit;
			}

			++progress;
			if ( ( progress % 2000 ) == 0 ) {
				timeout.setiActive ( true );
				rlout2 << progress << " done\r";
			}

			++ai;
		}
		dout2 << "Thresholds loaded... " << n_query << " queries executed." << endl;
		lout2 << k_found << " rows found, " << k_notfound << " rows notfound." << endl;

		progress = 0;
		ai = relation_alarm_map.begin();
		while ( ai != relation_alarm_map.end() ) {

			QString DirectKey = ai.key();

			int rel_id = btree_alarm_key_rel_id ( DirectKey );
			int a_idx = id_to_vect.value ( rel_id );
			// strip relation di form key
			DirectKey.remove ( 0, ALARM_RELATION_INKEY_DIGITS );

			if ( a_idx >= num_alarms || rel_id != current_relation ) {
				lout << "Misconfigured node relation id " << rel_id << " @ position " << a_idx
				     << ". (size = " << id_to_vect.size() << "),"
				     << " should be id " << current_relation
				     << " DirectKey [" << DirectKey << "]. Skipped."
				     << endl;
				++ai;
				break;
// 				continue;
			}

			// Valorize derived counters
			ntm.counterInterface->closeCounterUpdate (
			        &ntm,
			        &fake_params,
			        alarm_resource[a_idx].counters,
			        ai.data()->nodo,
			        alarm_resource[a_idx].n_int,
			        alarm_resource[a_idx].n_dbl );

			// plugin calls
			int p = 0;
			while ( alarm_resource[a_idx].handler[p].id > 0 && p < MAX_ALARM_GENERATOR ) {

				genAPI.operation = alarmHanlerOp_Evaluate;
				genAPI.alarm_index = a_idx;
				genAPI.time = now;
				genAPI.quarter = quart_num;
				genAPI.status_node = ai.data();
				genAPI.status_data = &ai.data()->ad[p];
				genAPI.node_key = DirectKey;
				genAPI.alarm_policy = &alarm_resource[a_idx].policy;
				genAPI.cnt_kit = alarm_resource[a_idx].counters;
				genAPI.threshold_ids = &alarm_resource[a_idx].threshold_ids;
				genAPI.thresholds = &thresholds;

				if ( ai.data()->nodo == 0 ) {
					lout << "Key:" << ai.key() << " a_idx=" << a_idx << " p=" << p << " has nodo=0" << endl;
					++p;
					continue;
				}

				alarm_resource[a_idx].handler[p].plugin->pluginWorker ( ( void * ) &genAPI );

				if ( genAPI.success == false ) {
					lout << "Key:" << ai.key()
					     << " a_idx=" << a_idx
					     << " p=" << p
					     << " alarm evaluation failed." << endl;
					++p;
					continue;
				}

				AlarmStatusData * asd = &ai.data()->ad[p];

				if ( genAPI.in_alarm ) {
					// how we have to set an alarm ?
					if ( genAPI.alarm_policy-> cNewPolicy == 'P' ) {
						// set by persistence
						asd-> persistence++;
						asd-> interval = genAPI.quarter;
						// change status if actually off
						if ( asd-> alarm_status == STATUS_OFF ) {
							asd-> alarm_status = STATUS_PREALARMED;
						}

						// now check for alarm policy limit trespassing
						if ( asd->persistence > genAPI.alarm_policy-> iPersistence ) {
							// got one
							if ( asd->alarm_status != STATUS_ON ) {
								asd->status_changed = true;
								asd->alarm_status = STATUS_ON;
// 								lout3 << " ON: P changed: " << asd->status_changed << endl;
								asd->a_time = genAPI.time;
							}
							// keep persistence into its limit
							asd->persistence--;
						}
					} else {
						// set by interval
						// change status if actually off
						if ( asd-> alarm_status == STATUS_OFF ) {
							asd-> alarm_status = STATUS_PREALARMED;
						}

						// now check for alarm policy limit trespassing
						if ( asd->interval >= genAPI.alarm_policy-> iInterval ) {
							// got one
							if ( asd->alarm_status != STATUS_ON ) {
								asd->status_changed = true;
								asd->alarm_status = STATUS_ON;
// 								lout3 << " ON: M changed: " << asd->status_changed << endl;
								asd->a_time = genAPI.time;
							}
						}
					}
				} else {
					// NOT in alarm
					if ( genAPI.alarm_policy-> cNewPolicy == 'P' ) {
						// set by persistence
						if ( asd->alarm_status != STATUS_OFF ) {
							if ( asd-> alarm_status == STATUS_ON ) {
								asd-> status_changed = true;
								asd-> alarm_status = STATUS_OFF;
// 								lout3 << "OFF: P changed: " << asd->status_changed << endl;
								asd-> a_time = genAPI.time;
								asd-> interval = genAPI.quarter;
								asd-> persistence = 0;
							}
						}
					} else {
						if ( asd->alarm_status != STATUS_OFF ) {
							if ( asd-> alarm_status == STATUS_ON ) {
								asd-> status_changed = true;
								asd-> alarm_status = STATUS_OFF;
// 								lout3 << "OFF: M changed: " << asd->status_changed << endl;
								asd-> a_time = genAPI.time;
								asd-> interval = 0;
								asd-> persistence = 0;
							}
						}
					}
				}


				// on status on we assign an hash key to the link
				if ( asd-> alarm_status == STATUS_ON && asd-> link == 0 ) {
					QString h_key = ai.key() + QChar ( '~' ) + QString::number ( a_idx )
					                + QChar ( '~' ) + QString::number ( p );
					QString unique_h_key = ai.key() + QChar ( '~' ) + QString::number ( rand() )
					                       + QChar ( '~' ) + QString::number ( a_idx )
					                       + QChar ( '~' ) + QString::number ( p );
					uint h_link = qHash ( unique_h_key );

					asd-> link = h_link;
// 					link_hash.insert ( h_link, h_key );
				}

				// store status if changed
				if ( asd-> alarm_status == STATUS_ON && asd-> status_changed ) {
					setHistory ( ai.data(), asd, &genAPI, a_idx, p );
					addAlarmToDB ( db, ai.key(), alarm_resource[a_idx].id, a_idx, p, asd, ai.data() );
				} else if ( asd-> alarm_status == STATUS_OFF && asd-> status_changed ) {
					addAlarmToDB ( db, ai.key(), alarm_resource[a_idx].id, a_idx, p, asd, ai.data() );
					resetHistory ( ai.data(), asd, a_idx );
				}

				// on status off we can NOW reset the link
				if ( asd-> alarm_status == STATUS_OFF && asd-> link > 0 ) {
// 					link_hash.remove ( asd-> link );
					asd-> link = 0;
				}

				// copy actives on temporary and update status counters
				switch ( asd->alarm_status ) {
					case STATUS_ON: /*{
							AlarmStatusData * n_asd = new AlarmStatusData;
							*n_asd = *asd;
							new_alarmMap->insert ( ai.key(), n_asd );
							n_asd->handler_id = p;
						}*/
						++count_on;
						break;
					case STATUS_OFF:
						++count_off;
						break;
					case STATUS_PREALARMED:
						++count_pre;
						break;
				}

				++p;
			}

			++progress;
			if ( ( progress % 2000 ) == 0 ) {
				timeout.setiActive ( true );
				rlout2 << progress << " done\r";
			}

			// reset counters
			ai.data()->nodo->reset();

			++ai;
		}

		unloadRelationStatus ( current_relation, relation_alarm_map );
	}

	dout1 << "** " << count_on << " ON, " << count_off << " OFF, " << count_pre << " PRE" << endl;

	// wait for client threads to terminate and lock them all
// 	lout3 << "generate: semaphore available: " << semaphore.available() << "..." << endl;
// 	semaphore.acquire ( MAX_CONCURRENT_ALARM_REQUESTS );

// 	lout1 << "Locked clients. Performing alarm status update." << endl;

// 	AlarmDataMap * old_alarmMap = alarmMap;
// 	alarmMap = new_alarmMap;

// 	semaphore.release ( MAX_CONCURRENT_ALARM_REQUESTS );
// 	lout3 << "generate: semaphore available: " << semaphore.available() << endl;

	//delete old map
// 	foreach ( AlarmStatusData * ad, *old_alarmMap )
// 	delete ad;
// 	old_alarmMap->clear();
// 	delete old_alarmMap;

	if ( !writeStatus() )
		lout << "*** Impossible to synchronize alarm node on file..." << endl;
}

bool AlarmMasterServerImpl::addAlarmToDB ( API_DataBase_alarmmasterserver & db, const QString & Key,
                int rel_id, int rel_ptr, int al_ptr,
                AlarmStatusData * asd, AlarmStatusNode * asn )
{

	QString DirectKey = Key;

	lout3 << "-------------- addAlarmToDB():" << endl;
	lout3 << "* rel_id         = " << rel_id << endl;
	lout3 << "* DirectKey      = " << DirectKey << endl;
	lout3 << "* rel_ptr        = " << rel_ptr << endl;
	lout3 << "* al_ptr         = " << al_ptr << endl;

	db.preparaInsert ( INSERT_INTO_T_ALARM );
	db.strValues.clear();

// 	QString h_key = DirectKey + QChar ( '~' ) + QString::number ( rel_ptr )
// 	                + QChar ( '~' ) + QString::number ( al_ptr );
	uint h_link = asd->link;

	int al_g = ntm.alarmRelationInterface->get ( alarm_vector[rel_ptr] )->data.alarmHandlers[al_ptr];

	// strip relation di form key
	DirectKey.remove ( 0, ALARM_RELATION_INKEY_DIGITS );

	db.strValues.append ( QString::number ( h_link ) );				// LINK_ALLARME
	db.strValues.append ( QString::number ( alarm_resource[rel_ptr].id ) );		// ID_RELATION
	db.strValues.append ( QString::number ( al_g ) );				// ID_KPI

	int rel_off_ptr = 0;
	QString relation_desc;
	QString relation_code;

	for ( int i = 0; i < alarm_resource[rel_ptr].defRel->getDimension(); i++ ) {

		QString elemN_code = DirectKey.mid ( rel_off_ptr, alarm_resource[rel_ptr].defRel->getAlarmRelationComponent ( i )->length );
		QString elemN_desc = "";
		QString rel_comp_desc = alarm_resource[rel_ptr].defRel->getAlarmRelationComponent ( i )->desc;
		rel_off_ptr += alarm_resource[rel_ptr].defRel->getAlarmRelationComponent ( i )->length;
		if ( alarm_resource[rel_ptr].help_present[i] ) {
			QString * htxt = helpDict[rel_comp_desc]->items[elemN_code];
			if ( htxt ) {
				elemN_desc = ( *htxt );
			}
		} else {
			QString itxt = checkInternalHelp ( rel_comp_desc, elemN_code );
			if ( itxt.length() > 0 )
				elemN_desc = itxt;
		}

		if ( i > 0 ) {
			relation_desc += "::";
			relation_code += "::";
		}

		relation_desc += elemN_desc.simplifyWhiteSpace();
		relation_code += elemN_code.simplifyWhiteSpace();
	}

	db.strValues.append ( relation_code );							// RELATION_KEY
	db.strValues.append ( relation_desc );							// RELATION_DESC
	db.strValues.append ( asd->a_time.toString ( "yyyyMMdd-hh:mm:ss" ) );			// DATA_ALLARME
	db.strValues.append ( asd->a_time.toString ( "hh" ) );					// DAYLY_HOUR
	db.strValues.append ( db.fmtNumber ( alarm_resource[rel_ptr].policy.coeff ) );		// COEFF
	db.strValues.append ( QChar ( alarm_resource[rel_ptr].policy.cNewPolicy ) );		// POL_ID
	db.strValues.append ( QString::number ( alarm_resource[rel_ptr].policy.iInterval ) );	// INTERVALLO
	db.strValues.append ( QString::number ( alarm_resource[rel_ptr].policy.iPersistence ) );// PERSISTENZA
	db.strValues.append ( QString::number ( asd->alarm_status ) );				// STATO

	db.creaInsert ( 1 );
	db.writeData ( INSERT_INTO_T_ALARM );

	//--------------------------------------------------------

	db.preparaInsert ( INSERT_INTO_T_ALARM_KPI );
	db.strValues.clear();

	AlarmGeneratorAPIData genAPI;
	QDateTime now = QDateTime::currentDateTime();
	int quart_num = now.time().hour() * 4;
	div_t div_tDiv = div ( now.time().minute(), 15 );

	if ( ( now.time().minute() <= 14 ) && ( now.time().hour() == 0 ) )
		quart_num = 0;
	else
		quart_num += div_tDiv.quot;

	if ( asd->hist_ala_pair_list ) {
		for ( int i = 0; i < asd->hist_ala_pair_list->size(); i++ ) {
			db.strValues.append ( QString::number ( h_link ) );				// LINK_ALLARME
			db.strValues.append ( asd->a_time.toString ( "yyyyMMdd-hh:mm:ss" ) );		// DATA_ALLARME
			db.strValues.append ( QString::number ( al_g ) );				// ID_KPI
			// DESCRIZIONE
			db.strValues.append ( asd->hist_ala_pair_list->at ( i ).first );
			// ALARM_VALUE
			db.strValues.append ( asd->hist_ala_pair_list->at ( i ).second );
		}

		db.creaInsert ( asd->hist_ala_pair_list->size() );
	} else {
		lout3 << ">>> asn->hist_ala_pair_list is null..." << endl;

		db.strValues.append ( QString::number ( h_link ) );				// LINK_ALLARME
		db.strValues.append ( asd->a_time.toString ( "yyyyMMdd-hh:mm:ss" ) );		// DATA_ALLARME
		db.strValues.append ( QString::number ( al_g ) );				// ID_KPI
		// DESCRIZIONE
		db.strValues.append ( "N/A" );
		// ALARM_VALUE
		db.strValues.append ( QString::number ( 0 ) );

		db.creaInsert ( 1 );
	}

	db.writeData ( INSERT_INTO_T_ALARM_KPI );

	//--------------------------------------------------------

	db.preparaInsert ( INSERT_INTO_T_ALARM_KPI_SOG );
	db.strValues.clear();

	genAPI.operation = alarmHanlerOp_ThresholdPairs;
	genAPI.alarm_index = rel_ptr;
	genAPI.time = now;
	genAPI.quarter = 0;
	genAPI.status_node = asn;
	genAPI.status_data = asd;
	genAPI.node_key = DirectKey;
	genAPI.alarm_policy = &alarm_resource[rel_ptr].policy;
	genAPI.cnt_kit = alarm_resource[rel_ptr].counters;
	genAPI.threshold_ids = &alarm_resource[rel_ptr].threshold_ids;
	genAPI.thresholds = &thresholds;
	genAPI.infoPairList = 0;

	alarm_resource[rel_ptr].handler[al_ptr].plugin->pluginWorker ( ( void * ) &genAPI );
	int num_pairs = 0;

	if ( genAPI.infoPairList ) {

		num_pairs = genAPI.infoPairList->size();

		for ( int i = 0; i < genAPI.infoPairList->size(); i++ ) {
			db.strValues.append ( QString::number ( h_link ) );				// LINK_ALLARME
			db.strValues.append ( asd->a_time.toString ( "yyyyMMdd-hh:mm:ss" ) );		// DATA_ALLARME
			db.strValues.append ( QString::number ( al_g ) );				// ID_KPI
			// DESCRIZIONE
			db.strValues.append ( genAPI.infoPairList->at ( i ).first );
			// SOG_VALUE
			db.strValues.append ( genAPI.infoPairList->at ( i ).second );
		}

		// remove all from list and list itself
		while ( ! genAPI.infoPairList->isEmpty() )
			genAPI.infoPairList->takeFirst();

		delete genAPI.infoPairList;
	}

	if ( num_pairs > 0 ) {
		db.creaInsert ( num_pairs );
		db.writeData ( INSERT_INTO_T_ALARM_KPI_SOG );
	}

	//--------------------------------------------------------

	db.preparaInsert ( INSERT_INTO_T_ALARM_COUNTER );
	db.strValues.clear();

	if ( asd->hist_nodo ) {

		TipoListaParametri* cntRow = asd->hist_nodo->GetLista();

		double d_val, d_val_t, valueRef;
		unsigned long i_val;
		num_pairs = 0;

// 		lout3 << "cntr kit: " << alarm_resource[rel_ptr].counters->data.tag << endl;

		for ( int i = 0; i < CNT_NUM; i++ ) {
			// unconfigured
			if ( alarm_resource[rel_ptr].counters->data.counterKit[i].tag[0] == '\0' )
				continue;
			// unmanaged
			if ( alarm_resource[rel_ptr].counters->data.counterKit[i].counterType == CounterInterface::Unmanaged )
				continue;
			// OK
			bool avg_type = ( alarm_resource[rel_ptr].counters->data.counterKit[i].counterType == CounterInterface::SimpleAverageCounter ||
			                  alarm_resource[rel_ptr].counters->data.counterKit[i].counterType == CounterInterface::SumAverageCounter );

			int idx = alarm_resource[rel_ptr].counters->data.counterKit[i].counterIndex;

			++num_pairs;
			db.strValues.append ( QString::number ( h_link ) );				// LINK_ALLARME
			db.strValues.append ( asd->a_time.toString ( "yyyyMMdd-hh:mm:ss" ) );		// DATA_ALLARME
			db.strValues.append ( QString::number ( al_g ) );				// ID_KPI


			switch ( ntm.counterInterface->getCounterType ( idx ) ) {
				case INT_TYPE:
					i_val = ntm.counterInterface->getIntCounterVal ( cntRow, idx );
					if ( alarm_resource[rel_ptr].counters->data.counterKit[i].percentOf > 0 && !avg_type ) {	// percent or absolute ?
						valueRef = ntm.counterInterface->getDoubleCounterVal ( cntRow, alarm_resource[rel_ptr].counters->data.counterKit[i].percentOf );
						if ( valueRef != 0 )
							d_val_t = ( ( double ) i_val * 100.0 ) / valueRef;
						else
							d_val_t = 0.0;

						db.strValues.append ( alarm_resource[rel_ptr].counters->data.counterKit[i].description );
						db.strValues.append ( QString::number ( d_val_t ) );
					} else {
						db.strValues.append ( alarm_resource[rel_ptr].counters->data.counterKit[i].description );
						db.strValues.append ( QString::number ( i_val ) );

					}
					break;
				case DOUBLE_TYPE:
					d_val = ntm.counterInterface->getDoubleCounterVal ( cntRow, idx );
					if ( alarm_resource[rel_ptr].counters->data.counterKit[i].percentOf > 0  && !avg_type ) {	// percent or absolute ?
						valueRef = ntm.counterInterface->getDoubleCounterVal ( cntRow, alarm_resource[rel_ptr].counters->data.counterKit[i].percentOf );
						if ( valueRef != 0 )
							d_val_t = ( d_val * 100.0 ) / valueRef;
						else
							d_val_t = 0.0;

						db.strValues.append ( alarm_resource[rel_ptr].counters->data.counterKit[i].description );
						db.strValues.append ( QString::number ( d_val_t ) );
					} else {
						db.strValues.append ( alarm_resource[rel_ptr].counters->data.counterKit[i].description );
						db.strValues.append ( QString::number ( d_val ) );

					}
					break;
			}
		}

		db.creaInsert ( num_pairs );
		db.writeData ( INSERT_INTO_T_ALARM_COUNTER );
	}


	return true;
}

void AlarmMasterServerImpl::setHistory ( AlarmStatusNode * asn, AlarmStatusData * asd, AlarmGeneratorAPIData * genAPI, int a_idx, int p )
{
// 	lout3 << "+++ setHistory()" << endl;

	asd->hist_nodo = new Nodo ( alarm_resource[a_idx].n_int, alarm_resource[a_idx].n_dbl );
	asd->hist_nodo->setupCounters();

	*asd->hist_nodo += *asn->nodo;

	asd->hist_ala_pair_list = new QList< QPair<QString, QString> >;
	// genAPI is already initialized by the caller
	genAPI->operation = alarmHanlerOp_AlarmPairs;
	genAPI->infoPairList = 0;

	alarm_resource[a_idx].handler[p].plugin->pluginWorker ( ( void * ) genAPI );
	if ( genAPI->infoPairList ) {
		*asd->hist_ala_pair_list = *genAPI->infoPairList;

		// remove all from list and list itself
		while ( ! genAPI->infoPairList->isEmpty() )
			genAPI->infoPairList->takeFirst();

		delete genAPI->infoPairList;
	}

}

void AlarmMasterServerImpl::resetHistory ( AlarmStatusNode * asn, AlarmStatusData * asd, int a_idx )
{
// 	lout3 << "--- resetHistory()" << endl;

// 	if (checkAllOFF(asn, a_idx)) {
// 		return;
// 		lout3 << "--- resetHistory(): all OFF" << endl;

	if ( asd->hist_nodo )
		delete asd->hist_nodo;
	asd->hist_nodo = 0;
// 	}

	if ( asd->hist_ala_pair_list ) {
		// remove all from list and list itself
		while ( ! asd->hist_ala_pair_list->isEmpty() )
			asd->hist_ala_pair_list->takeFirst();

		delete asd->hist_ala_pair_list;
	}
	asd->hist_ala_pair_list = 0;
}

// bool AlarmMasterServerImpl::checkAllOFF(AlarmStatusNode * asn, int a_idx)
// {
// 	int p = 0;
// 	while ( alarm_resource[a_idx].handler[p].id > 0 && p < MAX_ALARM_GENERATOR ) {
// 		if ( asn->ad[p].alarm_status != STATUS_OFF )
// 			return false;		// active alarms still present
// 		++p;
// 	}
//
// 	return true; // all OFF
// }


void AlarmMasterServerImpl::debugPluginError ( const QString & caller, const QString & requested, ams_MsgType type, PluginImpl * pli )
{
	switch ( type ) {
		case WrongType:
			lout << caller.latin1() << " : " << requested.latin1() << " : loaded wrong plugin type." << endl;
			break;
		default:
			lout << caller.latin1() << " : Generic error." << endl;
	}

	lout << "Plugin Info: " << endl;
	if ( pli ) {
		lout << pli->getPluginInfo().name.latin1() << " :: Type " << pli->getPluginInfo().globalTypeID << endl;
		lout << "++ Description    : " << pli->getPluginInfo().description.latin1() << endl;
		lout << "++ Release        : " << pli->getPluginInfo().majorVersionNumber << "." << pli->getPluginInfo().minorVersionNumber << endl;
		lout << "++ Release Date   : " << pli->getPluginInfo().releaseDate.toString().latin1() << endl;
		lout << "++ Author/Vendor  : " << pli->getPluginInfo().author.latin1() << " (" << pli->getPluginInfo().vendorName.latin1() << ")" << endl;
	} else {
		lout << "Unloaded plugin." << endl;
	}
}


/** Esegue l'attach dei plugins noti */

bool AlarmMasterServerImpl::initAlarmVector()
{
	dout1 << "Initalizing " << num_alarms << " alarm resources:" << endl;

	for ( int i = 0; i < num_alarms; i++ ) {
		alarm_resource[i].id = alarm_vector[i];

		// instance counters plugin
		lout1 << "loading counters kit for "
		      << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] )
		      << endl;
		int cntKitId = ntm.alarmRelationInterface->get ( alarm_vector[i] )->data.countersKitId;
		alarm_resource[i].counters = ntm.counterInterface->get ( cntKitId );
		if ( alarm_resource[i].counters == 0 ) {
			lout << "Builder::initAlarmVector : cannot get counters kit "
			     << cntKitId << " needed from relation "
			     << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] ) << endl;
			return true;
		}
		lout1 << "loading alarm generator plugins:" << endl;
		int p = 0;
		while ( ntm.alarmRelationInterface->get ( alarm_vector[i] )->data.alarmHandlers[p] >= 0 && p < MAX_ALARM_GENERATOR ) {
			int al_g = ntm.alarmRelationInterface->get ( alarm_vector[i] )->data.alarmHandlers[p];
			int pl_id = ntm.alarmGeneratorInterface->get ( al_g )->data.idPlugin;
			PluginImpl * plug = getGenericPluginInstance ( pl_id, Ntm_AlarmGenerator );

			if ( plug == 0 ) {
				lout << "AlarmMasterServerImpl::initAlarmVector : cannot get plugin "
				     << pl_id << " needed from relation "
				     << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] ) << endl;
				return true;
			}

			alarm_resource[i].handler[p].id = pl_id;
			alarm_resource[i].handler[p].plugin = plug;

			AlarmGeneratorAPIData api;
			api.operation = alarmHanlerOp_Startup;
			alarm_resource[i].handler[p].plugin->startup ( ( void * ) &api );

			// fills thresholds list
			int t = 0;
			while ( ntm.alarmGeneratorInterface->get ( al_g )->data.idThresholdGenerator[t] >= 0 && t < MAX_THRESHOLD_GENERATOR ) {
				int th_id = ntm.alarmGeneratorInterface->get ( al_g )->data.idThresholdGenerator[t];
				if ( ! alarm_resource[i].threshold_ids.contains ( ntm.thresholdGeneratorInterface->get ( th_id )->data.description ) )
					alarm_resource[i].threshold_ids.insert (
					        ntm.thresholdGeneratorInterface->get ( th_id )->data.description,
					        th_id
					);
				lout1 << "+ threshold: "
				      << ntm.thresholdGeneratorInterface->get ( th_id )->data.description
				      << " id: "
				      << alarm_resource[i].threshold_ids[ntm.thresholdGeneratorInterface->get ( th_id )->data.description]
				      << endl;
				++t;
			}

			++p;
		}

		lout1 << "initializing relation components for "
		      << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] )
		      << endl;
		alarm_resource[i].defRel = new DefineAlarmRelation();
		alarm_resource[i].defRel->setElementInRelation ( &ntm, alarm_resource[i].id );
		if ( !initRelation ( alarm_resource[i].defRel ) ) {	// hook plugins or scripts
			lout << "Builder::initAlarmVector : Cannot initialize relation handlers." << endl;
			return false;
		}

		ntm.counterInterface->getConfiguredCounters ( alarm_resource[i].counters,
		                alarm_resource[i].n_int,
		                alarm_resource[i].n_dbl );
		lout1 << "relation "
		      << ntm.alarmRelationInterface->decodeRel ( alarm_vector[i] )
		      << " has " << alarm_resource[i].n_int << " integer counters and "
		      << alarm_resource[i].n_dbl << " double counters."
		      << endl;

		for ( int j = 0; j < alarm_resource[i].defRel->getDimension(); j++ ) {
			DescHelperAPIData * hstr = helpDict[alarm_resource[i].defRel->getAlarmRelationComponent ( j )->desc];
			if ( hstr ) {
				hstr->helpTag = alarm_resource[i].defRel->getAlarmRelationComponent ( j )->desc;
				// 				lout << "setup help for [" << defineRelation.getRelationComponent(i)->desc << "]" << endl;
				hstr->nodeIndex = -2;
				hstr->handler->pluginWorker ( hstr );
				alarm_resource[i].help_present[j] = hstr->loaded;
				lout1 << "Helper for " << alarm_resource[i].defRel->getAlarmRelationComponent ( j )->desc << " loaded." << endl;
			} else
				alarm_resource[i].help_present[j] = false;
		}
	}

	return false;
}


bool AlarmMasterServerImpl::initRelation ( DefineAlarmRelation * dr )
{
	if ( dr == 0 ) {
		return false;
	}
	for ( int r = 0; r < dr->getDimension(); r++ ) {
		AlarmRelationComponent * c = dr->getAlarmRelationComponent ( r );
		if ( c == 0 ) {
			lout << "AlarmMasterServer::initRelation : Malformed RelationComponents !!" << endl;
			return false;
		}
		TrafficElement * te = ntm.trafficElementInterface->get ( c->id );
		if ( te == 0 ) {
			lout << "AlarmMasterServer::initRelation : Unable to initialize relation component: " << c->id << endl;
			return false;
		}
// 		if ( te->data.idPlugin > 0 ) {
// 			c->pluginHandler = getHandlerPluginInstance ( te );
//
// 			if ( c->pluginHandler ) {
// 				TEHandlerWorkerAPIData pld;
// 				if ( !c->pluginHandler->startup ( ( void * ) &pld ) ) {
// 					lout << "AlarmMasterServer::initOper : Cannot startup plugin #" <<
// 					te->data.idPlugin << " needed from Element " << te->data.shortDescription << endl;
// 					return 0;
// 				}
// 				if ( !pld.useFor[teOp_KeyBuilding] ) {
// 					delete c->pluginHandler;		// not useful for us
// 					c->pluginHandler = 0;
// 				}
// 				lout2 << "Loaded relation component plugin: " << te->data.shortDescription << endl;
// 				sleep (10);
// 				continue;
// 			}
// 			else
// 				return false;
// 		}
// 		else if ( te->data.scripts[Script::saKeyBuilding] > 0 ) {
// 			Script * inis = ntm.scriptInterface->get ( te->data.scripts[Script::saKeyBuilding] );
// 			if ( !inis ) {
// 				lout << "AlarmMasterServer::initRelation : Cannot get script #" <<
// 				te->data.scripts[Script::saKeyBuilding] << " needed from Element #" << c->id << endl;
// 				return false;
// 			}
// 			c->scriptHandler = getNtmBasicInstance ( inis );
// 			lout2 << "Loaded relation component script: " << te->data.shortDescription << endl;
// 			sleep (10);
// 			if ( c->scriptHandler == 0 )
// 				return false;
// 		}
	}

	return true;
}

NtmBasic * AlarmMasterServerImpl::getNtmBasicInstance ( Script * s )
{
	NtmBasic * nb = new NtmBasic;
	nb->setReportMode ( NtmBasic::nbToLogFile );

	if ( nb->getStatus() != NtmBasic::nbSuccess ) {
		delete nb;
		return 0;	// error
	}
	// set vars & text
	if ( !nb->setProgramScript ( *s ) ) {
		delete nb;
		return 0;
	}

	return nb;
}


bool AlarmMasterServerImpl::readConfiguration ( API_DataBase_alarmmasterserver *db )
{
	// *** gets standard repository
	QString dirPath = standardRepository();

	QString config_name = cnfparser.locateFile ( "alarms.ini", StoreNode::myExeName() );
	//QString cnFilename = cnfparser.parTxtGetValue ( "ActiveConfiguration", "Alarm_Globals", config_name );
	QString cnFilename = db->loadConfiguration ( serverID );
	lout << "Active Configuration = " << cnFilename << endl;
	lout << "Repository Path      = " << dirPath << endl;

	QString filePath = dirPath + "/" + cnFilename;

	// *** load all from file
	if ( !ntm.readConfiguration ( filePath ) )
		return false;
	// *** read in users too
// 	readNtmUsersPrivate ( *ntm.userInterface );

	/*	// load & merge indexes configuration
		StsConfigParser cnfparser;*/
	return true;
}

PluginImpl * AlarmMasterServerImpl::getHandlerPluginInstance ( TrafficElement * te )
{
// 	lout1 << "getHandlerPluginInstance: " << te->data.shortDescription << endl;
	PluginImpl * plugin = getGenericPluginInstance ( te->data.idPlugin, Ntm_ElementHandler );

	if ( plugin == 0 ) {
		lout << "AlarmMasterServer::getHandlerPluginInstance : Cannot get plugin needed from Element #"
		     << te->data.idElement << " " << te->data.shortDescription << endl;
		return 0;
	}
	return plugin;
}

PluginImpl * AlarmMasterServerImpl::getGenericPluginInstance ( int plugin_id, StsPluginType plugin_type, char * alt_path )
{
	PluginImpl * plugin;

// 	lout1 << "getGenericPluginInstance: " << plugin_id << endl;
	PluginRegistry * p = ntm.pluginRegistryInterface->get ( plugin_id );
	if ( p == 0 ) {
		lout << "AlarmMasterServer::getGenericPluginInstance : Cannot get plugin #" <<
		     plugin_id << endl;
		return 0;
	}
	// first check if there is an instance already setup ?
	plugin = pluginImplFactory[plugin_id];
	if ( plugin ) {
		return plugin;
	}
	// now check if a pluginbase already exists for this te

	PluginBase * plBase = pluginFactory[plugin_id];
	if ( plBase == 0 ) {		// new one
		plBase = new PluginBase ( pluginHome );
		char * pl_path = ( alt_path ) ? alt_path : ntm.cdrInterface->globalOpt()->data.glbDefaultPluginPath;
		if ( plBase->registerPlugin ( p->data.pluginName, pl_path, true ) ) {
			lout << "AlarmMasterServer::getGenericPluginInstance : Failed to register plugin #" <<
			     plugin_id << endl;
			return 0;			// plugin required but failed to attach
		}

		lout1 << "* Loaded plugin: " << p->data.pluginName << endl;

		pluginFactory.insert ( plugin_id, plBase );
	}
	plugin = plBase->getInstance();
	if ( !plugin->verifyType ( plugin_type ) ) {
		debugPluginError ( "AlarmMasterServer::getGenericPluginInstance", "Alarm", WrongType, plugin );
		delete plugin;
		return 0;
	}
	plugin->pluginSetupConfig ( ( void * ) &ntm, ( void * ) &fake_params );
	pluginImplFactory.insert ( plugin_id, plugin );
	return plugin;
}


QString AlarmMasterServerImpl::standardRepository()
{
	QString config_fname = cnfparser.locateFile ( NTMINIFILE, StoreNode::myExeName() );
	if ( config_fname.isEmpty() )
		return QString ( "" );

	QString cnfKey = CONFIGURATION_PATH_KEY;
	int slashPos = cnfKey.find ( '/' );

	char * _dir = cnfparser.parTxtGetValue ( cnfKey.mid ( slashPos + 1 ).latin1(), cnfKey.left ( slashPos ).latin1(), config_fname.latin1() );
	if ( _dir == 0 ) {
		lout << "REPOSITORY PATH not found in configuration: " << NTMINIFILE << endl;
		return QString ( "" );
	}
	QString dirPath = _dir;
	delete _dir;

	return dirPath;
}

QString AlarmMasterServerImpl::corbaBaseDir()
{
	QString config_fname = cnfparser.locateFile ( NTMINIFILE, StoreNode::myExeName() );
	if ( config_fname.isEmpty() )
		return QString ( "" );

	char * _dir = cnfparser.parTxtGetValue ( "CorbaHome", "NTM_General", config_fname.latin1() );
	if ( _dir == 0 ) {
		lout << "CorbaHome not found in configuration: " << NTMINIFILE << endl;
		return QString ( "" );
	}
	QString dirPath = _dir;
	delete _dir;

	return dirPath;
}


CORBA::Boolean AlarmMasterServerImpl::getNodes ( CentralImageSeq_out nodes ) throw ( CORBA::SystemException )
{
	timeout.setiActive ( true );	// reset watchdog
	if ( timeout.running() == false )
		timeout.start();	// watchdog active

	CentralImageSeq * LocalCentrali = new CentralImageSeq;
	DATA_CENTRALI_IMAGE * LocalDataCen;

	LocalCentrali->length ( nodeConfigHandler.getSwitchingList().count() );
	for ( int iCount = 0; iCount < nodeConfigHandler.getSwitchingList().count(); iCount++ ) {
		LocalDataCen = new DATA_CENTRALI_IMAGE;

		if ( !nodeConfigHandler.getSwitchingList().at ( iCount )->iActive ) {
			LocalDataCen->IdCentrale = -1;
			strcpy ( LocalDataCen->Descrizione, "" );
			continue;
		} else {
			LocalDataCen->IdCentrale = iCount;
			memset ( LocalDataCen->Descrizione, 0, sizeof ( LocalDataCen->Descrizione ) );
			strcpy ( LocalDataCen->Descrizione, nodeConfigHandler.getSwitchingList().at ( iCount )->acName );
			lout << "Centrale: " << nodeConfigHandler.getSwitchingList().at ( iCount )->acName << endl;
			lout << "Desc: " << LocalDataCen->Descrizione << endl;
		}

		( *LocalCentrali ) [iCount] = ( *LocalDataCen );
	}

	nodes = LocalCentrali;

	return ( CORBA::Boolean ) true;
}


void AlarmMasterServerImpl::registerHelperPlugins()
{
	QString PlugIn_dir = ntm.cdrInterface->globalOpt() ->data.glbDefaultPluginPath;

	if ( PlugIn_dir.mid ( 0, 1 ) == "~" ) {				// home relative path
		char * homedir = ::getenv ( "HOME" );
		if ( homedir ) {
			PlugIn_dir.replace ( 0, 1, homedir );
		} else {
			QString qhomedir = corbaBaseDir();
			if ( qhomedir.length() > 0 )
				PlugIn_dir.replace ( 0, 1, qhomedir );
		}
	}


	QDir d = QDir::root();
	if ( !d.cd ( PlugIn_dir ) ) {
		lout << "NTMMasterServerImpl::registerHelperPlugins : cannot chdir to " << PlugIn_dir << endl;
		return ;
	} else {
		d.setFilter ( QDir::Files );
		d.setNameFilter ( "helper_*.so" );
		d.setSorting ( QDir::Size | QDir::Reversed );

		/*const*/
		QFileInfoList list = d.entryInfoList();
		QFileInfoListIterator it;
		QFileInfo fi;

		for ( it = list.begin(); it != list.end(); ++it ) {
			fi = ( *it );
			PluginBase * pluginBase = new PluginBase;
			if ( pluginBase->registerPlugin ( fi.fileName(), PlugIn_dir, true ) ) {
				delete pluginBase;
				continue;
			}

			PluginImpl * pluginImpl = pluginBase->getInstance();
			if ( !pluginImpl->verifyType ( Ntm_DescriptionHelper ) ) {
				//debugPluginError("NTMMasterServerImpl::registerHelperPlugins", "Ntm_DescriptionHelper", WrongType, pluginImpl);
				delete pluginImpl;
				delete pluginBase;
				continue;
			}

			DescHelperAPIData * pli = new DescHelperAPIData;

// 			lout << "activating plugin " << fi->fileName();
			pluginImpl->pluginSetupConfig ( &ntm, &fake_params );
			pluginImpl->startup ( pli );
			pli->handler = pluginImpl;
// 			lout << " - OK" << endl;

			for ( QStringList::Iterator it = pli->elementMatch.begin(); it != pli->elementMatch.end(); ++it ) {
				helpDict.insert ( ( *it ), pli );
			}
		}
	}
}

QString AlarmMasterServerImpl::checkInternalHelp ( const QString & tag, const QString & id )
{
	TrafficElement * te = ntm.trafficElementInterface->getByTag ( tag );
	if ( te == 0 ) {
		return QString ( "" );
	}
	if ( te->data.LengthInRelation == 0 || ( te->data.guiObject != TrafficElement::ValueList && te->data.guiObject != TrafficElement::OptionList ) ) {
		return QString ( "" );
	}

	QString key;
	QString fmt = QString ( "%0" ) + QString::number ( te->data.LengthInRelation ) + QString ( "d" );

	for ( int i = 0; i < MAX_OPTIONS; i++ ) {
		if ( qstrlen ( te->data.valueListLabel[i] ) > 0 ) {
			key.sprintf ( fmt, QString::number ( te->data.valueList[i] ).toInt() );
			if ( key == id ) {
				return QString ( te->data.valueListLabel[i] );
			}
		} else
			break;
	}

	return QString ( "" );
}

CORBA::Long AlarmMasterServerImpl::checkPid ( ) throw ( CORBA::SystemException )
{
	XmlIO pid_file;

	if ( !pid_file.load ( m_pid_file() ) )
		lout << "** Error reading pid file." << endl;
	int cur_pid = pid_file.getNodeSimple ( "PID" ).toInt();

	return ( CORBA::Long ) cur_pid;
}

// kate: indent-mode cstyle; indent-width 8; replace-tabs off; tab-width 8; 


