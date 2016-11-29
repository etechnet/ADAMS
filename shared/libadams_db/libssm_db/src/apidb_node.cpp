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

#include <apidb_node.h>
#include <applogger.h>
#include <iostream>
using namespace std;

#define DEBUG_APIDB_SSM_HR                                  1


APIDB_NodeTable::APIDB_NodeTable ( )
	: nodetable ( 0 ),
	  m_connection ( "APIDB_NodeTable" ),
	  connected ( false )
{
}

bool APIDB_NodeTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
{
	method ( "openDB(QMYSQL)" );

	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;
	connected = true;

	db = QSqlDatabase::addDatabase ( "QMYSQL", m_connection ); // QMYSQL -> MySql Database
	db.setDatabaseName ( strDBNAME );
	db.setUserName ( strUSER );
	db.setPassword ( strPSWD );

	try {
		db_open ( db );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		connected = false;
	}

	return connected;
}

void APIDB_NodeTable::closeDB()
{
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
	connected = false;
}


APIDB_NodeTable::~APIDB_NodeTable()
{
	closeDB();
	if ( nodetable )
		delete ( nodetable );
}


QString APIDB_NodeTable::fmtNumber ( float num )
{
	QString buf;
	buf.sprintf ( "%10.2f", num );
	return buf;
}

bool APIDB_NodeTable::insertCreate ( int occurrenceNum )
{
	unsigned long i = 0;

	QString values = "";
	strInsert = "";

	QStringList::Iterator itVal = strValues.begin();

	strInsert += "INSERT INTO ";
	strInsert += strTableName;
	strInsert += " VALUES";

	for ( int i = 0; i < occurrenceNum; i++ ) {
		QList<int>::Iterator itType = strType.begin();

		values = " (";
		for ( int k = 0; k < fieldsNum; k++ ) {
			values += formatValue ( *itType, *itVal );
			//lout3 << " valore("<< k << "," << fieldsNum<< ")=" << *itVal << ", " << formatValue(*itType,*itVal) << ","<< *itType << endl;
			if ( k != fieldsNum - 1 ) {
				values += ",";
			}
			++itVal;
			++itType;
		}
		if ( i == occurrenceNum - 1 )
			values += ")";
		else
			values += "),";
		strInsert += values;
	}
}

bool APIDB_NodeTable::insertRecordInTable_BLOC()
{
	open_connection();

	method ( "insertRecordInTable_BLOC" );

	try {
		QSqlQuery query ( db );
		query.prepare ( strInsert );
		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	strValues.clear();
	return true;
}

bool APIDB_NodeTable::writeData ( int type )
{
	return insertRecordInTable_BLOC();
}

QString APIDB_NodeTable::formatValue ( int itType, QString value )
{
	QString str = "";
	switch ( itType ) {
		case T_STRING: {
			str += "'" + value + "'";
		}
		break;

		case T_NUMBER: {
			str += value;
		}
		break;

		case T_DATE: {
			str += "to_date('" + value + "','yyyymmdd')";
		}
		break;

		case T_DATETIME: {
			str += "to_date('" + value + "','yyyymmdd hhmmss')";
		}
		break;
	}

	return str;
}


QString APIDB_NodeTable::getTableName ( int t )
{
	QString appo = "UNDEFINED";

	switch ( t ) {
		case INSERT_INTO_T_NODE: {
			appo = "t_node";
		}
		break;
	}

	return appo;
}

bool APIDB_NodeTable::deleteRecordInTable ( const QString strDelete ) /* OK */
{
	open_connection();

	method ( "deleteRecordInTable(" + strDelete + ")" );

	try {
		QSqlQuery query ( db );
		query.prepare ( strDelete );
		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	closeDB();
	return true;
}


void APIDB_NodeTable::insertPrepare ( int type )
{

	strFields.clear();
	strType.clear();

	strInsert = "";
	switch ( type ) {
		case INSERT_INTO_T_NODE: { // insert into T_NODE
			strTableName = getTableName ( type );

			/* campi fissi */
			strFields.append ( "node_id" );
			strType.append ( T_NUMBER );
			strFields.append ( "name" );
			strType.append ( T_STRING );
			strFields.append ( "description" );
			strType.append ( T_STRING );
			strFields.append ( "process_group_id" );
			strType.append ( T_NUMBER );
			strFields.append ( "active" );
			strType.append ( T_NUMBER );
			strFields.append ( "location" );
			strType.append ( T_STRING );
		}
		break;

		default: {
		} break;
	}

	//lout << "insertPrepare()= "<< strInsert << "," << strTableName  << endl;

	fieldsNum = strFields.count();
}

bool APIDB_NodeTable::deleteNode ( QString nodeName )
{
	QString strDelete = "DELETE from t_node where name='" + nodeName + "'";
	return deleteRecordInTable ( strDelete );
}


bool APIDB_NodeTable::truncateTNODE()
{
	QString strDelete = "DELETE from t_node";
	return deleteRecordInTable ( strDelete );
}

bool APIDB_NodeTable::loadNode()
{
	open_connection();

	method ( "loadNode" );

	NodeTable nodetable;
	strSelect = "";

	strSelect += "SELECT node_id,name,description,process_group_id,active,location ";
	strSelect += " FROM t_node";


	try {
		QSqlQuery query ( db );
		query.prepare ( strSelect );
		query_exec ( query );


		while ( query.next() ) {
			nodetable.node_id = query.value ( 0 ).toInt();
			nodetable.name = query.value ( 1 ).toString();
			nodetable.description = query.value ( 2 ).toString();
			nodetable.process_group_id = query.value ( 3 ).toInt();
			nodetable.active = query.value ( 4 ).toInt();
			nodetable.location = query.value ( 5 ).toString();

			nodeList.append ( nodetable );

			debugNode ( &nodetable );
		}
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		closeDB();
		return false;
	}

	closeDB();
	return true;
}

NodeTable * APIDB_NodeTable::getNode ( QString nodeName )
{
	open_connection();

	method ( "getNode" );

	strSelect = "";
	strSelect += "SELECT node_id,name,description,process_group_id,active,location ";
	strSelect += " FROM t_node where name='" + nodeName + "'";

	try {
		QSqlQuery query ( db );
		query.prepare ( strSelect );
		query_exec ( query );

		if ( query.next() == true ) {
			nodetable = new NodeTable();
			nodetable->node_id = query.value ( 0 ).toInt();
			nodetable->name = query.value ( 1 ).toString();
			nodetable->description = query.value ( 2 ).toString();
			nodetable->process_group_id = query.value ( 3 ).toInt();
			nodetable->active = query.value ( 4 ).toInt();
			nodetable->location = query.value ( 5 ).toString();
		}
	}
	catch ( A_DBException & de ) {
		nodetable = NULL;
		lout << de.preface() << de.explain() << endl;
		nodetable = 0;
	}

	closeDB();
	return nodetable;
}

void APIDB_NodeTable::debugNode ( NodeTable *node )
{
	if ( node != NULL ) {
		lout3 << "node_id          = " << node->node_id << endl;
		lout3 << "name             = " << node->name << endl;
		lout3 << "description      = " << node->description << endl;
		lout3 << "process_group_id = " << node->process_group_id << endl;
		lout3 << "active           = " << node->active << endl;
		lout3 << "location         = " << node->location << endl;
	}

	else {
		lout3 << "NODE is NULL" << endl;
	}
}





