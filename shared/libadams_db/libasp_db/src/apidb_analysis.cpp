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
#include "apidb_analysis.h"

#include <analysisinterface.h>


using namespace std;

#define DEBUG_APIDB_ANALYSIS_HR                                  1


APIDB_AnalysisTable::APIDB_AnalysisTable ( ): m_connection ( "APIDB_AnalysisTable" )
{
  
}

APIDB_AnalysisTable::APIDB_AnalysisTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_AnalysisTable" )
{
	local_adc=adc;
}

bool APIDB_AnalysisTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_AnalysisTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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



APIDB_AnalysisTable::~APIDB_AnalysisTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_AnalysisTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_AnalysisTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_AnalysisTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}

void APIDB_AnalysisTable::getRelations(QString nameConfig)
{
	method ( "getRelations("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getRelations(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_relation a";
	QString fieldName[]={"a.RELATION_NAME","(select TAG from tab_config b where a.POSIZIONE_ELEMENTO=b.POSIZIONE_ELEMENTO and b.TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' ) R_TAG",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="R_TAG ,a.RELATION_NAME asc";
	
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
		
		i=1;

		while ( query.next() ) 
		{
			int value=i;
			QString key=query.value(fieldName_toPos.value("a.RELATION_NAME")).toString();
			
			relationList_it it_relationList = relationList.find(key);
			
			if(it_relationList == relationList.end() ) // key not found!!!!
			{
				relationList.insert(key,value);
			}
			//lout3 << key << "," << getIDRelations(key) << endl;
			++i;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int APIDB_AnalysisTable::getIDRelations(QString nameRelation)
{
	int idRelation=-1;
	relationList_it it_relationList = relationList.find(nameRelation);
	if(it_relationList == relationList.end() )
	{
		idRelation=-1;
	}else
	{
		idRelation=relationList.value(nameRelation);
	}
	return idRelation;
}

void APIDB_AnalysisTable::getRelationsAnalisys(QString nameConfig)
{
	method ( "getRelationsAnalisys("+nameConfig+"): " );
	//lout3 << "getRelationsAnalisys(" << nameConfig << ")" << endl;
	bool noDbError=false;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_analysis";
	QString fieldName[]={"RELATION_NAME","IDANALISI",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDANALISI asc";
	
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
			int key=query.value(fieldName_toPos.value("IDANALISI")).toInt();
			int value=getIDRelations(query.value(fieldName_toPos.value("RELATION_NAME")).toString());
			
			relAnalysisList_it it_relAnalysisList = relAnalysisList.find(key);
			
			if(it_relAnalysisList == relAnalysisList.end() ) // key not found!!!!
			{
				relAnalysis rela;
				rela.append(value);
				relAnalysisList.insert(key,rela);
			}
			else // key found
			{
				it_relAnalysisList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int *APIDB_AnalysisTable::getRelationsAnalisys(int idAnalisys)
{
	//lout3 << "getRelationsAnalisys(" << idAnalisys << ")" << endl;	
	int i=0;
	int *rel=new int[MAX_RELATION];
	for ( i = 0; i < MAX_RELATION; i++ )
	{
		rel[i] = 0;
	}
	
	relAnalysisList_it it_relAnalysisList = relAnalysisList.find(idAnalisys);
	
	if(it_relAnalysisList != relAnalysisList.end() ) // key found!!!!
	{	
		relAnalysis rela = it_relAnalysisList.value();
		
		relAnalysis_it it_relAnalysis;
		i=0;
		for (it_relAnalysis = rela.begin(); it_relAnalysis != rela.end(); ++it_relAnalysis)
		{
			rel[i]=*it_relAnalysis;
			++i;
		}
	}
	
	return rel;
} 

void APIDB_AnalysisTable::getReports(QString nameConfig)
{
	method ( "getReports("+nameConfig+"): " );
	//lout3 << "getReports(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_list_report";
	QString fieldName[]={"IDANALISI","IDREPORT",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDREPORT asc";
	
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
			int key=query.value(fieldName_toPos.value("IDANALISI")).toInt();
			int value=query.value(fieldName_toPos.value("IDREPORT")).toInt();
			
			reportList_it it_reportList = reportList.find(key);
			
			if(it_reportList == reportList.end() ) // key not found!!!!
			{
				reports e;
				e.append(value);
				reportList.insert(key,e);
			}
			else // key found
			{
				it_reportList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int *APIDB_AnalysisTable::getReports(int idAnalisys)
{
	//lout3 << "getReports(" << pos_element << ")" << endl;	
	int i=0;
	int *r=new int[MAX_ANALYSIS_REPORTS];
	for ( i = 0; i < MAX_ANALYSIS_REPORTS; i++ )
	{
		r[i] = 0;
	}
	
	reportList_it it_reportList = reportList.find(idAnalisys);
	
	if(it_reportList != reportList.end() ) // key found!!!!
	{	
		reports rep = it_reportList.value();
		
		reports_it it_reports;
		i=0;
		for (it_reports = rep.begin(); it_reports != rep.end(); ++it_reports)
		{
			r[i]=*it_reports;
			++i;
		}
	}
	
	return r;
}

void APIDB_AnalysisTable::debug()
{		
	/* debug Analysis list */
	for ( AnalysisIterator it_an = local_adc->analysisInterface->getIterator(); it_an != local_adc->analysisInterface->hashEnd(); it_an++ )
	{
		//if(it_an.value()->data.idAnalysis!=847) continue;
		lout3 << "idAnalysis         = " << it_an.value()->data.idAnalysis << endl;
		lout3 << "FlagArchiveData    = " << it_an.value()->data.FlagArchiveData << endl;
		lout3 << "FlagCentrale       = " << it_an.value()->data.FlagCentrale << endl;
		lout3 << "FlagDate           = " << it_an.value()->data.FlagDate << endl;
		lout3 << "FlagOutputType     = " << it_an.value()->data.FlagOutputType << endl;
		lout3 << "FlagTimeRes        = " << it_an.value()->data.FlagTimeRes << endl;
		lout3 << "FlagTrafficType    = " << it_an.value()->data.FlagTrafficType << endl;
		lout3 << "FlagSort           = " << it_an.value()->data.FlagSort << endl;
		lout3 << "FlagCumulativeData = " << it_an.value()->data.FlagCumulativeData << endl;
		lout3 << "LongDescription    = " << it_an.value()->data.LongDescription << endl;
		lout3 << "ShortDescription   = " << it_an.value()->data.ShortDescription << endl;
		lout3 << "countersKitId      = " << it_an.value()->data.countersKitId << endl;
		lout3 << "reportsId (";
		for ( int i = 0; i < MAX_ANALYSIS_REPORTS; i++ )
		{
			lout3 << it_an.value()->data.reportsId[i] << ",";
		}
		lout3 << ")" << endl;
		lout3 << "relations (";
		for ( int i = 0; i < MAX_RELATION; i++ )
		{
			lout3 << it_an.value()->data.relations[i] << ",";
		}
		lout3 << ")" << endl;
		lout3 << endl;
	}
}


void APIDB_AnalysisTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );	
	bool noDbError=false;
	Analysis * ana;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_analysis_type";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
                                                "IDANALISI",
						"NOMEANALISI",
						"IDCOUNTERKIT",
						"FLAGSORT",
						"FLAGDATA",
						"FLAGCENTRALE",
						"FLAGCUMULAZIONE",
						""				//endlist
						};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="NOMEANALISI asc";
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
			ana = new Analysis;
			
			ana->data.idAnalysis = query.value(fieldName_toPos.value("IDANALISI")).toInt();
			ana->data.FlagArchiveData = toBoolean("Y");
			ana->data.FlagCentrale = toBoolean(query.value(fieldName_toPos.value("FLAGCENTRALE")).toString());
			ana->data.FlagDate = toBoolean(query.value(fieldName_toPos.value("FLAGDATA")).toString());
			ana->data.FlagOutputType = toBoolean("Y");
			ana->data.FlagTimeRes = toBoolean("Y");
			ana->data.FlagTrafficType = toBoolean("Y");
			ana->data.FlagSort = toBoolean(query.value(fieldName_toPos.value("FLAGSORT")).toString());
			ana->data.FlagCumulativeData = toBoolean(query.value(fieldName_toPos.value("FLAGCUMULAZIONE")).toString());
			c_qstrncpy ( ana->data.LongDescription, qPrintable(query.value(fieldName_toPos.value("NOMEANALISI")).toString()), LONG_DESC_LEN );
			c_qstrncpy ( ana->data.ShortDescription, qPrintable(query.value(fieldName_toPos.value("NOMEANALISI")).toString()), SHORT_DESC_LEN );
			ana->data.countersKitId = query.value(fieldName_toPos.value("IDCOUNTERKIT")).toInt();
			int *rel_analisys=getRelationsAnalisys(ana->data.idAnalysis);
			for ( int i = 0; i < MAX_RELATION; i++ )
			{
				ana->data.relations[i] = rel_analisys[i];
			}
			int *reports_id=getReports(ana->data.idAnalysis);
			for ( int i = 0; i < MAX_ANALYSIS_REPORTS; i++ )
			{
				ana->data.reportsId[i] = reports_id[i];
			}
			local_adc->analysisInterface->add(ana);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_AnalysisTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_AnalysisTable::load(QString nameConfig)
{	
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getRelations(nameConfig);
		getRelationsAnalisys(nameConfig); // necessariamente dopo getRelations
		getReports(nameConfig);
		fillFromDB(nameConfig);
		closeDB();
	}
}

