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
#include "apidb_counter.h"

#include <counterinterface.h>


using namespace std;

#define DEBUG_APIDB_COUNTER_HR                                  1


APIDB_CounterTable::APIDB_CounterTable ( ): m_connection ( "APIDB_CounterTable" )
{
  
}


APIDB_CounterTable::APIDB_CounterTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_CounterTable" )
{
	local_adc=adc;
}



bool APIDB_CounterTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_CounterTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_CounterTable::~APIDB_CounterTable()
{
	if(isConnect()==true) closeDB();

	counterKitList_it it_counterKitList;
	for (it_counterKitList = counterKitList.begin(); it_counterKitList != counterKitList.end(); ++it_counterKitList)
	{
		counters cList = it_counterKitList.value();
		
		counters_it it_counters;
		
		for (it_counters = cList.begin(); it_counters != cList.end(); ++it_counters)
		{
			if(*it_counters) delete (*it_counters);
		}
		cList.clear();
		
	}
	counterKitList.clear();
}


void APIDB_CounterTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_CounterTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_CounterTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  
void APIDB_CounterTable::getHelps(QString nameConfig)
{
	method ( "getHelps("+nameConfig+"): " );
	//lout3 << "getHelps(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_help";
	QString fieldName[]={"ID","HELPVALUE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' AND IDHELP in ("+QString::number(TYPE_COUNTER_INDEX)+","+QString::number(TYPE_COUNTER_TYPE)+","+QString::number(TYPE_PERCENT_OF)+")";
	QString strOrderBy="HELPVALUE asc";
	
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
			int key=query.value(fieldName_toPos.value("ID")).toInt();
			int value=query.value(fieldName_toPos.value("HELPVALUE")).toInt();
			
			helps_it it_helps = helps.find(key);
			
			if(it_helps == helps.end() ) // key not found!!!!
			{
				helps.insert(key,value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int APIDB_CounterTable::getHelp(int typeField)
{
	int valueHelp=-1;
	
	helps_it it_helps = helps.find(typeField);
	
	if(it_helps == helps.end() )
	{
		valueHelp=-1;
	}else
	{
		valueHelp=helps.value(typeField);
	}
	
	return valueHelp;
}

void APIDB_CounterTable::getCounters(QString nameConfig)
{
	method ( "getCounters("+nameConfig+"): " );
	//lout3 << "getCounters(" << nameConfig << ")" << endl;
	bool noDbError=false;
	COUNTERKIT *c;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_type_counters";
	QString fieldName[]={"IDCOUNTER",
				"TRIGGERFIELD",
				"TRIGGERVALUE",
				"COUNTERINDEX",
				"COUNTERTYPE",
				"PERCENTOF",
				"TRIGGERCOUNTER",
				"TAG",
				"DESCRIPTION",
				""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="TAG asc";
	
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
			
			int key=query.value(fieldName_toPos.value("IDCOUNTER")).toInt();
			
			
			counterKitList_it it_counterKitList = counterKitList.find(key);
			
			c=new COUNTERKIT;
			int triggerField=query.value(fieldName_toPos.value("TRIGGERFIELD")).toInt();
			if(triggerField==-1)
			{
			    triggerField=0;
			}
			c->triggerField=triggerField;
			int triggerValue=query.value(fieldName_toPos.value("TRIGGERVALUE")).toInt();
			if(triggerValue==-1)
			{
			    triggerValue=0;
			}
			c->triggerValue = triggerValue;
			c->counterIndex = getHelp(query.value(fieldName_toPos.value("COUNTERINDEX")).toInt());
			c->counterType = getHelp(query.value(fieldName_toPos.value("COUNTERTYPE")).toInt());
			c->percentOf = getHelp(query.value(fieldName_toPos.value("PERCENTOF")).toInt());
			int triggerCounter=query.value(fieldName_toPos.value("TRIGGERCOUNTER")).toInt();
			if(triggerCounter==-1)
			{
			    triggerCounter=0;
			}
			c->triggerCounter = triggerCounter;
			c_qstrncpy ( c->tag, qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), CNT_TAG_LEN );
			c_qstrncpy ( c->description, qPrintable(query.value(fieldName_toPos.value("DESCRIPTION")).toString()), CNT_DESC_LEN );
			
			if(it_counterKitList == counterKitList.end() ) // key not found!!!!
			{
				counters cList;
				cList.append(c);
				counterKitList.insert(key,cList);
			}
			else // key found
			{
				it_counterKitList.value().append(c);
			}
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

COUNTERKIT *APIDB_CounterTable::getCounter(int idCounterKIT)
{
	//lout3 << "getCounter(" << idCounterKIT << ")" << endl;	
	COUNTERKIT *c=NULL;
	
	counterKitList_it it_counterKitList = counterKitList.find(idCounterKIT);
	
	if(it_counterKitList != counterKitList.end() ) // key found!!!!
	{	
		counters cList = it_counterKitList.value();
		counters_it it_counters=cList.begin();
		
		int numel =  (cList.size() > CNT_NUM ) ? CNT_NUM : cList.size();

		c= new COUNTERKIT[numel];

		for(int i=0;i<numel;i++)
		{
			c[i].triggerField=(*it_counters)->triggerField;
			c[i].triggerValue=(*it_counters)->triggerValue;
			c[i].counterIndex=(*it_counters)->counterIndex;
			c[i].counterType=(*it_counters)->counterType;
			c[i].percentOf=(*it_counters)->percentOf;
			c[i].triggerCounter=(*it_counters)->triggerCounter;
			c_qstrncpy ( c[i].tag, (*it_counters)->tag, CNT_TAG_LEN );
			c_qstrncpy ( c[i].description, (*it_counters)->description, CNT_DESC_LEN );
			++it_counters;
		}
	}
	
	return c;
}

int APIDB_CounterTable::getNumCounter(int idCounterKIT)
{
	//lout3 << "getNumCounter(" << idCounterKIT << ")" << endl;	
	int numel=0;
	
	counterKitList_it it_counterKitList = counterKitList.find(idCounterKIT);
	
	if(it_counterKitList != counterKitList.end() ) // key found!!!!
	{	
		counters cList = it_counterKitList.value();
		numel =  (cList.size() > CNT_NUM ) ? CNT_NUM : cList.size();
	}
	
	return numel;
}

void APIDB_CounterTable::debug()
{		
	/* debug counter list */
	for ( CountersIterator it_c = local_adc->counterInterface->getIterator(); it_c != local_adc->counterInterface->hashEnd(); it_c++ )
	{
		//if(it_c.value()->data.idCounter!=847) continue;
		lout3 << "idCounter  = " << it_c.value()->data.idCounter << endl;
		lout3 << "tag        = " << it_c.value()->data.tag << endl;
		lout3 << "usePlugin  = " << it_c.value()->data.usePlugin << endl;
		lout3 << "usePath    = " << it_c.value()->data.usePath << endl;
		lout3 << "pluginName = " << it_c.value()->data.pluginName << endl;
		lout3 << "pathName   = " << it_c.value()->data.pathName << endl;
		int numel = getNumCounter(it_c.value()->data.idCounter );
		lout3 << "num cont   = " << numel << endl;
		for ( int i = 0; i < numel; i++ ) {
			lout3 << "     triggerField   = " << it_c.value()->data.counterKit[i].triggerField << endl;
			lout3 << "     triggerValue   = " << it_c.value()->data.counterKit[i].triggerValue << endl;
			lout3 << "     counterIndex   = " << it_c.value()->data.counterKit[i].counterIndex << endl;
			lout3 << "     counterType    = " << it_c.value()->data.counterKit[i].counterType << endl;
			lout3 << "     percentOf      = " << it_c.value()->data.counterKit[i].percentOf << endl;
			lout3 << "     triggerCounter = " << it_c.value()->data.counterKit[i].triggerCounter << endl;
			lout3 << "     tag            = " << it_c.value()->data.counterKit[i].tag << endl;
			lout3 << "     description    = " << it_c.value()->data.counterKit[i].description << endl;
			lout3 << endl;
		}
		lout3 << endl;
	}
}


void APIDB_CounterTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );
	bool noDbError=false;
	Counters * c;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_counters_kit";
	QString fieldName[]={"IDCOUNTER",
				"TAG",
				"USEPLUGIN",
				"PLUGINNAME",
				"USEPATH",
				"PATHNAME",
				""
						};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="TAG asc";
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
			c = new Counters;
			
			c->data.idCounter = query.value(fieldName_toPos.value("IDCOUNTER")).toInt();
			c_qstrncpy ( c->data.tag, qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN );
			c->data.usePlugin = toBoolean(query.value(fieldName_toPos.value("USEPLUGIN")).toString());
			c->data.usePath = toBoolean(query.value(fieldName_toPos.value("USEPATH")).toString());
			c_qstrncpy ( c->data.pluginName, qPrintable(query.value(fieldName_toPos.value("PLUGINNAME")).toString()), CNT_PLUGINNAME_LEN );
			c_qstrncpy ( c->data.pathName, qPrintable(query.value(fieldName_toPos.value("PATHNAME")).toString()), CNT_PATH_LEN );

			int numel = getNumCounter(c->data.idCounter);

			for ( int i = 0; i < numel; i++ ) {
				c->data.counterKit[i].triggerField = getCounter(c->data.idCounter)[i].triggerField;
				c->data.counterKit[i].triggerValue = getCounter(c->data.idCounter)[i].triggerValue;
				c->data.counterKit[i].counterIndex = getCounter(c->data.idCounter)[i].counterIndex;
				c->data.counterKit[i].counterType =  getCounter(c->data.idCounter)[i].counterType;
				c->data.counterKit[i].percentOf = getCounter(c->data.idCounter)[i].percentOf;
				c->data.counterKit[i].triggerCounter = getCounter(c->data.idCounter)[i].triggerCounter;
				c_qstrncpy ( c->data.counterKit[i].tag, getCounter(c->data.idCounter)[i].tag, CNT_TAG_LEN );
				c_qstrncpy ( c->data.counterKit[i].description, getCounter(c->data.idCounter)[i].description, CNT_DESC_LEN );
				
			}
			
			COUNTERKIT *count=getCounter(c->data.idCounter);
			if(count) delete(count);
			
			local_adc->counterInterface->add(c);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_CounterTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_CounterTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getHelps("ALL_CONF");
		getCounters(nameConfig);
		fillFromDB(nameConfig);
		closeDB();
	}
}

