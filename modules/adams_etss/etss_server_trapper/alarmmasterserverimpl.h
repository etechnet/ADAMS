/***************************************************************************
                          processmonitorserverimpl.h  -  description
                             -------------------
    begin                : Tue Jan 21 2003
    copyright            : (C) 2003 by Raffaele Ficcadenti
    email                : raffaele.ficcadenti@e-tech.net
 ***************************************************************************/



#ifndef ALARMMASTERSERVER_H
#define ALARMMASTERSERVER_H

#include "ntmshared/PoaHierarchy.h"

#include <ntmshared/server_stub_safe_include.h>

#include "ntmshared/storenode.h"
#include "ntmshared/configuretypedefs.h"
#include "ntmshared/nodeconfighandler.h"
#include "ntmshared/stsconfigparser.h"
#include "ntmshared/ntmparams.h"
#include "ntmshared/pluginbase.h"
#include "ntmshared/ntmpluginsapi.h"
#include "ntmshared/timeout.h"
#include <ntmshared/definealarmrelation.h>
#include <ntmshared/ntmbasic.h>
#include "executionthread.h"
#include "ntmshared/ams_alarm_resource.h"
#include "api_database.h"

#include <Qt/qthread.h>
#include <Qt/qmutex.h>
#include <Qt/qhash.h>

#include <p_orb.h>
#include <libntmshared/ntmshared/ntmdataserverC.h>

#include <filePSWD.h>
#include <pwdcrypt.h>

#define PID_FILE_NAME_PREFIX	".ams_pid"

typedef Q3IntDict<PluginBase> PluginFactory;
typedef Q3IntDict<PluginImpl> PluginImplFactory;

// class QXmlStreamReader;
class AlarmMasterServerImpl;

class AMS_initRunner : public QThread
{
public:
	virtual void run();
	void setAMSInstance ( AlarmMasterServerImpl * ams_i ) {
		ams = ams_i;
	}

private:
	AlarmMasterServerImpl * ams;
};


class AlarmMasterServerImpl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_AlarmMasterServer,
	public QThread
{

public:

	friend class AMS_initRunner;

	/**
	 * Costruttore della classe ProcessMonitorServerImpl.
	 * All'interno di questo metodo vengono attaccate le due SharedMemory: SHARE_MEMORY_MONITOR e SHARE_MEMORY_NAME
	 */
	AlarmMasterServerImpl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer);

	/**
	 * Distruttore della classe AlarmMasterServerImpl.
	 * All'interno di questo metodo viene rilasciata tutta la memoria utilizzata durante la vita del processo Dispatcher.
	 */
	~AlarmMasterServerImpl();


	/**
	 * Metodo invocato dal client per fare lo shutdown del server.
	 */
	void ShutDown() throw ( CORBA::SystemException );

	/**
	 * Metodo invocato dai client per effettuare il wakeup delle operazioni schedulate
	 * Richiama il metodo QThread::run che effettua il lavoro effettivo in un thread separato
	 */
	void wakeup() throw ( CORBA::SystemException );
	virtual void run();
	/** Wait for generator thread before return */
	void waitGenerator();

	virtual PortableServer::POA_ptr _default_POA();

	CORBA::Boolean getNodes ( CentralImageSeq_out nodes ) throw ( CORBA::SystemException );
	CORBA::Long checkPid ( ) throw ( CORBA::SystemException );

protected:
	/** initialize globals */
	void initAll();
	/** Load alarm policies */
	bool loadPolicies();
	/** Check Working Day */
	int checkWorkingDay ( QDateTime & cur_date );

private:

	/** Tipologie di messaggio per debugPluginError() */
	enum ams_MsgType {
		UnknownType,
		WrongType,
		numBuilderMsgType
	};

	/** Questo metodo effettua la lettura della configurazione cnFilename dal repositorio standard */
	bool readConfiguration(API_DataBase_alarmmasterserver *db);
	/** Questo metodo viene utilizzato per estrarre la path al repositorio standard NTM */
	QString standardRepository();
	/** Questo metodo viene utilizzato per estrarre il path alla directory HOME dei processi CORBA */
	QString corbaBaseDir();
	/** Esegue l'attach del(dei) plugin richiesti per l'elaborazione e l'inizializzazione degli script sul
	    vettore degli allarmi attivi */
	bool initAlarmVector();
	/** completa i RelationComponent con plugin e scripts */
	bool initRelation ( DefineAlarmRelation * dr );
	/** Crea un istanza del plugin relativa ai traffic element */
	PluginImpl * getHandlerPluginInstance ( TrafficElement * te );
	/** Crea un istanza del plugin generico */
	PluginImpl * getGenericPluginInstance ( int plugin_id, StsPluginType plugin_type, char * alt_path = 0 );
	/** Crea un istanza NtmBasic relativa ai traffic element */
	NtmBasic * getNtmBasicInstance ( Script * s );
	/** Questo metodo emette un messaggio di errore appropriato relativamente ad errori su plugin */
	void debugPluginError ( const QString & caller, const QString & requested, ams_MsgType type, PluginImpl * pli = 0 );
	/** Ciclo generazione allarmi. Instanzia il DB, recupera le soglie ed invoca i plugin di
	    generazione degli allarmi.
	 */
	bool generateAlarms();
	/** Effettua la registrazione di tutti i plugin di help disponibili nel path specificato */
	void registerHelperPlugins();
	/** Questo metodo tenta di travare una descrizione per quegli elementi che non hanno associato uno specifico plugin
	    di help per le descrizioni, ma invece hannoi associata una OptionList interna alla configuirazione NTM */
	QString checkInternalHelp ( const QString & tag, const QString & id );
	/** Salva lo stato degli allarmi corrente su file XML
	 */
	bool writeStatus ( );
	/** Carica lo stato degli allarmi salvato su file XML
	 */
	bool readStatus ( );
	/** Salva lo stato delle relazione di allarme su file BIN
	 */
	bool unloadRelationStatus ( int reference_relation, BTreeAlarm & map, bool flush_map = true );
	/** Carica lo stato delle relazione di allarme da file BIN
	 */
	bool loadRelationStatus ( int reference_relation, BTreeAlarm & map );
	/** check for program start (restart) and load XML alarm status */
	void checkRestart();
	/** Clean all temporary data (both real-time and alarm status) */
	void cleanTemporary();
	/** exit in case of dup pids */
	void checkDup();
	/** Inserisce le informazione dell'allarme sulla tabella storica */
	bool addAlarmToDB ( API_DataBase_alarmmasterserver & db, const QString & DirectKey, int rel_id, int rel_ptr, int al_ptr, AlarmStatusData * asd, AlarmStatusNode * asn );
	/** Check alarm configuration changes */
	bool checkConfigChanges();

	void setHistory ( AlarmStatusNode * asn, AlarmStatusData * asd, AlarmGeneratorAPIData * genAPI, int a_idx, int p );
	void resetHistory ( AlarmStatusNode * asn, AlarmStatusData * asd, int a_idx );

	/** Compone il nome del file BIN completo di relation id */
	inline QString composeRelationStatusFileName ( int rel_id ) {
		QString id_string = QString ( REL_STATUS_FILE_NAME ) + QChar ( '_' ) + QString::number ( rel_id ) + QString ( ".bin" );
		return id_string;
	}

	inline QString m_pid_file() {
		QString fname(PID_FILE_NAME_PREFIX);
		fname += QString::number(serverID) + ".xml";
		return fname;
	}


	PoaHierarchy * m_poa_hierarchy;
	QString NOME_CENTRALE;
	QString status_file_name;
	StsConfigParser cnfparser;
	QString config_name;
	NtmCompleteConfig ntm;
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

	int serverID;
	QString db_nome_db;
	QString db_nome_nodo;
        QString db_type_pswd;
        QString db_nome_utente;
};

#endif
// kate: indent-mode cstyle; indent-width 8; replace-tabs off; tab-width 8;
