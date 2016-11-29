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
#include "apidb_script.h"

#include <scriptinterface.h>


using namespace std;

#define DEBUG_APIDB_SCRIPT_HR                                  1


APIDB_ScriptTable::APIDB_ScriptTable ( ): m_connection ( "APIDB_ScriptTable" )
{
  
}

APIDB_ScriptTable::APIDB_ScriptTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_ScriptTable" )
{
	local_adc=adc;
}

bool APIDB_ScriptTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_ScriptTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_ScriptTable::~APIDB_ScriptTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_ScriptTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_ScriptTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}


bool APIDB_ScriptTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  
void APIDB_ScriptTable::getScriptVars(QString nameConfig,int var)
{
	method ( "getScriptVars("+nameConfig+","+QString::number(var)+"): " );	
	//lout3 << "getScriptVars(" << nameConfig << "," << var << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_script_var"+QString::number(var);
	QString fieldName[]={"IDSCRIPT","VRBNAME",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDSCRIPT asc";
	
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
			int key=query.value(fieldName_toPos.value("IDSCRIPT")).toInt();
			QString value=query.value(fieldName_toPos.value("VRBNAME")).toString();
			
			scriptVarList_it it_scriptVarList = scriptVarList.find(key);
			
			if(it_scriptVarList == scriptVarList.end() ) // key not found!!!!
			{
				scriptVar sVar1;
				sVar1.append(value);
				scriptVarList.insert(key,sVar1);
			}
			else // key found
			{
				it_scriptVarList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_ScriptTable::getScriptTexts(QString nameConfig)
{
	method ( "getScriptTexts("+nameConfig+"): " );	
	//lout3 << "getScriptTexts(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_script_text";
	QString fieldName[]={"IDSCRIPT","VRBTEXT",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDSCRIPT asc";
	
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
			int key=query.value(fieldName_toPos.value("IDSCRIPT")).toInt();
			QString value=query.value(fieldName_toPos.value("VRBTEXT")).toString();
			
			scriptTextList_it it_scriptTextList = scriptTextList.find(key);
			
			if(it_scriptTextList == scriptTextList.end() ) // key not found!!!!
			{
				scriptText sText;
				sText.append(value);
				scriptTextList.insert(key,sText);
			}
			else // key found
			{
				it_scriptTextList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}


void APIDB_ScriptTable::debug()
{		
	/* debug sctipt list */
	for ( ScriptIterator it_sc = local_adc->scriptInterface->getIterator(); it_sc != local_adc->scriptInterface->hashEnd(); it_sc++ )
	{
		//if(it_sc.value()->data.idScript!=847) continue;
		lout3 << "idScript           = " << it_sc.value()->data.idScript << endl;
		lout3 << "tag                = " << it_sc.value()->data.tag << endl;
		lout3 << "dataOrigin         = " << it_sc.value()->data.dataOrigin << endl;
		lout3 << "resultVariableName = " << it_sc.value()->data.resultVariableName << endl;
		lout3 << "resultType         = " << it_sc.value()->data.resultType << endl;
		lout3 << "VAR                = (";
		scriptVarList_it it_scriptVarList = scriptVarList.find(it_sc.value()->data.idScript);	
		if(it_scriptVarList != scriptVarList.end() ) // key found!!!!
		{	
			scriptVar sVar = it_scriptVarList.value();
			
			scriptVar_it it_scriptVar;
			for (it_scriptVar = sVar.begin(); it_scriptVar != sVar.end(); ++it_scriptVar)
			{
				lout3 << *it_scriptVar << ",";
			}
		}
		lout3 << ")" << endl;
		lout3 << "TEXT               = (";
		scriptTextList_it it_scriptTextList = scriptTextList.find(it_sc.value()->data.idScript);	
		if(it_scriptTextList != scriptTextList.end() ) // key found!!!!
		{	
			scriptText sVar = it_scriptTextList.value();
			
			scriptText_it it_scriptText;
			for (it_scriptText = sVar.begin(); it_scriptText != sVar.end(); ++it_scriptText)
			{
				lout3 << *it_scriptText;
			}
		}
		lout3 << ")" << endl;
		lout3 << endl;
	}
}

void APIDB_ScriptTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );
	bool noDbError=false;
	Script * sc;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_scripts";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
			      "IDSCRIPT",
			      "TAG",
			      "NUMVARIABLE1",
			      "NUMVARIABLE2",
			      "NUMSCRIPTTEXT",
			      "RESULTVARNAME",
			      "RESULTTYPE",
			      "FLAG_VALIDATE",
			      ""				//endlist
			      };
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDSCRIPT asc";
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
			sc = new Script;
			
			sc->data.idScript = query.value(fieldName_toPos.value("IDSCRIPT")).toInt();
			c_qstrncpy(sc->data.tag, qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN);
			sc->data.dataOrigin = (Script::scriptDataOrigin)2;
			sc->data.resultVariableName = query.value(fieldName_toPos.value("RESULTVARNAME")).toString();
			sc->data.resultType = (SimpleTypesEnum)query.value(fieldName_toPos.value("RESULTTYPE")).toInt();
			
			scriptVarList_it it_scriptVarList = scriptVarList.find(sc->data.idScript);
			
			if(it_scriptVarList != scriptVarList.end() ) // key found!!!!
			{	
				scriptVar sVar = it_scriptVarList.value();
				
				scriptVar_it it_scriptVar;
				for (it_scriptVar = sVar.begin(); it_scriptVar != sVar.end(); ++it_scriptVar)
				{
					sc->data.variables << *it_scriptVar;
				}
			}
			
			scriptTextList_it it_scriptTextList = scriptTextList.find(sc->data.idScript);
			
			if(it_scriptTextList != scriptTextList.end() ) // key found!!!!
			{	
				scriptText sText = it_scriptTextList.value();
				
				scriptText_it it_scriptText;
				for (it_scriptText = sText.begin(); it_scriptText != sText.end(); ++it_scriptText)
				{
					sc->data.scriptText << *it_scriptText;
				}
			}
			
			local_adc->scriptInterface->add(sc);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_ScriptTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_ScriptTable::load(QString nameConfig)
{	
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getScriptVars(nameConfig,1);
		getScriptVars(nameConfig,2);
		getScriptTexts(nameConfig);
		fillFromDB(nameConfig);
		closeDB();
	}
}

