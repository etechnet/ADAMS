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


#ifndef APIDB_THRESHOLDGENERATOR_H
#define APIDB_THRESHOLDGENERATOR_H

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

typedef QMap<QString,int>::iterator pluginList_it;

/**
 * Classe gestione connesione con DataBase
 */
class APIDB_ThresholdGeneratorTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_ThresholdGeneratorTable ();
	APIDB_ThresholdGeneratorTable (AdamsCompleteConfig *adc);
	

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il db Oracle.
	 */
	~APIDB_ThresholdGeneratorTable();

	void setAdamsCompleteConfig(AdamsCompleteConfig *adc);
	void load(QString nameConfig);
	
	void fillFromDB (QString nameConfig);
	
	void getPlugins(QString nameConfig);
	int getIdPlugin(QString namePlugin);
	
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
	
	QMap<QString,int> pluginList;
};

#endif
