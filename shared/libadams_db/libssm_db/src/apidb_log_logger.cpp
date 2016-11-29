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

#include <apidb_log_logger.h>
#include <applogger.h>
#include <Qt/qstringbuilder.h>

APIDB_Log_Logger::APIDB_Log_Logger ( )
	: connected ( false ),
	  m_connection ( "APIDB_Log_Logger" )
{
}

bool APIDB_Log_Logger::openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD )
{
	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;

	db = QSqlDatabase::addDatabase ( "QMYSQL", m_connection ); // QMYSQL -> MySql Database

	db.setDatabaseName ( strDBNAME );
	db.setUserName ( strUSER );
	db.setPassword ( strPSWD );

	try {
		db_open ( db );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
	}

	connected = true;
	return true;
}

void APIDB_Log_Logger::closeDB()
{
	if ( !connected )
		return;

	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
	connected = false;
}


APIDB_Log_Logger::~APIDB_Log_Logger()
{
	closeDB();
}


bool APIDB_Log_Logger::insertLog ( const QString & node, const QString & process, const QDateTime & time, pid_t pid, const QString & msg )
{
	open_connection();
	method ( "insertLog" );

	QSqlQuery query ( db );

	// Check for an entry in db

	try {
		query.prepare ( "INSERT INTO t_log_logger (node_name, process_name, pid, time, mesg) "
				"VALUES (?, ?, ?, ?, ?)" );

		query.addBindValue ( node );
		query.addBindValue ( process );
		query.addBindValue ( pid );
		query.addBindValue ( time );
		query.addBindValue ( msg );

		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		m_error_msg = de.preface() + de.explain();
		return false;
	}

	return true;
}

bool APIDB_Log_Logger::insertMon ( const QString & node, const QString & process, const QDateTime & time, int urgency, const QString & msg )
{
	open_connection();
	method ( "insertMon" );

	QSqlQuery query ( db );

	// Check for an entry in db

	try {
		query.prepare ( "INSERT INTO t_log_monitor (node_name, process_name, time, urgency, mesg) "
		"VALUES (?, ?, ?, ?, ?)" );

		query.addBindValue ( node );
		query.addBindValue ( process );
		query.addBindValue ( time );
		query.addBindValue ( urgency );
		query.addBindValue ( msg );

		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		m_error_msg = de.preface() + de.explain();
		return false;
	}

	return true;
}


// ----

