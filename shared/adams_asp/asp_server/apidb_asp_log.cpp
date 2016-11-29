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
#  Author Name <raffale.ficcadenti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--08-10-13 Raffaele Ficcadenti
#
*/

#include <apidb_asp_log.h>
#include <applogger.h>
#include <iostream>

using namespace std;

#define DEBUG_APIDB_ASP_LOG_HR                                  1
#define DB_SCHEMA						"adams_log"


APIDB_AspLog::APIDB_AspLog ( ): m_connection ( "APIDB_AspLog" )
{
}

void APIDB_AspLog::setDBNameCnnection (QString str)
{
	m_connection = m_connection+"::"+str;
}


bool APIDB_AspLog::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
{
	bool noDbError = false;

	method ( "openDB(APIDB_AspLog): " );	
	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;
	
	if ( isConnect() == false )
	{   
		db = QSqlDatabase::addDatabase ( "QMYSQL" ,m_connection); // QMYSQL -> MySql Database
		db.setDatabaseName ( strDBNAME );
		db.setUserName ( strUSER );
		db.setPassword ( strPSWD );
		
		try {
			db_open( db );
			lout3 << "Connected to DB APIDB_AspLog ."<< endl;
			noDbError=true;
		}
		catch ( A_DBException & de ) 
		{
			lout << de.preface() << de.explain() << endl;
			noDbError=false;
		}
	}
	else
	{
		lout3 << "Connected to DB is already open." << endl;	
		noDbError=true;
	}
	
	setError ( db );
			
	return noDbError;
}

bool APIDB_AspLog::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	//lout3 << "APIDB_AspLog.isOpen("<< m_connection << ")=" << db_test.isOpen() << endl;	
	return db_test.isOpen();
}

void APIDB_AspLog::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_AspLog::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}


APIDB_AspLog::~APIDB_AspLog()
{
	closeDB();
}

bool APIDB_AspLog::writeOnDB(const S_APP_LOG &sAppLog)
{
	bool noDbError = false;
	
	insertPrepare ( INSERT_INTO_T_LOG_APPLICATION );
	
	strValues.append ( sAppLog.timeStamp );
	strValues.append ( sAppLog.ip_server );
	strValues.append ( sAppLog.hostname_server );
	strValues.append ( sAppLog.ip_client );
	strValues.append ( sAppLog.hostname_client );
	strValues.append ( sAppLog.application_user );
	strValues.append ( sAppLog.client_user );
	strValues.append ( sAppLog.application );
	strValues.append ( sAppLog.action );
	strValues.append ( sAppLog.action_object );
	strValues.append ( sAppLog.parameter );
	strValues.append ( sAppLog.successful );
	strValues.append ( QString::number(sAppLog.return_code) );
	
	insertCreate ( 1 );
	noDbError = writeData ( INSERT_INTO_T_LOG_APPLICATION );

	strValues.clear();
	
	return noDbError;
}

bool APIDB_AspLog::debug(const S_APP_LOG &sAppLog)
{
	QDateTime dt_current = QDateTime::currentDateTime();

	lout3 << "***********************************************************************" << endl;
	lout3 << "dt_current:       " << dt_current.toString() << endl;
	lout3 << "timeStamp:        " << sAppLog.timeStamp << endl;
	lout3 << "ip_server:        " << sAppLog.ip_server << endl;
	lout3 << "hostname_server:  " << sAppLog.hostname_server << endl;
	lout3 << "ip_client:        " << sAppLog.ip_client << endl;
	lout3 << "hostname_client:  " << sAppLog.hostname_client << endl;
	lout3 << "application_user: " << sAppLog.application_user << endl;
	lout3 << "client_user:      " << sAppLog.client_user << endl;
	lout3 << "application:      " << sAppLog.application << endl;
	lout3 << "action:           " << sAppLog.action << endl;
	lout3 << "action_object:    " << sAppLog.action_object << endl;
	lout3 << "parameter:        " << sAppLog.parameter << endl;
	lout3 << "successful:       " << sAppLog.successful << endl;
	lout3 << "return_code:      " << sAppLog.return_code<< endl;
	lout3 << "***********************************************************************" << endl;
	
	return true;
}

bool APIDB_AspLog::insertRecordInTable_BLOC()
{
	method ( "APIDB_AspLog::insertRecordInTable_BLOC(): " );	
	
	bool noDbError = false;
	
	//lout << "-----> APIDB_AspLog::insertRecordInTable_BLOC("<< qPrintable(strTableName)<< ") START" << endl;
	if ( isConnect() == true ) 
	{
		try {
			QSqlQuery query(db);
			query.prepare( strInsert );
			query_exec (query);
			noDbError=true;
			//lout << "-----> APIDB_AspLog::strInsert("<< qPrintable(strInsert) << ")" << endl;
		}
		catch ( A_DBException & de ) 
		{
			lout << de.preface() << de.explain() << endl;
			noDbError=false;
		}
	}
	else
	{	
		noDbError=false;
	}
	strValues.clear();
	//lout << "-----> APIDB_AspLog::insertRecordInTable_BLOC("<< qPrintable(strTableName) << ") END" << endl;
	
	return noDbError;
}

void APIDB_AspLog::insertPrepare ( int type )
{

	strFields.clear();
	strType.clear();
	
	strInsert = "";
	switch ( type ) {
		case INSERT_INTO_T_LOG_APPLICATION: { // insert into t_log_application
				strTableName = getTableName ( type );
				/* campi fissi */
				strFields.append ( "timestamp" );
				strType.append ( T_STRING );
				strFields.append ( "ip_server" );
				strType.append ( T_STRING );
				strFields.append ( "hostname_server" );
				strType.append ( T_STRING );
				strFields.append ( "ip_client" );
				strType.append ( T_STRING );
				strFields.append ( "hostname_client" );
				strType.append ( T_STRING );
				strFields.append ( "application_user" );
				strType.append ( T_STRING );
				strFields.append ( "client_user" );
				strType.append ( T_STRING );
				strFields.append ( "application" );
				strType.append ( T_STRING );
				strFields.append ( "action" );
				strType.append ( T_STRING );
				strFields.append ( "object" );
				strType.append ( T_STRING );
				strFields.append ( "parameter" );
				strType.append ( T_STRING );
				strFields.append ( "successful" );
				strType.append ( T_STRING );
				strFields.append ( "return_code" );
				strType.append ( T_NUMBER );
			}
			break;

		default: {
			} break;
	}

	//lout << "insertPrepare()= "<< strInsert << "," << strTableName  << endl;

	fieldsNum = strFields.count();
}

bool APIDB_AspLog::insertCreate ( int occurrenceNum )
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
		if(i==occurrenceNum-1)
			values += ")";
		else
			values += "),";
		strInsert += values;
	}


	//lout3 << strInsert << endl;
	//lout3 << " len stringa= " << QString(strInsert).length() << endl;
}

bool APIDB_AspLog::writeData ( int type )
{
	bool bRet2 = insertRecordInTable_BLOC();
}

QString APIDB_AspLog::getTableName ( int t )
{
	QString appo = "UNDEFINED";

	switch ( t ) {
		case INSERT_INTO_T_LOG_APPLICATION: {
				appo = QString(DB_SCHEMA)+".t_log_application";
			}
			break;

		default:
			{} break;
	}

	return appo;
}

QString APIDB_AspLog::formatValue ( int itType, QString value )
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






