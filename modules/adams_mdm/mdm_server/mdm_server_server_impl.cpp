/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */


#include "mdm_server_server_impl.h"
#include "reportformatter.h"
#include <applogger.h>
#include <apidb_config.h>

#include <sys/types.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <Qt/qtextstream.h>


#define USE_INIT
#define	MAX_START_TIMING	(60*1000)	// half a minute to start a dataserver !
#define WATCHDOG_TIMEOUT	(60*4)		// four minutes to activate watchdog
#define LONG_WATCHDOG_TIMEOUT	(60*20)		// long watchdog delay

static QFile * aud_log_file = 0;
static QFile * sensitive_aud_log_file = 0;
static PluginFactory pluginFactory;			// a proxy factory for traffic elements plugins
static QMap<QString, PluginImpl *>	counters_plugin_factory;

mdm_server_server_impl::mdm_server_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy ),
	  confLoaded ( false ),
	  queryCompleted(false)
{
	cnfparser.locateFile();
	cnfparser.addMarkerTagPath ( QString ( "MDM_Server." ) + AdamsServer::getNode() );

	// auditing configuration
	QString fname = cnfparser.parQTxtGetValue ( "AuditRequestFilePath", "MDM", ConfigParser::PathValue );
	if ( fname.isEmpty() ) {
		lout << "Undefined audit file path. Auditing disabled." << endl;
	}
	else {
		dir_check ( fname, true );
		aud_log_file = new QFile ( fname );
		if ( ! aud_log_file->open ( QIODevice::Append ) ) {
			lout << "Cannot open audit file path. Auditing disabled." << endl;
			delete aud_log_file;
			aud_log_file = 0;
		}
	}

	fname = cnfparser.parQTxtGetValue ( "SensitiveDataAuditRequestFilePath", "MDM", ConfigParser::PathValue );
	if ( fname.isEmpty() ) {
		lout << "Undefined sensistive data audit file path. Auditing disabled." << endl;
	}
	else {
		dir_check ( fname, true );
		sensitive_aud_log_file = new QFile ( fname );
		if ( ! sensitive_aud_log_file->open ( QIODevice::Append ) ) {
			lout << "Cannot open sensitive data audit file path. Auditing disabled." << endl;
			delete sensitive_aud_log_file;
			sensitive_aud_log_file = 0;
		}
	}

	jobServer = 0;

	// if activate is true then create an object id and activate the servant in the correct POA
	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing() ->activate_object_with_id ( oid.in(), this );
	}

	// Initialize watchdog
	timeout.setProcess ( ::getpid() );
	timeout.setTimeOut ( WATCHDOG_TIMEOUT );
	timeout.setORB ( AdamsServer::orb() );
	timeout.setThreadCheck ( dynamic_cast<QThread *> ( this ) );
	timeout.start();		// watchdog active
}


/**
 */

mdm_server_server_impl::~mdm_server_server_impl()
{
}

// IDL implementation interfaces

void mdm_server_server_impl::shutDown() throw ( CORBA::SystemException )
{
	lout2 << "Shut down request received." << endl;

	int nsec = 0;
	if ( isRunning() ) {
		while ( !isFinished() ) {
			QThread::sleep ( 1 );
			++nsec;
		}
	}

	lout2 << "pending shutdown for " << nsec << " seconds." << endl;
	lout << "-> shutting down now." << endl;
	if ( !CORBA::is_nil ( AdamsServer::orb() ) ) {
		try {
			AdamsServer::orb()->shutdown ( 0 );
		}
		catch ( const CORBA::Exception & ) {
			lout << "*** Abnormal orb shutdown." << endl;
			::exit ( 1 );
		}
	}
}

void mdm_server_server_impl::registerHelperPlugins()
{
	QString PlugIn_dir = acc.drInterface->globalOpt() ->data.glbDefaultPluginPath;

	if ( PlugIn_dir.mid ( 0, 1 ) == "~" ) {				// home relative path
		QString homedir = ::getenv ( "HOME" );
		if ( !homedir.isEmpty() ) {
			PlugIn_dir.replace ( 0, 1, homedir );
		}
	}
	else if ( PlugIn_dir.mid ( 0, 1 ) != "/" ) {			// not an absolute path
		PlugIn_dir = AdamsServer::getInstallPath() % "/" % PlugIn_dir;
	}

	QDir d = QDir::root();
	if ( !d.cd ( PlugIn_dir ) ) {
		lout << "mdm_server_server_impl::registerHelperPlugins : cannot chdir to " << PlugIn_dir << endl;
		return ;
	}
	else {
		d.setFilter ( QDir::Files );
		d.setNameFilters ( QStringList() << "*.so" );
		d.setSorting ( QDir::Size | QDir::Reversed );

		/*const*/
		QFileInfoList list = d.entryInfoList();
		QFileInfo fi;

		for ( int i = 0; i < list.size(); i++ ) {
			fi = list.at ( i );
			PluginBase * pluginBase = new PluginBase;
			if ( pluginBase->registerPlugin ( fi.fileName(), PlugIn_dir, true ) ) {
				delete pluginBase;
				continue;
			}

			PluginImpl * pluginImpl = pluginBase->getInstance();
			if ( !pluginImpl->verifyType ( Adams_DescriptionHelper ) ) {
				//debugPluginError("mdm_server_server_impl::registerHelperPlugins", "Adams_DescriptionHelper", WrongType, pluginImpl);
				delete pluginImpl;
				delete pluginBase;
				continue;
			}

			DescHelperAPIData * pli = new DescHelperAPIData;

			// 			lout << "activating plugin " << fi->fileName();
			pluginImpl->pluginSetupConfig ( &acc, &UserParameter );
			pluginImpl->startup ( pli );
			pli->handler = pluginImpl;
			// 			lout << " - OK" << endl;

			for ( QStringList::Iterator it = pli->elementMatch.begin(); it != pli->elementMatch.end(); ++it ) {
				helpDict.insert ( ( *it ), pli );
			}
		}
	}
}

//
// debug su plugins
//

void mdm_server_server_impl::debugPluginError ( const QString & caller, const QString & requested, pluginMsgType type, PluginImpl * pli )
{
	switch ( type ) {
		case WrongType:
			lout << caller << " : " << requested << " : loaded wrong plugin type." << endl;
			break;
		default:
			lout << caller << " : Generic error." << endl;
	}

	lout << "Plugin Info: " << endl;
	if ( pli ) {
		lout << pli->getPluginInfo().name << " :: Type " << pli->getPluginInfo().globalTypeID << endl;
		lout << "++ Description    : " << pli->getPluginInfo().description << endl;
		lout << "++ Release        : " << pli->getPluginInfo().majorVersionNumber << "." << pli->getPluginInfo().minorVersionNumber << endl;
		lout << "++ Release Date   : " << pli->getPluginInfo().releaseDate.toString() << endl;
		lout << "++ Author/Vendor  : " << pli->getPluginInfo().author << " (" << pli->getPluginInfo().vendorName << ")" << endl;
	}
	else {
		lout << "Unloaded plugin." << endl;
	}
}


//
// main activation method, invoked primarily from the java client
//

CORBA::Boolean mdm_server_server_impl::startMDMServer ( ADAMS_COMPLETE_CONFIG_out accConf, FEPSeq_out CEN, const char* configName ) throw ( CORBA::SystemException )
{
	lout << ">> startMDMServer" << endl;

	if ( configName ) {
		activeConfigName = configName;
		lout << "LOAD REQUEST: " << activeConfigName << endl;
	}

	if ( !configName || activeConfigName.length() == 0 ) {
		lout << "mdm_server_server_impl::startMDMServer: NULL CONFIGURATION REQUESTED." << endl;
		return ( CORBA::Boolean ) false;
	}

	if ( !confLoaded ) {
		loadConfiguration ( activeConfigName );
		confLoaded = true;
	}

	accConf = new ADAMS_COMPLETE_CONFIG;

	ADAMS_COMPLETE_wrapper::fillFrom ( &acc, accConf );

	CEN = new FEPSeq;

	return ( CORBA::Boolean ) true;
}

CORBA::Boolean mdm_server_server_impl::getHelperValues ( const char* configName, const char* tag, const STRUCT_PARAMS& params, HelperSeq_out DESC ) throw ( CORBA::SystemException )
{
	lout << ">> getHelperValues" << endl;
	HelperSeq * LocalDESCS = new HelperSeq;
	DESC = LocalDESCS;

	timeout.setiActive ( true );	// reset watchdog
	if ( timeout.isRunning() == false )
		timeout.start();	// watchdog active

	if ( tag == 0 ) {
		return ( CORBA::Boolean ) false;
	}

	UserParameter.clear();
	UserParameter.fillFromCorba ( &params );

	if ( !confLoaded ) {
		loadConfiguration ( configName );
		confLoaded = true;
	}
	QString today = QDate::currentDate().toString ( "yyyyMMdd" );
// 	today.sprintf ( "%04d%02d%02d", QDate::currentDate().year(),
// 	                QDate::currentDate().month(),
// 	                QDate::currentDate().day() );

	UserParameter.data.ElaborationData.append ( today );

	if ( helpDict.count() == 0 )  		// scan helper plugins
		registerHelperPlugins();

	DescHelperAPIData * hstr = helpDict[ tag ];
	if ( hstr ) {
		hstr->helpTag = tag;
		hstr->nodeIndex = -2;
		hstr->handler->pluginWorker ( hstr );
	}
	else
		return ( CORBA::Boolean ) false;

	if ( hstr->loaded ) {		// successfull loaded
		LocalDESCS->length ( hstr->items.count() );

		int idx = 0;
		DATA_HELP LocalData;
		for ( HelpItemsIterator it = hstr->items.begin(); it != hstr->items.end(); ++it ) {
			qstrncpy ( LocalData.key, qPrintable ( it.key() ), ASCII_REST_LEN );
			qstrncpy ( LocalData.data, qPrintable ( *it.value() ), HELPER_DESC_LEN );

			( *LocalDESCS ) [ idx++ ] = LocalData;
		}
	}
	else
		return ( CORBA::Boolean ) false;

	return ( CORBA::Boolean ) true;
}

QString mdm_server_server_impl::pivotBaseDir()
{
	QString pdir = cnfparser.parQTxtGetValue ( "PivotBaseDir", "MDM", ConfigParser::PathValue );
	if ( pdir.isEmpty() ) {
		lout << "PivotBaseDir not found in configuration. Using default \"~/.adams.tmpdir\"" << endl;
		pdir = "~/.adams.tmpdir";
	}

	dir_check ( pdir );
	return pdir;
}

CORBA::Boolean mdm_server_server_impl::directoryListing ( const char* altPath, DirEntrySeq_out dirList ) throw ( CORBA::SystemException )
{
	dirList = new DirEntrySeq;
	return ( CORBA::Boolean ) true;
}


CORBA::Boolean mdm_server_server_impl::verifyUserLogin ( const char* name, const char* password ) throw ( CORBA::SystemException )
{
	return true;
}


CORBA::Boolean mdm_server_server_impl::readUsers ( const char* loginid, AdamsUserSeq_out users, CORBA::Boolean allusr ) throw ( CORBA::SystemException )
{
	users = new AdamsUserSeq;
	return ( CORBA::Boolean ) true;
}



//
// corba wrapper to write users to file
//

CORBA::Boolean mdm_server_server_impl::writeUsers ( const AdamsUserSeq & users ) throw ( CORBA::SystemException )
{
	return ( CORBA::Boolean ) true;
}


CORBA::Boolean mdm_server_server_impl::getUsersList ( JobUserSeq_out USER ) throw ( CORBA::SystemException )
{
	return ( CORBA::Boolean ) true;
}


CORBA::Boolean mdm_server_server_impl::runMDMServerBlocking ( const STRUCT_PARAMS& UserInputI, CORBA::String_out UrlI ) throw ( CORBA::SystemException )
{
	lout << ">> runMDMServerBlocking" << endl;

	UserInput = UserInputI;
	queryCompleted = false;

	start();

	while ( !isFinished() ) {
		QThread::sleep ( 3 );
	}
	UrlI = Url;
	return ( CORBA::Boolean ) imStat;
}

CORBA::Boolean mdm_server_server_impl::runMDMServerNonBlocking ( const STRUCT_PARAMS& UserInputI ) throw ( CORBA::SystemException )
{
	lout << ">> runMDMServerNonBlocking" << endl;

	UserInput = UserInputI;
	queryCompleted = false;

	for ( int i = 0; i < MAX_FEP; i++ ) {
		global_AgentsRunStatus.percent[ i ] = 0;
		global_AgentsRunStatus.desc[ i ][ 0 ] = '\0';
		global_AgentsRunStatus.url[ i ][ 0 ] = '\0';
	}

	start();
	return ( CORBA::Boolean ) true;
}


CORBA::Boolean mdm_server_server_impl::getRunningStatus ( INVOKE_STATUS_out invokeStatus, CORBA::Boolean_out query_completed ) throw ( CORBA::SystemException )
{
	for ( int i = 0; i < MAX_FEP; i++ ) {
		invokeStatus.percent[ i ] = global_AgentsRunStatus.percent[ i ];
		memset ( ( void * ) & invokeStatus.desc[ i ], 0, 20 );
		qstrncpy ( invokeStatus.desc[ i ], global_AgentsRunStatus.desc[ i ], 20 );
		memset ( ( void * ) & invokeStatus.url[ i ], 0, 512 );
		qstrncpy ( invokeStatus.url[ i ], global_AgentsRunStatus.url[ i ], 512 );
	}

	query_completed = queryCompleted;

	timeout.setiActive ( true );	// reset watchdog
	return ( CORBA::Boolean ) true;
}

QString mdm_server_server_impl::expandRestrictionDescription ( const AdamsCompleteConfig & acc, QueryParams::Rest * r )
{
	if ( acc.dataElementInterface->get ( r->Element ) ->data.guiObject == DataElement::OptionList ) {
		int i = 0;
		while ( i < MAX_OPTIONS ) {
			if ( acc.dataElementInterface->get ( r->Element ) ->data.valueList[ i ] == r->Value[ 0 ] )
				return QString ( acc.dataElementInterface->get ( r->Element ) ->data.valueListLabel[ i ] );
			++i;
		}
	}
	return QString ( r->AsciiValue[ 0 ] );
}


void mdm_server_server_impl::requestAudit ( const AdamsCompleteConfig & acc, const QueryParams & UserParameter, const STRUCT_PARAMS & UserInput )
{
	if ( ! aud_log_file )
		return;

	QString qs, buf;
	QTextStream audrec ( aud_log_file );

	audrec << "--[Request Info Begin]--" << endl;

	audrec << " Server PID      :" << ::getpid() << endl;
	audrec << " User name       :" << UserParameter.data.user << endl;
	audrec << " User ip         :" << UserParameter.data.user_ip << endl;
	audrec << " Profile used    :" << UserParameter.data.Name << endl;

	qs = "";
	for ( QStringList::ConstIterator de = UserParameter.data.ElaborationData.begin(); de != UserParameter.data.ElaborationData.end(); ++de ) {
		qs += ( *de ).mid ( 6, 2 ) + QString ( "/" ) + ( *de ).mid ( 4, 2 ) + QString ( "/" ) + ( *de ).mid ( 0, 4 ) + QString ( " " );
	}
	audrec << " Processed dates :" << qs << endl;
	qs = "";

	for ( int i = 0; i < MAX_FEP; i++ ) {
		if ( UserInput.Fep[ i ] ) {
			qs += QString ( nodeConfigHandler.getInputNodeList().at ( i ).acName ) + " ";
		}
	}
	audrec << " Switching(s)    :" << qs << endl;
	acc.relationInterface->setDataElementReference ( acc.dataElementInterface );
	audrec << " Relation        :" << UserParameter.data.Relation
	       << " [" << acc.relationInterface->decodeRel ( UserParameter.data.Relation, UserParameter.data.ffRelation ) << "]" << endl;
	qs = "";

	if ( !UserParameter.data.Filters.isEmpty() ) {
		if ( UserParameter.data.Filters.count() > 0 ) {
			foreach ( QueryParams::Rest qpr, UserParameter.data.Filters ) {
				if ( qpr.Value[ 0 ] < 0 )
					break;
				DataElement * de = acc.dataElementInterface->get ( qpr.Element );
				if ( de == 0 )
					break;
				qs += ( QString ( de->data.longDescription )
				        + QString ( " : " )
				        + expandRestrictionDescription ( acc, &qpr ) ) + " ";
				int p = 1;
				while ( qpr.Value[ p ] >= 0 ) {
					qs += QString ( ", " ) + QString ( qpr.AsciiValue[ p ] );
					++p;
				}
				qs += "\n";
			}
		}
	}
	audrec << " Restrictions    :" << qs;

	audrec << " Schema id       :" << UserParameter.data.idReportSchema << endl;
	audrec << " Analysis        :" << acc.analysisInterface->get ( UserParameter.data.AnalysisType ) ->data.LongDescription << endl;
	audrec << " Network         :" << ( ( UserParameter.data.NetworkRealTime ) ? "Yes" : "No" ) << endl;

	audrec << "--[Request Info End  ]--" << endl;
}

void mdm_server_server_impl::requestAuditSensitiveData ( const AdamsCompleteConfig & acc, QueryParams & UserParameter, const STRUCT_PARAMS & UserInput, int rows )
{
	if ( ! sensitive_aud_log_file )
		return;

	QString qs, buf;
	QTextStream audrec ( sensitive_aud_log_file );

	lout << "requestAuditSensitiveData" << endl;

	//TODO: Rewrite from scratch...

// 	// in any case unprivileged users are not logged
// 	if ( UserParameter.data.Reserved == false )
// 		do_log = false;
//
// 	if ( do_log == false )
// 		return;			// nothing to log
//
// 	// AAAA-MM-DD-HH-MM-SS
// 	qs = QDateTime::currentDateTime().toString ( "yyyy-MM-dd-hh-mm-ss" ) + ";";
// 	audrec << qs;
//
// 	// IP generator (RDA)
// 	InetHostAddress inha ( "RDA" );
// 	audrec << inet_ntoa ( inha.getAddress() ) << ";";
//
// 	// hostaname generator
// 	audrec << inha.getHostname() << ";";
//
// 	// IP client
// 	audrec << UserParameter.data.user_ip << ";";
//
// 	// hostaname client (not available)
// 	audrec << "N/A;";
//
// 	// application user (not available)
// 	audrec << "N/A;";
//
// 	// client user
// 	audrec << UserParameter.data.user << ";";
//
// 	// application client
// 	audrec << "N.T.M." << ";";
//
// 	// action (relation)
// 	audrec << "relation=" << "[" << acc.relationInterface->decodeRel ( UserParameter.data.Relazione, UserParameter.data.ffRelation ) << "];";
//
// 	// object (restrictions)
// 	qs = "";
// 	audrec << "date(s)=";
//
// 	for ( QStringList::ConstIterator de = UserParameter.data.ElaborationData.begin(); de != UserParameter.data.ElaborationData.end(); ++de ) {
// 		qs += ( *de ).mid ( 6, 2 ) + QString ( "/" ) + ( *de ).mid ( 4, 2 ) + QString ( "/" ) + ( *de ).mid ( 0, 4 ) + QString ( " " );
// 	}
// 	audrec << qs << ",";
// 	qs = "";
//
// 	for ( int i = 0; i < MAX_FEP; i++ ) {
// 		if ( UserInput.Fep[ i ] ) {
// 			qs += QString ( nodeConfigHandler.getSwitchingList().at ( i ) ->acName ) + " ";
// 		}
// 	}
// 	audrec << " Switching(s)=" << qs << ",";
// 	qs = "";
//
// 	if ( !UserParameter.data.Restrizioni.isEmpty() ) {
// 		if ( UserParameter.data.Restrizioni.count() > 0 ) {
// 			Q3PtrListIterator<QueryParams::Rest> it ( UserParameter.data.Restrizioni );
// 			while ( it.current() ) {
// 				if ( it.current() ->Value[ 0 ] < 0 )
// 					break;
// 				DataElement * te = acc.dataElementInterface->get ( it.current() ->Elemento );
// 				if ( te == 0 )
// 					break;
// 				qs += ( QString ( te->data.longDescription )
// 				        + QString ( " : " )
// 				        + expandRestrictionDescription ( acc, it.current() ) ) + " ";
// 				int p = 1;
// 				while ( it.current() ->Value[ p ] >= 0 ) {
// 					qs += QString ( ", " ) + QString ( it.current() ->AsciiValue[ p ] );
// 					++p;
// 				}
// 				qs += ",";
// 				// audrec << endl;
// 				++it;
// 			}
// 		}
// 	}
// 	audrec << " Restrictions=" << qs << ";";
//
// 	// parametri (not available)
// 	audrec << "N/A;";
//
// 	// results
// 	audrec << rows << ";";
//
// 	// ret code (not available)
// 	audrec << "N/A;" << endl;

}

void mdm_server_server_impl::run()
{
	int i;
	int iCenExecute = 0;
	ExecutionThread * ExecThread[ MAX_FEP ];
	QueryParams::Rest * Rest;
	int iNumCenDone = 0;
	HttpImpl * http;
	bool ReportDone[ MAX_FEP ];
	bool StartFailure[ MAX_FEP ];
	int argc = 0;		// fake parameters
	char **argv = 0;	//
	BtreeMap * BtreeDirect = 0;		// map of Btree
	BtreeMap * BtreeInverse = 0;		// map of Btree
	PivotFile * npvf = 0;

	// Start watchdog
	timeout.setProcess ( getpid() );
	timeout.setTimeOut ( WATCHDOG_TIMEOUT );

	if ( !nodeConfigHandler.loaded() ) {
		lout << "Impossibile caricare struttura CENTRALI" << endl;
		imStat = false;
		queryCompleted = true;
		return ;
	}

	/*
	 * * Copia Configurazione su struttura UserParam
	 */
	UserParameter.clear();
	UserParameter.fillFromCorba ( &UserInput );

	// TODO: disable timestamp

	lout1 << "Generating ";
	switch ( UserParameter.data.OutputType ) {
		case usrOutputType_Web:
			lout1 << "HTML/WEB";
			break;
		case usrOutputType_Pivot:
			lout1 << "PIVOT";
			break;
		case usrOutputType_Export:
			lout1 << "EXPORT";
			break;
	}
	lout1 << " output..." << endl;

	if ( UserParameter.data.OutputType == usrOutputType_Pivot ||
	                UserParameter.data.OutputType == usrOutputType_Export ) {
		lout << "Pivot clean check..." << endl;
		QDir d ( pivotBaseDir() );
		// 		d.setNameFilter( "*.dat;*.db" );
		d.setNameFilters ( QStringList() << "*.d" << "*.dat" );

		QFileInfoList list = d.entryInfoList();
		QFileInfo fi;
		QDateTime tlimit = QDateTime::currentDateTime().addDays ( -2 );

		for ( int i = 0; i < list.size(); ++i ) {
			fi = list.at ( i );
			if ( fi.lastModified() < tlimit ) {
				if ( fi.isDir() ) {
					QString sdir_name = pivotBaseDir() + QString ( "/" ) + fi.fileName();
					QDir s_d ( sdir_name );
					lout << "deleting contens of [" << sdir_name << "]" << endl;
					QFileInfoList s_list = s_d.entryInfoList();
					QFileInfo s_fi;
					for ( int j = 0; j < s_list.size(); ++j ) {
						s_fi = s_list.at ( j );
						lout << "removing file [" << s_fi.fileName() << "]" << endl;
						s_d.remove ( s_fi.fileName() );
					}
					lout << "removing dir [" << fi.fileName() << "]" << endl;
					d.rmdir ( fi.fileName() );
				}
				else {
					lout << "removing file [" << fi.fileName() << "]" << endl;
					d.remove ( fi.fileName() );
				}
			}
		}
	}

	try {

		// 		lout << "NtmMasterServer: starting with configName: " << activeConfigName << endl;
		if ( activeConfigName.length() == 0 ) {	// this process restarted after timeout...
			activeConfigName = UserParameter.data.Name;
			lout << "NtmMasterServer: reloading: " << activeConfigName << endl;
		}

		if ( !confLoaded ) {
			loadConfiguration ( activeConfigName );
			confLoaded = true;
		}

		requestAudit ( acc, UserParameter, UserInput );
		/*
		 * * A seconda delle centrali richieste
		 ** vengono lanciati i threads relativi.
		 */
		int iNumCenToDo = 0;
		BtreeNetRT_Direct.clear();
		BtreeNetRT_Inverse.clear();
		BtreeNetRT_Unsort.clear();
		BtreeNetRT_Direct.resetDoneInputsN();
		BtreeNetRT_Inverse.resetDoneInputsN();
		BtreeNetRT_Unsort.resetDoneInputsN();

		if ( helpDict.count() == 0 )            // scan helper plugins
			registerHelperPlugins();

		//
		// Start all requests in distinct threads, checking for (eventually) failures/timeouts
		//
		timeout.setiActive ( true );	// reset watchdog
		if ( timeout.isRunning() == false )
			timeout.start();	// watchdog active

		QTime timer;
		for ( int iCen = 0; iCen < MAX_FEP; iCen++ ) {
			ReportDone[ iCen ] = StartFailure[ iCen ] = false;
			if ( UserInput.Fep[ iCen ] ) {
				iNumCenToDo++;
				QString Centrale ( nodeConfigHandler.getInputNodeList().at ( iCen ).acName );
				if ( ( UserParameter.data.OutputType == usrOutputType_Pivot || UserParameter.data.OutputType == usrOutputType_Export ) &&
				                ( !UserParameter.data.NetworkRealTime ) ) {
					QString pfTemp;
					if ( UserParameter.data.OutputType == usrOutputType_Pivot )
						pfTemp = "MDM_PIVOT_";
					else
						pfTemp = "MDM_EXPORT_";
					PivotFile * pvf = new PivotFile ( pivotBaseDir(), tempFileName ( qPrintable ( pfTemp ) ) );
					lout << "PVF/EXP PREFIX:" << pvf->getFilePrefix() << " in " << pvf->getDataDir() << endl;
					ExecThread[ iCen ] = new ExecutionThread ( UserInput, Centrale, UserParameter, &acc, &BtreeNetRT_Unsort, pvf );
				}
				else
					ExecThread[ iCen ] = new ExecutionThread ( UserInput, Centrale, UserParameter, &acc, &BtreeNetRT_Unsort );
				ExecThread[ iCen ] ->setIndex ( iCen );
				ExecThread[ iCen ] ->useProgressCounters ( &global_AgentsRunStatus );
				ExecThread[ iCen ] ->start();
				timer.start();
				timeout.setiActive ( true );	// reset watchdog
				while ( ExecThread[ iCen ] ->getStatus() == ExecutionThread::Starting ) {
					QThread::sleep ( 1 );
					if ( timer.elapsed() >= MAX_START_TIMING ) {
						lout << "FAILURE: node (" << iCen << ") failed to start after " << MAX_START_TIMING << " msecs" << endl;
						--iNumCenToDo;
						StartFailure[ iCen ] = true;
						global_AgentsRunStatus.percent[ iCen ] = -1;
						break;
					}
				}
				iCenExecute = iCen;
			}
		}

		// -----------
		if ( UserParameter.data.NetworkRealTime == true ) {
			while ( ! ( iNumCenToDo == BtreeNetRT_Unsort.getDoneInputsN() ) ) {
				bool doBreak = true;
				for ( int failcnt = 0; failcnt < MAX_FEP; failcnt++ ) {
					if ( UserInput.Fep[ failcnt ] && !StartFailure[ failcnt ] )
						if ( ExecThread[ failcnt ] ->getStatus() == ExecutionThread::Running )
							doBreak = false;
				}
				if ( doBreak )
					break;
				QThread::sleep ( 1 );
			}
			bool Failures[ MAX_FEP ];
			for ( int fl = 0; fl < MAX_FEP; fl++ ) {
				if ( UserInput.Fep[ fl ] ) {
					if ( StartFailure[ fl ] || ExecThread[ fl ] ->getStatus() == ExecutionThread::Failure )
						Failures[ fl ] = true;
					else
						Failures[ fl ] = false;
				}
				else
					Failures[ fl ] = false;
				if ( Failures[ fl ] )
					global_AgentsRunStatus.percent[ fl ] = -1;
			}
			// Check for StartFailures that, actually, have to be considered panicking condition
			for ( int fl = 0; fl < MAX_FEP; fl++ ) {
				if ( UserInput.Fep[ fl ] && StartFailure[ fl ] ) {
					lout << "WARNING: Report thread(s) failed to start aborting execution !!" << endl;

					imStat = false;
					queryCompleted = true;
					return ;
				}
			}

			performFinalCountersEvaluation();

			QString nodeName = "Network";
			if ( UserParameter.data.OutputType == usrOutputType_Pivot ||
			                UserParameter.data.OutputType == usrOutputType_Export ) {
				// 			npvf = new PivotFile();
				npvf = new PivotFile ( pivotBaseDir(), tempFileName ( "NET_PIVOT_" ) );
				// 			npvf->setDataDir(pivotBaseDir());
				// 			npvf->setFilePrefix(QString(tempnam("", "NET_PIVOT_")).remove("/tmp/"));
				lout << "PVF/EXP PREFIX:" << npvf->getFilePrefix() << " in " << npvf->getDataDir() << endl;

				sortNetworkTreeByCounter ( npvf );

				nodeName += QString ( " " ) + npvf->getFilePrefix() + QString ( " " );
				Url = CORBA::string_dup ( qPrintable ( nodeName ) );
				if ( UserParameter.data.OutputType == usrOutputType_Pivot )
					lout << "Completed NETWORK PIVOT analysis." << endl;
				else
					lout << "Completed NETWORK EXPORT analysis." << endl;

				qstrcpy ( global_AgentsRunStatus.url[ 0 ], qPrintable ( npvf->getFilePrefix() ) );
				qstrcpy ( global_AgentsRunStatus.desc[ 0 ], qPrintable ( nodeName ) );
			}
			else {
				sortNetworkTreeByCounter ( 0 );

				ReportFormatter * rf = new ReportFormatter ( acc, UserParameter, &timeout );
				rf->setHelperDict ( &helpDict );
				rf->init ( &BtreeNetRT_Direct, &BtreeNetRT_Inverse );	// -1 means "network"
				rf->exec();
				rf->closeMedia();

				nodeName += QString ( " " ) + rf->getMediaName().remove ( "/tmp/" ) + QString ( " " );
				Url = CORBA::string_dup ( qPrintable ( nodeName ) );

				qstrcpy ( global_AgentsRunStatus.url[ 0 ], qPrintable ( rf->getMediaName().remove ( "/tmp/" ) ) );
				qstrcpy ( global_AgentsRunStatus.desc[ 0 ], qPrintable ( nodeName ) );
			}
			global_AgentsRunStatus.percent[ 0 ] = 999;	// completed

			requestAuditSensitiveData ( acc, UserParameter, UserInput, 0 /*max( BtreeNetRT_Direct.count(), BtreeNetRT_Inverse.count() )*/ );

			for ( int iCen = 0; iCen < MAX_FEP; iCen++ ) {
				if ( UserInput.Fep[ iCen ] ) {
					if ( UserInput.Fep[ iCen ] && !StartFailure[ iCen ] ) {
						//						lout << "deleting thread " << iCen << endl;
						delete ExecThread[ iCen ];
					}
					else if ( UserInput.Fep[ iCen ] && StartFailure[ iCen ] ) {
						lout << "skipping deletion of thread " << iCen << endl;
					}
				}
			}
			imStat = true;
			queryCompleted = true;
			return ;
		} // ----------- Network
		else {
			bool Failures[ MAX_FEP ];
			while ( iNumCenDone < iNumCenToDo ) {
				for ( int iCen = 0; iCen < MAX_FEP; iCen++ ) {
					if ( UserInput.Fep[ iCen ] ) {
						if ( StartFailure[ iCen ] || ExecThread[ iCen ] ->getStatus() == ExecutionThread::Failure )
							Failures[ iCen ] = true;
						else
							Failures[ iCen ] = false;
					}
					else
						Failures[ iCen ] = false;
					if ( Failures[ iCen ] )
						global_AgentsRunStatus.percent[ iCen ] = -1;

					// Check for StartFailures that, actually, have to be considered panicking condition
					for ( int fl = 0; fl < MAX_FEP; fl++ ) {
						if ( UserInput.Fep[ fl ] && StartFailure[ fl ] ) {
							lout << "WARNING: Network Report thread(s) failed to start aborting execution !!" << endl;
							imStat = false;
							queryCompleted = true;
							return ;
						}
					}

					if ( UserInput.Fep[ iCen ] && ExecThread[ iCen ] ->getStatus() != ExecutionThread::Running &&
					                !ReportDone[ iCen ] && !StartFailure[ iCen ] ) {
						// if some remote finished analysis we print it
						if ( UserParameter.data.OutputType == usrOutputType_Pivot ||
						                UserParameter.data.OutputType == usrOutputType_Export ) {
							QString nodeName = QString ( nodeConfigHandler.getInputNodeList().at ( iCen ).acName );
							PivotFile * pvf = ExecThread[ iCen ] ->getPivotFile();
							nodeName += QString ( " " ) + pvf->getFilePrefix() + QString ( " " );
							Url = CORBA::string_dup ( qPrintable ( nodeName ) );
							if ( UserParameter.data.OutputType == usrOutputType_Pivot )
								lout << "Completed PIVOT analysis for:[" << nodeName << "]" << endl;
							else
								lout << "Completed EXPORT analysis for:[" << nodeName << "]" << endl;
							requestAuditSensitiveData ( acc, UserParameter, UserInput, 0 );

							qstrcpy ( global_AgentsRunStatus.url[ iCen ], qPrintable ( pvf->getFilePrefix() ) );
							qstrcpy ( global_AgentsRunStatus.desc[ iCen ], qPrintable ( nodeName ) );
						}
						else {
							ReportFormatter * rf = new ReportFormatter ( acc, UserParameter, &timeout );
							rf->setHelperDict ( &helpDict );
							// 							rf->setOutputMedia(ReportFormatter::LocalFile, "/usr/users/orbix/reportdebug");
							switch ( UserParameter.data.RelationDirection ) {
								case usrRelationKind_Both:
									BtreeDirect = ExecThread[ iCen ] ->getBtreeDirect();
									performFinalCountersEvaluation ( BtreeDirect );
									BtreeInverse = ExecThread[ iCen ] ->getBtreeInverse();
									performFinalCountersEvaluation ( BtreeInverse );
									break;
								case usrRelationKind_Inverse:
									performFinalCountersEvaluation ( BtreeInverse );
									BtreeInverse = ExecThread[ iCen ] ->getBtreeInverse();
									break;
								case usrRelationKind_Direct:
								default:
									BtreeDirect = ExecThread[ iCen ] ->getBtreeDirect();
									performFinalCountersEvaluation ( BtreeDirect );
									break;
							}
							rf->init ( BtreeDirect, BtreeInverse, iCen );
							rf->exec();
							rf->closeMedia();
							timeout.setiActive ( true );

							requestAuditSensitiveData ( acc, UserParameter, UserInput, 0 /*max( BtreeDirect->count(), BtreeInverse->count() )*/ );

							QString nodeName = QString ( nodeConfigHandler.getInputNodeList().at ( iCen ).acName );
							nodeName += QString ( " " ) + rf->getMediaName().remove ( "/tmp/" ) + QString ( " " );
							Url = CORBA::string_dup ( qPrintable ( nodeName ) );

							qstrcpy ( global_AgentsRunStatus.url[ iCen ], qPrintable ( rf->getMediaName().remove ( "/tmp/" ) ) );
							qstrcpy ( global_AgentsRunStatus.desc[ iCen ], qPrintable ( nodeName ) );
						}

						if ( global_AgentsRunStatus.percent[ iCen ] != -1 )
							global_AgentsRunStatus.percent[ iCen ] = 999;	// completed

						ReportDone[ iCen ] = true;
						iNumCenDone++;
						lout2 << "Completed report " << iCen << endl;
					}
				}
				QThread::sleep ( 2 );
			}

			for ( int iCen = 0; iCen < MAX_FEP; iCen++ ) {
				if ( UserInput.Fep[ iCen ] && !StartFailure[ iCen ] ) {
					lout3 << "deleting thread " << iCen << endl;
					ExecThread[ iCen ] ->terminate();
					delete ExecThread[ iCen ];
				}
				else if ( UserInput.Fep[ iCen ] && StartFailure[ iCen ] ) {
					lout << "skipping deletion of thread " << iCen << endl;
				}
			}
		}
	}
	catch ( const CORBA::Exception & e ) {
		//		lout << "Unexpected exception" << e << endl;
		lout << "Unexpected exception" << endl;
		imStat = false;
		queryCompleted = true;
		return ;
	}

	imStat = true;
	queryCompleted = true;
	lout3 << "MDM Server pid[" << getpid() << "] terminated query." << endl;

	return ;
}


bool mdm_server_server_impl::performFinalCountersEvaluation ( BtreeMap * Btree )
{
	int int_n, dbl_n;
	Counters * counters;
	PluginImpl * counters_plugin = 0;
	CountersPluginAPIData countersPluginAPIData;

	Nodo::getCountersSize ( int_n, dbl_n );

	if ( Analysis * an = acc.analysisInterface->get ( UserInput.AnalysisType ) ) {
		counters = acc.counterInterface->get ( an->data.countersKitId );
		if ( counters == 0 ) {
			lout << "performFinalCountersEvaluation : Cannot get counters kit #" << an->data.countersKitId <<
			     " from Analisi #" << UserInput.AnalysisType << endl;
			return false;
		}
	}
	else {
		lout << "performFinalCountersEvaluation : Cannot get Analisi #" << UserInput.AnalysisType << endl;
		return false;
	}

	if ( counters->data.usePlugin ) {
		if ( counters_plugin_factory.find ( counters->data.pluginName ) != counters_plugin_factory.end() ) {
			counters_plugin = counters_plugin_factory.value ( counters->data.pluginName );
		}
		else {
			PluginBase * countersPluginBase = new PluginBase;
			QString PlugIn_dir = acc.drInterface->globalOpt()->data.glbDefaultPluginPath;

			expand_plugin_dir ( PlugIn_dir );

			if ( countersPluginBase->registerPlugin ( counters->data.pluginName,
			                ( counters->data.usePath ) ? counters->data.pathName : PlugIn_dir, true ) ) {
				lout << "performFinalCountersEvaluation : cannot instance counters plugin "
				     << counters->data.pluginName
				     << endl;
				return false;			// plugin required but failed to attach
			}
			counters_plugin = countersPluginBase->getInstance();
			if ( !counters_plugin->verifyType ( Adams_CounterHandler ) ) {
				debugPluginError ( "performFinalCountersEvaluation", "CounterHandler", WrongType, counters_plugin );
				return false;

				counters_plugin->pluginSetupConfig ( ( void * ) &acc, ( void * ) &UserParameter );
				countersPluginAPIData.dayIndex = 0;

				counters_plugin->startup ( ( void * ) &countersPluginAPIData );

			}

			counters_plugin_factory.insert ( counters->data.pluginName, counters_plugin );
		}
	}

	lout1 << "Final counters evaluation running..." << endl;

	BtreeMapIterator it;
	BtreeMapIterator tree_end_it;

	if ( Btree ) {
		it = Btree->begin();
		tree_end_it = Btree->end();
	}
	else {
		it = BtreeNetRT_Unsort.begin();
		tree_end_it = BtreeNetRT_Unsort.end();
	}

	while ( it != tree_end_it ) {

		if ( counters_plugin ) {
			countersPluginAPIData.operation = cntNetworkEvaluation;
			countersPluginAPIData.countersNode = &it.value();

			counters_plugin->pluginWorker ( ( void * ) &countersPluginAPIData );

		}
		else {
			// Valorize derived counters
			acc.counterInterface->closeCounterUpdate (
			        &acc,
			        &UserParameter,
			        counters,
			        &it.value(),
			        int_n,
			        dbl_n );
		}

		++it;
	}

	lout1 << "done." << endl;
	return true;
}

int mdm_server_server_impl::sortNetworkTreeByCounter ( PivotFile * pivotFile )
{
	char DirectKey[ MAX_KEY_LENGTH ];
	char InverseKey[ MAX_KEY_LENGTH ];
	char FirstKey[ MAX_KEY_LENGTH ];
	char SecondKey[ MAX_KEY_LENGTH ];
	char SortKey[ MAX_KEY_LENGTH ];
	int sortIndex = UserParameter.data.ElementToSort - 1;
	int percIndex = -1;
	int percType = -1;
	int int_n, dbl_n;
	BTreeNetworkRT::Iterator it;
	DefineRelation DefRel;
	RelationComponent * aiElemenInRelation;
	bool ghostRelation = false;

	SortKey[ 0 ] = '\0';
	DefRel.setElementInRelation ( &acc, UserParameter.data.Relation, UserParameter.data.ffRelation );
	aiElemenInRelation = DefRel.getRelationComponentsArray();

	bool isPivot = ( UserParameter.data.OutputType == usrOutputType_Pivot || UserParameter.data.OutputType == usrOutputType_Export );
	if ( isPivot )
		lout << "ASSEMBLING NETWORK PIVOT" << endl;

	// copia in locale

	int klen1 = aiElemenInRelation[ 0 ].length;		// lunghezza chiavi rel. primo livello
	int klen2 = aiElemenInRelation[ 1 ].length;

	Nodo::getCountersSize ( int_n, dbl_n );

	Counters * cnts = 0;
	QStringVector pivotKeys ( MAX_PIVOT_KEYS );

	if ( Analysis * an = acc.analysisInterface->get ( UserInput.AnalysisType ) ) {
		cnts = acc.counterInterface->get ( an->data.countersKitId );
		if ( cnts == 0 ) {
			lout << "mdm_server_server_impl::sortNetworkTreeByCounter : Cannot get counters kit #" << an->data.countersKitId <<
			     " from Analisi #" << UserInput.AnalysisType << endl;
			return -1;
		}
	}
	else {
		lout << "mdm_server_server_impl::sortNetworkTreeByCounter : Cannot get Analisi #" << UserInput.AnalysisType << endl;
		return -1;
	}

	relation * r = acc.relationInterface->get ( UserParameter.data.Relation );
	if ( r == 0 ) {
		lout << "mdm_server_server_impl::sortNetworkTreeByCounter : Invalid user params : NO relation." << endl;
		return -1;
	}
	if ( r->data.ghostRelation ) {
		ghostRelation = true;
	}

	if ( sortIndex >= MAX_INT_COUNTERS )
		sortIndex -= MAX_INT_COUNTERS + int_n + 1;

	if ( sortIndex >= 0 )
		percIndex = acc.counterInterface->getPercentOf ( *cnts, sortIndex );

	if ( percIndex >= 0 && UserParameter.data.IsPercent ) {			// parametri ordinamento
		if ( percIndex >= int_n && percIndex < ( int_n + dbl_n ) ) {
			percType = 0;		// double
		}
		else if ( percIndex >= 0 && sortIndex < int_n ) {
			percType = 1;		// integer
		}
	}

	if ( isPivot ) {		// store analysis lenght & fixed params
		pivotFile->setInfoLenght ( BtreeNetRT_Unsort.count() );
		pivotFile->setInfoInts ( int_n );
		pivotFile->setInfoDbls ( dbl_n );
		pivotFile->setInfoName ( UserParameter.data.Name );
		pivotFile->setInfoRelation ( UserParameter.data.Relation );
		pivotFile->setInfoRelationSize ( DefRel.getDimension() );
		pivotFile->setInfoSwitching ( -1 );
		pivotFile->setInfoDate ( UserParameter.data.ElaborationData.last() );
		pivotFile->setInfoSchema ( UserParameter.data.idReportSchema );
		pivotFile->setInfoAnalysis ( UserParameter.data.AnalysisType );
		pivotFile->setInfoIsPercent ( UserParameter.data.IsPercent );
		pivotFile->setInfoWithPreSort ( ( sortIndex >= 0 ) );
		pivotFile->setInfoReversePreSort ( pivotFile->getInfoWithPreSort() && !UserParameter.data.Ascendent );
		pivotFile->setInfoSortDataCol ( sortIndex );
		pivotFile->setInfoSortPercCol ( percIndex );
		pivotFile->setInfoRestrictions ( UserParameter.data.Filters );
		pivotFile->setFfRelation ( UserParameter.data.ffRelation );
		pivotFile->setReserved ( UserParameter.data.Reserved );

		// fill key description
		int k_offset = 0;
		for ( int i = 0; i < DefRel.getDimension(); i++ ) {
			pivotFile->setInfoKey ( i, aiElemenInRelation[ i ].desc, aiElemenInRelation[ i ].length, k_offset );
			k_offset += aiElemenInRelation[ i ].length;
		}

		if ( pivotFile->writeInfo() )
			return -1;

		if ( pivotFile->openForWrite() ) {
			return -1;
		}
	}


	for ( it = BtreeNetRT_Unsort.begin(); it != BtreeNetRT_Unsort.end(); ++it ) {

		Nodo * nodo = new Nodo;
		nodo->setupCounters();
		*nodo += it.value();

		if ( sortIndex >= 0 ) {						// ordinamento richiesto
			if ( sortIndex >= int_n && sortIndex < ( int_n + dbl_n ) ) {
				switch ( percType ) {
					case 0:  	// double referrer
						sprintf ( SortKey, "%08.2f", ( nodo->GetLista() ->dbl_counters[ sortIndex - int_n ] * 100.0 ) / nodo->GetLista() ->dbl_counters[ percIndex - int_n ] );
						break;
					case 1:   // int referrer
						sprintf ( SortKey, "%08.2f", ( nodo->GetLista() ->dbl_counters[ sortIndex - int_n ] * 100.0 ) / ( double ) nodo->GetLista() ->int_counters[ percIndex ] );
						break;
					default:
						sprintf ( SortKey, "%08.2f", nodo->GetLista() ->dbl_counters[ sortIndex - int_n ] );
				}
			}
			else if ( sortIndex >= 0 && sortIndex < int_n ) {
				switch ( percType ) {
					case 0:  	// double referrer
						sprintf ( SortKey, "%08.2f", ( nodo->GetLista() ->int_counters[ sortIndex ] * 100.0 ) / nodo->GetLista() ->dbl_counters[ percIndex - int_n ] );
						break;
					case 1:   // int referrer
						sprintf ( SortKey, "%08.2f", ( nodo->GetLista() ->int_counters[ sortIndex ] * 100.0 ) / ( double ) nodo->GetLista() ->int_counters[ percIndex ] );
						break;
					default:
						sprintf ( SortKey, "%08d", nodo->GetLista() ->int_counters[ sortIndex ] );
				}
			}
		}

		if ( ghostRelation ) {
			qstrcpy ( DirectKey, qPrintable ( it.key().mid ( 0, klen1 ) ) );
			BtreeNetRT_Direct.insertOrSum ( DirectKey, *nodo );
		}
		else if ( isPivot ) {
			int koffs = 0;
			for ( int k = 0; k < DefRel.getDimension(); k++ ) {
				int klen = aiElemenInRelation[ k ].length;
				pivotKeys[ k ] = QString ( it.key() ).mid ( koffs, klen );
				koffs += klen;
			}

			pivotFile->writeNode ( pivotKeys, nodo );
		}
		else {

			qstrcpy ( FirstKey, qPrintable ( it.key().mid ( 0, klen1 ) ) );
			qstrcpy ( SecondKey, qPrintable ( it.key().mid ( klen1, klen2 ) ) );

			qstrcpy ( DirectKey, FirstKey );
			strcat ( DirectKey, SortKey );
			strcat ( DirectKey, SecondKey );

			if ( ( klen1 + klen2 ) < it.key().length() )
				strcat ( DirectKey, qPrintable ( it.key().mid ( klen1 + klen2 ) ) );

			qstrcpy ( InverseKey, SecondKey );
			strcat ( InverseKey, SortKey );
			strcat ( InverseKey, FirstKey );
			if ( ( klen1 + klen2 ) < it.key().length() )
				strcat ( InverseKey, qPrintable ( it.key().mid ( klen1 + klen2 ) ) );


			if ( UserParameter.data.RelationDirection == usrRelationKind_Direct || UserParameter.data.RelationDirection == usrRelationKind_Both || isPivot ) {
				BtreeNetRT_Direct.insertOrSum ( DirectKey, *nodo );
			}
			if ( ( UserParameter.data.RelationDirection == usrRelationKind_Inverse || UserParameter.data.RelationDirection == usrRelationKind_Both ) && !isPivot ) {
				BtreeNetRT_Inverse.insertOrSum ( InverseKey, *nodo );
			}
		}
	}

	if ( isPivot ) {
		pivotFile->close ();
	}

	if ( UserParameter.data.RelationDirection != usrRelationKind_Inverse )
		return BtreeNetRT_Direct.count();
	else
		return BtreeNetRT_Inverse.count();
}

/** Deriva l'indice del contatore sulla base del tag */

int mdm_server_server_impl::getCounterIndex ( Counters * kit, const QString & cntTag, int & tagi )
{
	for ( int i = 0; i < CNT_NUM; i++ ) {
		if ( qstrlen ( kit->data.counterKit[ i ].tag ) == 0 )
			break;
		else if ( cntTag == kit->data.counterKit[ i ].tag ) {
			tagi = i;
			return kit->data.counterKit[ i ].counterIndex;
		}
	}

	return -1;
}

PluginImpl * mdm_server_server_impl::getHandlerPluginInstance ( DataElement * te )
{
	PluginImpl * plugin;

	PluginRegistry * p = acc.pluginRegistryInterface->get ( te->data.idPlugin );
	if ( p == 0 ) {
		lout << "NtmMasterServer : ReportFormatter::getHandlerPluginInstance : Cannot get plugin #" <<
		     te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
		return 0;
	}

	//####### This piece of code is needed under HP-UX since server invocation upon a shell
	//        (as the serverfactory does) completely blank user environment.......
	QString PlugIn_dir = acc.drInterface->globalOpt()->data.glbDefaultPluginPath;

	expand_plugin_dir ( PlugIn_dir );
	//#######

	// first check if a pluginbase already exists for this te
	PluginBase * plBase = pluginFactory[ te->data.idElement ];
	if ( plBase == 0 ) {		// new one
		plBase = new PluginBase;
		if ( plBase->registerPlugin ( p->data.pluginName, PlugIn_dir, true ) ) {
			lout << "NtmMasterServer : ReportFormatter::getHandlerPluginInstance : Failed to register plugin " <<
			     p->data.pluginName << " needed from Element #" << te->data.idElement << endl;
			return 0;			// plugin required but failed to attach
		}

		pluginFactory.insert ( te->data.idElement, plBase );
	}
	plugin = plBase->getInstance();
	if ( !plugin->verifyType ( Adams_ElementHandler ) ) {
		debugPluginError ( "NtmMasterServer : ReportFormatter::getHandlerPluginInstance", "DataElementHandler", WrongType, plugin );
		delete plugin;
		return 0;
	}
	plugin->pluginSetupConfig ( &acc, &UserParameter );
	return plugin;
}

CORBA::Boolean mdm_server_server_impl::getExportReport ( const char* name, ExportDataSeq_out exportData ) throw ( CORBA::SystemException )
{
	ExportDataSeq * export_d = new ExportDataSeq;
	PivotDataNode_private pdi;
	int rec_in_file = 0;
	QString cnfName;
	int relID;
	int nodeIndex;
	QString elabDate;
	int reportID;
	int analysisID;

	pdi.int_n = 0;
	pdi.dbl_n = 0;
	pdi.key_n = 0;

	timeout.setTimeOut ( WATCHDOG_TIMEOUT );
	timeout.setiActive ( true );	// reset watchdog
	if ( timeout.isRunning() == false )
		timeout.start();	// watchdog active

	lout << "getExportReport: file is     : [" << name << "]" << endl;
	PivotFile pvf ( pivotBaseDir(), name );

	if ( pvf.readInfo() ) {
		lout << "**** CANNOT ACCESS EXPORT ANALYSIS INFO FILE: " << pvf.getFilePrefix() << " in " << pvf.getDataDir() << endl;
		return ( CORBA::Boolean ) false;
	}

	UserParameter.clear();

	// retrieve analysis informations
	rec_in_file = pvf.getInfoLenght();
	pdi.int_n = pvf.getInfoInts();
	pdi.dbl_n = pvf.getInfoDbls();
	cnfName = pvf.getInfoName();
	relID = pvf.getInfoRelation();
	nodeIndex = pvf.getInfoSwitching();
	elabDate = pvf.getInfoDate();
	reportID = pvf.getInfoSchema();
	analysisID = pvf.getInfoAnalysis();
	pdi.isPercent = true;
	UserParameter.data.Reserved = pdi.reserved = pvf.getReserved();
	pdi.withPreSort = pvf.getInfoWithPreSort();
	int sortIndex = pvf.getInfoSortDataCol();
	int percIndex = pvf.getInfoSortPercCol();
	UserParameter.data.Filters = pvf.getInfoRestrictions();
	int * pv_ffRelation = pvf.getFfRelation();
	for ( int i = 0; i < MAX_DIMENSION; i++ )
		UserParameter.data.ffRelation[ i ] = pv_ffRelation[ i ];
	pdi.userData = pvf.getUserData();
	pdi.userDataSize = pvf.getUserDataSize();

	lout1 << "export lines = " << rec_in_file << endl;

	UserParameter.data.ElaborationData << elabDate;
	UserParameter.data.Relation = relID;

	if ( !pdi.userData )
		lout1 << "getExportReport: # records   : [" << rec_in_file << "] of [" << pdi.int_n << "] ints and [" << pdi.dbl_n << "] doubles " << endl;
	else
		lout1 << "getExportReport: # records   : [" << rec_in_file << "] with [" << pdi.userDataSize << "] bytes of user data" << endl;
	lout1 << "getExportReport: config file : [" << cnfName << "]" << endl;
	lout1 << "getExportReport: relation    : [" << relID << "]" << endl;
	lout1 << "getExportReport: switching   : [" << nodeIndex << "]" << endl;
	lout1 << "getExportReport: elab. date  : [" << elabDate << "]" << endl;
	lout1 << "getExportReport: id schema   : [" << reportID << "]" << endl;
	lout1 << "getExportReport: id analysis : [" << analysisID << "]" << endl;
	lout1 << "getExportReport: presort     : [" << ( ( pdi.withPreSort ) ? "Yes" : "No" ) << "]" << endl;
	lout1 << "getExportReport: # of restr. : [" << UserParameter.data.Filters.count() << "]" << endl;
	lout1 << "getExportReport: reserved    : [" << pdi.reserved << "]" << endl;

	if ( pvf.openForRead() ) {
		lout << "**** Cannot access export data for read. ****" << endl;
		return ( CORBA::Boolean ) false;
	}
	lout1 << "Export data opened for read" << endl;

	if ( activeConfigName.length() == 0 ) {
		activeConfigName = cnfName;
		lout << "loading: " << activeConfigName << endl;
	}

	if ( !confLoaded ) {
		loadConfiguration ( activeConfigName );
		confLoaded = true;
	}
	lout1 << "loaded configuration." << endl;

	if ( helpDict.count() == 0 )  		// scan helper plugins
		registerHelperPlugins();
	lout1 << "helper plugins registered." << endl;

	DefineRelation defineRelation;
	pdi.defineRelation = &defineRelation;
	defineRelation.setElementInRelation ( &acc, relID, UserParameter.data.ffRelation );
	int relationSize = defineRelation.getDimension();
	lout1 << "getExportReport: rel. size   : [" << relationSize << "]" << endl;
	acc.relationInterface->setDataElementReference ( acc.dataElementInterface );
	pdi.r_key_n = pdi.key_n = relationSize;
	ReportSchema * schema = acc.reportInterface->get ( reportID );

	if ( schema->objO.data.u.object.usePlugin ) {	// plugin handled
		QString PlugIn_dir = acc.drInterface->globalOpt()->data.glbDefaultPluginPath;

		expand_plugin_dir ( PlugIn_dir );

		PluginBase pluginBase;
		if ( pluginBase.registerPlugin ( schema->objO.data.u.object.pluginName, PlugIn_dir, true ) ) {
			return ( CORBA::Boolean ) false;
		}

		PluginImpl * pluginImpl = pluginBase.getInstance();
		if ( !pluginImpl->verifyType ( Adams_ReportHandler ) ) {
			debugPluginError ( "getExportReport", "Adams_ReportHandler", WrongType, pluginImpl );
			delete pluginImpl;
			return ( CORBA::Boolean ) false;
		}

		lout2 << "Calling report handler plugin..." << endl;

		ReportHandlerAPIData reportHandlerAPIData;

		reportHandlerAPIData.directTree = 0;
		reportHandlerAPIData.inverseTree = 0;
		reportHandlerAPIData.nodeIndex = 0;
		reportHandlerAPIData.http = 0;
		reportHandlerAPIData.stream = 0;
		reportHandlerAPIData.mediaType = ReportFormatter::DataExport;
		reportHandlerAPIData.haveHeaderManagement = false;
		reportHandlerAPIData.helper = &helpDict;
		reportHandlerAPIData.pivotFile = &pvf;
		reportHandlerAPIData.exportData = export_d;

		pluginImpl->pluginSetupConfig ( &acc, &UserParameter );
		pluginImpl->startup ( &reportHandlerAPIData );

		pluginImpl->pluginWorker ( &reportHandlerAPIData );

		delete pluginImpl;
		exportData = export_d;
		lout1 << "** exported data. [" << export_d->length() << "] lines in CORBA vector." << endl;

		// 		destroyPDI ( pdi );

		timeout.setTimeOut ( LONG_WATCHDOG_TIMEOUT );
		timeout.setiActive ( true );		// set long lifetime watchdog
		return ( CORBA::Boolean ) true;
	}


	int col_in_a_row = relationSize;
	int col_0_count = 0;
	int rowCount = 0;
	export_d->length ( 1 );

	lout1 << "getExportReport: rel. string : [" << acc.relationInterface->decodeRel ( UserParameter.data.Relation, UserParameter.data.ffRelation ) << "]" << endl;

	pdi.keyHelperPresent = new bool [ relationSize ];
	pdi.keyScript = new AdamsBasic * [ relationSize ];
	pdi.keyPlugin = new PluginImpl * [ relationSize ];
	for ( int i = 0; i < relationSize; i++ ) {	// clean dynamics
		pdi.keyScript[ i ] = 0;
		pdi.keyPlugin[ i ] = 0;
	}
	for ( int i = 0; i < relationSize; i++ ) {
		DescHelperAPIData * hstr = helpDict[ defineRelation.getRelationComponent ( i ) ->desc ];

		if ( hstr ) {
			hstr->helpTag = defineRelation.getRelationComponent ( i ) ->desc;
			hstr->nodeIndex = nodeIndex;
			hstr->handler->pluginWorker ( hstr );
			pdi.keyHelperPresent[ i ] = hstr->loaded;
		}
		else
			pdi.keyHelperPresent[ i ] = false;

		DataElement * te = acc.dataElementInterface->get ( defineRelation.getRelationComponent ( i ) ->id );
		Script * inis = 0;
		bool hasPlugin = false;
		if ( te ) {
			if ( te->data.scripts[ Script::saReportProduction ] > 0 )
				inis = acc.scriptInterface->get ( te->data.scripts[ Script::saReportProduction ] );
			hasPlugin = ( te->data.idPlugin > 0 );
		}
		if ( hasPlugin ) {
			DEHandlerWorkerAPIData pld;
			lout1 << "element " << te->data.idElement << " has plugin" << endl;
			// handled by plugin, so recall it
			pdi.keyPlugin[ i ] = getHandlerPluginInstance ( te );
			if ( pdi.keyPlugin[ i ] > 0 ) {

				if ( !pdi.keyPlugin[ i ] ->startup ( ( void * ) & pld ) ) {
					lout << "NtmMasterServer : getExportReport : Cannot startup plugin #" <<
					     te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
					delete pdi.keyPlugin[ i ];
					pdi.keyPlugin[ i ] = 0;
				}
				else {
					if ( pld.useFor[teOp_ReportProduction] == false ) {
						// unsupported by plugin's functionality
						lout1 << "plugin element " << te->data.idElement << " report usage unsupported" << endl;
						delete pdi.keyPlugin[i];
						pdi.keyPlugin[i] = 0;
						hasPlugin = false;
					}
					else {
						// call plugin to setup it (eventually)
						pld.element = te;
						pld.operation = teOp_InitPhase;
						pdi.keyPlugin[i]->pluginWorker ( ( void * ) &pld );
						if ( pld.status == teStatus_Error ) {
							lout1 << "NtmMasterServer : getExportReport : Plugin execution (InitPhase) was unsucessfull for element #" <<  te->data.idElement << endl;
							delete pdi.keyPlugin[i];
							pdi.keyPlugin[i] = 0;
						}
					}
				}
			}
		}
		if ( inis && !hasPlugin ) {	// plugins always have precedence
			AdamsBasic * nb = new AdamsBasic;
			nb->setReportMode ( AdamsBasic::nbToLogFile );

			if ( nb->getStatus() != AdamsBasic::nbSuccess ) {
				delete nb;
				pdi.keyScript[ i ] = 0;	// error
			}
			else if ( !nb->setProgramScript ( *inis ) ) {
				delete nb;
				pdi.keyScript[ i ] = 0;	// error
			}
			else
				pdi.keyScript[ i ] = nb;
		}
	}
	lout1 << "helpers initialized." << endl;

	// evaluate rows to pass to client, both counters and scripts are taken.
	int cntKitId;

	Analysis * analysis = acc.analysisInterface->get ( analysisID );
	if ( analysis ) {
		cntKitId = analysis->data.countersKitId;
	}
	else {
		lout << "getExportReport : cannot get analisys #" << analysisID << endl;
	}
	pdi.kit = acc.counterInterface->get ( cntKitId );
	if ( pdi.kit == 0 ) {
		lout << "getExportReport : cannot get counters kit #" << cntKitId << endl;
	}

	if ( sortIndex >= 0 && percIndex < 0 ) {	// fix unassigned percIndex
		percIndex = acc.counterInterface->getPercentOf ( *pdi.kit, sortIndex );
	}

	pdi.idn = 0;
	pdi.pseqlen = 0;

	// how many counters to pass back?
	if ( schema ) {
		if ( schema->bdyV.count() > 0 ) {
			for ( RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it ) {
				if ( ( *it ).data.u.body.source == ReportSchemaObj::Script && ( *it ).data.u.body.idScript > 0 ) {
					++pdi.pseqlen;
				}
				else if ( ( *it ).data.u.body.source == ReportSchemaObj::CounterKit && ( *it ).data.u.body.idCounter > 0 ) {
					++pdi.pseqlen;
				}
			}
		}
	}

	col_in_a_row += pdi.pseqlen;

	// Generate headers row
	( *export_d ) [ rowCount ].exportCol.length ( col_in_a_row );
	for ( int i = 0; i < relationSize; i++ ) {
		DescHelperAPIData * hstr = helpDict[ defineRelation.getRelationComponent ( i ) ->desc ];

		( *export_d ) [ rowCount ].exportCol[ col_0_count ].typeCol = exdt_Key;
		( *export_d ) [ rowCount ].exportCol[ col_0_count++ ].valueCol = CORBA::string_dup ( qPrintable ( defineRelation.getRelationComponent ( i ) ->desc ) );
	}


	if ( schema ) {

		if ( schema->bdyV.count() > 0 ) {
			pdi.bdyScripts = new BodyScripts [ schema->bdyV.count() ];	// how many ?
			pdi.pdMap = new PivotDataMap_private [ schema->bdyV.count() ];
			int idx = 0;

			for ( RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it ) {
				// some init ?
				Script * inis = 0;
				pdi.bdyScripts[ idx ].sHndl = 0;

				if ( ( *it ).data.u.body.source == ReportSchemaObj::Script && ( *it ).data.u.body.idScript > 0 ) {
					inis = acc.scriptInterface->get ( ( *it ).data.u.body.idScript );
					if ( inis ) {
						AdamsBasic * nb = new AdamsBasic;
						nb->setReportMode ( AdamsBasic::nbToLogFile );

						if ( nb->getStatus() != AdamsBasic::nbSuccess ) {
							delete nb;
							pdi.bdyScripts[ idx ].sHndl = 0;	// error
						}
						else if ( !nb->setProgramScript ( *inis ) ) {
							delete nb;
							pdi.bdyScripts[ idx ].sHndl = 0;	// error
						}
						else
							pdi.bdyScripts[ idx ].sHndl = nb;
						// presets variables indexes
						if ( pdi.bdyScripts[ idx ].sHndl ) {
							int bsi = 0, vari = 0;
							pdi.bdyScripts[ idx ].rso = & ( *it );
							for ( QStringList::ConstIterator var = inis->data.variables.begin(); var != inis->data.variables.end(); ++var ) {
								if ( ( vari = getCounterIndex ( pdi.kit, ( *var ), pdi.bdyScripts[ idx ].tagsIndex[ bsi ] ) ) >= 0 )
									pdi.bdyScripts[ idx ].varsIndex[ bsi++ ] = vari;
							}

							pdi.pdMap[ pdi.idn ].source = pdScript;
							pdi.pdMap[ pdi.idn ].index = idx;
							( *export_d ) [ rowCount ].exportCol[ col_0_count ].typeCol = exdt_Counter;
							( *export_d ) [ rowCount ].exportCol[ col_0_count++ ].valueCol = CORBA::string_dup ( ( *it ).data.tag );
							lout1 << "** script [" << ( *it ).data.tag << "]" << endl;;
							++pdi.idn;
						}
						else
							lout << "!! error on script for [" << ( *it ).data.tag << "]" << endl;
					}
				}
				else if ( ( *it ).data.u.body.source == ReportSchemaObj::CounterKit && ( *it ).data.u.body.idCounter > 0 ) {
					pdi.pdMap[ pdi.idn ].source = pdCounter;
					pdi.pdMap[ pdi.idn ].index = ( *it ).data.u.body.idCounter - 1;
					( *export_d ) [ rowCount ].exportCol[ col_0_count ].typeCol = exdt_Counter;
					( *export_d ) [ rowCount ].exportCol[ col_0_count++ ].valueCol = CORBA::string_dup ( ( *it ).data.tag );
					lout1 << "** counter [" << ( *it ).data.tag << "]" << endl;
					++pdi.idn;
				}

				++idx;
			}

			pdi.pdMap[ pdi.idn ].source = pdUnused;		// close map
			pdi.pdMap[ pdi.idn ].index = -1;
		}
	}
	lout1 << endl << "counters/scripts assigned." << endl;

	int upperlim = rec_in_file + 1;

	export_d->length ( upperlim );		// total number of rows to pass back
	lout << "Will pass back " << upperlim << " rows." << endl;
	lout3 << "col_in_a_row=" << col_in_a_row << endl;

	PFRecord pfr;

	// Assign Header Row
	rowCount++;

	// set format key by user request
	pvf.setAllUserKey();
	if ( sortIndex >= 0 )
		pvf.setSortOnDataCol ( sortIndex, percIndex );

	// load btree & rewind cursors
	pvf.loadBtree();
	pvf.rewind();
	pdi.key_n = pvf.numOfKeysInTree();

	while ( pvf.getRow ( pfr ) == false ) {
		( *export_d ) [ rowCount ].exportCol.length ( col_in_a_row );
		copyExportRecordToCorba ( &pfr, ( *export_d ) [ rowCount ], pdi );
		++rowCount;
		if ( rowCount >= upperlim )
			break;
	}

	lout1 << "found/extracted " << rowCount << " records" << endl;

	if ( rowCount != upperlim ) {
		lout << "!!! expected " << upperlim << " rows but got " << rowCount << " instead. Something wrong..." << endl;
	}

	exportData = export_d;
	lout << "** data transferred. [" << rowCount << "] lines out of " << export_d->length() << " CORBA vector." << endl;

	destroyPDI ( pdi );

	timeout.setTimeOut ( LONG_WATCHDOG_TIMEOUT );
	timeout.setiActive ( true );		// set long lifetime watchdog

	return ( CORBA::Boolean ) true;
}

void mdm_server_server_impl::copyExportRecordToCorba ( PFRecord * pfr, EXPORT_ROW & pdn, PivotDataNode_private & pdi )
{

	QString qsbuf, curkey;
	QString full_key, filler;
	int tk = 0;

	for ( int k = 0; k < pdi.r_key_n; k++ ) {
		// 		if ( pdi.enabled_keys[k] == false ) {	// skip disabled keys
		// 			filler.truncate ( 0 );
		// 			full_key += filler.fill ( ' ', pdi.defineRelation->getRelationComponent ( k )->length );
		// 			continue;
		// 		}
		full_key += pfr->pfi_key[ tk ].name;
		++tk;
	}

	// retrieve keys for this node
	tk = 0;
	for ( int k = 0; k < pdi.r_key_n; k++ ) {
		// 		if ( pdi.enabled_keys[k] == false ) {	// skip disabled keys
		// 			continue;
		// 		}

		qsbuf = curkey = pfr->pfi_key[ tk ].name;

		if ( pdi.keyPlugin[ k ] > 0 ) {		// managed by plugin
			char ikbuf[ 129 ];
			DEHandlerWorkerAPIData pld;


			qstrncpy ( ikbuf, qPrintable ( qsbuf ), 129 );
			pld.operation = teOp_ReportProduction;
			pld.inKeyValue = ikbuf;
			pld.offset = pdi.defineRelation->getRelationComponent ( k ) ->id;
			pld.CDRr = ( DRImageHandler * ) & full_key;
			pdi.keyPlugin[ k ] ->pluginWorker ( ( void * ) & pld );

			if ( pld.status == teStatus_Error )
				qsbuf = "(P!)";
			else
				qsbuf = pld.inKeyValue;
		}
		else if ( pdi.keyScript[ k ] > 0 ) {		// managed by script
			QString result;
			if ( !callBasic_TE ( qsbuf, full_key, pdi.keyScript[ k ], result ) )
				qsbuf = "(S!)";
			else
				qsbuf = result;
		}

		if ( pdi.keyHelperPresent[ k ] ) {
			QString * htxt = helpDict[ pdi.defineRelation->getRelationComponent ( k ) ->desc ] ->items[ curkey ];
			if ( htxt ) {
				qsbuf = ( *htxt );
			}
		}
		else {
			QString itxt = checkInternalHelp ( acc, pdi.defineRelation->getRelationComponent ( k ) ->desc, curkey );
			if ( itxt.length() > 0 )
				qsbuf = itxt;
		}

		pdn.exportCol[tk].typeCol = exdt_String;
		pdn.exportCol[tk].valueCol = ( qsbuf.length() > 0 ) ? CORBA::string_dup ( qPrintable ( qsbuf ) ) : CORBA::string_dup ( "" );
		++tk;
	}

	// store data now
	double cellValue;
	QString cellString;
	int c_index, c_type;

	for ( int m = 0; m < pdi.idn; m++ ) {						// scan pivot map

		switch ( pdi.pdMap[ m ].source ) {
			case pdScript:
				cellString = "0.0";
				if ( !callBasic_BODY ( pdi.bdyScripts, pdi.kit, pdi.pdMap[ m ].index, pfr, pdi.int_n, pdi.dbl_n, qsbuf, 4 ) ) {
					cellString = "-1.0";
				}
				else {
					cellString = qsbuf;
				}
				// 										lout << "S -> [" << cellValue << "]" << endl;
				pdn.exportCol[ tk ].typeCol = exdt_Double;
				pdn.exportCol[ tk ].valueCol = CORBA::string_dup ( qPrintable ( qsbuf ) );
				break;
			case pdCounter:
				c_index = pdi.kit->data.counterKit[ pdi.pdMap[ m ].index ].counterIndex;
				c_type = getCounterType ( c_index );
				cellValue = getCounterValue ( pdi.kit, pdi.int_n, pdi.dbl_n, c_index, pfr );
				// percent or absolute ?
				if ( pdi.kit->data.counterKit[ pdi.pdMap[ m ].index ].percentOf > 0 && pdi.isPercent ) {
					double valueRef = getCounterValue ( pdi.kit, pdi.int_n, pdi.dbl_n, pdi.kit->data.counterKit[ pdi.pdMap[ m ].index ].percentOf, pfr );
					if ( valueRef != 0 )
						cellValue = ( cellValue * 100.0 ) / valueRef;
					c_type = DOUBLE_TYPE;
				}
				cellString = QString::number ( cellValue );
				if ( c_type == DOUBLE_TYPE ) {
					pdn.exportCol[ tk ].typeCol = exdt_Double;
					pdn.exportCol[ tk ].valueCol = CORBA::string_dup ( qPrintable ( cellString ) );
				}
				else {
					pdn.exportCol[ tk ].typeCol = exdt_Int;
					pdn.exportCol[ tk ].valueCol = CORBA::string_dup ( qPrintable ( cellString ) );
				}
				// 				lout3 << "C -> [" << cellValue << "]" << endl;
				break;
		}

		++tk;
	}

	// 	lout3 << "Inserted " << tk << " elements in a row" << endl;

	return ;
}


CORBA::Boolean mdm_server_server_impl::getPivotData ( const char* name, CORBA::Long startLine, CORBA::Long maxLines, PIVOTREQ& pvreq,
                CORBA::Long_out totalRows, PivotDataNodeSeq_out pivot, IDListSeq_out IDCounters ) throw ( CORBA::SystemException )
{
	PivotDataNodeSeq * pivot_d = new PivotDataNodeSeq;
	PivotDataNode_private pdi;
	int rec_in_file = 0;
	QString cnfName;
	int relID;
	int nodeIndex;
	QString elabDate;
	int reportID;
	int analysisID;

	pdi.int_n = 0;
	pdi.dbl_n = 0;
	pdi.key_n = 0;

	timeout.setTimeOut ( WATCHDOG_TIMEOUT );
	timeout.setiActive ( true );	// reset watchdog
	if ( timeout.isRunning() == false )
		timeout.start();	// watchdog active

	lout << "getPivotData: file is     : [" << name << "]" << endl;
	PivotFile pvf ( pivotBaseDir(), name );
	// 	pvf.setDataDir(pivotBaseDir());
	// 	pvf.setFilePrefix(name);

	if ( pvf.readInfo() ) {
		lout << "**** CANNOT ACCESS PIVOT ANALYSIS INFO FILE: " << pvf.getFilePrefix() << " in " << pvf.getDataDir() << endl;
		return ( CORBA::Boolean ) false;
	}

	UserParameter.clear();

	// retrieve analysis informations
	rec_in_file = pvf.getInfoLenght();
	pdi.int_n = pvf.getInfoInts();
	pdi.dbl_n = pvf.getInfoDbls();
	cnfName = pvf.getInfoName();
	relID = pvf.getInfoRelation();
	nodeIndex = pvf.getInfoSwitching();
	elabDate = pvf.getInfoDate();
	reportID = pvf.getInfoSchema();
	analysisID = pvf.getInfoAnalysis();
	pdi.isPercent = pvreq.percent;
	UserParameter.data.Reserved = pdi.reserved = pvf.getReserved();
	pdi.withPreSort = pvf.getInfoWithPreSort();
	int sortIndex = ( pvreq.elementToSort >= 0 ) ? pvreq.elementToSort : pvf.getInfoSortDataCol();
	int percIndex = pvf.getInfoSortPercCol();
	UserParameter.data.Filters = pvf.getInfoRestrictions();
	int * pv_ffRelation = pvf.getFfRelation();
	for ( int i = 0; i < MAX_DIMENSION; i++ )
		UserParameter.data.ffRelation[ i ] = pv_ffRelation[ i ];

	bool justInfo = ( ( maxLines < 0 ) || ( pvreq.enabledColumns[ 0 ] < 0 ) );
	if ( maxLines <= 0 || maxLines > rec_in_file )
		maxLines = rec_in_file;
	if ( startLine < 0 || startLine > maxLines )
		startLine = 0;
	totalRows = rec_in_file;		// pass back records in file
	int readKeyNo = pvreq.sortColumn;
	int focusLen = qstrlen ( pvreq.focusOn );

	lout << "start rec # " << startLine << " max lines = " << maxLines << endl;
	lout << "Re-use data = " << pvreq.last_row << endl;

	UserParameter.data.ElaborationData << elabDate;
	UserParameter.data.Relation = relID;

	lout << "getPivotData: # records   : [" << rec_in_file << "] of [" << pdi.int_n << "] ints and [" << pdi.dbl_n << "] doubles " << endl;
	lout << "getPivotData: config file : [" << cnfName << "]" << endl;
	lout << "getPivotData: relation    : [" << relID << "]" << endl;
	lout << "getPivotData: switching   : [" << nodeIndex << "]" << endl;
	lout << "getPivotData: elab. date  : [" << elabDate << "]" << endl;
	lout << "getPivotData: id schema   : [" << reportID << "]" << endl;
	lout << "getPivotData: id analysis : [" << analysisID << "]" << endl;
	lout << "getPivotData: presort     : [" << ( ( pdi.withPreSort ) ? "Yes" : "No" ) << "]" << endl;
	lout << "getPivotData: focus col.  : [" << readKeyNo << "]" << endl;
	lout << "getPivotData: descending  : [" << ( ( pvreq.descending ) ? "Yes" : "No" ) << "]" << endl;
	lout << "getPivotData: # of restr. : [" << UserParameter.data.Filters.count() << "]" << endl;

	if ( pvf.openForRead() ) {
		lout << "**** Cannot access pivot data for read. ****" << endl;
		return ( CORBA::Boolean ) false;
	}
	lout << "pivot data opened for read" << endl;

	if ( activeConfigName.length() == 0 ) {
		activeConfigName = cnfName;
		lout << "loading: " << activeConfigName << endl;
	}

	if ( !confLoaded ) {
		loadConfiguration ( activeConfigName );
		confLoaded = true;
	}
	lout << "loaded configuration." << endl;
	if ( helpDict.count() == 0 )  		// scan helper plugins
		registerHelperPlugins();
	lout << "helper plugins registered." << endl;

	DefineRelation defineRelation;
	pdi.defineRelation = &defineRelation;
	defineRelation.setElementInRelation ( &acc, relID, UserParameter.data.ffRelation );
	int relationSize = defineRelation.getDimension();
	acc.relationInterface->setDataElementReference ( acc.dataElementInterface );
	pdi.r_key_n = pdi.key_n = relationSize;
	pdi.enabled_keys = pvreq.enabledKeys;

	lout << "getPivotData: rel. string : [" << acc.relationInterface->decodeRel ( UserParameter.data.Relation, UserParameter.data.ffRelation ) << "]" << endl;

	pdi.keyHelperPresent = new bool [ relationSize ];
	pdi.keyScript = new AdamsBasic * [ relationSize ];
	pdi.keyPlugin = new PluginImpl * [ relationSize ];
	for ( int i = 0; i < relationSize; i++ ) {	// clean dynamics
		pdi.keyScript[ i ] = 0;
		pdi.keyPlugin[ i ] = 0;
	}
	for ( int i = 0; i < relationSize; i++ ) {
		if ( justInfo )
			continue;
		DescHelperAPIData * hstr = helpDict[ defineRelation.getRelationComponent ( i ) ->desc ];
		if ( hstr ) {
			hstr->helpTag = defineRelation.getRelationComponent ( i ) ->desc;
			hstr->nodeIndex = nodeIndex;
			hstr->handler->pluginWorker ( hstr );
			pdi.keyHelperPresent[ i ] = hstr->loaded;
			lout3 << "initialized tag " << hstr->helpTag << endl;
		}
		else
			pdi.keyHelperPresent[ i ] = false;

		DataElement * te = acc.dataElementInterface->get ( defineRelation.getRelationComponent ( i ) ->id );
		Script * inis = 0;
		bool hasPlugin = false;
		if ( te ) {
			if ( te->data.scripts[ Script::saReportProduction ] > 0 )
				inis = acc.scriptInterface->get ( te->data.scripts[ Script::saReportProduction ] );
			hasPlugin = ( te->data.idPlugin > 0 );
		}
		if ( hasPlugin ) {
			DEHandlerWorkerAPIData pld;
			lout << "element " << te->data.idElement << " has plugin" << endl;
			// handled by plugin, so recall it
			pdi.keyPlugin[ i ] = getHandlerPluginInstance ( te );
			if ( pdi.keyPlugin[ i ] > 0 ) {
				// 				lout << "got plugin instance for key " << i << endl;
				if ( !pdi.keyPlugin[ i ] ->startup ( ( void * ) & pld ) ) {
					lout << "NtmMasterServer : getPivotData : Cannot startup plugin #" <<
					     te->data.idPlugin << " needed from Element #" << te->data.idElement << endl;
					delete pdi.keyPlugin[ i ];
					pdi.keyPlugin[ i ] = 0;
				}
				else {
					if ( pld.useFor[teOp_ReportProduction] == false ) {
						// unsupported by plugin's functionality
						lout << "plugin element " << te->data.idElement << " report usage unsupported" << endl;
						delete pdi.keyPlugin[i];
						pdi.keyPlugin[i] = 0;
						hasPlugin = false;
					}
					else {
						// call plugin to setup it (eventually)
						pld.element = te;
						pld.operation = teOp_InitPhase;
						pdi.keyPlugin[i]->pluginWorker ( ( void * ) &pld );
						if ( pld.status == teStatus_Error ) {
							lout << "NtmMasterServer : getPivotData : Plugin execution (InitPhase) was unsucessfull for element #" <<  te->data.idElement << endl;
							delete pdi.keyPlugin[i];
							pdi.keyPlugin[i] = 0;
						}
					}
				}
			}
		}
		if ( inis && !hasPlugin ) {	// plugins always have precedence
			AdamsBasic * nb = new AdamsBasic;
			nb->setReportMode ( AdamsBasic::nbToLogFile );

			if ( nb->getStatus() != AdamsBasic::nbSuccess ) {
				delete nb;
				pdi.keyScript[ i ] = 0;	// error
			}
			else if ( !nb->setProgramScript ( *inis ) ) {
				delete nb;
				pdi.keyScript[ i ] = 0;	// error
			}
			else
				pdi.keyScript[ i ] = nb;
		}
	}
	lout << "helpers initialized." << endl;

	// evaluate rows to pass to client, both counters and scripts are taken.
	int cntKitId;

	Analysis * analysis = acc.analysisInterface->get ( analysisID );
	if ( analysis ) {
		cntKitId = analysis->data.countersKitId;
	}
	else {
		lout << "getPivotData : cannot get analisys #" << analysisID << endl;
	}
	pdi.kit = acc.counterInterface->get ( cntKitId );
	if ( pdi.kit == 0 ) {
		lout << "getPivotData : cannot get counters kit #" << cntKitId << endl;
	}

	if ( sortIndex >= 0 && percIndex < 0 ) {	// fix unassigned percIndex
		percIndex = acc.counterInterface->getPercentOf ( *pdi.kit, sortIndex );
	}

	ReportSchema * schema = acc.reportInterface->get ( reportID );
	IDListSeq * cntids = new IDListSeq;
	pdi.idn = 0;

	if ( schema ) {

		if ( schema->bdyV.count() > 0 ) {
			pdi.bdyScripts = new BodyScripts [ schema->bdyV.count() ];	// how many ?
			pdi.pdMap = new PivotDataMap_private [ schema->bdyV.count() ];
			int idx = 0;

			for ( RSOVectorIterator it = schema->bdyV.begin(); it != schema->bdyV.end(); ++it ) {
				// some init ?
				Script * inis = 0;
				pdi.bdyScripts[ idx ].sHndl = 0;

				if ( ( *it ).data.u.body.source == ReportSchemaObj::Script && ( *it ).data.u.body.idScript > 0 ) {
					inis = acc.scriptInterface->get ( ( *it ).data.u.body.idScript );
					if ( inis ) {
						AdamsBasic * nb = new AdamsBasic;
						nb->setReportMode ( AdamsBasic::nbToLogFile );

						if ( nb->getStatus() != AdamsBasic::nbSuccess ) {
							delete nb;
							pdi.bdyScripts[ idx ].sHndl = 0;	// error
						}
						else if ( !nb->setProgramScript ( *inis ) ) {
							delete nb;
							pdi.bdyScripts[ idx ].sHndl = 0;	// error
						}
						else
							pdi.bdyScripts[ idx ].sHndl = nb;
						// presets variables indexes
						if ( pdi.bdyScripts[ idx ].sHndl ) {
							int bsi = 0, vari = 0;
							pdi.bdyScripts[ idx ].rso = & ( *it );
							for ( QStringList::ConstIterator var = inis->data.variables.begin(); var != inis->data.variables.end(); ++var ) {
								if ( ( vari = getCounterIndex ( pdi.kit, ( *var ), pdi.bdyScripts[ idx ].tagsIndex[ bsi ] ) ) >= 0 )
									pdi.bdyScripts[ idx ].varsIndex[ bsi++ ] = vari;
							}

							pdi.pdMap[ pdi.idn ].source = pdScript;
							pdi.pdMap[ pdi.idn ].index = idx;
							cntids->length ( pdi.idn + 1 );
							( *cntids ) [ pdi.idn ] = CORBA::string_dup ( ( *it ).data.tag );
							lout << "** script [" << ( *it ).data.tag << "]" << endl;;
							++pdi.idn;
						}
						else
							lout << "!! error on script for [" << ( *it ).data.tag << "]" << endl;
					}
				}
				else if ( ( *it ).data.u.body.source == ReportSchemaObj::CounterKit && ( *it ).data.u.body.idCounter > 0 ) {
					pdi.pdMap[ pdi.idn ].source = pdCounter;
					pdi.pdMap[ pdi.idn ].index = ( *it ).data.u.body.idCounter - 1;
					cntids->length ( pdi.idn + 1 );
					( *cntids ) [ pdi.idn ] = CORBA::string_dup ( ( *it ).data.tag );
					lout << "** counter [" << ( *it ).data.tag << "]" << endl;
					++pdi.idn;
				}

				++idx;
			}

			pdi.pdMap[ pdi.idn ].source = pdUnused;		// close map
			pdi.pdMap[ pdi.idn ].index = -1;
		}
	}
	lout << endl << "counters/scripts assigned." << endl;

	IDCounters = cntids;				// pass back counters id

	pdi.pseqlen = 0;
	pdi.pvreq = &pvreq;
	int upperlim = maxLines - startLine;

	if ( !justInfo ) {
		pivot_d->length ( upperlim );		// total number of rows to pass back
		lout << "Will pass back " << upperlim << " rows." << endl;
		for ( int p = 0; p < CNT_NUM; p++ ) {
			if ( pvreq.enabledColumns[ p ] < 0 )
				break;
			else
				++pdi.pseqlen;		// size of requested params sequence
		}
	}

	PFRecord pfr;
	int rowCount = 0;

	// 	QString uk;
	// 	for (int i = 0; i < MAX_PIVOT_KEYS; i++)
	// 		if (pvreq.enabledKeys[i] == false) uk += QString::number(i) + ":off ";
	// 		else uk += QString::number(i) + ":on ";
	// 	lout << uk << endl;

	if ( justInfo ) {			// no need further ops...
		lout << "justInfo request..." << endl;
		if ( hasHiddenColumns ( pvreq.enabledKeys ) || focusLen ) { 	// recalc total rows in case of hidden columns
			pvf.setUserKey ( pvreq.enabledKeys );
			if ( focusLen )
				pvf.setFocusFilter ( pvreq.focusOn, readKeyNo );
			pvf.loadBtree();
			totalRows = pvf.count();
			lout << "Has data variations... " << totalRows << " is new count." << endl;
		}
		pivot = pivot_d;
		destroyPDI ( pdi );
		return ( CORBA::Boolean ) true;
	}

	// direction ?
	if ( pvreq.descending ) {
		lout << "REVERSE direction" << endl;
		pvf.setDirection ( PivotFile::reverse );
	}

	// set format key by user request
	pvf.setUserKey ( pvreq.enabledKeys );
	if ( focusLen )
		pvf.setFocusFilter ( pvreq.focusOn, readKeyNo );
	if ( readKeyNo >= 0 )
		pvf.setSortOnKey ( readKeyNo );
	if ( sortIndex >= 0 )
		pvf.setSortOnDataCol ( sortIndex, percIndex );
	// load btree & rewind cursors
	pvf.loadBtree();
	pvf.rewind();
	pdi.key_n = pvf.numOfKeysInTree();


	if ( startLine != 0 ) {
		lout << "skipping " << startLine << " records..." << endl;
		if ( pvf.seekRowNum ( startLine ) ) {
			lout << "!!! SKIP error..." << endl;
			destroyPDI ( pdi );
			pivot = pivot_d;
			return ( CORBA::Boolean ) false;
		}
	}

	while ( pvf.getRow ( pfr ) == false ) {
		copyPFRecordToCorba ( &pfr, ( *pivot_d ) [ rowCount ], pdi );
		++rowCount;
		if ( rowCount >= upperlim )
			break;
	}

	lout << "found/extracted " << rowCount << " records" << endl;

	// 	pvreq.last_row = rowCount + startLine;
	pvreq.last_row = 1;				// remember first run
	pvreq.last_key = CORBA::string_dup ( "" );	// unused

	if ( rowCount != upperlim ) {
		lout << "!!! expected " << upperlim << " rows but got " << rowCount << " instead. Something wrong..." << endl;
		destroyPDI ( pdi );
		pivot = pivot_d;
		return ( CORBA::Boolean ) false;
	}

	pivot = pivot_d;
	lout << "** data transferred. [" << rowCount << "] lines out of " << pivot_d->length() << " CORBA vector." << endl;

	destroyPDI ( pdi );

	timeout.setTimeOut ( LONG_WATCHDOG_TIMEOUT );
	timeout.setiActive ( true );		// set long lifetime watchdog

	return ( CORBA::Boolean ) true;
}

void mdm_server_server_impl::destroyPDI ( PivotDataNode_private & pdi )
{
	// clean-up
	delete [] pdi.keyHelperPresent;
	delete [] pdi.pdMap;
	delete [] pdi.bdyScripts;
	for ( int i = 0; i < pdi.r_key_n; i++ ) {	// clean dynamics
		if ( pdi.keyScript[ i ] > 0 )
			delete pdi.keyScript[ i ];
		if ( pdi.keyPlugin[ i ] > 0 )
			delete pdi.keyPlugin[ i ];
	}
	if ( pdi.defineRelation->getDimension() ) {
		delete [] pdi.keyScript;
		delete [] pdi.keyPlugin;
	}
}

bool mdm_server_server_impl::callBasic_TE ( const QString & keyPart, const QString & currentKey, AdamsBasic * nb, QString & result )
{
	if ( nb == 0 ) {
		lout << "NtmMasterServer : mdm_server_server_impl::callBasic_TE : Called with non-instantiated AdamsBasic reference." << endl;
		return false;
	}
	const Script * scr = nb->getOriginatingScript();
	if ( scr == 0 ) {
		lout << "NtmMasterServer : mdm_server_server_impl::callBasic_TE : Called with no reference to originating script." << endl;
		return false;
	}
	// valorize var list
	bool justifyAtLeft = false;

	nb->setValue ( "keypart", keyPart );
	nb->setValue ( "currentkey", currentKey );
	nb->setValue ( "pivot_running", ( long ) 1 );
	// Execute
	if ( !nb->execute() ) {
		return false;
	}
	// Get result
	if ( !nb->getResult ( result ) )
		return false;

	return true;
}

void mdm_server_server_impl::copyPFRecordToCorba ( PFRecord * pfr, PIVOTDATANODE & pdn, PivotDataNode_private & pdi )
{

	pdn.pkeySeq.length ( pdi.key_n );

	QString qsbuf, curkey;
	QString full_key, filler;
	int tk = 0;

	for ( int k = 0; k < pdi.r_key_n; k++ ) {
		if ( pdi.enabled_keys[k] == false ) {	// skip disabled keys
			filler.truncate ( 0 );
			full_key += filler.fill ( ' ', pdi.defineRelation->getRelationComponent ( k )->length );
			continue;
		}
		full_key += pfr->pfi_key[ tk ].name;
		++tk;
	}

	// retrieve keys for this node
	tk = 0;
	for ( int k = 0; k < pdi.r_key_n; k++ ) {
		if ( pdi.enabled_keys[k] == false ) {	// skip disabled keys
			continue;
		}

		qsbuf = curkey = pfr->pfi_key[ tk ].name;

		if ( pdi.keyPlugin[ k ] > 0 ) {		// managed by plugin
			char ikbuf[ 129 ];
			DEHandlerWorkerAPIData pld;


			qstrncpy ( ikbuf, qPrintable ( qsbuf ), 129 );
			pld.operation = teOp_ReportProduction;
			pld.inKeyValue = ikbuf;
			pld.offset = pdi.defineRelation->getRelationComponent ( k ) ->id;
			pld.CDRr = ( DRImageHandler * ) & full_key;
			pdi.keyPlugin[ k ] ->pluginWorker ( ( void * ) & pld );

			if ( pld.status == teStatus_Error )
				qsbuf = "(P!)";
			else
				qsbuf = pld.inKeyValue;
		}
		else if ( pdi.keyScript[ k ] > 0 ) {		// managed by script
			QString result;
			if ( !callBasic_TE ( qsbuf, full_key, pdi.keyScript[ k ], result ) )
				qsbuf = "(S!)";
			else
				qsbuf = result;
		}

		if ( pdi.keyHelperPresent[ k ] ) {
			QString * htxt = helpDict[ pdi.defineRelation->getRelationComponent ( k ) ->desc ] ->items[ curkey ];
			if ( htxt ) {
				qsbuf = ( *htxt );
			}
		}
		else {
			QString itxt = checkInternalHelp ( acc, pdi.defineRelation->getRelationComponent ( k ) ->desc, curkey );
			if ( itxt.length() > 0 )
				qsbuf = itxt;
		}

		pdn.pkeySeq[ tk ] = ( qsbuf.length() > 0 ) ? CORBA::string_dup ( qPrintable ( qsbuf ) ) : CORBA::string_dup ( "" );
		++tk;
	}

	// store data now
	pdn.ParamSeq.length ( pdi.pseqlen );
	double cellValue;
	int c_index, c_type;

	int p = 0;
	for ( int m = 0; m < pdi.idn; m++ ) {						// scan pivot map
		bool isok = false;
		for ( int j = 0; j < pdi.pseqlen; j++ ) {
			if ( pdi.pvreq->enabledColumns[ j ] == m ) {
				isok = true;
				break;
			}
		}
		if ( !isok )
			continue;

		switch ( pdi.pdMap[ m ].source ) {
			case pdScript:
				cellValue = 0.0;
				if ( !callBasic_BODY ( pdi.bdyScripts, pdi.kit, pdi.pdMap[ m ].index, pfr, pdi.int_n, pdi.dbl_n, qsbuf, 4 ) ) {
					cellValue = -1.0;
				}
				else {
					cellValue = qsbuf.toDouble();
				}
				// 										lout << "S -> [" << cellValue << "]" << endl;
				pdn.ParamSeq[ p ].Type = 1;
				pdn.ParamSeq[ p ].Value.Decimal ( cellValue );
				break;
			case pdCounter:
				c_index = pdi.kit->data.counterKit[ pdi.pdMap[ m ].index ].counterIndex;
				c_type = getCounterType ( c_index );
				cellValue = getCounterValue ( pdi.kit, pdi.int_n, pdi.dbl_n, c_index, pfr );
				// percent or absolute ?
				if ( pdi.kit->data.counterKit[ pdi.pdMap[ m ].index ].percentOf > 0 && pdi.isPercent ) {
					double valueRef = getCounterValue ( pdi.kit, pdi.int_n, pdi.dbl_n, pdi.kit->data.counterKit[ pdi.pdMap[ m ].index ].percentOf, pfr );
					if ( valueRef != 0 )
						cellValue = ( cellValue * 100.0 ) / valueRef;
					c_type = DOUBLE_TYPE;
				}
				if ( c_type == DOUBLE_TYPE ) {
					pdn.ParamSeq[ p ].Type = DOUBLE_TYPE;
					pdn.ParamSeq[ p ].Value.Decimal ( cellValue );
				}
				else {
					pdn.ParamSeq[ p ].Type = INT_TYPE;
					pdn.ParamSeq[ p ].Value.UnsignedInteger ( ( unsigned long ) cellValue );
				}
				// 								 		lout << "C -> [" << cellValue << "]" << endl;
				break;
		}

		++p;
	}

	return ;
}

double mdm_server_server_impl::getCounterValue ( Counters * kit, int numIntCounters, int numDblCounters, int idx, PFRecord * cntRow )
{
	switch ( getCounterType ( idx ) ) {
		case INT_TYPE:
			if ( idx > numIntCounters ) {
				lout << "mdm_server_server_impl::getCounterValue : WARNING : Out of INTEGERS counters bounds index: " << idx << endl;
				return -1.0;
			}
			else
				return ( double ) cntRow->cnt_int[ idx - 1 ];
		case DOUBLE_TYPE:
			idx -= MAX_INT_COUNTERS;
			if ( idx > numDblCounters ) {
				lout << "mdm_server_server_impl::getCounterValue : WARNING : Out of FLOATING POINT counters bounds index: " << idx << endl;
				return -1.0;
			}
			else
				return cntRow->cnt_dbl[ idx - 1 ];
		default:
			lout << "mdm_server_server_impl::getCounterValue : WARNING : Misaligned counter index: " << idx << endl;
			return -1.0;
	}
}

bool mdm_server_server_impl::callBasic_BODY ( BodyScripts * bdyScripts, Counters * kit, int bdyidx, PFRecord * cntRow,
                int numI, int numD, QString & result, int ndec )
{
	if ( bdyScripts[ bdyidx ].sHndl == 0 ) {
		lout << "mdm_server_server_impl::callBasic_BODY : Called with non-instantiated AdamsBasic reference." << endl;
		return false;
	}

	int vari = 0, i;
	while ( ( i = bdyScripts[ bdyidx ].varsIndex[ vari ] ) >= 0 ) {
		int t = bdyScripts[ bdyidx ].tagsIndex[ vari ];
		bdyScripts[ bdyidx ].sHndl->setValue ( QString ( kit->data.counterKit[ t ].tag ).toLower(),
		                                       ( double ) getCounterValue ( kit, numI, numD, i, cntRow ) );
		//		lout << "setto valore: " << QString(kit->data.counterKit[t].tag).lower() << " a " << (double)getCounterValue(i, cntRow) << endl;
		++vari;
	}
	bdyScripts[ bdyidx ].sHndl->setValue ( "pivot_running", ( long ) 1 );
	// Execute
	if ( !bdyScripts[ bdyidx ].sHndl->execute() ) {
		lout << "ERRORE EXEC BASIC" << endl;
		return false;
	}
	// Get result
	long iresult;
	double dresult;
	if ( bdyScripts[ bdyidx ].sHndl->getResult ( dresult ) ) {
		result = QString::number ( dresult, 'f', ndec );
		return true;
	}
	else if ( bdyScripts[ bdyidx ].sHndl->getResult ( iresult ) ) {
		result = QString::number ( iresult );
		return true;
	}
	else if ( !bdyScripts[ bdyidx ].sHndl->getResult ( result ) ) {
		//		lout << "ERRORE GETRESULT BASIC" << endl;
		return false;
	}

	return true;
}



QString mdm_server_server_impl::checkInternalHelp ( AdamsCompleteConfig & config, const QString & tag, const QString & id )
{
	DataElement * te = config.dataElementInterface->getByTag ( tag );
	if ( te == 0 ) {
		return QString ( "" );
	}
	if ( te->data.LengthInRelation == 0 || ( te->data.guiObject != DataElement::ValueList && te->data.guiObject != DataElement::OptionList ) ) {
		return QString ( "" );
	}

	QString key;
// 	QString fmt = QString ( "%0" ) + QString::number ( te->data.LengthInRelation ) + QString ( "d" );

	for ( int i = 0; i < MAX_OPTIONS; i++ ) {
		if ( qstrlen ( te->data.valueListLabel[ i ] ) > 0 ) {
// 			key.sprintf ( fmt, QString::number ( te->data.valueList[ i ] ).toInt() );
			key = QString ( "%1" ).arg ( QString::number ( te->data.valueList[ i ] ), te->data.LengthInRelation, '0' );
			if ( key == id ) {
				return QString ( te->data.valueListLabel[ i ] );
			}
		}
		else
			break;
	}

	return QString ( "" );
}


bool mdm_server_server_impl::loadConfiguration ( const QString & conf_name )
{
	APIDB_ConfigTable conf_db;
	AdamsCompleteConfig * acc_ptr;

	acc_ptr = conf_db.loadConfig ( conf_name );
	if ( ! acc_ptr ) {
		lout << "*** Unable to load configuration: " << conf_name << endl;
		return false;
	}

	acc = *acc_ptr;
	delete acc_ptr;

	return true;
}


CORBA::Boolean mdm_server_server_impl::verifyScript ( const SCRIPT_VALIDATE& scriptData, ERROR_TEXT_out errorText ) throw ( CORBA::SystemException )
{
	lout << ">> verifyScript" << endl;
	static QString basicFile;
	bool debug = true;
	QString config_fname;

	basicFile = cnfparser.parQTxtGetValue ( "BasicConfigFile", "MDM" );

	if ( basicFile.isEmpty() ) {
		QString emsg = "ENVIRON init failed: Null BasicConfigFile parameter from : ";
		emsg += cnfparser.getConfigFilePath() + "\n";

		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
		if ( debug )
			lout << emsg << endl;
		return ( CORBA::Boolean ) false;
	}

	basicFile = cnfparser.locateUserFile ( basicFile );

	if ( basicFile.isEmpty() ) {
		QString emsg = "ENVIRON init failed: Unable to locate AdamsBasic configuration file : ";
		emsg += cnfparser.getConfigFilePath() + "\n";

		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
		if ( debug )
			lout << emsg << endl;
		return ( CORBA::Boolean ) false;
	}

	lout << "BASICFILE=" << basicFile << endl;

	AdamsBasic * nb = new AdamsBasic;
	nb->setReportMode ( AdamsBasic::nbToExternal );

	if ( nb->getStatus() != 0 ) {
		QString emsg = "INTERNAL ERROR activating AdamsBasic engine !!";

		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
		if ( debug )
			lout << emsg << endl;
		return ( CORBA::Boolean ) false;
	}

	// Variable declarations
	for ( int i = 0; i < SCRIPT_MAX_VAR; i++ ) {
		if ( qstrlen ( scriptData.variables[ i ] ) == 0 )
			break;
		nb->addInitVariable ( QString ( scriptData.variables[ i ] ).toLower(), DRF_long );
	}
	nb->addInitVariable ( QString ( scriptData.resultVariableName ).toLower(), DRF_long );

	// Assign values as of user input
	QStringList userLines = QString(scriptData.variablesText).split ( '\n', QString::KeepEmptyParts );
	QString varText;
	QStringList varVars;
	for ( QStringList::Iterator it = userLines.begin(); it != userLines.end(); ++it ) {
		varText = ( *it ).simplified();
		if ( !varText.isEmpty() ) {
			varVars << varText.toLower();
		}
	}
	varVars << "\n";

	// Compose and sets program String
	QString prgString = varVars.join ( "\n" );
	prgString += QString ( scriptData.scriptText ).toLower();

	if ( debug ) {
		lout << ( QString ( "resultVariableName=" ) + scriptData.resultVariableName + "\n" ) << endl;
		lout << ( QString ( "varVars=" ) + varVars.join ( "\n" ) + "\n" ) << endl;
		lout << ( QString ( "scriptText=" ) + scriptData.scriptText + "\n" ) << endl;
		lout << ( QString ( "prgString=" ) + prgString + "\n" ) << endl;
	}

	if ( !nb->setProgramString ( prgString ) ) {
		QString emsg = "ERROR setProgramString: invalid script Text\nor variables initalization.";

		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
		if ( debug )
			lout << emsg << endl;
		return ( CORBA::Boolean ) false;
	}

	nb->setResultName ( QString ( scriptData.resultVariableName ).toLower() );

	// Execute
	if ( !nb->execute() ) {
		QString emsg = nb->getLastErrorMessage();

		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
		if ( debug )
			lout << emsg << endl;
		return ( CORBA::Boolean ) false;
	}

	// Get result
	QString result, resultType;
	bool resultOk, compatible = false;
	long rtype = nb->probeResultType();

	if ( rtype < 0 ) {
		QString emsg = "Result variable name mismatch or execution error.";

		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
		if ( debug )
			lout << emsg << endl;
		return ( CORBA::Boolean ) false;
	}

	switch ( rtype ) {
		case SBT_LONG:
			resultType = "[INTEGER NUMBER]";
			{
				long r;
				resultOk = nb->getResult ( r );
				if ( resultOk ) {
					result = QString::number ( r );
					if ( r == 0 || r == 1 )
						resultType = "[INTEGER NUMBER CASTABLE TO BOOLEAN]";
					if ( scriptData.resultType != tString )
						compatible = true;
				}
			}
			break;
		case SBT_DOUBLE:
			resultType = "[FLOATING POINT NUMBER]";
			{
				double r;
				resultOk = nb->getResult ( r );
				if ( resultOk ) {
					result = QString::number ( r );
					if ( scriptData.resultType != tString )
						compatible = true;
				}
			}
			break;
		case SBT_STRING:
			resultType = "[STRING]";
			resultOk = nb->getResult ( result );
			if ( scriptData.resultType != tNumber )
				compatible = true;
			break;
		default:
			resultType = "[UNDEF]";
			resultOk = false;
	}

	if ( resultOk ) {
		QString emsg = "EXECUTION OK!\n";
		emsg += QString ( "Result is: " ) + result + "\n";
		emsg += QString ( "Type is: " ) + resultType + "\n";
		emsg += QString ( "Your selection type is: " );
		emsg += ( compatible ) ? "Compatible" : "Incompatible";
		emsg += "\n";
		if ( debug )
			lout << emsg << endl;
		qstrncpy ( errorText.text, qPrintable(emsg), ERROR_TEXT_LEN );
	}
	else
		qstrncpy ( errorText.text, "", ERROR_TEXT_LEN );

	delete nb;

	return ( CORBA::Boolean ) true;
}


QString mdm_server_server_impl::tempFileName ( const QString & prefix )
{
	QString name;

	// 	name = QString( tempnam( "/tmp/", "" ) ).remove( "/tmp/" );
	name = QString ( tempnam ( "/tmp/", "" ) );
	int slpos = name.lastIndexOf ( '/' );

	if ( slpos < 0 ) {
		return prefix + name;
	}
	else {
		return prefix + name.right ( name.length() - slpos - 1 );

	}
}

// EOF
// kate: indent-mode cstyle; indent-width 8; replace-tabs off; tab-width 8;

