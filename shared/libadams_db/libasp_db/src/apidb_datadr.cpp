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
#include "apidb_datadr.h"

#include <drinterface.h>


using namespace std;

#define DEBUG_APIDB_DATADR_HR                                  1


APIDB_DataDRTable::APIDB_DataDRTable ( ): m_connection ( "APIDB_DataDRTable" )
{
  
}

APIDB_DataDRTable::APIDB_DataDRTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_DataDRTable" )
{
	local_adc=adc;
	
}



bool APIDB_DataDRTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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

bool APIDB_DataDRTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}


APIDB_DataDRTable::~APIDB_DataDRTable()
{
	if(isConnect()==true) closeDB();
}


void APIDB_DataDRTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}


void APIDB_DataDRTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}


bool APIDB_DataDRTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}

void APIDB_DataDRTable::getHelps(QString nameConfig)
{
	method ( "getHelps("+nameConfig+"): " );	
	//lout3 << "getScripts(" << getHelps(DATA_DR) << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_help";
	QString fieldName[]={"ID","DESCRIPTION",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' AND IDHELP="+QString::number(TYPE_HELP_DR);
	QString strOrderBy="DESCRIPTION asc";
	
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
			QString value=query.value(fieldName_toPos.value("DESCRIPTION")).toString();
			
			helpList_it it_helpList = helpList.find(key);
			
			if(it_helpList == helpList.end() ) // key not found!!!!
			{
				helpList.insert(key,value);
			}
			//lout3 << key << "," << value << " tipo="<< getHelp(key) << endl;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int APIDB_DataDRTable::getHelp(int typeField)
{
	int typeHelp=-1;
	helpList_it it_helpList = helpList.find(typeField);
	if(it_helpList == helpList.end() )
	{
		typeHelp=-1;
	}else
	{
		QString desc=helpList.value(typeField);
		if(desc=="Char")
			typeHelp=0;
		else if(desc=="Filler")
			typeHelp=0;
		else if(desc=="Unsigned Char")
			typeHelp=1;
		else if(desc=="Short")
			typeHelp=2;
		else if(desc=="Unsigned Short")
			typeHelp=3;
		else if(desc=="Int")
			typeHelp=4;
		else if(desc=="Unsigned Int")
			typeHelp=5;
		else if(desc=="Long")
			typeHelp=6;
		else if(desc=="Unsigned Long")
			typeHelp=7;
		else if(desc=="Float")
			typeHelp=8;
		else if(desc=="Double")
			typeHelp=9;
		else if(desc=="Binary Coded Decimal")
			typeHelp=10;
		else if(desc=="C-Type Char String")
			typeHelp=11;
		else if(desc=="Byte String")
			typeHelp=12;
	}
	return typeHelp;
}

void APIDB_DataDRTable::debug()
{		
	/* debug dataelement list */
	for ( DRFieldIterator it_dr = local_adc->drInterface->getIterator(); it_dr != local_adc->drInterface->hashEnd(); it_dr++ )
	{
		//if(it_dr.value()->data.position!=101) continue;
		lout3 << "position               = " << it_dr.value()->data.position << endl;
		lout3 << "fieldtype              = " << it_dr.value()->data.fieldtype << endl;
		lout3 << "offset                 = " << it_dr.value()->data.offset << endl;
		lout3 << "size                   = " << it_dr.value()->data.size << endl;
		lout3 << "type_size              = " << it_dr.value()->data.type_size << endl;
		lout3 << "is_array               = " << it_dr.value()->data.is_array << endl;
		lout3 << "array_size             = " << it_dr.value()->data.array_size << endl;
		lout3 << "description            = " << it_dr.value()->data.description << endl;
		lout3 << "isIndex                = " << it_dr.value()->data.isIndex << endl;
		lout3 << "indexByPlugin          = " << it_dr.value()->data.indexByPlugin << endl;
		lout3 << "indexBlockSize         = " << it_dr.value()->data.indexBlockSize << endl;
		lout3 << "indexRealTimeBlockSize = " << it_dr.value()->data.indexRealTimeBlockSize << endl;
		lout3 << "startSize              = " << it_dr.value()->data.startSize << endl;
		lout3 << "indexSuffix            = " << it_dr.value()->data.indexSuffix << endl;
		lout3 << "startTime              = " << it_dr.value()->data.startTime << endl;
		lout3 << "endTime                = " << it_dr.value()->data.endTime << endl;
		lout3 << endl;
	  
	}
}


void APIDB_DataDRTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );	
	bool noDbError=false;
	DRField * dr;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_config";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
                                                "POSIZIONE_ELEMENTO",		//ID Univoco progressivo contatore  // --- INPUT DATA
						"POSIZIONE_CAMPO_DR",		//field     se -1 no DR             // --- INPUT DATA  
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
                                                "OPERATORI_RESTRIZIONE",	//Compare Selection                 // --- TRAFFIC ELEMENT
                                                "LUNG_ELEMENTO_CHIAVE",    	//Subkey Length                     // --- TRAFFIC ELEMENT
                                                "PLUGIN", 			//PATH                              // --- TRAFFIC ELEMENT
                                                "PLUGIN_PATH",	 		//PLUGIN_PATH                       // --- TRAFFIC ELEMENT
                                                "PLUGIN_GUI",			//PLUGIN_GUI                        // --- TRAFFIC ELEMENT
                                                "DEFAULT_RESTRICTION",		//default restriction               // --- TRAFFIC ELEMENT
                                                "FFENABLED",			//enabled Free Format Relation      // --- TRAFFIC ELEMENT 
                                                "ALWAYS_SHOW_REPORT",		//Always show in thr report         // --- TRAFFIC ELEMENT
						"LINK_TE",			//link
						""				//endlist
						};
	QString strWhere="POSIZIONE_ELEMENTO!=-2 and POSIZIONE_CAMPO_DR > 0 and TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";

	QString strOrderBy="POSIZIONE_CAMPO_DR asc";
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
		query_exec( query );

		int dr_offset=0,dr_size=0;
		while ( query.next() ) 
		{	
			dr = new DRField;
			dr_size=0;
			
			if( toBoolean(query.value(fieldName_toPos.value("FLAG_ARRAY")).toString()) == true )
			{
				dr_size=query.value(fieldName_toPos.value("SIZE_CAMPO_DR")).toInt() * query.value(fieldName_toPos.value("NUMERO_ELEM_ARRAY")).toInt();
			}
			else
			{
				dr_size=query.value(fieldName_toPos.value("SIZE_CAMPO_DR")).toInt();
			}
			
			dr->data.position = query.value(fieldName_toPos.value("POSIZIONE_CAMPO_DR")).toInt();
			dr->data.fieldtype = getHelp(query.value(fieldName_toPos.value("TIPO_CAMPO")).toInt());
			dr->data.offset = dr_offset;
			dr->data.size = dr_size;
			dr->data.type_size = query.value(fieldName_toPos.value("SIZE_CAMPO_DR")).toInt();
			dr->data.is_array = toBoolean(query.value(fieldName_toPos.value("FLAG_ARRAY")).toString());
			dr->data.array_size = query.value(fieldName_toPos.value("NUMERO_ELEM_ARRAY")).toInt();
			c_qstrncpy ( dr->data.description, qPrintable(query.value(fieldName_toPos.value("LABEL_DR_NORMALIZZ")).toString() ), DR_FIELDSDESCRIPTIONLENGHT );
			dr->data.isIndex = toBoolean(query.value(fieldName_toPos.value("SE_INDICE")).toString());
			dr->data.indexByPlugin = false;
			dr->data.indexBlockSize = query.value(fieldName_toPos.value("VALORE_AGGREGAZIONE_DEF")).toInt();
			dr->data.indexRealTimeBlockSize = query.value(fieldName_toPos.value("VALORE_AGGREGAZIONE")).toInt();
			dr->data.startSize = query.value(fieldName_toPos.value("LENGTH_VALORE_START")).toInt();
			c_qstrncpy ( dr->data.indexSuffix, qPrintable( query.value(fieldName_toPos.value("SUFF_VALORE_INDICE")).toString() ), TE_SUFFIX_LEN );
			c_qstrncpy ( dr->data.startTime, qPrintable( query.value(fieldName_toPos.value("DATA_INIZIO_INDICE")).toString() ), DR_DATE_LEN );
			c_qstrncpy ( dr->data.endTime, qPrintable( query.value(fieldName_toPos.value("DATA_FINE_INDICE")).toString() ), DR_DATE_LEN );

			local_adc->drInterface->add(dr);
			
			
			dr_offset+=dr_size;
		}
		
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
	}
	
}

void APIDB_DataDRTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_DataDRTable::load(QString nameConfig)
{
	
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getHelps("ALL_CONF");
		fillFromDB(nameConfig);
		closeDB();
	}
}

