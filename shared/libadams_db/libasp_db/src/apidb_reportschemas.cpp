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
#include "apidb_reportschemas.h"

#include <reportschemainterface.h>


using namespace std;

#define DEBUG_APIDB_REPORT_HR                                  1


APIDB_ReportSchemasTable::APIDB_ReportSchemasTable ( ): m_connection ( "APIDB_ReportSchemasTable" )
{
  
}

APIDB_ReportSchemasTable::APIDB_ReportSchemasTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_ReportSchemasTable" )
{
	local_adc=adc;
}

bool APIDB_ReportSchemasTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_ReportSchemasTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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

APIDB_ReportSchemasTable::~APIDB_ReportSchemasTable()
{
	if(isConnect()==true) closeDB();
}


void APIDB_ReportSchemasTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_ReportSchemasTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_ReportSchemasTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}

bool APIDB_ReportSchemasTable::toBoolean(int value) 
{
	if(value>0)
            return true;
        else
            return false;
}

void APIDB_ReportSchemasTable::getHelps(QString nameConfig)
{
	method ( "getHelps("+nameConfig+"): " );
	//lout3 << "getHelps(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_help";
	QString fieldName[]={"ID","HELPVALUE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' AND IDHELP in ("+QString::number(TYPE_COUNTER_INDEX)+")";
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

int APIDB_ReportSchemasTable::getHelp(int typeField)
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

void APIDB_ReportSchemasTable::getCounters(QString nameConfig)
{
	method ( "getCounters("+nameConfig+"): " );	
	//lout3 << "getCounters(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_analysis_type a,adams_asp.tab_type_counters b";
	QString fieldName[]={"CONCAT (a.IDANALISI,'::',b.TAG)",
				"b.COUNTERINDEX",
				""};
	QString strWhere="b.TIPO_DI_CONFIGURAZIONE = a.TIPO_DI_CONFIGURAZIONE and a.TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' and b.IDCOUNTER=a.IDCOUNTERKIT";
	QString strOrderBy="KEY asc";
	
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
	
		while ( query.next() ) 
		{
			int value=query.value(fieldName_toPos.value("b.COUNTERINDEX")).toInt();
			QString key=query.value(fieldName_toPos.value("CONCAT (a.IDANALISI,'::',b.TAG)")).toString();
			
			counterList_it it_counterList = counterList.find(key);
			
			if(it_counterList == counterList.end() ) // key not found!!!!
			{
				counterList.insert(key,value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int APIDB_ReportSchemasTable::getIDCounter(QString nameCounter)
{
	int idCounter=-1;
	counterList_it it_counterList = counterList.find(nameCounter);
	if(it_counterList == counterList.end() )
	{
		idCounter=-1;
	}else
	{
		idCounter=counterList.value(nameCounter);
	}
	return idCounter;
}

void APIDB_ReportSchemasTable::getPlugins(QString nameConfig)
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
	strSelect+=" ORDER BY "+strOrderBy;
	
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

int APIDB_ReportSchemasTable::getIdPlugin(QString namePlugin)
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


void APIDB_ReportSchemasTable::getReportSchemaObjs(QString nameConfig)
{
	method ( "getReportSchemaObjs("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getReportSchemaObjs(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_report";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
			      "IDREPORTSCHEMA",
			      "IDOBJECT",
			      "TYPE",
			      "SUB_TYPE",
			      "TAG",
			      "HTML_VSIZE",
			      "HTML_HSIZE",
			      "HTML_VALIGN",
			      "HTML_HALIGN",
			      "HTML_WRAP",
			      "HTML_FOREGROUND",
			      "HTML_FOREGROUND_R",
			      "HTML_FOREGROUND_G",
			      "HTML_FOREGROUND_B",
			      "HTML_BACKGROUND",
			      "HTML_BACKGROUND_R",
			      "HTML_BACKGROUND_G",
			      "HTML_BACKGROUND_B",
			      "HTML_STYLE",
			      "HTML_FONTSIZE",
			      "HTML_BOLD",
			      "HTML_ITALIC",
			      "HTML_UNDERLINE",
			      "OBJECT_TAG",
			      "OBJECT_HASBORDER",
			      "OBJECT_SIMPLEBODY",
			      "OBJECT_EXCELCSV",
			      "OBJECT_IDANALISYS",
			      "OBJECT_USEPLUGIN",
			      "OBJECT_PLUGINNAME",
			      "OBJECT_DEFAULT_FOREGROUND_R",
			      "OBJECT_DEFAULT_FOREGROUND_G",
			      "OBJECT_DEFAULT_FOREGROUND_B",
			      "OBJECT_DEFAULT_BACKGROUND_R",
			      "OBJECT_DEFAULT_BACKGROUND_G",
			      "OBJECT_DEFAULT_BACKGROUND_B",
			      "HEADER_USERLABEL",
			      "HEADER_VALUE",
			      "HEADER_USERVALUE",
			      "HEADER_ISURL",
			      "HEADER_LINE",
			      "BODY_SOURCE",
			      "BODY_IDKEY",
			      "BODY_IDCOUNTER",
			      "BODY_TAGCOUNTER",
			      "BODY_IDSCRIPT",
			      "BODY_PLUGIN_TAG",
			      "BODY_PLUGIN_NAME",
			      "BODY_USERVALUE",
			      "BODY_ISURL",
			      "BODY_TOTALIZER",
			      "BODY_LINE",
			      "BODY_MAXDECIMALDIGIT",
			      "TOTALIZER_TRIGGER",
			      "TOTALIZER_LABEL",
			      "TOTALIZER_REDRAWHEADER",
			      "TOTALIZER_BORDERAROUND",
			      "TOTALIZER_LINE",
			      "TOTALIZER_USESCRIPT",
			      "TOTALIZER_IDSCRIPT",
			      "FOOTER_SOURCE",
			      "FOOTER_LABEL",
			      "FOOTER_USERVALUE",
			      "FOOTER_ISURL",
			      "FOOTER_LINE",
			      "POSIZIONE",
			      "BODY_REPEATKEY",
			      ""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDREPORTSCHEMA,TAG asc";
	
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
	
		int cont=0;

		while ( query.next() ) 
		{
			int key=query.value(fieldName_toPos.value("IDREPORTSCHEMA")).toInt();
			
			//lout3 << key << "("<< ++cont <<") ---> " << query.value(fieldName_toPos.value("TAG")).toString() <<"(" << value << ")" << endl;
			
			reportSchemaList_it it_reportSchemaList = reportSchemaList.find(key);
			
			if(it_reportSchemaList == reportSchemaList.end() ) // key not found!!!!
			{
				//lout3 << "ID Report("<< key <<") non presente lo creo nuovo " << endl;
				ReportSchema *repSchema=new ReportSchema;
				
				repSchema->idReportSchema=query.value(fieldName_toPos.value("IDREPORTSCHEMA")).toInt();
				
				//fillCommonFromDB(repSchema->objO,query,fieldName_toPos);
				fillObjectFromDB(repSchema->objO,query,fieldName_toPos,TYPE_REPORT_SCHEMA);
				/*//COMMON Section
				repSchema->objO.data.idReportSchema = query.value(fieldName_toPos.value("IDREPORTSCHEMA")).toInt();
				repSchema->objO.data.idObject = query.value(fieldName_toPos.value("IDOBJECT")).toInt();
				repSchema->objO.data.type = ( ReportSchemaObj::CellObject ) 0;//( ReportSchemaObj::CellObject ) query.value(fieldName_toPos.value("TYPE")).toInt();
				c_qstrncpy ( repSchema->objO.data.tag,qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN );
				
				//HTML Section
				repSchema->objO.data.html.vSize = query.value(fieldName_toPos.value("HTML_VSIZE")).toInt();
				repSchema->objO.data.html.hSize = query.value(fieldName_toPos.value("HTML_HSIZE")).toInt();
				repSchema->objO.data.html.vAlign = ( ReportSchemaObj::VertAlign ) query.value(fieldName_toPos.value("HTML_VALIGN")).toInt();
				repSchema->objO.data.html.hAlign = ( ReportSchemaObj::HoriAlign ) query.value(fieldName_toPos.value("HTML_HALIGN")).toInt();
				repSchema->objO.data.html.wrap = toBoolean(query.value(fieldName_toPos.value("HTML_WRAP")).toInt());
				repSchema->objO.data.html.foreground.r = query.value(fieldName_toPos.value("HTML_FOREGROUND_R")).toInt();
				repSchema->objO.data.html.foreground.g = query.value(fieldName_toPos.value("HTML_FOREGROUND_G")).toInt();
				repSchema->objO.data.html.foreground.b = query.value(fieldName_toPos.value("HTML_FOREGROUND_B")).toInt();
				repSchema->objO.data.html.background.r = query.value(fieldName_toPos.value("HTML_BACKGROUND_R")).toInt();
				repSchema->objO.data.html.background.g = query.value(fieldName_toPos.value("HTML_BACKGROUND_G")).toInt();
				repSchema->objO.data.html.background.b = query.value(fieldName_toPos.value("HTML_BACKGROUND_B")).toInt();
				repSchema->objO.data.html.style = ( ReportSchemaObj::FontStyle ) query.value(fieldName_toPos.value("HTML_STYLE")).toInt();
				repSchema->objO.data.html.fontSize = query.value(fieldName_toPos.value("HTML_FONTSIZE")).toInt();
				repSchema->objO.data.html.bold = toBoolean(query.value(fieldName_toPos.value("HTML_BOLD")).toInt());
				repSchema->objO.data.html.italic = toBoolean(query.value(fieldName_toPos.value("HTML_ITALIC")).toInt());
				repSchema->objO.data.html.underline = toBoolean(query.value(fieldName_toPos.value("HTML_UNDERLINE")).toInt());
				
				//Object Section
				c_qstrncpy ( repSchema->objO.data.u.object.tag, qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN );
				repSchema->objO.data.u.object.hasBorder = toBoolean( query.value(fieldName_toPos.value("OBJECT_HASBORDER")).toInt());
				repSchema->objO.data.u.object.excelCSV = query.value(fieldName_toPos.value("OBJECT_EXCELCSV")).toInt();
				repSchema->objO.data.u.object.simpleBody = toBoolean( query.value(fieldName_toPos.value("OBJECT_SIMPLEBODY")).toInt());
				repSchema->objO.data.u.object.idAnalisys = query.value(fieldName_toPos.value("OBJECT_IDANALISYS")).toInt();
				repSchema->objO.data.u.object.usePlugin = toBoolean( query.value(fieldName_toPos.value("OBJECT_USEPLUGIN")).toInt() );
				c_qstrncpy ( repSchema->objO.data.u.object.pluginName, qPrintable(query.value(fieldName_toPos.value("OBJECT_PLUGINNAME")).toString()), SHORT_PLUGIN_NAME );
				repSchema->objO.data.u.object.defaultForeground.r = query.value(fieldName_toPos.value("OBJECT_DEFAULT_FOREGROUND_R")).toInt();
				repSchema->objO.data.u.object.defaultForeground.g = query.value(fieldName_toPos.value("OBJECT_DEFAULT_FOREGROUND_G")).toInt();
				repSchema->objO.data.u.object.defaultForeground.b = query.value(fieldName_toPos.value("OBJECT_DEFAULT_FOREGROUND_B")).toInt();
				repSchema->objO.data.u.object.defaultBackground.r = query.value(fieldName_toPos.value("OBJECT_DEFAULT_BACKGROUND_R")).toInt();
				repSchema->objO.data.u.object.defaultBackground.g = query.value(fieldName_toPos.value("OBJECT_DEFAULT_BACKGROUND_G")).toInt();
				repSchema->objO.data.u.object.defaultBackground.b = query.value(fieldName_toPos.value("OBJECT_DEFAULT_BACKGROUND_B")).toInt();
				*/
				reportSchemaList.insert(key,repSchema);
			}
			else // key found
			{	
				ReportSchema *repSchema=it_reportSchemaList.value();
				int type=( ReportSchemaObj::CellObject ) query.value(fieldName_toPos.value("TYPE")).toInt();
				//lout3 << "ID Report("<< key <<") presente " << endl;
				
				ReportSchemaObj rso;
				//fillCommonFromDB(rso,query,fieldName_toPos);
				/*//COMMON Section
				rso.data.idReportSchema = query.value(fieldName_toPos.value("IDREPORTSCHEMA")).toInt();
				rso.data.idObject = query.value(fieldName_toPos.value("IDOBJECT")).toInt();
				rso.data.type = ( ReportSchemaObj::CellObject ) query.value(fieldName_toPos.value("TYPE")).toInt();
				c_qstrncpy ( rso.data.tag,qPrintable(query.value(fieldName_toPos.value("OBJECT_TAG")).toString()), SHORT_DESC_LEN );
				
				//HTML Section
				rso.data.html.vSize = query.value(fieldName_toPos.value("HTML_VSIZE")).toInt();
				rso.data.html.hSize = query.value(fieldName_toPos.value("HTML_HSIZE")).toInt();
				rso.data.html.vAlign = ( ReportSchemaObj::VertAlign ) query.value(fieldName_toPos.value("HTML_VALIGN")).toInt();
				rso.data.html.hAlign = ( ReportSchemaObj::HoriAlign ) query.value(fieldName_toPos.value("HTML_HALIGN")).toInt();
				rso.data.html.wrap = toBoolean(query.value(fieldName_toPos.value("HTML_WRAP")).toInt());
				rso.data.html.foreground.r = query.value(fieldName_toPos.value("HTML_FOREGROUND_R")).toInt();
				rso.data.html.foreground.g = query.value(fieldName_toPos.value("HTML_FOREGROUND_G")).toInt();
				rso.data.html.foreground.b = query.value(fieldName_toPos.value("HTML_FOREGROUND_B")).toInt();
				rso.data.html.background.r = query.value(fieldName_toPos.value("HTML_BACKGROUND_R")).toInt();
				rso.data.html.background.g = query.value(fieldName_toPos.value("HTML_BACKGROUND_G")).toInt();
				rso.data.html.background.b = query.value(fieldName_toPos.value("HTML_BACKGROUND_B")).toInt();
				rso.data.html.style = ( ReportSchemaObj::FontStyle ) query.value(fieldName_toPos.value("HTML_STYLE")).toInt();
				rso.data.html.fontSize = query.value(fieldName_toPos.value("HTML_FONTSIZE")).toInt();
				rso.data.html.bold = toBoolean(query.value(fieldName_toPos.value("HTML_BOLD")).toInt());
				rso.data.html.italic = toBoolean(query.value(fieldName_toPos.value("HTML_ITALIC")).toInt());
				rso.data.html.underline = toBoolean(query.value(fieldName_toPos.value("HTML_UNDERLINE")).toInt());
				*/		
				switch(type)
				{
					case ReportSchemaObj::HeaderT: // HEADER
					{
						fillHeaderFromDB(rso,query,fieldName_toPos);
						repSchema->hdrV << rso;
					}break;
					
					case ReportSchemaObj::BodyT: //BODY
					{
						fillBodyFromDB(rso,query,fieldName_toPos);
						repSchema->bdyV << rso;
					}break;
					
					case ReportSchemaObj::TotalizerT: //TOTALIZER
					{
						fillTotalizerFromDB(rso,query,fieldName_toPos);
						repSchema->totV << rso;
					}break;
					
					case ReportSchemaObj::FooterT: //FOOTER
					{
						fillFooterFromDB(rso,query,fieldName_toPos);
						repSchema->fooV << rso;
					}break;
				}
				//it_reportSchemaList.value().append(repSchema);
			}
			
			
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_ReportSchemasTable::fillCommonFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos,int flag)
{
	rso.data.idReportSchema = query.value(fieldName_toPos.value("IDREPORTSCHEMA")).toInt();
	rso.data.idObject = query.value(fieldName_toPos.value("IDOBJECT")).toInt();
	if(flag==TYPE_REPORT_SCHEMA)
	{
		rso.data.type = ( ReportSchemaObj::CellObject ) 0;
		c_qstrncpy ( rso.data.tag,qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN );
	}
	else if(flag==TYPE_REPORT_OBJECT)
	{
		rso.data.type = ( ReportSchemaObj::CellObject ) query.value(fieldName_toPos.value("TYPE")).toInt();
		c_qstrncpy ( rso.data.tag,qPrintable(query.value(fieldName_toPos.value("OBJECT_TAG")).toString()), SHORT_DESC_LEN );
	}

	//HTML Section
	rso.data.html.vSize = query.value(fieldName_toPos.value("HTML_VSIZE")).toInt();
	rso.data.html.hSize = query.value(fieldName_toPos.value("HTML_HSIZE")).toInt();
	rso.data.html.vAlign = ( ReportSchemaObj::VertAlign ) query.value(fieldName_toPos.value("HTML_VALIGN")).toInt();
	rso.data.html.hAlign = ( ReportSchemaObj::HoriAlign ) query.value(fieldName_toPos.value("HTML_HALIGN")).toInt();
	rso.data.html.wrap = toBoolean(query.value(fieldName_toPos.value("HTML_WRAP")).toInt());
	rso.data.html.foreground.r = query.value(fieldName_toPos.value("HTML_FOREGROUND_R")).toInt();
	rso.data.html.foreground.g = query.value(fieldName_toPos.value("HTML_FOREGROUND_G")).toInt();
	rso.data.html.foreground.b = query.value(fieldName_toPos.value("HTML_FOREGROUND_B")).toInt();
	rso.data.html.background.r = query.value(fieldName_toPos.value("HTML_BACKGROUND_R")).toInt();
	rso.data.html.background.g = query.value(fieldName_toPos.value("HTML_BACKGROUND_G")).toInt();
	rso.data.html.background.b = query.value(fieldName_toPos.value("HTML_BACKGROUND_B")).toInt();
	rso.data.html.style = ( ReportSchemaObj::FontStyle ) query.value(fieldName_toPos.value("HTML_STYLE")).toInt();
	rso.data.html.fontSize = query.value(fieldName_toPos.value("HTML_FONTSIZE")).toInt();
	rso.data.html.bold = toBoolean(query.value(fieldName_toPos.value("HTML_BOLD")).toInt());
	rso.data.html.italic = toBoolean(query.value(fieldName_toPos.value("HTML_ITALIC")).toInt());
	rso.data.html.underline = toBoolean(query.value(fieldName_toPos.value("HTML_UNDERLINE")).toInt());					
}

void APIDB_ReportSchemasTable::fillObjectFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos,int flag)
{
	fillCommonFromDB(rso,query,fieldName_toPos,flag);
	
	//Object Section
	c_qstrncpy ( rso.data.u.object.tag, qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN );
	rso.data.u.object.hasBorder = toBoolean( query.value(fieldName_toPos.value("OBJECT_HASBORDER")).toInt());
	rso.data.u.object.excelCSV = query.value(fieldName_toPos.value("OBJECT_EXCELCSV")).toInt();
	rso.data.u.object.simpleBody = toBoolean( query.value(fieldName_toPos.value("OBJECT_SIMPLEBODY")).toInt());
	rso.data.u.object.idAnalisys = query.value(fieldName_toPos.value("OBJECT_IDANALISYS")).toInt();
	rso.data.u.object.usePlugin = toBoolean( query.value(fieldName_toPos.value("OBJECT_USEPLUGIN")).toInt() );
	c_qstrncpy ( rso.data.u.object.pluginName, qPrintable(query.value(fieldName_toPos.value("OBJECT_PLUGINNAME")).toString()), SHORT_PLUGIN_NAME );
	rso.data.u.object.defaultForeground.r = query.value(fieldName_toPos.value("OBJECT_DEFAULT_FOREGROUND_R")).toInt();
	rso.data.u.object.defaultForeground.g = query.value(fieldName_toPos.value("OBJECT_DEFAULT_FOREGROUND_G")).toInt();
	rso.data.u.object.defaultForeground.b = query.value(fieldName_toPos.value("OBJECT_DEFAULT_FOREGROUND_B")).toInt();
	rso.data.u.object.defaultBackground.r = query.value(fieldName_toPos.value("OBJECT_DEFAULT_BACKGROUND_R")).toInt();
	rso.data.u.object.defaultBackground.g = query.value(fieldName_toPos.value("OBJECT_DEFAULT_BACKGROUND_G")).toInt();
	rso.data.u.object.defaultBackground.b = query.value(fieldName_toPos.value("OBJECT_DEFAULT_BACKGROUND_B")).toInt();	
}

void APIDB_ReportSchemasTable::fillHeaderFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos)
{
	//COMMON,HTML Section
	fillCommonFromDB(rso,query,fieldName_toPos,TYPE_REPORT_OBJECT);
	//HEADER Section
	c_qstrncpy ( rso.data.u.header.userLabel, qPrintable(query.value(fieldName_toPos.value("HEADER_USERLABEL")).toString()), REPO_LABEL_LEN );
	rso.data.u.header.value = ( ReportSchemaObj::HeaderValue ) query.value(fieldName_toPos.value("HEADER_VALUE")).toInt();
	c_qstrncpy ( rso.data.u.header.userValue, qPrintable(query.value(fieldName_toPos.value("HEADER_USERVALUE")).toString()), REPO_USER_LEN );
	rso.data.u.header.isUrl = toBoolean(query.value(fieldName_toPos.value("HEADER_ISURL")).toInt());
	rso.data.u.header.line = ( ReportSchemaObj::BreakingType ) query.value(fieldName_toPos.value("HEADER_LINE")).toInt();
}

void APIDB_ReportSchemasTable::fillBodyFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos)
{
	//COMMON,HTML Section
	fillCommonFromDB(rso,query,fieldName_toPos,TYPE_REPORT_OBJECT);

	//BODY Section
	rso.data.u.body.source = ( ReportSchemaObj::DataSource ) query.value(fieldName_toPos.value("BODY_SOURCE")).toInt();
	rso.data.u.body.idKey = query.value(fieldName_toPos.value("BODY_IDKEY")).toInt();
	int idAnalisys=query.value(fieldName_toPos.value("OBJECT_IDANALISYS")).toInt();
	rso.data.u.body.idCounter = getIDCounter(QString::number(idAnalisys)+QString("::")+query.value(fieldName_toPos.value("BODY_TAGCOUNTER")).toString());
	//rso.data.u.body.idCounter = getIDCounter(query.value(fieldName_toPos.value("TIPO_DI_CONFIGURAZIONE")).toString(),query.value(fieldName_toPos.value("OBJECT_IDANALISYS")).toInt(),query.value(fieldName_toPos.value("BODY_TAGCOUNTER")).toString());
	rso.data.u.body.idScript = query.value(fieldName_toPos.value("BODY_IDSCRIPT")).toInt();
	rso.data.u.body.idPlugin = getIdPlugin(query.value(fieldName_toPos.value("BODY_PLUGIN_NAME")).toString());
	c_qstrncpy ( rso.data.u.body.userValue, qPrintable(query.value(fieldName_toPos.value("BODY_USERVALUE")).toString()), REPO_USER_LEN );
	rso.data.u.body.isUrl = toBoolean(query.value(fieldName_toPos.value("BODY_ISURL")).toInt());
	rso.data.u.body.noTotals = toBoolean(query.value(fieldName_toPos.value("BODY_TOTALIZER")).toInt());
	rso.data.u.body.repeatKey = toBoolean(query.value(fieldName_toPos.value("BODY_REPEATKEY")).toInt());
	rso.data.u.body.decimals = query.value(fieldName_toPos.value("BODY_MAXDECIMALDIGIT")).toInt();
	rso.data.u.body.line = ( ReportSchemaObj::BreakingType ) query.value(fieldName_toPos.value("BODY_LINE")).toInt();
}

void APIDB_ReportSchemasTable::fillTotalizerFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos)
{
	//COMMON,HTML Section
	fillCommonFromDB(rso,query,fieldName_toPos,TYPE_REPORT_OBJECT);
	
	//Totalizer Section
	rso.data.u.totalizer.trigger = query.value(fieldName_toPos.value("TOTALIZER_TRIGGER")).toInt();
	c_qstrncpy ( rso.data.u.totalizer.label, qPrintable(query.value(fieldName_toPos.value("TOTALIZER_LABEL")).toString()), REPO_LABEL_LEN );
	rso.data.u.totalizer.redrawHeader = toBoolean(query.value(fieldName_toPos.value("TOTALIZER_REDRAWHEADER")).toInt());
	rso.data.u.totalizer.border = toBoolean(query.value(fieldName_toPos.value("TOTALIZER_BORDERAROUND")).toInt());
	rso.data.u.totalizer.line = ( ReportSchemaObj::BreakingType ) query.value(fieldName_toPos.value("TOTALIZER_LINE")).toInt();
	rso.data.u.totalizer.useScript = toBoolean(query.value(fieldName_toPos.value("TOTALIZER_USESCRIPT")).toInt());
	rso.data.u.totalizer.idScript = query.value(fieldName_toPos.value("TOTALIZER_IDSCRIPT")).toInt();
}

void APIDB_ReportSchemasTable::fillFooterFromDB(ReportSchemaObj &rso,QSqlQuery &query,QMap<QString, int> &fieldName_toPos)
{
	//COMMON,HTML Section
	fillCommonFromDB(rso,query,fieldName_toPos,TYPE_REPORT_OBJECT);	
	
	//Footer Section
	rso.data.u.footer.source = query.value(fieldName_toPos.value("FOOTER_SOURCE")).toInt();
	c_qstrncpy ( rso.data.u.footer.label, qPrintable (query.value(fieldName_toPos.value("FOOTER_LABEL")).toString() ), REPO_LABEL_LEN );
	c_qstrncpy ( rso.data.u.footer.userValue, qPrintable (query.value(fieldName_toPos.value("FOOTER_USERVALUE")).toString() ), REPO_USER_LEN );
	rso.data.u.footer.isUrl = toBoolean( query.value(fieldName_toPos.value("FOOTER_SOURCE")).toInt() );
	rso.data.u.footer.line = ( ReportSchemaObj::BreakingType ) query.value(fieldName_toPos.value("FOOTER_LINE")).toInt();
}


int APIDB_ReportSchemasTable::getIDCounter(QString nameConfig,int idAnalisys,QString tagCounter)
{
	method ( "getHelps("+nameConfig+"): " );	
	bool noDbError=false;
	int value=0;

	strSelect="select a.COUNTERINDEX,a.TAG from adams_asp.tab_type_counters a ";
	strSelect+="where a.TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' ";
	strSelect+="and a.IDCOUNTER = (select IDCOUNTERKIT from adams_asp.tab_analysis_type where TIPO_DI_CONFIGURAZIONE='N.T.M.-Voce-Standard' and IDANALISI="+QString::number(idAnalisys)+")";
	strSelect+="and TAG='"+tagCounter+"'";
	
	//lout3 << strSelect << endl;
	
	try {
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec (query);

	
		while ( query.next() ) 
		{
			value=query.value(0).toInt();
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}

	return value;
}

void APIDB_ReportSchemasTable::debugCommon(const ReportSchemaObj &rso)
{
	lout3 << "  [ COMMON Section ] START" << endl;
	lout3 << "    idReportSchema    = " << rso.data.idReportSchema << endl;
	lout3 << "    idObject          = " << rso.data.idObject << endl;
	lout3 << "    type              = " << rso.data.type << endl;
	lout3 << "    tag               = " << rso.data.tag << endl;
	lout3 << "  [ COMMON Section ] END" << endl;
	lout3 << "  [ HTML Section ] START" << endl;
	lout3 << "    html.vSize        = " << rso.data.html.vSize << endl;
	lout3 << "    html.hSize        = " << rso.data.html.hSize << endl;
	lout3 << "    html.vAlign       = " << rso.data.html.vAlign << endl;
	lout3 << "    html.hAlign       = " << rso.data.html.hAlign << endl;
	lout3 << "    html.wrap         = " << rso.data.html.wrap << endl;
	lout3 << "    html.foreground.r = " << rso.data.html.foreground.r << endl;
	lout3 << "    html.foreground.g = " << rso.data.html.foreground.g << endl;
	lout3 << "    html.foreground.b = " << rso.data.html.foreground.b << endl;
	lout3 << "    html.background.r = " << rso.data.html.foreground.r << endl;
	lout3 << "    html.background.g = " << rso.data.html.foreground.g << endl;
	lout3 << "    html.background.b = " << rso.data.html.foreground.b << endl;
	lout3 << "    html.style        = " << rso.data.html.style << endl;
	lout3 << "    html.fontSize     = " << rso.data.html.fontSize << endl;
	lout3 << "    html.bold         = " << rso.data.html.bold << endl;
	lout3 << "    html.italic       = " << rso.data.html.italic << endl;
	lout3 << "    html.underline    = " << rso.data.html.underline << endl;
	lout3 << "  [ HTML Section ] END" << endl;
}

void APIDB_ReportSchemasTable::debugObject(const ReportSchemaObj &rso)
{
	lout3 << "  object.tag = " << rso.data.u.object.tag << endl;
	lout3 << "  object.hasBorder = " << rso.data.u.object.hasBorder << endl;
	lout3 << "  object.excelCSV = " << rso.data.u.object.excelCSV << endl;
	lout3 << "  object.simpleBody = " << rso.data.u.object.simpleBody << endl;
	lout3 << "  object.idAnalisys = " << rso.data.u.object.idAnalisys << endl;
	lout3 << "  object.usePlugin = " << rso.data.u.object.usePlugin << endl;
	lout3 << "  object.pluginName = " << rso.data.u.object.pluginName << endl;
	lout3 << "  object.defaultForeground.r = " << rso.data.u.object.defaultForeground.r << endl;
	lout3 << "  object.defaultForeground.g = " << rso.data.u.object.defaultForeground.g << endl;
	lout3 << "  object.defaultForeground.b = " << rso.data.u.object.defaultForeground.b << endl;
	lout3 << "  object.defaultBackground.r = " << rso.data.u.object.defaultBackground.r << endl;
	lout3 << "  object.defaultBackground.g = " << rso.data.u.object.defaultBackground.g << endl;
	lout3 << "  object.defaultBackground.b = " << rso.data.u.object.defaultBackground.b << endl;
}

void APIDB_ReportSchemasTable::debugHeadr(const ReportSchemaObj &rso)
{
	lout3 << "  header.userLabel = " << rso.data.u.header.userLabel << endl;
	lout3 << "  header.value     = " << rso.data.u.header.value << endl;
	lout3 << "  header.userValue = " << rso.data.u.header.userValue << endl;
	lout3 << "  header.isUrl     = " << rso.data.u.header.isUrl << endl;
	lout3 << "  header.line      = " << rso.data.u.header.line << endl;
}

void APIDB_ReportSchemasTable::debugBody(const ReportSchemaObj &rso)
{
	lout3 << "  body.source    = " << rso.data.u.body.source << endl;
	lout3 << "  body.idKey     = " << rso.data.u.body.idKey << endl;
	lout3 << "  body.idCounter = " << rso.data.u.body.idCounter << endl;
	lout3 << "  body.idScript  = " << rso.data.u.body.idScript << endl;
	lout3 << "  body.idPlugin  = " << rso.data.u.body.idPlugin << endl;
	lout3 << "  body.userValue = " << rso.data.u.body.userValue << endl;
	lout3 << "  body.isUrl     = " << rso.data.u.body.isUrl << endl;
	lout3 << "  body.noTotals  = " << rso.data.u.body.noTotals << endl;
	lout3 << "  body.repeatKey = " << rso.data.u.body.repeatKey << endl;
	lout3 << "  body.decimals  = " << rso.data.u.body.decimals << endl;
	lout3 << "  body.line      = " << rso.data.u.body.line << endl;
}

void APIDB_ReportSchemasTable::debugTotalizer(const ReportSchemaObj &rso)
{
	lout3 << "  totalizer.trigger      = " << rso.data.u.totalizer.trigger << endl;
	lout3 << "  totalizer.label        = " << rso.data.u.totalizer.label << endl;
	lout3 << "  totalizer.redrawHeader = " << rso.data.u.totalizer.redrawHeader << endl;
	lout3 << "  totalizer.border       = " << rso.data.u.totalizer.border << endl;
	lout3 << "  totalizer.line         = " << rso.data.u.totalizer.line << endl;
	lout3 << "  totalizer.useScript    = " << rso.data.u.totalizer.useScript << endl;
	lout3 << "  totalizer.idScript     = " << rso.data.u.totalizer.idScript << endl;
}

void APIDB_ReportSchemasTable::debugFooter(const ReportSchemaObj &rso)
{
	lout3 << "  footer.source    = " << rso.data.u.footer.source << endl;
	lout3 << "  footer.label     = " << rso.data.u.footer.label << endl;
	lout3 << "  footer.userValue = " << rso.data.u.footer.userValue << endl;
	lout3 << "  footer.isUrl     = " << rso.data.u.footer.isUrl << endl;
	lout3 << "  footer.line      = " << rso.data.u.footer.line << endl;
}


void APIDB_ReportSchemasTable::debug()
{		
	/* debug report list */
	for ( ReportSchemasIterator it_re = local_adc->reportInterface->getIterator(); it_re != local_adc->reportInterface->hashEnd(); it_re++ )
	{
		//if(it_re.value()->data.idReportSchema!=847) continue;
		lout3 << "****************** idReportSchema = " << it_re.value()->idReportSchema << endl;
		lout3 << "[ OBJ Section ] START" << endl;
		debugCommon(it_re.value()->objO);
		debugObject(it_re.value()->objO);
		lout3 << "[ OBJ Section ] END" << endl;
		
		//  HEADER Section 
		lout3 << "[ HEADER Section ] START" << endl;
		for(int i=0;i<it_re.value()->hdrV.size();i++)
		{
			ReportSchemaObj rso=it_re.value()->hdrV.at(i);
			debugCommon(rso);
			debugHeadr(rso);
		}
		lout3 << "[ HEADER Section ] END" << endl;

		//  BODY Section 
		lout3 << "[ BODY Section ] START" << endl;
		for(int i=0;i<it_re.value()->bdyV.size();i++)
		{
			ReportSchemaObj rso=it_re.value()->bdyV.at(i);
			debugCommon(rso);
			debugBody(rso);
		}
		lout3 << "[ BODY Section ] END" << endl;
		
		//  TOTALIZER Section 
		lout3 << "[ TOTALIZER Section ] START" << endl;
		for(int i=0;i<it_re.value()->totV.size();i++)
		{
			ReportSchemaObj rso=it_re.value()->totV.at(i);
			debugCommon(rso);
			debugTotalizer(rso);
		}
		lout3 << "[ TOTALIZER Section ] END" << endl;
		
		//  FOOTER Section 
		lout3 << "[ FOOTER Section ] START" << endl;
		for(int i=0;i<it_re.value()->fooV.size();i++)
		{
			ReportSchemaObj rso=it_re.value()->fooV.at(i);
			debugCommon(rso);
			debugFooter(rso);
		}
		lout3 << "[ FOOTER Section ] END" << endl;
		
		lout3 << "******************" << endl;
		lout3 << endl << endl;

	  
	}
}

void APIDB_ReportSchemasTable::fillFromDB (QString nameConfig)
{
	int cont=0;
	reportSchemaList_it it_reportSchemaList = reportSchemaList.begin();
	
	while (it_reportSchemaList != reportSchemaList.constEnd()) 
	{
		ReportSchema *rep= it_reportSchemaList.value();
		//lout3 << "Report("<< ++cont <<") IDReport=" << rep->idReportSchema  << " TAG=" << rep->objO.data.tag << endl;
		
		local_adc->reportInterface->add(rep);
		++it_reportSchemaList;
	}
}

void APIDB_ReportSchemasTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_ReportSchemasTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getHelps("ALL_CONF");
		getPlugins(nameConfig);
		getCounters(nameConfig);
		getReportSchemaObjs(nameConfig);
		fillFromDB(nameConfig);
		closeDB();
	}
}

