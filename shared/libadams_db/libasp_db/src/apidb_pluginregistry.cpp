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
#include "apidb_pluginregistry.h"

#include <pluginregistryinterface.h>


using namespace std;

#define DEBUG_APIDB_PLUGINREGISTRY_HR                                  1


APIDB_PluginRegistryTable::APIDB_PluginRegistryTable ( ): m_connection ( "APIDB_PluginRegistryTable" )
{
  
}

APIDB_PluginRegistryTable::APIDB_PluginRegistryTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_PluginRegistryTable" )
{
	local_adc=adc;
}


bool APIDB_PluginRegistryTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_PluginRegistryTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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

APIDB_PluginRegistryTable::~APIDB_PluginRegistryTable()
{
	if(isConnect()==true) closeDB();
}


void APIDB_PluginRegistryTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_PluginRegistryTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_PluginRegistryTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  

void APIDB_PluginRegistryTable::debug()
{		
	/* debug pluginRegistry list */
	for ( PluginRegistryIterator it_pl = local_adc->pluginRegistryInterface->getIterator(); it_pl != local_adc->pluginRegistryInterface->hashEnd(); it_pl++ )
	{
		//if(it_pl.value()->data.idPlugin!=847) continue;
		lout3 << "idPlugin   = " << it_pl.value()->data.idPlugin << endl;
		lout3 << "tag        = " << it_pl.value()->data.tag << endl;
		lout3 << "pluginName = " << it_pl.value()->data.pluginName << endl;
		lout3 << "usage      = " << it_pl.value()->data.usage << endl;
		lout3 << "enabled    = " << it_pl.value()->data.enabled << endl;
		lout3 << endl;
	}
}

void APIDB_PluginRegistryTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );	
	bool noDbError=false;
	PluginRegistry * pl;
	
	QString strFieldName="";
	QString tableName="adams_asp.l_plugin_library";
	QString fieldName[]={"NOME_PLUGIN","DESCRIZIONE",""};
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
			pl = new PluginRegistry;

			pl->data.idPlugin = i;
			c_qstrncpy ( pl->data.tag, qPrintable(query.value(fieldName_toPos.value("DESCRIZIONE")).toString()), SHORT_DESC_LEN );
			c_qstrncpy ( pl->data.pluginName, qPrintable(query.value(fieldName_toPos.value("NOME_PLUGIN")).toString()), PLR_PLUGINNAME_LEN );
			pl->data.usage = ( PluginRegistry::plrUsage ) 0;
			pl->data.enabled = toBoolean("Y");
			
			local_adc->pluginRegistryInterface->add(pl);
			
			++i;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_PluginRegistryTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_PluginRegistryTable::load(QString nameConfig)
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

