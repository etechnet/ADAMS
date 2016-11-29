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

#include <apidb_status.h>
#include <applogger.h>
#include <Qt/qstringbuilder.h>

APIDB_Status::APIDB_Status ( )
	: connected ( false ),
	  m_connection ( "APIDB_Status" )
{
}

bool APIDB_Status::openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD )
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

void APIDB_Status::closeDB()
{
	if ( !connected )
		return;

	db.close();
	// also QT's engineers are a bit crazy...
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
	connected = false;
}


APIDB_Status::~APIDB_Status()
{
	closeDB();
}


bool APIDB_Status::setProcessStatus ( const QString & node, const QString & process, const QString & status, const QString & msg )
{
	open_connection();
	method ( "setProcessStatus" );

	QSqlQuery query ( db );

	// Check for an entry in db

	try {
		query.prepare ( "SELECT * FROM t_proc_status "
		                "WHERE node_name=? "
		                "AND process_name=? " );

		query.addBindValue ( node );
		query.addBindValue ( process );

		query_exec ( query );

		if ( query.size() <= 0 )
			insert_my_row ( node, process );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	// update status

	try {
		query.prepare ( "UPDATE t_proc_status SET "
		                "status=?, "
		                "status_message=? "
		                "WHERE node_name=? "
		                "AND process_name=?" );

		query.addBindValue ( status );
		query.addBindValue ( msg );
		query.addBindValue ( node );
		query.addBindValue ( process );

		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	return true;
}

bool APIDB_Status::insert_my_row ( const QString & node, const QString & process )
{
	method ( "insert_my_row" );

	QSqlQuery query ( db );

	try {
		query.prepare ( "INSERT INTO t_proc_status (node_name, process_name) "
		                "VALUES (?, ?)" );

		query.addBindValue ( node );
		query.addBindValue ( process );

		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	return true;
}

// ----

