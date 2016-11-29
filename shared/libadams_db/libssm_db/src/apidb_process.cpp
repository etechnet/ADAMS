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

#include <apidb_process.h>
#include <applogger.h>
#include <iostream>

using namespace std;

#define DEBUG_APIDB_SSM_HR                                  1


APIDB_ProcessTable::APIDB_ProcessTable ( )
	: processtable ( 0 ),
	  m_connection ( "APIDB_ProcessTable" ),
	  connected ( false )
{
}

bool APIDB_ProcessTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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

void APIDB_ProcessTable::closeDB()
{
	if ( !connected )
		return;

	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
	connected = false;
}


APIDB_ProcessTable::~APIDB_ProcessTable()
{
	closeDB();
	if ( processtable )
		delete ( processtable );
}

QString APIDB_ProcessTable::fmtNumber ( float num )
{
	char retVal[15] = "";
	sprintf ( retVal, "%10.2f\0", num );
	return retVal;
}

bool APIDB_ProcessTable::insertCreate ( int occurrenceNum )
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

bool APIDB_ProcessTable::insertRecordInTable_BLOC()
{
	open_connection();
	method ( "insertRecordInTable_BLOC(): " );

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
	closeDB();
	return true;
}

bool APIDB_ProcessTable::writeData ( int type )
{
	return insertRecordInTable_BLOC();
}

QString APIDB_ProcessTable::formatValue ( int itType, QString value )
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


QString APIDB_ProcessTable::getTableName ( int t )
{
	QString appo = "UNDEFINED";

	switch ( t ) {
		case INSERT_INTO_T_PROCESS: {
			appo = "t_process";
		}
		break;
	}

	return appo;
}

bool APIDB_ProcessTable::deleteRecordInTable ( const QString strDelete ) /* OK */
{
	open_connection();
	method ( "deleteRecordInTable" );

	try {
		QSqlQuery query ( db );
		query.prepare ( strDelete );
		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		closeDB();
		return false;
	}

	closeDB();
	return true;
}


void APIDB_ProcessTable::insertPrepare ( int type )
{

	strFields.clear();
	strType.clear();

	strInsert = "";
	switch ( type ) {
		case INSERT_INTO_T_PROCESS: { // insert into T_PROCESS
			strTableName = getTableName ( type );

			/* campi fissi */
			strFields.append ( "process_id" );
			strType.append ( T_NUMBER );
			strFields.append ( "process_name" );
			strType.append ( T_STRING );
			strFields.append ( "process_type" );
			strType.append ( T_NUMBER );
			strFields.append ( "schedule_ignore" );
			strType.append ( T_NUMBER );
			strFields.append ( "start_cmd" );
			strType.append ( T_STRING );
			strFields.append ( "wake_time" );
			strType.append ( T_DATETIME );
		}
		break;

	}

	//lout << "insertPrepare()= "<< strInsert << "," << strTableName  << endl;

	fieldsNum = strFields.count();
}

bool APIDB_ProcessTable::deleteProcess ( QString processName )
{
	QString strDelete = "DELETE from t_process where process_name='" + processName + "'";
	return deleteRecordInTable ( strDelete );
}


bool APIDB_ProcessTable::truncateTPROCESS()
{
	QString strDelete = "DELETE from t_process";
	return deleteRecordInTable ( strDelete );
}

bool APIDB_ProcessTable::loadProcess()
{
	open_connection();
	method ( "loadProcess" );

	ProcessTable processtable;

	QString strFieldName = "";
	QString tableName = "t_process";
	QString fieldName[] = {"process_id",
	                       "process_name",
	                       "process_type",
	                       "schedule_ignore",
	                       "start_cmd",
	                       "wake_time",
	                       ""
	                      };
	QString strWhere = "1";
	QString strOrderBy = "process_name asc";

	QMap<QString, int> fieldName_toPos;

	int i = 0;
	while ( fieldName[i] != "" ) {
		strFieldName += fieldName[i];
		strFieldName += ",";
		fieldName_toPos.insert ( fieldName[i], i );
		++i;
	}


	strFieldName = strFieldName.mid ( 0, strFieldName.size() - 1 ); // remove , at the end string

	strSelect = "SELECT " + strFieldName;
	strSelect += " FROM " + tableName;
	strSelect += " WHERE " + strWhere;
	strSelect += " ORDER BY " + strOrderBy;

	//lout3 << strSelect << endl;

	try {
		QSqlQuery query ( db );
		query.prepare ( strSelect );
		query_exec ( query );


		while ( query.next() ) {

			processtable.process_id = query.value ( fieldName_toPos.value ( "process_id" ) ).toInt();
			processtable.process_name = query.value ( fieldName_toPos.value ( "process_name" ) ).toString();
			processtable.process_type = query.value ( fieldName_toPos.value ( "process_type" ) ).toInt();
			processtable.schedule_ignore = query.value ( fieldName_toPos.value ( "schedule_ignore" ) ).toInt();
			processtable.start_cmd = query.value ( fieldName_toPos.value ( "start_cmd" ) ).toString();
			processtable.wake_time = query.value ( fieldName_toPos.value ( "wake_time" ) ).toDateTime();

			processList.append ( processtable );

			debugProcess ( &processtable );
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

ProcessTable * APIDB_ProcessTable::getProcess ( QString processName )
{
	open_connection();
	method ( "getProcess(" + processName + ")" );

	QString strFieldName = "";
	QString tableName = "t_process";
	QString fieldName[] = {"process_id",
	                       "process_name",
	                       "process_type",
	                       "schedule_ignore",
	                       "start_cmd",
	                       "wake_time",
	                       ""
	                      };
	QString strWhere = " process_name='" + processName + "'";
	QString strOrderBy = "process_name asc";

	QMap<QString, int> fieldName_toPos;

	int i = 0;
	while ( fieldName[i] != "" ) {
		strFieldName += fieldName[i];
		strFieldName += ",";
		fieldName_toPos.insert ( fieldName[i], i );
		++i;
	}


	strFieldName = strFieldName.mid ( 0, strFieldName.size() - 1 ); // remove , at the end string

	strSelect = "SELECT " + strFieldName;
	strSelect += " FROM " + tableName;
	strSelect += " WHERE " + strWhere;
	strSelect += " ORDER BY " + strOrderBy;

	try {
		QSqlQuery query ( db );
		query.prepare ( strSelect );
		query_exec ( query );

		if ( query.next() == true ) {
			processtable = new ProcessTable();
			processtable->process_id = query.value ( fieldName_toPos.value ( "process_id" ) ).toInt();
			processtable->process_name = query.value ( fieldName_toPos.value ( "process_name" ) ).toString();
			processtable->process_type = query.value ( fieldName_toPos.value ( "process_type" ) ).toInt();
			processtable->schedule_ignore = query.value ( fieldName_toPos.value ( "schedule_ignore" ) ).toInt();
			processtable->start_cmd = query.value ( fieldName_toPos.value ( "start_cmd" ) ).toString();
			processtable->wake_time = query.value ( fieldName_toPos.value ( "wake_time" ) ).toDateTime();

		}
	}
	catch ( A_DBException & de ) {
		processtable = NULL;
		lout << de.preface() << de.explain() << endl;
		processtable = 0;
	}


	closeDB();
	return processtable;
}

void APIDB_ProcessTable::debugProcess ( ProcessTable *process )
{
	if ( process ) {
		lout3 << "process_id      = " << process->process_id << endl;
		lout3 << "process_name    = " << process->process_name << endl;
		lout3 << "process_type    = " << process->process_type << endl;
		lout3 << "schedule_ignore = " << process->schedule_ignore << endl;
		lout3 << "start_cmd       = " << process->start_cmd << endl;
		lout3 << "wake_time       = " << process->wake_time.toString ( "yyyy-MM-dd hh:mm:ss" ) << endl;
	}

	else {
		lout3 << "PROCESS is NULL" << endl;
	}
}





