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


#ifndef APIDB_COUNTER_H
#define APIDB_COUNTER_H

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
#define TYPE_COUNTER_TYPE		7
#define TYPE_PERCENT_OF			8


typedef struct {
	int triggerField;
	long triggerValue;
	int counterIndex;
	int counterType;
	int percentOf;
	int triggerCounter;
	char tag[CNT_TAG_LEN];
	char description[CNT_DESC_LEN];
} COUNTERKIT;

typedef QMap<int,int>::iterator helps_it;


typedef QList<COUNTERKIT*> counters;
typedef QList<COUNTERKIT*>::iterator counters_it;
typedef QMap<int,counters>::iterator counterKitList_it;

	
	
/**
 * Classe gestione connesione con il database
 */
class APIDB_CounterTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_CounterTable ();
	APIDB_CounterTable (AdamsCompleteConfig *adc);
	

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il database.
	 */
	~APIDB_CounterTable();

	void setAdamsCompleteConfig(AdamsCompleteConfig *adc);
	void load(QString nameConfig);
	
	void getHelps(QString nameConfig);
	int getHelp(int typeField);
	
	void getCounters(QString nameConfig);
	int getNumCounter(int idCounterKIT);
	COUNTERKIT *getCounter(int idCounterKIT);
	
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
	
	QSqlDatabase 	db;
	QString 	m_connection;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	QString     	strSelect;

	AdamsCompleteConfig *local_adc;
	
		
	
	QMap<int,int> helps;
	QMap<int,counters> counterKitList;
	
};

#endif
