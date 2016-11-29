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


#include "nodetable.h"

#define nameDB 	"adams_ssm"
#define loginDB "adams"
#define pswdDB	"adams"


/**
 * Classe gestione connesione con Database
 */
class APIDB_NodeTable: public APIDB_Base
{
public:
	/**
	 * Costruttore della classe APIDB_NodeTable: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_NodeTable();

	/**
	 * Distruttore della classe APIDB_NodeTable: chiude la connessione con il Database.
	 */
	~APIDB_NodeTable();

	inline bool open_connection() {
		if ( !connected )
			return openDB ( nameDB, loginDB, pswdDB );

		return true;
	}

	void debugNode(NodeTable *node);

	/** Ritorna un accesso all QList dei process */
	inline NodeList & getNodeList() {
		loadNode();
		return nodeList;
	}

	inline QSqlDatabase & getDb() {
		return db;
	}

	bool deleteNode(QString nodeName);
	bool truncateTNODE();
	NodeTable * getNode(QString nodeName);
	QString getTableName ( int t );
	QString fmtNumber ( float num );



private:
	bool openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();

	bool insertRecordInTable_BLOC();
	void insertPrepare ( int type );
	bool insertCreate ( int occurrenceNum );
	bool writeData ( int type );
	bool deleteRecordInTable ( const QString strDelete );
	bool loadNode();

	QSqlDatabase 	db;
	int 		last_result_db;
	int 		last_result_query;
	int 		last_result;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;
	NodeList 	nodeList;
	NodeTable	*nodetable;
	QString		m_connection;
	bool 		connected;

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
