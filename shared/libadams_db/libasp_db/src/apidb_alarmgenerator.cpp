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
#include "apidb_alarmgenerator.h"

#include <alarmgeneratorinterface.h>


using namespace std;

#define DEBUG_APIDB_ALARMGENERATOR_HR                                  1


APIDB_AlarmGeneratorTable::APIDB_AlarmGeneratorTable ( ): m_connection ( "APIDB_AlarmGeneratorTable" )
{
  
}

APIDB_AlarmGeneratorTable::APIDB_AlarmGeneratorTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_AlarmGeneratorTable" )
{
	local_adc=adc;
}

bool APIDB_AlarmGeneratorTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_AlarmGeneratorTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_AlarmGeneratorTable::~APIDB_AlarmGeneratorTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_AlarmGeneratorTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_AlarmGeneratorTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}


bool APIDB_AlarmGeneratorTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}

void APIDB_AlarmGeneratorTable::getPlugins(QString nameConfig)
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

int APIDB_AlarmGeneratorTable::getIdPlugin(QString namePlugin)
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

void APIDB_AlarmGeneratorTable::getThrGenerators(QString nameConfig)
{
	method ( "getThrGenerators("+nameConfig+"): " );	
	//lout3 << "getThrGenerators(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_alarm_generator_thresholds";
	QString fieldName[]={"ID_ALARM_GENERATOR","ID_THRESHOLD_GENERATOR",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="ID_ALARM_GENERATOR asc";
	
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
	strSelect+=" ORDER BY "+strOrderBy;
	
	//lout << strSelect << endl;
	
	try {
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec (query);
	
		while ( query.next() ) 
		{
			int key=query.value(fieldName_toPos.value("ID_ALARM_GENERATOR")).toInt();
			int value=query.value(fieldName_toPos.value("ID_THRESHOLD_GENERATOR")).toInt();
			
			thrGeneratorList_it it_thrGeneratorList = thrGeneratorList.find(key);
			
			if(it_thrGeneratorList == thrGeneratorList.end() ) // key not found!!!!
			{
				thrGenerator thrG;
				thrG.append(value);
				thrGeneratorList.insert(key,thrG);
			}
			else // key found
			{
				it_thrGeneratorList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int * APIDB_AlarmGeneratorTable::getThrGenerator(int idAlarmGenerator)
{
	//lout3 << "getThrGenerator(" << idAlarmGenerator << ")" << endl;	
	int i=0;
	int *thrG=new int[MAX_THRESHOLD_GENERATOR];
	for ( i = 0; i < MAX_THRESHOLD_GENERATOR; i++ )
	{
		thrG[i] = 0;
	}
	
	thrGeneratorList_it it_thrGeneratorList = thrGeneratorList.find(idAlarmGenerator);
	
	if(it_thrGeneratorList != thrGeneratorList.end() ) // key found!!!!
	{	
		thrGenerator thrGen = it_thrGeneratorList.value();
		
		thrGenerator_it it_thrGenerator;
		i=0;
		for (it_thrGenerator = thrGen.begin(); it_thrGenerator != thrGen.end(); ++it_thrGenerator)
		{
			thrG[i]=*it_thrGenerator;
			++i;
		}
	}
	
	return thrG;
}


void APIDB_AlarmGeneratorTable::debug()
{		
	/* debug alarmgenerator list */
	for ( AlarmGeneratorIterator it_al = local_adc->alarmGeneratorInterface->getIterator(); it_al != local_adc->alarmGeneratorInterface->hashEnd(); it_al++ )
	{
		//if(it_al.value()->data.idAlarmGenerator!=847) continue;
		lout3 << "idAlarmGenerator    = " << it_al.value()->data.idAlarmGenerator << endl;
		lout3 << "shortDescription    = " << it_al.value()->data.shortDescription << endl;
		lout3 << "longDescription     = " << it_al.value()->data.longDescription << endl;
		lout3 << "idPlugin            = " << it_al.value()->data.idPlugin << endl;
		lout3 << "thresholdManagement = " << it_al.value()->data.thresholdManagement << endl;
		lout3 << "idThresholdGenerator (";
		for ( int i = 0; i < MAX_THRESHOLD_GENERATOR; i++ )
			{
				lout3  << it_al.value()->data.idThresholdGenerator[i] << ",";
			}
		lout3 << ")" << endl;
		lout3 << endl;
	}
}

void APIDB_AlarmGeneratorTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );
	bool noDbError=false;
	alarmgenerator * al;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_alarm_generator";
	QString fieldName[]={"ID_ALARM_GENERATOR",
				"SHORT_DESCRIPTION",
				"LONG_DESCRIPTION",
				"STR_PLUGIN",
				"THRESHOLD_MANAGEMENT",
				""				//endlist
				};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="ID_ALARM_GENERATOR asc";
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
			al = new alarmgenerator;
			
			al->data.idAlarmGenerator = query.value(fieldName_toPos.value("ID_ALARM_GENERATOR")).toInt();
			c_qstrncpy ( al->data.shortDescription, qPrintable (query.value(fieldName_toPos.value("SHORT_DESCRIPTION")).toString() ), SHORT_DESC_LEN );
			c_qstrncpy ( al->data.longDescription, qPrintable (query.value(fieldName_toPos.value("LONG_DESCRIPTION")).toString() ), LONG_DESC_LEN );
			al->data.idPlugin = getIdPlugin(query.value(fieldName_toPos.value("STR_PLUGIN")).toString());
			al->data.thresholdManagement = toBoolean( query.value(fieldName_toPos.value("THRESHOLD_MANAGEMENT")).toString() );
			int *thrGenerator=getThrGenerator(al->data.idAlarmGenerator);
			for ( int i = 0; i < MAX_THRESHOLD_GENERATOR; i++ )
			{
				al->data.idThresholdGenerator[i] = thrGenerator[i];
			}
		
			local_adc->alarmGeneratorInterface->add(al);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}

	
}

void APIDB_AlarmGeneratorTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_AlarmGeneratorTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getPlugins(nameConfig);
		getThrGenerators(nameConfig);
		fillFromDB(nameConfig);
		closeDB();
	}
}

