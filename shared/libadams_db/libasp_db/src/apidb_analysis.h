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

#ifndef APIDB_ANALYSIS_H
#define APIDB_ANALYSIS_H

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

typedef QList<int> reports;
typedef QList<int>::iterator reports_it;
typedef QMap<int, reports>::iterator reportList_it;

typedef QMap<QString,int>::iterator relationList_it;

typedef QList<int> relAnalysis;
typedef QList<int>::iterator relAnalysis_it;
typedef QMap<int, relAnalysis>::iterator relAnalysisList_it;


/**
 * Classe gestione connesione con il Database
 */
class APIDB_AnalysisTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_AnalysisTable ();
	APIDB_AnalysisTable (AdamsCompleteConfig *adc);
	

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il db Database.
	 */
	~APIDB_AnalysisTable();

	void setAdamsCompleteConfig(AdamsCompleteConfig *adc);
	void load(QString nameConfig);
	
	void fillFromDB (QString nameConfig);
	void getReports(QString nameConfig);
	int *getReports(int idAnalisys);
	void getRelations(QString nameConfig);
	int getIDRelations(QString nameRelation);
	void getRelationsAnalisys(QString nameConfig);
	int *getRelationsAnalisys(int idAnalisys);
	
	
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
	
	QMap<int, reports> reportList;
	QMap<QString,int> relationList;
	QMap<int,relAnalysis>relAnalysisList;
};

#endif
