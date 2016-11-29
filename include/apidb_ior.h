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

#ifndef APIDB_IORTABLE_H
#define APIDB_IORTABLE_H

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qlist.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <apidb_define.h>
#include <iortable.h>
#include <apidb_base.h>

static const QString nameDB = "adams_ssm";
static const QString loginDB = "adams";
static const QString pswdDB = "adams";


/**
 * Classe gestione connesione con Database
 */
class APIDB_IorTable: public APIDB_Base
{
public:
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_IorTable();

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il db Database.
	 */
	~APIDB_IorTable();

	inline bool open_connection() {
		if ( !connected )
			return openDB ( nameDB, loginDB, pswdDB );

		return true;
	}

	void debugIor ( IorTable *ior );

	IorTable * getIor ( const QString& processName, const QString& node, bool already_connected = false );

	bool deleteIor ( const QString& processName, const QString& node );
	bool truncateTIOR();
	bool putIor ( const QString& processName, const QString& node, const QString& ior );

	inline QSqlDatabase & getDb() {
		return db;
	}

private:

	bool openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD );
	void closeDB();

	bool insertRecordInTable_BLOC();
	void insertPrepare ( int type );
	bool insertCreate ( int occurrenceNum );
	bool writeData ( int type );
	int getNodeId ( const QString& node, bool already_connected = false );

	QString getTableName ( int t );
	QString fmtNumber ( float num );

	/* campi generici per costruzione insert */

	QSqlDatabase 	db;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;
	QString		m_connection;
	bool		connected;

	QString 	strTableName;
	QStringList 	strFields;
	QStringList 	strValues;
	QString     	strInsert;
	QString     	strSelect;
	QList<int>	strType;
	int 		fieldsNum;


	QString formatValue ( int itType, const QString& value );
};

#endif
