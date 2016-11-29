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
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

#ifndef APIDB_CONFIG_H
#define APIDB_CONFIG_H

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


/**
 * Classe gestione connesione con Database
 */
class APIDB_ConfigTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_ConfigTable ();
	

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il Database.
	 */
	~APIDB_ConfigTable();

	AdamsCompleteConfig * loadConfig(QString nameConfig);


private:

	inline int getExitStatusDB() {
		return last_result_db;
	}


	bool openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();
	bool isConnect();
	bool testConnection();

	void setError ( QSqlDatabase &dbError );
	
	QSqlDatabase 	db;
	QString 	m_connection;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	QString     	strSelect;

	
	AdamsCompleteConfig *adc;
};

#endif
