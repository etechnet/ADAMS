/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <raffale.ficcadenti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--08-10-13 Raffaele Ficcadenti
#
*/

#ifndef APIDB_REPORT_H
#define APIDB_REPORT_H

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <QtSql/QSqlDriver>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qvariant.h>
#include <Qt/qlist.h>
#include <Qt/qstringlist.h>
#include <apidb_define.h>
#include <apidb_base.h>
#include <configuretypedefs.h>

#define TYPE_COUNTER_INDEX		6
#define	TYPE_REPORT_SCHEMA		1
#define	TYPE_REPORT_OBJECT		2

typedef QMap<int,int>::iterator helps_it;
typedef QMap<QString,int>::iterator pluginList_it;

typedef QMap<QString, int>::iterator counterList_it;


typedef QMap<int,ReportSchema *>::iterator reportSchemaList_it;

/**
 * Classe gestione connesione con DataBase
 */
class APIDB_ReportSchemasTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_ReportSchemasTable ();
	APIDB_ReportSchemasTable (AdamsCompleteConfig *adc);
	

	/**
	 * Distruttore della classe, chiude la connessione con il DataBase.
	 */
	~APIDB_ReportSchemasTable();

	void setAdamsCompleteConfig(AdamsCompleteConfig *adc);
	void load(QString nameConfig);
	
	void getHelps(QString nameConfig);
	int getHelp(int typeField);
	void getPlugins(QString nameConfig);
	int getIdPlugin(QString namePlugin);
	void getCounters(QString nameConfig);
	int getIDCounter(QString nameCounter);
	void getReportSchemaObjs(QString nameConfig);
	int getIDCounter(QString nameConfig,int idAnalisys,QString tagCounter);
	
	void fillFromDB (QString nameConfig);

	void debug();


private:

	inline int getExitStatusDB() {
		return last_result_db;
	}

	bool openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();
	bool isConnect();

	void setError ( QSqlDatabase &dbError );

	bool toBoolean ( QString str );
	bool toBoolean(int value);
	
	void fillObjectFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos,int flag);
	void fillCommonFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos,int flag);
	void fillHeaderFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos);
	void fillBodyFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos);
	void fillTotalizerFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos);
	void fillFooterFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos);
	
	void debugCommon(const ReportSchemaObj &rso);
	void debugObject(const ReportSchemaObj &rso);
	void debugHeadr(const ReportSchemaObj &rso);
	void debugBody(const ReportSchemaObj &rso);
	void debugTotalizer(const ReportSchemaObj &rso);
	void debugFooter(const ReportSchemaObj &rso);
	
	QSqlDatabase 	db;
	QString 	m_connection;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	QString     	strSelect;

	AdamsCompleteConfig *local_adc;
	
	QMap<int,ReportSchema *> reportSchemaList;
	QMap<QString,int> pluginList;
	QMap<int,int> helps;
	QMap<QString, int> counterList;
};

#endif
