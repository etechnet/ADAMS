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

#ifndef _MDM_SERVER_SERVER_H
#define _MDM_SERVER_SERVER_H

#include <poahierarchy.h>
#include <mdmS.h>
#include <p_orb.h>
#include <Qt/qthread.h>
#include <Qt/qhash.h>
#include <Qt/qstringbuilder.h>

#include "executionthread.h"
#include "pivotfile.h"

#include <userinterface.h>
#include <configuretypedefs.h>
#include <nodeconfighandler.h>
#include <configparser.h>
#include <queryparams.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <httpimpl.h>
#include <timeout.h>
#include <btreenetworkrt.h>
#include <commontypes.h>
#include <adamsbasic.h>

class DefineRelation;

typedef QHash<int, PluginBase *> PluginFactory;

using namespace net::etech;
using namespace net::etech::MDM;

class mdm_server_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::MDM::mdm_server_server,
	public QThread
{

public:

	mdm_server_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );
	~mdm_server_server_impl();

	virtual void run();

	CORBA::Boolean runMDMServerBlocking ( const STRUCT_PARAMS&  UserInputI, CORBA::String_out  UrlI ) throw ( CORBA::SystemException );
	CORBA::Boolean runMDMServerNonBlocking ( const STRUCT_PARAMS&  UserInputI ) throw ( CORBA::SystemException );
	CORBA::Boolean getRunningStatus ( INVOKE_STATUS_out  invokeStatus, CORBA::Boolean_out query_completed ) throw ( CORBA::SystemException );
	CORBA::Boolean startMDMServer ( ADAMS_COMPLETE_CONFIG_out  ntmConf,  FEPSeq_out CEN, const char*  configName ) throw ( CORBA::SystemException );
	CORBA::Boolean insertJob ( const STRUCT_PARAMS&  Job, const STRUCT_JOB&  InfoJOB ) throw ( CORBA::SystemException );
	CORBA::Boolean modifyJob ( const STRUCT_JOB&  InfoJOB ) throw ( CORBA::SystemException );
	CORBA::Boolean getAllJobs ( JOBSeq_out JOBS, const STRUCT_USER&  InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean getJob ( STRUCT_PARAMS_out  JobOut, const STRUCT_USER&  InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean removeJob ( const STRUCT_USER&  InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean removeAllJobs ( const STRUCT_USER&  InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean getUsersList ( JobUserSeq_out USER ) throw ( CORBA::SystemException );
	CORBA::Boolean getHelperValues ( const char*  configName, const char*  tag, const STRUCT_PARAMS&  params, HelperSeq_out DESC ) throw ( CORBA::SystemException );
	CORBA::Boolean directoryListing ( const char*  altPath, DirEntrySeq_out dirList ) throw ( CORBA::SystemException );
	CORBA::Boolean readUsers ( const char*  loginid, AdamsUserSeq_out users, CORBA::Boolean allusr ) throw ( CORBA::SystemException );
	CORBA::Boolean writeUsers ( const AdamsUserSeq &  users ) throw ( CORBA::SystemException );
	CORBA::Boolean storeConfiguration ( const ADAMS_COMPLETE_CONFIG&  ntmConfig, const char*  configName ) throw ( CORBA::SystemException );
	CORBA::Boolean verifyUserLogin ( const char*  name, const char*  password ) throw ( CORBA::SystemException );
	CORBA::Boolean verifyScript ( const SCRIPT_VALIDATE&  scriptData, ERROR_TEXT_out  errorText ) throw ( CORBA::SystemException );

	void shutDown() throw ( CORBA::SystemException );
	CORBA::Boolean getPivotData ( const char*  name, CORBA::Long startLine, CORBA::Long maxLines, PIVOTREQ&  pvreq,
	                              CORBA::Long_out totalRows, PivotDataNodeSeq_out pivot, IDListSeq_out IDCounters ) throw ( CORBA::SystemException );
	/** Questo metodo viene invocato dal client per effettuare la ricezione dei dati con output XLS/PDF */
	CORBA::Boolean getExportReport ( const char* name, ExportDataSeq_out exportData ) throw ( CORBA::SystemException );

private:
	/** Tipologie di messaggio per debugPluginError() */
	enum pluginMsgType {
	        UnknownType,
	        WrongType,
	        numBuilderMsgType
	};

	enum PivotDataSourceType {
	        pdUnused,
	        pdCounter,
	        pdScript
	};

	typedef struct {				// maps pivot node data from file to report
		PivotDataSourceType source;
		int index;
	} PivotDataMap_private;

	/** Questa classe viene utilizzata internamente alla classe per gestire la manipolazione delle scripts sulle righe
	 * del corpo.
	 *	  @short Gestione scripts righe (USO INTERNO)
	 */
	class BodyScripts
	{
	public:
		BodyScripts() : rso ( 0 ), sHndl ( 0 ) {
			for ( int i = 0; i < CNT_NUM; i++ ) varsIndex[i] = -1;
			for ( int i = 0; i < CNT_NUM; i++ ) tagsIndex[i] = -1;
		}

		ReportSchemaObj * rso;
		AdamsBasic * sHndl;
		int varsIndex[CNT_NUM];
		int tagsIndex[CNT_NUM];
	};

	typedef struct {				// holds pivot node data during transfer to client
		int key_n;
		int r_key_n;
		int int_n;
		int dbl_n;
		int pseqlen;
		PivotDataMap_private * pdMap;
		int idn;
		const PIVOTREQ *  pvreq;
		bool * keyHelperPresent;
		DefineRelation * defineRelation;
		BodyScripts * bdyScripts;
		AdamsBasic ** keyScript;
		PluginImpl ** keyPlugin;
		Counters * kit;
		bool isPercent;
		bool withPreSort;
		bool * enabled_keys;
		bool userData;
		int userDataSize;
		bool reserved;
	} PivotDataNode_private;

// 	/** Questo metodo effettua la lettura della configurazione cnFilename dal repositorio standard */
	bool loadConfiguration ( const QString & conf_name );
	QString pivotBaseDir();
	/** Effettua il post-sort degli alberi diretto <-> inverso, per le analysis di tipo Network */
	int sortNetworkTreeByCounter ( PivotFile * pivotFile = 0 );
	/** Effettua la registrazione di tutti i plugin di help disponibili nel path specificato */
	void registerHelperPlugins();
	/** Questo metodo emette un messaggio di errore appropriato relativamente ad errori su plugin */
	void debugPluginError ( const QString & caller, const QString & requested, pluginMsgType type, PluginImpl * pli = 0 );
	/** Ricerca nella configurazione la descrizione associata ad un determinato elemento di traffico */
	QString checkInternalHelp ( AdamsCompleteConfig & config, const QString & tag, const QString & id );
	/** Deriva l'indice del contatore sulla base del tag */
	int getCounterIndex ( Counters * kit, const QString & cntTag, int & tagi );
	/** Valorizzazione dei campi gestiti da script tramite richiamo di AdamsBasic */
	bool callBasic_BODY ( BodyScripts * bdyScripts, Counters * kit, int bdyidx, PFRecord * cntRow,
	                      int numI, int numD, QString & result, int ndec );

	/**
	 *	Ritorna il tipo del contatore in argomento
	 *		@param idx Indice contatore
	 */
	inline int getCounterType ( int idx ) {
		if ( idx > 0 && idx <= MAX_INT_COUNTERS ) return INT_TYPE;
		else if ( idx > MAX_INT_COUNTERS && idx <= ( MAX_INT_COUNTERS + MAX_DBL_COUNTERS ) ) return DOUBLE_TYPE;
		else return -1;
	}
	double getCounterValue ( Counters * kit, int numIntCounters, int numDblCounters, int idx, PFRecord * cntRow );
	void copyPFRecordToCorba ( PFRecord * pfr, PIVOTDATANODE & pdn, PivotDataNode_private & pdi );
	void copyExportRecordToCorba ( PFRecord * pfr, EXPORT_ROW & pdn, PivotDataNode_private & pdi );
	void destroyPDI ( PivotDataNode_private & pdi );
	void requestAudit ( const AdamsCompleteConfig & ntm, const QueryParams & UserParameter, const STRUCT_PARAMS & UserInput );
	void requestAuditSensitiveData ( const AdamsCompleteConfig & ntm, QueryParams & UserParameter, const STRUCT_PARAMS & UserInput, int rows );
	QString expandRestrictionDescription ( const AdamsCompleteConfig & ntm, QueryParams::Rest * r );
	PluginImpl * getHandlerPluginInstance ( DataElement * te );
	bool callBasic_TE ( const QString & keyPart, const QString & currentKey, AdamsBasic * nb, QString & result );

	inline bool hasHiddenColumns ( bool * vec ) {
		for ( int i = 0; i < MAX_PIVOT_KEYS; i++ ) if ( !vec[i] ) return true;
		return false;
	}

	QString tempFileName ( const QString & );

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

	inline void expand_plugin_dir ( QString & plug_dir ) {
		if ( plug_dir.mid ( 0, 1 ) == "~" ) {				// home relative path
			QString homedir = ::getenv ( "HOME" );
			if ( !homedir.isEmpty() ) {
				plug_dir.replace ( 0, 1, homedir );
			}
		}
		else if ( plug_dir.mid ( 0, 1 ) != "/" ) {			// not an absolute path
			plug_dir = AdamsServer::getInstallPath() % "/" % plug_dir;
		}

	}

	/** Richiama il plugin dei contatori per i calcoli di chiusura (centralizzati) */
	bool performFinalCountersEvaluation ( BtreeMap * Btree = 0 );

// 	CentralSeq CentralFormat;	// Centrali
	AdamsCompleteConfig acc;
	QString activeConfigName;

	BTreeNetworkRT BtreeNetRT_Direct;
	BTreeNetworkRT BtreeNetRT_Inverse;
	BTreeNetworkRT BtreeNetRT_Unsort;
	NodeConfigHandler nodeConfigHandler;

	mdm_server_job_server_var jobServer;

	char * config_fname;
	char * acEnv;
	ConfigParser cnfparser;
	HelpDictionary helpDict;

	STRUCT_PARAMS UserInput;
	MDMAgentCallStatus global_AgentsRunStatus;
	QueryParams UserParameter;
	char * Url;
	bool imStat;
	bool queryCompleted;
	bool confLoaded;

	TimeOut timeout;	// watchdog

	PoaHierarchy * m_poa_hierarchy;
	int serverID;

};

#endif
