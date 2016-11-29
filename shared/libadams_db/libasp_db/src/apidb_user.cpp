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

#include <applogger.h>
#include <iostream>
#include "apidb_user.h"

#include <userinterface.h>


using namespace std;

#define DEBUG_APIDB_USER_HR                                  1


APIDB_UserTable::APIDB_UserTable ( ): m_connection ( "APIDB_UserTable" )
{
  
}

APIDB_UserTable::APIDB_UserTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_UserTable" )
{
	local_adc=adc;
}

bool APIDB_UserTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_UserTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
{
	method ( "openDB(): " );	
	bool noDbError = false;
	
	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;

	if ( isConnect() == false )
	{   
		db = QSqlDatabase::addDatabase ( "QMYSQL",m_connection ); // QMYSQL -> MySql Database
		db.setDatabaseName ( strDBNAME );
		db.setUserName ( strUSER );
		db.setPassword ( strPSWD );
		
		try {
			db_open( db );
			lout3 << "Connected to DB."<< endl;
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


APIDB_UserTable::~APIDB_UserTable()
{
	if(isConnect()==true) closeDB();
}



void APIDB_UserTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_UserTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_UserTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  

void APIDB_UserTable::debug()
{		
	/* debug dataelement list */
	for ( UserIterator it_user = local_adc->userInterface->getIterator(); it_user != local_adc->userInterface->hashEnd(); it_user++ )
	{
		//if(it_user.value()->data.idElement!=847) continue;
		lout3 << "login                 = " << it_user.value()->data.login << endl;
		lout3 << "passwd                = " << it_user.value()->data.passwd << endl;
		lout3 << "userAdmin             = " << it_user.value()->data.userAdmin << endl;
		lout3 << "configAdmin           = " << it_user.value()->data.configAdmin << endl;
		lout3 << "enabledConfigurations = (";
		for ( int i = 0; i < MAX_ENABLE_CONFIGS; i++ )
		{
			lout3 << it_user.value()->data.enabledConfigurations[i] << ",";
		}
		lout3 << ")" <<endl;
		lout3 << endl;
	  
	}
}

void APIDB_UserTable::fillFromDB (QString nameConfig)
{
	User * u=new User();
	
	c_qstrncpy ( u->data.login, "test", USR_LOGIN_LEN );
	c_qstrncpy ( u->data.passwd, "test", USR_PASSWD_LEN );
	u->data.userAdmin = true;
	u->data.configAdmin = true;
	for ( int i = 0; i < MAX_ENABLE_CONFIGS; i++ ) {
		c_qstrncpy ( u->data.enabledConfigurations[i], "test.conf", MAX_CONFIG_FILENAME );
	}

	local_adc->userInterface->add(u);
}

void APIDB_UserTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_UserTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		fillFromDB(nameConfig);
		closeDB();
	}
}

