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
#include "apidb_exception.h"

#include <exceptioninterface.h>


using namespace std;

#define DEBUG_APIDB_EXCEPTION_HR                                  1


APIDB_ExceptionTable::APIDB_ExceptionTable ( ): m_connection ( "APIDB_ExceptionTable" )
{
  
}

APIDB_ExceptionTable::APIDB_ExceptionTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_ExceptionTable" )
{
	local_adc=adc;
}

bool APIDB_ExceptionTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_ExceptionTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_ExceptionTable::~APIDB_ExceptionTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_ExceptionTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_ExceptionTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_ExceptionTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}

void APIDB_ExceptionTable::getHelps(QString nameConfig)
{
	method ( "getHelps("+nameConfig+"): " );
	//lout3 << "getHelps(" << nameConfig << ")" << endl;
	bool noDbError=false;
	
	QString strFieldName="";
	QString tableName="adams_asp.tab_help";
	QString fieldName[]={"ID","HELPVALUE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' AND IDHELP="+QString::number(TYPE_HELP_TRIGGER);
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

int APIDB_ExceptionTable::getHelp(int typeField)
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


void APIDB_ExceptionTable::debug()
{		
	/* debug exception list */
	for ( ExceptionIterator it_exc = local_adc->exceptionInterface->getIterator(); it_exc != local_adc->exceptionInterface->hashEnd(); it_exc++ )
	{
		//if(it_exc.value()->data.idException!=847) continue;
		lout3 << "idException          = " << it_exc.value()->data.idException << endl;
		lout3 << "tag                  = " << it_exc.value()->data.tag << endl;
		lout3 << "description          = " << it_exc.value()->data.description << endl;
		lout3 << "idTriggerRestriction = " << it_exc.value()->data.idTriggerRestriction << endl;
		lout3 << "triggeredStatus      = " << it_exc.value()->data.triggeredStatus << endl;
		lout3 << "triggeredValue       = " << it_exc.value()->data.triggeredValue << endl;
		lout3 << "action               = " << it_exc.value()->data.action << endl;
		lout3 << "targetValue          = " << it_exc.value()->data.targetValue << endl;
		lout3 << "idAggregateException = " << it_exc.value()->data.idAggregateException << endl;
		lout3 << endl;
	}
}


void APIDB_ExceptionTable::fillFromDB (QString nameConfig)
{
	method ( "nameConfig("+nameConfig+"): " );
	bool noDbError=false;
	Exception * exc;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_gui_events";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",   
                            "IDEXCEPTION",
                            "TAG",
                            "DESCRIPTION",
                            "IDTRIGGERRESTRICTION",
                            "TRIGGEREDSTATUS",
                            "TRIGGEREDVALUE",
                            "ACTION",
                            "TARGETVALUE",
                            "IDAGGREGATEEXCEPTION",
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
			exc = new Exception;
			exc->data.idException = query.value(fieldName_toPos.value("IDEXCEPTION")).toInt();
			c_qstrncpy ( exc->data.tag, qPrintable(query.value(fieldName_toPos.value("TAG")).toString()), SHORT_DESC_LEN );
			c_qstrncpy ( exc->data.description, qPrintable(query.value(fieldName_toPos.value("DESCRIPTION")).toString()), LONG_DESC_LEN );
			
			int idTriggerRestriction=query.value(fieldName_toPos.value("IDTRIGGERRESTRICTION")).toInt();
			if(idTriggerRestriction==-1) 
			{
				idTriggerRestriction=0;
			}
			exc->data.idTriggerRestriction = idTriggerRestriction;
			
			exc->data.triggeredStatus = getHelp(query.value(fieldName_toPos.value("TRIGGEREDSTATUS")).toInt());
			c_qstrncpy ( exc->data.triggeredValue, qPrintable(query.value(fieldName_toPos.value("TRIGGEREDVALUE")).toString()), ASCII_REST_LEN );
			exc->data.action = getHelp(query.value(fieldName_toPos.value("ACTION")).toInt());
			c_qstrncpy ( exc->data.targetValue, qPrintable(query.value(fieldName_toPos.value("TARGETVALUE")).toString()), ASCII_REST_LEN );
			
			int idAggregateException=query.value(fieldName_toPos.value("IDAGGREGATEEXCEPTION")).toInt();
			if(idAggregateException==-1) 
			{
				idAggregateException=0;
			}
			exc->data.idAggregateException = idAggregateException;

		
			local_adc->exceptionInterface->add(exc);
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}

	
}

void APIDB_ExceptionTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_ExceptionTable::load(QString nameConfig)
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

