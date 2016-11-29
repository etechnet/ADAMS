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
#include "apidb_thresholdgenerator.h"

#include <thresholdgeneratorinterface.h>


using namespace std;

#define DEBUG_APIDB_THRESHOLDGENERATOR_HR                                  1


APIDB_ThresholdGeneratorTable::APIDB_ThresholdGeneratorTable ( ): m_connection ( "APIDB_ThresholdGeneratorTable" )
{
  
}

APIDB_ThresholdGeneratorTable::APIDB_ThresholdGeneratorTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_ThresholdGeneratorTable" )
{
	local_adc=adc;
}

bool APIDB_ThresholdGeneratorTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_ThresholdGeneratorTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_ThresholdGeneratorTable::~APIDB_ThresholdGeneratorTable()
{
	if(isConnect()==true) closeDB();
}


void APIDB_ThresholdGeneratorTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_ThresholdGeneratorTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_ThresholdGeneratorTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  
void APIDB_ThresholdGeneratorTable::getPlugins(QString nameConfig)
{
	method ( "getPlugins("+nameConfig+"): " );
	//lout3 << "getPlugins(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.l_plugin_library";
	QString fieldName[]={"NOME_PLUGIN",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="NOME_PLUGIN asc";
	
	QMap<QString, int> fieldName_toPos;
	
	int i=0;
	while(fieldName[i]!="")
	{
		strFieldName+=fieldName[i];
		strFieldName+=",";
		fieldName_toPos.insert ( fieldName[i], i );
		++i;
	}
	

	strFieldName=strFieldName.mid(0,strFieldName.size()-1); // remove , at the end string
	
	strSelect="SELECT "+strFieldName;
	strSelect+=" FROM "+tableName;
	strSelect+=" WHERE "+strWhere;
	//strSelect+=" ORDER BY "+strOrderBy;
	
	//lout << strSelect << endl;
	
	try {
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec (query);
		
		i=0;

		while ( query.next() ) 
		{
			int value=i;
			QString key=query.value(fieldName_toPos.value("NOME_PLUGIN")).toString();
			
			pluginList_it it_pluginList = pluginList.find(key);
			
			if(it_pluginList == pluginList.end() ) // key not found!!!!
			{
				pluginList.insert(key,value);
			}
			//lout3 << key << "," << value << endl;
			++i;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int APIDB_ThresholdGeneratorTable::getIdPlugin(QString namePlugin)
{
	int idPlugin=-1;
	pluginList_it it_pluginList = pluginList.find(namePlugin);
	if(it_pluginList == pluginList.end() )
	{
		idPlugin=-1;
	}else
	{
		idPlugin=pluginList.value(namePlugin);
	}
	return idPlugin;
}

void APIDB_ThresholdGeneratorTable::debug()
{		
	/* debug thresholdgenerator list */
	for ( ThresholdGeneratorIterator it_thr = local_adc->thresholdGeneratorInterface->getIterator(); it_thr != local_adc->thresholdGeneratorInterface->hashEnd(); it_thr++ )
	{
		//if(it_thr.value()->data.idThresholdGenerator!=847) continue;
		lout3 << "idThresholdGenerator   = " << it_thr.value()->data.idThresholdGenerator << endl;
		lout3 << "description            = " << it_thr.value()->data.description << endl;
		lout3 << "typeThreshold          = " << it_thr.value()->data.typeThreshold << endl;
		lout3 << "enableHolydayThreshold = " << it_thr.value()->data.enableHolydayThreshold << endl;
		lout3 << "idPlugin               = " << it_thr.value()->data.idPlugin << endl;
		lout3 << "thresholdPersistence   = " << it_thr.value()->data.thresholdPersistence << endl;
		lout3 << "hoursAggregate         = " << it_thr.value()->data.hoursAggregate << endl;
		lout3 << endl;
	}
}

void APIDB_ThresholdGeneratorTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );	
	bool noDbError=false;
	thresholdgenerator * thr;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_alarm_threshold_generator";
	QString fieldName[]={"ID_THRESHOLD_GENERATOR",
				"DESCRIPTION",
				"TYPE_THRESHOLD",
				"ENABLE_HOLYDAY_THRESHOLD",
				"STR_PLUGIN",
				"THRESHOLD_PERSISTENCE",
				"HOURS_AGGREGATE",
				""
				};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="ID_THRESHOLD_GENERATOR asc";
	QMap<QString, int> fieldName_toPos;
	
	while(fieldName[i]!="")
	{
		strFieldName+=fieldName[i];
		strFieldName+=",";
		fieldName_toPos.insert ( fieldName[i], i );
		++i;
	}

	strFieldName=strFieldName.mid(0,strFieldName.size()-1); // remove , at the end string
	
	strSelect="SELECT "+strFieldName;
	strSelect+=" FROM "+tableName;
	strSelect+=" WHERE "+strWhere;
	strSelect+=" ORDER BY "+strOrderBy;
	
	//lout << strSelect << endl;

	try {
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec (query);

	
		while ( query.next() ) 
		{	
			thr = new thresholdgenerator;
			
			thr->data.idThresholdGenerator = query.value(fieldName_toPos.value("ID_THRESHOLD_GENERATOR")).toInt();
			c_qstrncpy ( thr->data.description, qPrintable (query.value(fieldName_toPos.value("DESCRIPTION")).toString() ), LONG_DESC_LEN );
			thr->data.typeThreshold = query.value(fieldName_toPos.value("TYPE_THRESHOLD")).toInt();
			thr->data.enableHolydayThreshold = toBoolean( query.value(fieldName_toPos.value("ENABLE_HOLYDAY_THRESHOLD")).toString() );
			thr->data.idPlugin = getIdPlugin(query.value(fieldName_toPos.value("STR_PLUGIN")).toString());
			thr->data.thresholdPersistence = query.value(fieldName_toPos.value("THRESHOLD_PERSISTENCE")).toInt();
			thr->data.hoursAggregate = query.value(fieldName_toPos.value("HOURS_AGGREGATE")).toInt();
		  
			local_adc->thresholdGeneratorInterface->add(thr);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}

	
}

void APIDB_ThresholdGeneratorTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_ThresholdGeneratorTable::load(QString nameConfig)
{
	
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getPlugins(nameConfig);	
		fillFromDB(nameConfig);
		closeDB();
	}
}

