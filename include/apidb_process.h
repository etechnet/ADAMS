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

#ifndef API_DB_ADAMS_H
#define API_DATABASE_H

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

#include "processtable.h"

#define nameDB 	"adams_ssm"
#define loginDB "adams"
#define pswdDB	"adams"


/**
 * Classe gestione connesione con Database
 */
class APIDB_ProcessTable: public APIDB_Base
{
public:
	/**
	 * Costruttore della classe APIDB_IorTable: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_ProcessTable();

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il db Database.
	 */
	~APIDB_ProcessTable();

	inline bool open_connection() {
		if ( !connected )
			return openDB ( nameDB, loginDB, pswdDB );

		return true;
	}

	void debugProcess(ProcessTable *process);

	/** Ritorna un accesso all QList dei process */
	inline ProcessList & getProcessList() {
		loadProcess();
		return processList;
	}

	bool deleteProcess(QString processName);
	bool truncateTPROCESS();
	ProcessTable * getProcess(QString processName);


	bool openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();

	inline QSqlDatabase & getDb() {
		return db;
	}

private:

	bool insertRecordInTable_BLOC();
	void insertPrepare ( int type );
	bool insertCreate ( int occurrenceNum );
	bool writeData ( int type );
	bool deleteRecordInTable ( const QString strDelete );
	QString getTableName ( int t );
	QString fmtNumber ( float num );

	bool loadProcess();

	QSqlDatabase 	db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;
	ProcessList 	processList;
	ProcessTable	* processtable;
	QString		m_connection;
	bool		connected;

	/* campi generici per costruzione insert */
	QString 	strTableName;
	QStringList 	strFields;
	QStringList 	strValues;
	QString     	strInsert;
	QString     	strSelect;
	QList<int>	strType;
	int 		fieldsNum;


	QString formatValue ( int itType, QString value );
};

#endif
