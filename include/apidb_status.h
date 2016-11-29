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

#ifndef APIDB_STATUS_H
#define APIDB_STATUS_H

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qlist.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <apidb_define.h>
#include <apidb_base.h>

static const QString ast_nameDB = "adams_log";
static const QString ast_loginDB = "adams";
static const QString ast_pswdDB = "adams";


class APIDB_Status : public APIDB_Base
{
public:
	APIDB_Status();
	~APIDB_Status();

	bool setProcessStatus ( const QString & node, const QString & process, const QString & status, const QString & msg );

	inline bool open_connection() {
		if ( !connected )
			return openDB ( ast_nameDB, ast_loginDB, ast_pswdDB );

		return true;
	}

	inline QSqlDatabase & getDb() {
		return db;
	}

	inline void close_connection() {
		closeDB();
	}

private:

	bool openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD );
	void closeDB();

	bool insert_my_row ( const QString & node, const QString & process );

	QSqlDatabase db;
	bool connected;
	int last_result;
	bool enablePSWD;
	QString strUSER;
	QString strPSWD;
	QString strDBNAME;
	QString m_connection;
};

#endif // APIDB_STATUS_H
