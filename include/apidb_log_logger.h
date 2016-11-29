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

#ifndef APIDB_LOG_LOGGER_H
#define APIDB_LOG_LOGGER_H

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qlist.h>
#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <apidb_define.h>
#include <apidb_base.h>
#include <sys/types.h>

static const QString all_nameDB = "adams_log";
static const QString all_loginDB = "adams";
static const QString all_pswdDB = "adams";


class APIDB_Log_Logger : public APIDB_Base
{
public:
	APIDB_Log_Logger();
	~APIDB_Log_Logger();

	bool insertLog ( const QString & node, const QString & process, const QDateTime & time, pid_t pid, const QString & msg );
	bool insertMon ( const QString & node, const QString & process, const QDateTime & time, int urgency, const QString & msg );

	inline bool open_connection() {
		if ( !connected )
			return openDB ( all_nameDB, all_loginDB, all_pswdDB );

		return true;
	}

	inline QSqlDatabase & getDb() {
		return db;
	}

	inline void close_connection() {
		closeDB();
	}

	inline const QString & getErrorMsg() { return m_error_msg; }

private:

	bool openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD );
	void closeDB();

	QSqlDatabase db;
	bool connected;
	int last_result;
	bool enablePSWD;
	QString strUSER;
	QString strPSWD;
	QString strDBNAME;
	QString m_connection;
	QString m_error_msg;
};

#endif // APIDB_LOG_LOGGER_H
