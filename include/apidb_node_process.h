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

#ifndef APIDB_NODEPROCESS_H
#define APIDB_NODEPROCESS_H

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qlist.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <apidb_define.h>
#include <apidb_base.h>
#include <nodeprocesstable.h>

static const QString anp_nameDB = "adams_ssm";
static const QString anp_loginDB = "adams";
static const QString anp_pswdDB = "adams";


/**
 * NodeProcess Table handler class
 */
class APIDB_NodeProcess: public APIDB_Base
{
public:

	APIDB_NodeProcess();
	~APIDB_NodeProcess();

	bool loadNodeProcessList ( const QString & node, bool silent = false );
	bool updateNodeProcessTable ( const NodeProcessList & p_list );
	bool updateFullNodeProcessTable ( const NodeProcessList & p_list );

	inline NodeProcessList & getNodeProcessList ( const QString & node, bool silent = false ) {
		loadNodeProcessList ( node, silent );
		return np_list;
	}

	inline bool getNodeProcessList ( const QString & node, NodeProcessList & rlist, bool silent = false ) {
		bool stat = loadNodeProcessList ( node, silent );
		if (stat)
			rlist = np_list;
		return stat;
	}

	inline bool open_connection() {
		if ( !connected )
			return openDB ( anp_nameDB, anp_loginDB, anp_pswdDB );

		return true;
	}

	inline QSqlDatabase & getDb() {
		return db;
	}

	inline const QString & getErrorMsg() { return m_error_msg; }

private:

	bool openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD );
	void closeDB();
	int getNodeId ( const QString & node );

	QSqlDatabase db;
	bool connected;
	int last_result;
	bool enablePSWD;
	QString strUSER;
	QString strPSWD;
	QString strDBNAME;
	NodeProcessTable np_table;
	NodeProcessList np_list;
	QString m_connection;
	QString m_error_msg;
};

#endif
