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

#ifndef APIDB_DATAELEMENT_H
#define APIDB_DATAELEMENT_H

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

#define D_SCRIPT		0
#define D_SCRIPT_TYPE		1

typedef QList<double> value_code;
typedef QList<double>::iterator value_code_it;
typedef QMap<int, value_code>::iterator valueCodeList_it;

typedef QList<QString> value_desc;
typedef QList<QString>::iterator value_desc_it;
typedef QMap<int, value_desc>::iterator valueDescList_it;

typedef QList<int> restrictions;
typedef QList<int>::iterator restrictions_it;
typedef QMap<int, restrictions>::iterator aggregateRestrictionList_it;

typedef QList<int> exceptions;
typedef QList<int>::iterator exceptions_it;
typedef QMap<int, exceptions>::iterator exceptionList_it;

typedef QList<QString> shifters;
typedef QList<QString>::iterator shifters_it;
typedef QMap<int, shifters>::iterator shifterList_it;

typedef QMap<QString,int>::iterator pluginList_it;
typedef QMap<int,int*>::iterator scriptList_it;


/**
 * Classe gestione connesione con DataBase
 */
class APIDB_DataElementTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_DataElementTable ();
	APIDB_DataElementTable (AdamsCompleteConfig *adc);
	

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il db Database.
	 */
	~APIDB_DataElementTable();

	void setAdamsCompleteConfig(AdamsCompleteConfig *adc);
	void load(QString nameConfig);
	
	void fillFromDB (QString nameConfig);
	
	double *getValueList(int pos_element);
	QString *getHelperValues(int pos_element);
	void getValueList(QString nameConfig);
	void getAggregateRestrictions(QString nameConfig);
	int *getAggregateRestrictions(int pos_element);
	void getExceptions(QString nameConfig);
	int *getExceptions(int pos_element);
	void getShifters(QString nameConfig);
	QString * getShifters(int pos_element);
	void getPlugins(QString nameConfig);
	int getIdPlugin(QString namePlugin);
	
	void getScripts(QString nameConfig);
	int *getScripts(int pos_element);
	
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
	
	QSqlDatabase 	db;
	QString 	m_connection;
	
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	QString     	strSelect;

	AdamsCompleteConfig *local_adc;
	
	QMap<int, value_code> valueCodeList;
	QMap<int, value_desc> valueDescList;
	QMap<int, restrictions> aggregateRestrictionList;
	QMap<int, exceptions> exceptionList;
	QMap<int, shifters> shifterList;
	QMap<QString,int> pluginList;
	QMap<int,int*> scriptList;
};

#endif
