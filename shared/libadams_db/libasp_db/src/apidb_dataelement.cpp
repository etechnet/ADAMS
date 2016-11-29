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
#include <ace/config-posix.h>
#include "apidb_dataelement.h"

#include <dataelementinterface.h>


using namespace std;

#define DEBUG_APIDB_DATAELEMENT_HR                                  1


APIDB_DataElementTable::APIDB_DataElementTable ( ): m_connection ( "APIDB_DataElementTable" )
{
  
}

APIDB_DataElementTable::APIDB_DataElementTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_DataElementTable" )
{
	local_adc=adc;
}



bool APIDB_DataElementTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_DataElementTable::~APIDB_DataElementTable()
{
	if(isConnect()==true) closeDB();
}


void APIDB_DataElementTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_DataElementTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}


bool APIDB_DataElementTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}


bool APIDB_DataElementTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}
  

void APIDB_DataElementTable::debug()
{		
	/* debug dataelement list */
	for ( DataElementIterator it_de = local_adc->dataElementInterface->getIterator(); it_de != local_adc->dataElementInterface->hashEnd(); it_de++ )
	{
		//if(it_de.value()->data.idElement!=847) continue;
		lout3 << "idElement             = " << it_de.value()->data.idElement << endl;
		lout3 << "shortDescription      = " << it_de.value()->data.shortDescription << endl;
		lout3 << "longDescription       = " << it_de.value()->data.longDescription << endl;
		lout3 << "guiObject             = " << it_de.value()->data.guiObject << endl;
		lout3 << "pixmapFileName        = " << it_de.value()->data.pixmapFileName << endl;
		lout3 << "valueRange            = " << it_de.value()->data.valueRange << endl;
		lout3 << "valueList             = (";
		for ( int i = 0; i < MAX_OPTIONS; i++ )
		{
			lout3 << it_de.value()->data.valueList[i] << ",";
		}
		lout3 << ")" <<endl;
		lout3 << "descList              = (";
		for ( int i = 0; i < MAX_OPTIONS; i++ )
		{
			lout3 << it_de.value()->data.valueListLabel[i] << ",";
		}
		lout3 << ")" <<endl;
		lout3 << "defaultValue          = " << it_de.value()->data.defaultValue << endl;
		lout3 << "acceptHex             = " << it_de.value()->data.acceptHex << endl;
		lout3 << "priority              = " << it_de.value()->data.priority << endl;
		lout3 << "aggregateRestrictions = (";
		for ( int i = 0; i < MAX_AGGREGATE_RESTR; i++ )
		{
			lout3 << it_de.value()->data.aggregateRestrictions[i] << ",";
		}
		lout3 << ")" <<endl;
		lout3 << "exceptions            = (";
		for ( int i = 0; i < MAX_EXCEPTIONS; i++ )
		{
			lout3 << it_de.value()->data.exceptions[i] << ",";
		}
		lout3 << ")" <<endl;
		lout3 << "idDRLink              = " << it_de.value()->data.idDRLink << endl;
		lout3 << "elementType           = " << it_de.value()->data.elementType << endl;
		lout3 << "compareSelection      = " << it_de.value()->data.compareSelection << endl;
		lout3 << "Suffix                = " << it_de.value()->data.Suffix << endl;
		lout3 << "LengthInRelation      = " << it_de.value()->data.LengthInRelation << endl;
		lout3 << "valueShifter          = (";
		for ( int i = 0; i < VALSHIFTER_MAX; i++ )
		{
			char temp[(VALSHIFTER_LEN * 3)+1];
			c_qstrncpy ( temp, qPrintable (QString(it_de.value()->data.valueShifter[i])), (VALSHIFTER_LEN * 3)+1 );
			lout3 << temp << ",";
		}
		lout3 << ")" <<endl;
		lout3 << "idPlugin             = " << it_de.value()->data.idPlugin << endl;
		lout3 << "idPluginGUI             = " << it_de.value()->data.idPluginGUI << endl;
		lout3 << "repeatKey             = " << it_de.value()->data.repeatKey << endl;
		lout3 << "ffEnabled             = " << it_de.value()->data.ffEnabled << endl;
		lout3 << "scripts               = (";
		for ( int i = 0; i < MAX_TE_SCRIPTS; i++ )
		{
			lout3 << it_de.value()->data.scripts[i] << ",";
		}
		lout3 << ")" <<endl;
		lout3 << endl;
	  
	}
}

void APIDB_DataElementTable::getScripts(QString nameConfig)
{
	method ( "getScripts("+nameConfig+"): " );	
	//lout3 << "getScripts(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_list_script";
	QString fieldName[]={"POSIZIONE_ELEMENTO","SCRIPT","SCRIPT_TYPE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="SCRIPT asc";
	
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
		query_exec( query );
	
		while ( query.next() ) 
		{
			int v_script[MAX_TE_SCRIPTS];
			for(int j=0;j<MAX_TE_SCRIPTS;j++)
			{
			  v_script[j]=0;
			}
			
			int key=query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			int script_id=query.value(fieldName_toPos.value("SCRIPT")).toInt();
			int script_type=query.value(fieldName_toPos.value("SCRIPT_TYPE")).toInt();
			
			v_script[script_type]=script_id;
			
			scriptList_it it_scriptList = scriptList.find(key);
			
			if(it_scriptList == scriptList.end() ) // key not found!!!!
			{
				scriptList.insert(key,v_script);
			}
			//lout3 << key << ", SCRIPT=" << v_script[D_SCRIPT] << "  SCRIPT_TYPE="<< v_script[D_SCRIPT_TYPE] << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
	
}

int * APIDB_DataElementTable::getScripts(int pos_element)
{
	//lout3 << "getScripts(" << pos_element << ")" << endl;	
	int i=0;
	int *scr=new int[MAX_TE_SCRIPTS];
	
	for ( i = 0; i < MAX_TE_SCRIPTS; i++ )
	{
		scr[i] = 0;
	}
	
	scriptList_it it_scriptList = scriptList.find(pos_element);
	
	if(it_scriptList != scriptList.end() ) // key found!!!!
	{	
		return *it_scriptList;
	}
	
	return scr;
}


void APIDB_DataElementTable::getPlugins(QString nameConfig)
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
		query_exec( query );
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

int APIDB_DataElementTable::getIdPlugin(QString namePlugin)
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

void APIDB_DataElementTable::getShifters(QString nameConfig)
{
	method ( "getShifters("+nameConfig+"): " );	
	//lout3 << "getShifters(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_subst_values";
	QString fieldName[]={"POSIZIONE_ELEMENTO","VALORI_SOSTITUZIONE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="VALORI_SOSTITUZIONE asc";
	
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
		query_exec( query );
	
		while ( query.next() ) 
		{
			int key=query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			QString value=query.value(fieldName_toPos.value("VALORI_SOSTITUZIONE")).toString();
			
			shifterList_it it_shifterList = shifterList.find(key);
			
			if(it_shifterList == shifterList.end() ) // key not found!!!!
			{
				shifters s;
				s.append(value);
				shifterList.insert(key,s);
			}
			else // key found
			{
				it_shifterList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

QString * APIDB_DataElementTable::getShifters(int pos_element)
{
	//lout3 << "getShifters(" << pos_element << ")" << endl;	
	int i=0;
	QString *s=new QString[VALSHIFTER_MAX];
	for ( i = 0; i < VALSHIFTER_MAX; i++ )
	{
		s[i] = "";
	}
	
	shifterList_it it_shifterList = shifterList.find(pos_element);
	
	if(it_shifterList != shifterList.end() ) // key found!!!!
	{	
		shifters shift = it_shifterList.value();
		
		shifters_it it_shifters;
		i=0;
		for (it_shifters = shift.begin(); it_shifters != shift.end(); ++it_shifters)
		{
			s[i]=*it_shifters;
			++i;
		}
	}
	
	return s;
}

void APIDB_DataElementTable::getExceptions(QString nameConfig)
{
	method ( "getExceptions("+nameConfig+"): " );	 
	//lout3 << "getExceptions(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_list_exception";
	QString fieldName[]={"POSIZIONE_ELEMENTO","IDEXCEPTION",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="IDEXCEPTION asc";
	
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
		query_exec( query );
	
	
		while ( query.next() ) 
		{
			int key=query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			int value=query.value(fieldName_toPos.value("IDEXCEPTION")).toInt();
			
			exceptionList_it it_exceptionList = exceptionList.find(key);
			
			if(it_exceptionList == exceptionList.end() ) // key not found!!!!
			{
				exceptions e;
				e.append(value);
				exceptionList.insert(key,e);
			}
			else // key found
			{
				it_exceptionList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int * APIDB_DataElementTable::getExceptions(int pos_element)
{
	//lout3 << "getExceptions(" << pos_element << ")" << endl;	
	int i=0;
	int *e=new int[MAX_EXCEPTIONS];
	for ( i = 0; i < MAX_EXCEPTIONS; i++ )
	{
		e[i] = 0;
	}
	
	exceptionList_it it_exceptionList = exceptionList.find(pos_element);
	
	if(it_exceptionList != exceptionList.end() ) // key found!!!!
	{	
		exceptions exc = it_exceptionList.value();
		
		exceptions_it it_exceptions;
		i=0;
		for (it_exceptions = exc.begin(); it_exceptions != exc.end(); ++it_exceptions)
		{
			e[i]=*it_exceptions;
			++i;
		}
	}
	
	return e;
}

void APIDB_DataElementTable::getAggregateRestrictions(QString nameConfig)
{
	method ( "getAggregateRestrictions("+nameConfig+"): " );	 
	//lout3 << "getAggregateRestrictions(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_aggregate_restrictions";
	QString fieldName[]={"POSIZIONE_ELEMENTO","AGGREG_RESTRICTION",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="AGGREG_RESTRICTION asc";
	
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
			int key=query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			int value=query.value(fieldName_toPos.value("AGGREG_RESTRICTION")).toInt();
			
			aggregateRestrictionList_it it_aggregateRestrictionList = aggregateRestrictionList.find(key);
			
			if(it_aggregateRestrictionList == aggregateRestrictionList.end() ) // key not found!!!!
			{
				restrictions r;
				r.append(value);
				aggregateRestrictionList.insert(key,r);
			}
			else // key found
			{
				it_aggregateRestrictionList.value().append(value);
			}
			//lout3 << key << "," << value << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int * APIDB_DataElementTable::getAggregateRestrictions(int pos_element)
{
	//lout3 << "getAggregateRestrictions(" << pos_element << ")" << endl;	
	int i=0;
	int *r=new int[MAX_AGGREGATE_RESTR];
	for ( i = 0; i < MAX_AGGREGATE_RESTR; i++ )
	{
		r[i] = 0;
	}
	
	aggregateRestrictionList_it it_aggregateRestrictionList = aggregateRestrictionList.find(pos_element);
	
	if(it_aggregateRestrictionList != aggregateRestrictionList.end() ) // key found!!!!
	{	
		restrictions rest = it_aggregateRestrictionList.value();
		
		restrictions_it it_restrictions;
		i=0;
		for (it_restrictions = rest.begin(); it_restrictions != rest.end(); ++it_restrictions)
		{
			r[i]=*it_restrictions;
			++i;
		}
	}
	
	return r;
}

void APIDB_DataElementTable::getValueList(QString nameConfig)
{
	method ( "getValueList("+nameConfig+"): " );	 
	//lout3 << "getValueList(" << nameConfig << ")" << endl;	
	
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_value_type";
	QString fieldName[]={"POSIZIONE_ELEMENTO","COD_VALORE","DESC_VALORE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="COD_VALORE asc";
	
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
			int key=query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			double value=query.value(fieldName_toPos.value("COD_VALORE")).toDouble();
			QString desc=query.value(fieldName_toPos.value("DESC_VALORE")).toString();
			
			valueCodeList_it it_valueCodeList = valueCodeList.find(key);
			valueDescList_it it_valueDescList = valueDescList.find(key);
			
			if(it_valueCodeList == valueCodeList.end() ) // key not found!!!!
			{
				value_code vl_code;
				vl_code.append(value);
				valueCodeList.insert(key,vl_code);
				
				value_desc vl_desc;
				vl_desc.append(desc);
				valueDescList.insert(key,vl_desc);
			}
			else // key found
			{
				it_valueCodeList.value().append(value);
				it_valueDescList.value().append(desc);
			}
			//lout3 << key << "," << value << ","<< desc << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

double * APIDB_DataElementTable::getValueList(int pos_element)
{
	//lout3 << "getValueList(" << pos_element << ")" << endl;	
	int i=0;
	double *valueCL=new double[MAX_OPTIONS];
	for ( i = 0; i < MAX_OPTIONS; i++ )
	{
		valueCL[i] = 0.0;
	}
	
	valueCodeList_it it_valueCodeList = valueCodeList.find(pos_element);
	
	if(it_valueCodeList != valueCodeList.end() ) // key found!!!!
	{	
		value_code vl_code = it_valueCodeList.value();
		
		value_code_it it_value_code;
		i=0;
		for (it_value_code = vl_code.begin(); it_value_code != vl_code.end(); ++it_value_code)
		{
			valueCL[i]=*it_value_code;
			++i;
		}
	}
	
	return valueCL;
}

QString * APIDB_DataElementTable::getHelperValues(int pos_element)
{
	//lout3 << "getHelperValues(" << pos_element << ")" << endl;	
	int i=0;
	QString *valueDL=new QString[MAX_OPTIONS];
	for ( i = 0; i < MAX_OPTIONS; i++ )
	{
		valueDL[i] = "";
	}
	
	valueDescList_it it_valueDescList = valueDescList.find(pos_element);
	
	if(it_valueDescList != valueDescList.end() ) // key found!!!!
	{	
		value_desc vl_desc = it_valueDescList.value();
		
		value_desc_it it_value_desc;
		i=0;
		for (it_value_desc = vl_desc.begin(); it_value_desc != vl_desc.end(); ++it_value_desc)
		{
			valueDL[i]=*it_value_desc;
			++i;
		}
	}
	
	return valueDL;
}

void APIDB_DataElementTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );
	bool noDbError=false;
	DataElement * de;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_config";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
                                                "POSIZIONE_ELEMENTO",		//ID Univoco progressivo contatore  // --- INPUT DATA
                                                "LINK_TE link_te_o",		//Link
						"IF(POSIZIONE_CAMPO_DR=-1 AND LINK_TE>0,(SELECT POSIZIONE_CAMPO_DR FROM adams_asp.tab_config WHERE POSIZIONE_ELEMENTO=link_te_o and TIPO_DI_CONFIGURAZIONE='N.T.M.-Voce-Standard'),POSIZIONE_CAMPO_DR)",		//field     se -1 no DR             // --- INPUT DATA  
                                                "TIPO_CAMPO",			//type                              // --- INPUT DATA
                                                "SIZE_CAMPO_DR",		//type size                         // --- INPUT DATA
                                                "FLAG_ARRAY",			//is array                          // --- INPUT DATA
                                                "NUMERO_ELEM_ARRAY",		//array size                        // --- INPUT DATA
                                                "LABEL_DR_NORMALIZZ",		//description DR                    // --- INPUT DATA
                                                "SE_INDICE",			//Identifica se Indice              // --- INPUT DATA    
                                                "VALORE_AGGREGAZIONE_DEF",	//Per Configurazione Indice         // --- INPUT DATA   
                                                "VALORE_AGGREGAZIONE",		//Per Configurazione Indice         // --- INPUT DATA         
                                                "LENGTH_VALORE_START",		//Per Configurazione Indice         // --- INPUT DATA         
                                                "SUFF_VALORE_INDICE",		//Per Configurazione Indice         // --- INPUT DATA    
                                                "DATA_INIZIO_INDICE",		//Per Configurazione Indice         // --- INPUT DATA         
                                                "DATA_FINE_INDICE",		//Per Configurazione Indice         // --- INPUT DATA       
                                                "TAG",				//TAG                               // --- TRAFFIC ELEMENT
                                                "DESCRIPTION",			//description TE                    // --- TRAFFIC ELEMENT
                                                "TIPO_OGGETTO_GUI",		//GUI Object                        // --- TRAFFIC ELEMENT
                                                "RANGE_VAL",			//Value Range                       // --- TRAFFIC ELEMENT
                                                "VALORE_DEFAULT",		//Default Value                     // --- TRAFFIC ELEMENT
                                                "ICONA",			//Pixmap URL                        // --- TRAFFIC ELEMENT
                                                "RAPPRESENTAZ_ESADECIMALE",	//Hex.Input                         // --- TRAFFIC ELEMENT
                                                "PRIORITA",			//Prioriry                          // --- TRAFFIC ELEMENT
                                                "(select HELPVALUE from adams_asp.tab_help WHERE OPERATORI_RESTRIZIONE=ID) HELPVALUE",			//Compare Selection                 // --- TRAFFIC ELEMENT
                                                "LUNG_ELEMENTO_CHIAVE",    	//Subkey Length                     // --- TRAFFIC ELEMENT
                                                "PLUGIN", 			//PATH                              // --- TRAFFIC ELEMENT
                                                "PLUGIN_PATH",	 		//PLUGIN_PATH                       // --- TRAFFIC ELEMENT
                                                "PLUGIN_GUI",			//PLUGIN_GUI                        // --- TRAFFIC ELEMENT
                                                "DEFAULT_RESTRICTION",		//default restriction               // --- TRAFFIC ELEMENT
                                                "FFENABLED",			//enabled Free Format Relation      // --- TRAFFIC ELEMENT 
                                                "ALWAYS_SHOW_REPORT",		//Always show in thr report         // --- TRAFFIC ELEMENT
						""				//endlist
						};
	QString strWhere="POSIZIONE_ELEMENTO!=-2 and TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
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
			de = new DataElement;
			de->data.idElement = query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			c_qstrncpy ( de->data.shortDescription, qPrintable (query.value(fieldName_toPos.value("TAG")).toString() ), SHORT_DESC_LEN );
			c_qstrncpy ( de->data.longDescription, qPrintable (query.value(fieldName_toPos.value("DESCRIPTION")).toString() ), LONG_DESC_LEN );
			de->data.guiObject = query.value(fieldName_toPos.value("TIPO_OGGETTO_GUI")).toInt();
			c_qstrncpy ( de->data.pixmapFileName, qPrintable (query.value(fieldName_toPos.value("ICONA")).toString() ), PIXMAP_FILENAME_LEN );
			c_qstrncpy ( de->data.valueRange, qPrintable (query.value(fieldName_toPos.value("RANGE_VAL")).toString() ), VALUE_RANGE_LEN );
			double *valuelist=getValueList(de->data.idElement);
			for ( int i = 0; i < MAX_OPTIONS; i++ )
			{
				de->data.valueList[i] = valuelist[i];
			}
			QString *desclist=getHelperValues(de->data.idElement);
			for ( int i = 0; i < MAX_OPTIONS; i++ )
			{
				c_qstrncpy ( de->data.valueListLabel[i], qPrintable(desclist[i]), OPTION_LABEL_LEN );
			}
			de->data.defaultValue = query.value(fieldName_toPos.value("VALORE_DEFAULT")).toDouble();
			de->data.acceptHex = toBoolean( query.value(fieldName_toPos.value("RAPPRESENTAZ_ESADECIMALE")).toString() );
			de->data.priority = query.value(fieldName_toPos.value("PRIORITA")).toInt();
			int *aggregate_restrictions=getAggregateRestrictions(de->data.idElement);
			for ( int i = 0; i < MAX_AGGREGATE_RESTR; i++ )
			{
				de->data.aggregateRestrictions[i] = aggregate_restrictions[i];
			}
			int *except=getExceptions(de->data.idElement);
			for ( int i = 0; i < MAX_EXCEPTIONS; i++ )
			{
				de->data.exceptions[i] = except[i];
			}
			int posDR=query.value(fieldName_toPos.value("IF(POSIZIONE_CAMPO_DR=-1 AND LINK_TE>0,(SELECT POSIZIONE_CAMPO_DR FROM adams_asp.tab_config WHERE POSIZIONE_ELEMENTO=link_te_o and TIPO_DI_CONFIGURAZIONE='N.T.M.-Voce-Standard'),POSIZIONE_CAMPO_DR)")).toInt();
			if(posDR != -1) posDR--;
			
			de->data.idDRLink = posDR;
			de->data.elementType = 0;
			de->data.compareSelection = query.value(fieldName_toPos.value("(select HELPVALUE from adams_asp.tab_help WHERE OPERATORI_RESTRIZIONE=ID) HELPVALUE")).toInt();
			
			c_qstrncpy ( de->data.Suffix, qPrintable(QString("")), TE_SUFFIX_LEN );
			de->data.LengthInRelation = query.value(fieldName_toPos.value("LUNG_ELEMENTO_CHIAVE")).toInt();
			QString *shift=getShifters(de->data.idElement);
			for ( int i = 0; i < VALSHIFTER_MAX; i++ )
			{
				strncpy ( de->data.valueShifter[i], qPrintable (shift[i]), (VALSHIFTER_LEN * 3) );
			}
			int *scripts=getScripts(de->data.idElement);
			for ( int i = 0; i < MAX_TE_SCRIPTS; i++ )
			{
				de->data.scripts[i] = scripts[i];
			}
			de->data.idPlugin = getIdPlugin(query.value(fieldName_toPos.value("PLUGIN")).toString());
			de->data.idPluginGUI = getIdPlugin(query.value(fieldName_toPos.value("PLUGIN_GUI")).toString());
			de->data.repeatKey = toBoolean( query.value(fieldName_toPos.value("ALWAYS_SHOW_REPORT")).toString() );
			de->data.ffEnabled = toBoolean( query.value(fieldName_toPos.value("FFENABLED")).toString() );
			
			local_adc->dataElementInterface->add(de);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
	
}

void APIDB_DataElementTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_DataElementTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getValueList(nameConfig);
		getAggregateRestrictions(nameConfig);
		getExceptions(nameConfig);
		getShifters(nameConfig);
		getPlugins(nameConfig);	
		getScripts(nameConfig);	
		
		fillFromDB(nameConfig);
		closeDB();
	}
}

