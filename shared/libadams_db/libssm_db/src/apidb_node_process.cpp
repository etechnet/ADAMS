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

#include <apidb_node_process.h>
#include <applogger.h>
#include <Qt/qstringbuilder.h>

APIDB_NodeProcess::APIDB_NodeProcess ( )
	: connected ( false ),
	  m_connection ( "APIDB_NodeProcess" )
{
}

bool APIDB_NodeProcess::openDB ( const QString& hstrDBNAME, const QString& hstrUSER, const QString& hstrPSWD )
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

void APIDB_NodeProcess::closeDB()
{
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
	connected = false;
}


APIDB_NodeProcess::~APIDB_NodeProcess()
{
	closeDB();
}

int APIDB_NodeProcess::getNodeId ( const QString & node )
{
	open_connection();
	method ( "getNodeId" );

	QSqlQuery query ( db );
	int node_id = -1;

	try {
		query.prepare ( "SELECT node_id FROM adams_ssm.t_node WHERE name = ?" );
		query.addBindValue ( node );
		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return -1;
	}

	while ( query.next() ) {
		node_id = query.value ( 0 ).toInt();
	}

	return node_id;
}


bool APIDB_NodeProcess::loadNodeProcessList ( const QString & node, bool silent )
{
	open_connection();
	method ( "loadNodeProcessList" );

	QSqlQuery query ( db );

	int id_node = getNodeId ( node );
	if ( id_node < 0 )
		return false;

	try {
		query.prepare ( "SELECT * FROM t_node_process WHERE node_id = ?" );
		query.addBindValue ( id_node );
		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		if (silent)
			m_error_msg = de.preface() + de.explain();
		else
			lout << de.preface() << de.explain() << endl;
		return false;
	}

	while ( query.next() ) {

		np_table.node_id = query.value ( 0 ).toInt();
		np_table.process_name = query.value ( 1 ).toString();
		np_table.process_type = query.value ( 2 ).toInt();
		np_table.process_id = query.value ( 3 ).toInt();
		np_table.active_flag = query.value ( 4 ).toInt();
		np_table.schedule_ignore = query.value ( 5 ).toInt();
		np_table.wake_time = query.value ( 6 ).toTime();
		np_table.start_cmd = query.value ( 7 ).toString();
		np_table.last_restart = query.value ( 8 ).toDateTime();
		np_table.num_restart = query.value ( 9 ).toInt();
		np_table.pid = query.value ( 10 ).toInt();
		np_table.log_level = query.value ( 11 ).toInt();
		np_table.log_output = query.value ( 12 ).toString();
		np_table.log_pipe_cmd = query.value ( 13 ).toString();
		np_table.assigned_port = query.value ( 14 ).toInt();

		np_list.append ( np_table );
	}

	return true;
}


bool APIDB_NodeProcess::updateNodeProcessTable ( const NodeProcessList & p_list )
{
	open_connection();
	method ( "updateNodeProcessTable" );

	QSqlQuery query ( db );

	try {
		query_exec ( query, "set autocommit=0" );
// 		query_exec(query, "start transaction");
		query_exec ( query, "lock tables t_node_process write" );

		for ( int p = 0; p < p_list.count(); p++ ) {

			query.prepare ( "UPDATE t_node_process SET "
			                "active_flag=?, "
			                "last_restart=?, "
			                "num_restart=?, "
			                "pid=? "
			                "WHERE node_id=? "
			                "AND process_name=?" );

			query.addBindValue ( p_list[p].active_flag );
			query.addBindValue ( p_list[p].last_restart );
			query.addBindValue ( p_list[p].num_restart );
			query.addBindValue ( p_list[p].pid );
			query.addBindValue ( p_list[p].node_id );
			query.addBindValue ( p_list[p].process_name );

			query_exec ( query );
// 			lout3 << "query"
// 				<< " new pid=" << p_list[p].pid
// 				<< " new last_restart=" << p_list[p].last_restart.toString()
// 				<< endl;
		}

		query_exec ( query, "commit" );
		query_exec ( query, "unlock tables" );
		query_exec ( query, "set autocommit=1" );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		query_exec ( query, "rollback" );
		query_exec ( query, "unlock tables" );
		query_exec ( query, "set autocommit=1" );
		return false;
	}

	return true;
}

bool APIDB_NodeProcess::updateFullNodeProcessTable ( const NodeProcessList & p_list )
{
	open_connection();
	method ( "updateNodeProcessTable" );

	QSqlQuery query ( db );

	try {
		query_exec ( query, "set autocommit=0" );
		query_exec ( query, "lock tables t_node_process write" );

		for ( int p = 0; p < p_list.count(); p++ ) {

			query.prepare ( "UPDATE t_node_process SET "
			                "process_type=?, "
			                "process_id=?, "
			                "active_flag=?, "
			                "schedule_ignore=?, "
			                "wake_time=?, "
			                "start_cmd=?, "
			                "last_restart=?, "
			                "num_restart=?, "
			                "pid=?, "
			                "log_level=?, "
			                "log_output=?, "
			                "log_pipe_cmd=?, "
			                "assigned_port=? "
			                "WHERE node_id=? "
			                "AND process_name=?" );

			query.addBindValue ( p_list[p].process_type );
			query.addBindValue ( p_list[p].process_id );
			query.addBindValue ( p_list[p].active_flag );
			query.addBindValue ( p_list[p].schedule_ignore );
			query.addBindValue ( p_list[p].wake_time );
			query.addBindValue ( p_list[p].start_cmd );
			query.addBindValue ( p_list[p].last_restart );
			query.addBindValue ( p_list[p].num_restart );
			query.addBindValue ( p_list[p].pid );
			query.addBindValue ( p_list[p].log_level );
			query.addBindValue ( p_list[p].log_output );
			query.addBindValue ( p_list[p].log_pipe_cmd );
			query.addBindValue ( p_list[p].assigned_port );
			query.addBindValue ( p_list[p].node_id );
			query.addBindValue ( p_list[p].process_name );

			query_exec ( query );
// 			lout3 << "query" << endl;
		}

		query_exec ( query, "commit" );
		query_exec ( query, "unlock tables" );
		query_exec ( query, "set autocommit=1" );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		query_exec ( query, "rollback" );
		query_exec ( query, "unlock tables" );
		query_exec ( query, "set autocommit=1" );
		return false;
	}

	return true;
}

// ----
