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
#include "apidb_alarmrelation.h"

#include <alarmrelationinterface.h>


using namespace std;

#define DEBUG_APIDB_ALARMRELATION_HR                                  1


APIDB_AlarmRelationTable::APIDB_AlarmRelationTable ( ): m_connection ( "APIDB_AlarmRelationTable" )
{
  
}

APIDB_AlarmRelationTable::APIDB_AlarmRelationTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_AlarmRelationTable" )
{
	local_adc=adc;
}

bool APIDB_AlarmRelationTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_AlarmRelationTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_AlarmRelationTable::~APIDB_AlarmRelationTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_AlarmRelationTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}



void APIDB_AlarmRelationTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_AlarmRelationTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  
void APIDB_AlarmRelationTable::getPlugins(QString nameConfig)
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

int APIDB_AlarmRelationTable::getIdPlugin(QString namePlugin)
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


void APIDB_AlarmRelationTable::getRelationElements(QString nameConfig)
{
	method ( "getRelationElements("+nameConfig+"): " );
	//lout3 << "getRelationElements(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_alarm_relation_elements";
	
	
	QString fieldName[]={"ID_ALARM_RELATION","ID_ELEMENT","ENABLED_DETAIL",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="POSITION_ELEMENT asc";
	
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
			int key=query.value(fieldName_toPos.value("ID_ALARM_RELATION")).toInt();
			int value1=query.value(fieldName_toPos.value("ID_ELEMENT")).toInt();
			int value2=query.value(fieldName_toPos.value("ENABLED_DETAIL")).toInt();
			
			relationElementsList_it it_relationElementsList = relationElementsList .find(key);
			
			if(it_relationElementsList == relationElementsList.end() ) // key not found!!!!
			{
				relationElement rel_e;
				
				rel_e.append(value1);
				relationElementsList.insert(key,rel_e);
			}
			else // key found
			{
				it_relationElementsList.value().append(value1);
			}
			
			relationElementEnabledList_it it_relationElementEnabledList = relationElementEnabledList .find(key);
			
			if(it_relationElementEnabledList == relationElementEnabledList.end() ) // key not found!!!!
			{
				relationElementEnabled rel_enable;
				
				rel_enable.append(value2);
				relationElementEnabledList.insert(key,rel_enable);
			}
			else // key found
			{
				it_relationElementEnabledList.value().append(value2);
			}
			
			//lout3 << key << "," << value1 << "," << value2 << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int *APIDB_AlarmRelationTable::getRelationElement(int idAlarmRelation)
{
	//lout3 << "getRelationElement(" << idAlarmRelation << ")" << endl;	
	int i=0;
	int *rel_e=new int[MAX_DIMENSION];
	for ( i = 0; i < MAX_DIMENSION; i++ )
	{
		rel_e[i] = 0;
	}
	
	relationElementsList_it it_relationElementsList = relationElementsList.find(idAlarmRelation);
	
	if(it_relationElementsList != relationElementsList.end() ) // key found!!!!
	{	
		relationElement relElem = it_relationElementsList.value();
		
		relationElement_it it_relationElement;
		i=0;
		for (it_relationElement = relElem.begin(); it_relationElement != relElem.end(); ++it_relationElement)
		{
			rel_e[i]=*it_relationElement;
			++i;
		}
	}
	
	return rel_e;
}

int *APIDB_AlarmRelationTable::gelReationElementEnabled(int idAlarmRelation)
{
	//lout3 << "gelReationElementEnabled(" << idAlarmRelation << ")" << endl;	
	int i=0;
	int *rel_enabled=new int[MAX_DIMENSION];
	for ( i = 0; i < MAX_DIMENSION; i++ )
	{
		rel_enabled[i] = 0;
	}
	
	relationElementEnabledList_it it_relationElementEnabledList = relationElementEnabledList.find(idAlarmRelation);
	
	if(it_relationElementEnabledList != relationElementEnabledList.end() ) // key found!!!!
	{	
		relationElementEnabled relElemEnabled = it_relationElementEnabledList.value();
		
		relationElementEnabled_it it_relationElementEnabled;
		i=0;
		for (it_relationElementEnabled = relElemEnabled.begin(); it_relationElementEnabled != relElemEnabled.end(); ++it_relationElementEnabled )
		{
			rel_enabled[i]=*it_relationElementEnabled;
			++i;
		}
	}
	
	return rel_enabled;
}

void APIDB_AlarmRelationTable::getRelationHandlers(QString nameConfig)
{
	method ( "getRelationHandlers("+nameConfig+"): " );
	//lout3 << "getRelationHandlers(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_alarm_relation_handlers";
	
	
	QString fieldName[]={"ID_ALARM_RELATION","ID_ALARM_GENERATOR",""};
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
			int key=query.value(fieldName_toPos.value("ID_ALARM_RELATION")).toInt();
			int value=query.value(fieldName_toPos.value("ID_ALARM_GENERATOR")).toInt();
			
			relationHandlerList_it it_relationHandlerList = relationHandlerList.find(key);
			
			if(it_relationHandlerList == relationHandlerList.end() ) // key not found!!!!
			{
				relationHandler rel_h;
				
				rel_h.append(value);
				relationHandlerList.insert(key,rel_h);
			}
			else // key found
			{
				it_relationHandlerList.value().append(value);
			}
			
			
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int *APIDB_AlarmRelationTable::getRelationHandler(int idAlarmRelation)
{
	//lout3 << "getRelationHandler(" << idAlarmRelation << ")" << endl;	
	int i=0;
	int *rel_h=new int[MAX_ALARM_GENERATOR];
	for ( i = 0; i < MAX_ALARM_GENERATOR; i++ )
	{
		rel_h[i] = 0;
	}
	
	relationHandlerList_it it_relationHandlerList = relationHandlerList.find(idAlarmRelation);
	
	if(it_relationHandlerList != relationHandlerList.end() ) // key found!!!!
	{	
		relationHandler relHandler = it_relationHandlerList.value();
		
		relationHandler_it it_relationHandler;
		i=0;
		for (it_relationHandler = relHandler.begin(); it_relationHandler != relHandler.end(); ++it_relationHandler)
		{
			rel_h[i]=*it_relationHandler;
			++i;
		}
	}
	
	return rel_h;
}      
	

void APIDB_AlarmRelationTable::debug()
{		
	/* debug alarmrelation list */
	for ( AlarmRelationsIterator it_alrel = local_adc->alarmRelationInterface->getIterator(); it_alrel != local_adc->alarmRelationInterface->hashEnd(); it_alrel++ )
	{
		//if(it_alrel.value()->data.idElement!=847) continue;
		lout3 << "idAlarmRelation         = " << it_alrel.value()->data.idAlarmRelation << endl;
		lout3 << "description             = " << it_alrel.value()->data.description << endl;
		lout3 << "timeFractionElementId   = " << it_alrel.value()->data.timeFractionElementId << endl;
		lout3 << "familyId                = " << it_alrel.value()->data.familyId << endl;
		lout3 << "countersKitId           = " << it_alrel.value()->data.countersKitId << endl;
		lout3 << "idConditionScript       = " << it_alrel.value()->data.idConditionScript << endl;
		lout3 << "idConditionPlugin       = " << it_alrel.value()->data.idConditionPlugin << endl;
		lout3 << "relationElements        = (";
		for ( int i = 0; i < MAX_DIMENSION; i++ )
		{
			lout << it_alrel.value()->data.relationElements[i] << ",";
		}	
		lout3 << ")" << endl;
		lout3 << "relationElementsEnabled = (";
		for ( int i = 0; i < MAX_DIMENSION; i++ )
		{
			lout << it_alrel.value()->data.relationElementsEnabled[i] << ",";
		}	
		lout3 << ")" << endl;
		lout3 << "alarmHandlers           = (";
		for ( int i = 0; i < MAX_ALARM_GENERATOR; i++ )
		{
			lout << it_alrel.value()->data.alarmHandlers[i] << ",";
		}	
		lout3 << ")" << endl;
		lout3 << endl;
		
		/*
		 alrel->data. = query.value(fieldName_toPos.value("ID_ALARM_RELATION")).toInt();
			c_qstrncpy ( alrel->data., qPrintable(query.value(fieldName_toPos.value("DESCRIPTION")).toString()), LONG_DESC_LEN );
			for ( int i = 0; i < MAX_DIMENSION; i++ )
			{
				alrel->data.relationElements[i] = getRelationElement(alrel->data.idAlarmRelation)[i];
			}
			
			for ( int i = 0; i < MAX_DIMENSION; i++ )
			{
				alrel->data.relationElementsEnabled[i] = gelReationElementEnabled(alrel->data.idAlarmRelation)[i];
			}
			for ( int i = 0; i < MAX_ALARM_GENERATOR; i++ )
			{
				alrel->data.alarmHandlers[i] = getRelationHandler(alrel->data.idAlarmRelation)[i];
			}
			alrel->data.timeFractionElementId = query.value(fieldName_toPos.value("TIME_FRACTION_ELEMENT_ID")).toInt();
			alrel->data. = query.value(fieldName_toPos.value("FAMILY_ID")).toInt();
			alrel->data. = query.value(fieldName_toPos.value("COUNTERS_KIT_ID")).toInt();
			alrel->data. = query.value(fieldName_toPos.value("ID_CONDITION_SCRIPT")).toInt();
			alrel->data. = getIdPlugin(query.value(fieldName_toPos.value("STR_CONDITION_PLUGIN")).toString());
		  
		 */
	  
	}
}

void APIDB_AlarmRelationTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );
	bool noDbError=false;
	alarmrelation * alrel;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_alarm_relation";
	QString fieldName[]={"ID_ALARM_RELATION",
				"DESCRIPTION",
				"ID_CONDITION_SCRIPT",
				"STR_CONDITION_PLUGIN",
				"TIPO_DI_CONFIGURAZIONE",
				"COUNTERS_KIT_ID",
				"TIME_FRACTION_ELEMENT_ID",
				"FAMILY_ID",
				""				//endlist
				};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="ID_ALARM_RELATION asc";
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
			alrel = new alarmrelation;
			
			
			alrel->data.idAlarmRelation = query.value(fieldName_toPos.value("ID_ALARM_RELATION")).toInt();
			c_qstrncpy ( alrel->data.description, qPrintable(query.value(fieldName_toPos.value("DESCRIPTION")).toString()), LONG_DESC_LEN );
			int *elem_rel=getRelationElement(alrel->data.idAlarmRelation);
			for ( int i = 0; i < MAX_DIMENSION; i++ )
			{
				alrel->data.relationElements[i] = elem_rel[i];
			}
			int *elem_rel_enable=gelReationElementEnabled(alrel->data.idAlarmRelation);
			for ( int i = 0; i < MAX_DIMENSION; i++ )
			{
				alrel->data.relationElementsEnabled[i] = elem_rel_enable[i];
			}
			int *rel_hand=getRelationHandler(alrel->data.idAlarmRelation);
			for ( int i = 0; i < MAX_ALARM_GENERATOR; i++ )
			{
				alrel->data.alarmHandlers[i] = rel_hand[i];
			}
			alrel->data.timeFractionElementId = query.value(fieldName_toPos.value("TIME_FRACTION_ELEMENT_ID")).toInt();
			alrel->data.familyId = query.value(fieldName_toPos.value("FAMILY_ID")).toInt();
			alrel->data.countersKitId = query.value(fieldName_toPos.value("COUNTERS_KIT_ID")).toInt();
			alrel->data.idConditionScript = query.value(fieldName_toPos.value("ID_CONDITION_SCRIPT")).toInt();
			alrel->data.idConditionPlugin = getIdPlugin(query.value(fieldName_toPos.value("STR_CONDITION_PLUGIN")).toString());
		  
		
			local_adc->alarmRelationInterface->add(alrel);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}	
}

void APIDB_AlarmRelationTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_AlarmRelationTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getPlugins(nameConfig);
		getRelationElements(nameConfig);
		getRelationHandlers(nameConfig);
		fillFromDB(nameConfig);
	}
}

