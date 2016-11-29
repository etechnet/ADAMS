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

#ifndef _ETSS_SERVER_TRAPPER_SERVER_H
#define _ETSS_SERVER_TRAPPER_SERVER_H

#include <poahierarchy.h>
#include <etssS.h>
#include <p_orb.h>
#include <configparser.h>
#include <storenode.h>
#include <configuretypedefs.h>
#include <nodeconfighandler.h>
#include <configparser.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <timeout.h>
#include <definealarmrelation.h>
#include <adamsbasic.h>
#include "executionthread.h"
#include <est_alarm_resource.h>
#include "api_database.h"

#include <Qt/qthread.h>
#include <Qt/qmutex.h>
#include <Qt/qstring.h>
#include <Qt/qdir.h>
#include <Qt/qhash.h>

#define PID_FILE_NAME_PREFIX	".ams_pid"

typedef QHash<int, PluginBase *> PluginFactory;
typedef QHash<int, PluginImpl *> PluginImplFactory;

using namespace net::etech;
using namespace net::etech::ETSS;


class etss_server_trapper_server_impl;

class ETS_initRunner : public QThread {
public:
	virtual void run();
	void setETSInstance ( etss_server_trapper_server_impl * ets_i ) {
		ets = ets_i;
	}

private:
	etss_server_trapper_server_impl * ets;
};



class etss_server_trapper_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::ETSS::etss_server_trapper_server {

public:
	friend class ETS_initRunner;

	etss_server_trapper_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );
	~etss_server_trapper_server_impl();

	virtual PortableServer::POA_ptr _default_POA();

	virtual void run();
	/** Wait for generator thread before return */
	void waitGenerator();

	// IDL implementation methods
	virtual void wakeup ( void ) throw ( CORBA::SystemException );

	virtual void shutDown ( void ) throw ( CORBA::SystemException );

	virtual ::CORBA::Long checkPid ( void ) throw ( CORBA::SystemException );

	virtual ::CORBA::Boolean getNodes ( ::net::etech::FepImageSeq_out nodes ) throw ( CORBA::SystemException );

protected:
	/** initialize globals */
	void initAll();
	/** Load alarm policies */
	bool loadPolicies();
	/** Check Working Day */
	int checkWorkingDay ( QDateTime & cur_date );

private:

	ConfigParser cnfparser;
	PoaHierarchy * m_poa_hierarchy;
	int serverID;
	QString NOME_CENTRALE;
	QString status_file_name;
	QString config_name;
	AdamsCompleteConfig ntm;
	TimeOut timeout;	// watchdog
	CORBA::ORB_var orb;
	NodeConfigHandler nodeConfigHandler;
	LOC_INVOKE_STATUS invokeStatus;
	AlarmDataMap * alarmMap;
	PluginImplFactory pluginImplFactory;		// a proxy factory for traffic elements plugins instances
	QString pluginHome;
	int alarm_vector[MAX_ALARMS];
	int num_alarms;
	AMS_ALARM_RESOURCE alarm_resource[MAX_ALARMS];
	NTMParams fake_params;
	QMap<QString, THRESHOLD_VALUE> thresholds;
	HelpDictionary helpDict;
	QDateTime last_run_time;
	QDateTime change_day_time;
	bool last_run_time_loaded;
	AMS_initRunner m_initRunner;
	char remote_password[ DIM_PSWD_PASSWORD ];
	API_DataBase_alarmmasterserver * db;
	QString db_nome_db;
	QString db_nome_nodo;
	QString db_type_pswd;
	QString db_nome_utente;

	inline void dir_check ( const QString & a_path, bool isFilePath = false ) {
		QString path = a_path;
		if ( path.mid ( 0, 1 ) == "~" ) {				// home relative path
			QString homedir = ::getenv ( "HOME" );
			if ( !homedir.isEmpty() ) {
				path.replace ( 0, 1, homedir );
			}
		}
		if ( isFilePath ) {
			int slashpos = path.lastIndexOf ( '/' );
			if ( slashpos >= 0 )
				path = path.left ( slashpos );
		}

		QDir dir ( path );
		if ( ! dir.exists() )
			dir.mkpath ( path );
	}

	/** Tipologie di messaggio per debugPluginError() */
	enum est_MsgType {
	        UnknownType,
	        WrongType,
	        numBuilderMsgType
	};

	bool readConfiguration ( API_DataBase_alarmmasterserver * db );
	QString standardRepository();
	QString corbaBaseDir();
	bool initAlarmVector();
	bool initRelation ( DefineAlarmRelation * dr );
	PluginImpl * getHandlerPluginInstance ( TrafficElement * te );
	PluginImpl * getGenericPluginInstance ( int plugin_id, StsPluginType plugin_type, char * alt_path = 0 );
	NtmBasic * getNtmBasicInstance ( Script * s );
	void debugPluginError ( const QString & caller, const QString & requested, ams_MsgType type, PluginImpl * pli = 0 );
	bool generateAlarms();
	void registerHelperPlugins();
	QString checkInternalHelp ( const QString & tag, const QString & id );
	bool writeStatus ( );
	bool readStatus ( );
	bool unloadRelationStatus ( int reference_relation, BTreeAlarm & map, bool flush_map = true );
	bool loadRelationStatus ( int reference_relation, BTreeAlarm & map );
	void checkRestart();
	void cleanTemporary();
	void checkDup();
	bool addAlarmToDB ( API_DataBase_alarmmasterserver & db, const QString & DirectKey, int rel_id, int rel_ptr, int al_ptr, AlarmStatusData * asd, AlarmStatusNode * asn );
	bool checkConfigChanges();

	void setHistory ( AlarmStatusNode * asn, AlarmStatusData * asd, AlarmGeneratorAPIData * genAPI, int a_idx, int p );
	void resetHistory ( AlarmStatusNode * asn, AlarmStatusData * asd, int a_idx );

	inline QString composeRelationStatusFileName ( int rel_id ) {
		QString id_string = QString ( REL_STATUS_FILE_NAME ) + QChar ( '_' ) + QString::number ( rel_id ) + QString ( ".bin" );
		return id_string;
	}

	inline QString m_pid_file() {
		QString fname ( PID_FILE_NAME_PREFIX );
		fname += QString::number ( serverID ) + ".xml";
		return fname;
	}

};

#endif // _ETSS_SERVER_TRAPPER_SERVER_H
