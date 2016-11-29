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

#include <applogger.h>
#include <iostream>
#include <apidb_config.h>
#include "apidb_user.h"
#include "apidb_datadr.h"
#include "apidb_dataelement.h"
#include "apidb_exception.h"
#include "apidb_relation.h"
#include "apidb_analysis.h"
#include "apidb_counter.h"
#include "apidb_counter.h"
#include "apidb_pluginregistry.h"
#include "apidb_script.h"
#include "apidb_reportschemas.h"
#include "apidb_thresholdgenerator.h"
#include "apidb_alarmgenerator.h"
#include "apidb_alarmrelation.h"


using namespace std;

#define DEBUG_APIDB_CONFIG_HR                                  1


APIDB_ConfigTable::APIDB_ConfigTable ( ): m_connection ( "APIDB_ConfigTable" )
{
}


bool APIDB_ConfigTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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
			lout3 << "Connected to DB APIDB_ConfigTable."<< endl;
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


APIDB_ConfigTable::~APIDB_ConfigTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_ConfigTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_ConfigTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( m_connection );
}


bool APIDB_ConfigTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_ConfigTable::testConnection()
{
	bool dbConnect = isConnect();
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	return dbConnect;
}


AdamsCompleteConfig * APIDB_ConfigTable::loadConfig(QString nameConfig)
{
	bool noDbError=false;
	//removeDB();


	bool dbConnect=testConnection();

	if ( dbConnect == false )
	{
		return NULL;
	}
	else
	{
		QDateTime time_start=QDateTime::currentDateTime();
		lout3 << "Loading AdamsCompleteConfig at: "<< time_start.toString("dd-MM-yy hh:mm:ss - z") << endl;

		adc = new AdamsCompleteConfig();
		if (adc->isNull()) adc->allocate();

		APIDB_UserTable apidb_user(adc);
		lout3 << "Loading User..." << endl;
		apidb_user.load(nameConfig);
		//apidb_user.debug();

		APIDB_DataDRTable apidb_datadr(adc);
		lout3 << "Loading DataDR..." << endl;
		apidb_datadr.load(nameConfig);
		//apidb_datadr.debug();

		APIDB_DataElementTable apidb_dataelement(adc);
		lout3 << "Loading DataElement..." << endl;
		apidb_dataelement.load(nameConfig);
		//apidb_dataelement.debug();

		APIDB_ExceptionTable apidb_exception(adc);
		lout3 << "Loading Exception..." << endl;
		apidb_exception.load(nameConfig);
		//apidb_exception.debug();

		APIDB_RelationTable apidb_relation(adc);
		lout3 << "Loading Relation..." << endl;
		apidb_relation.load(nameConfig);
		//apidb_relation.debug();

		APIDB_AnalysisTable apidb_analysis(adc);
		lout3 << "Loading Analysis..." << endl;
		apidb_analysis.load(nameConfig);
		//apidb_analysis.debug();

		APIDB_CounterTable apidb_counter(adc);
		lout3 << "Loading Counters..." << endl;
		apidb_counter.load(nameConfig);
		//apidb_counter.debug();

		APIDB_PluginRegistryTable apidb_pluginregistry(adc);
		lout3 << "Loading PluginRegistry..." << endl;
		apidb_pluginregistry.load(nameConfig);
		//apidb_pluginregistry.debug();

		APIDB_ScriptTable apidb_script(adc);
		lout3 << "Loading Script..." << endl;
		apidb_script.load(nameConfig);
		//apidb_script.debug();

		APIDB_ReportSchemasTable apidb_reportschemas(adc);
		lout3 << "Loading ReportSchemas..." << endl;
		apidb_reportschemas.load(nameConfig);
		//apidb_reportschemas.debug();

		APIDB_ThresholdGeneratorTable apidb_thresholdgenerator(adc);
		lout3 << "Loading ThresholdGenerator..." << endl;
		apidb_thresholdgenerator.load(nameConfig);
		//apidb_thresholdgenerator.debug();

		APIDB_AlarmGeneratorTable apidb_alarmgenerator(adc);
		lout3 << "Loading AlarmGenerator..." << endl;
		apidb_alarmgenerator.load(nameConfig);
		//apidb_alarmgenerator.debug();

		APIDB_AlarmRelationTable apidb_alarmrelation(adc);
		lout3 << "Loading AlarmRelation..." << endl;
		apidb_alarmrelation.load(nameConfig);
		//apidb_alarmrelation.debug();

		QDateTime time_stop=QDateTime::currentDateTime();
		lout3 << "Loading AdamsCompleteCOnfig complete at: "<< time_stop.toString("dd-MM-yy hh:mm:ss - z") << endl;
		lout3 << "Elapsed time: " << time_start.msecsTo(time_stop) << " msec. " << endl;

		closeDB();
	}



	return adc;

}

