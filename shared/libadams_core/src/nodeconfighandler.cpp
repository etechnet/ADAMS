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
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

/***************************************************************************
                          nodeconfighandler.cpp  -  description
                             -------------------
    begin                : Wed May 2 2001
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include <nodeconfighandler.h>
#include <cnfglobals.h>
#include <adamsserver.h>
#include <applogger.h>

NodeConfigHandler::NodeConfigHandler() : initializedFlag(false)
{
	inifilename = ADAMSINIFILE;
	load("adams_asp","adams","adams");
	return;
}

NodeConfigHandler::NodeConfigHandler(const QString & iniref) : initializedFlag(false)
{
	inifilename = iniref;
	load("adams_asp","adams","adams");
	return;
}

bool NodeConfigHandler::openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD)
{
        bool noDbError = false;
	
	method ( "openDB(NodeConfigHandler): " );	
	strUSER = hstrUSER;
	strPSWD = hstrPSWD;
	strDBNAME = hstrDBNAME;

	if ( isConnect() == false )
	{   
		db = QSqlDatabase::addDatabase ( "QMYSQL" ); // QMYSQL -> MySql Database
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
	
	return noDbError;
}

bool NodeConfigHandler::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database();
	return db_test.isOpen();
}


void NodeConfigHandler::load(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD)
{
	bool noDbError=false;
	PAR_INPUT_NODE parinputnode_ptr;
	
	if (initializedFlag)			/* all things already done */
		return;
	
	lout3 << "-----> NodeConfigHandler::load("<< hstrDBNAME << "," <<  hstrUSER << "," << hstrPSWD <<")" << endl;
	
	bool dbConnect=isConnect();
	
	if ( dbConnect == false )
	{
		dbConnect=openDB(hstrDBNAME,hstrUSER,hstrPSWD);
	}
	
	if ( dbConnect == true )
	{
		inputNodeList.clear();
	      
		QString strFieldName="";
		QString tableName="adams_asp.tab_inputnode";
		QString fieldName[]={"IND_NODE",
				      "FLAG_ATT",
				      "PFX_NODE",
				      "SUF_NODE",
				      "NODE",
				      "NAME_NODE",
				      "TYPE_NODE",				 
				      "LATITUDE_O",
				      "LATITUDE_G",
				      "LATITUDE_P",
				      "LATITUDE_S",
				      "LONGITUDE_O",
				      "LONGITUDE_G",
				      "LONGITUDE_P",
				      "LONGITUDE_S",
				      ""};
		QString strWhere="1";
		QString strOrderBy="NAME_NODE asc";
		
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
		
		QString strSelect="SELECT "+strFieldName;
		strSelect+=" FROM "+tableName;
		strSelect+=" WHERE "+strWhere;
		strSelect+=" ORDER BY "+strOrderBy;
	  
		//lout3 << strSelect << endl;
	      
		try {
			QSqlQuery query(db);
			query.prepare( strSelect );
			query_exec (query);

			while ( query.next() ) 
			{
				parinputnode_ptr.iIndice=query.value(fieldName_toPos.value("IND_NODE")).toInt();
				parinputnode_ptr.iActive=query.value(fieldName_toPos.value("FLAG_ATT")).toInt();
				parinputnode_ptr.acPrefix=(query.value(fieldName_toPos.value("PFX_NODE")).toString());
				parinputnode_ptr.acSuffix=(query.value(fieldName_toPos.value("SUF_NODE")).toString()) ;
				parinputnode_ptr.acName=(query.value(fieldName_toPos.value("NODE")).toString()) ;
				parinputnode_ptr.acNameNodo=(query.value(fieldName_toPos.value("NAME_NODE")).toString());    
				parinputnode_ptr.typeNode=(query.value(fieldName_toPos.value("TYPE_NODE")).toString());    
				
				parinputnode_ptr.latitude_orientation=( orientation ) query.value(fieldName_toPos.value("LATITUDE_O")).toInt();
				parinputnode_ptr.latitude_degrees=query.value(fieldName_toPos.value("LATITUDE_G")).toDouble();
				parinputnode_ptr.latitude_first=query.value(fieldName_toPos.value("LATITUDE_P")).toDouble();
				parinputnode_ptr.latitude_second=query.value(fieldName_toPos.value("LATITUDE_S")).toDouble();
				parinputnode_ptr.longitude_orientation=( orientation ) query.value(fieldName_toPos.value("LONGITUDE_O")).toInt();
				parinputnode_ptr.longitude_degrees=query.value(fieldName_toPos.value("LONGITUDE_G")).toDouble();
				parinputnode_ptr.longitude_first=query.value(fieldName_toPos.value("LONGITUDE_P")).toDouble();
				parinputnode_ptr.longitude_second=query.value(fieldName_toPos.value("LONGITUDE_S")).toDouble();
			
		
				inputNodeList.append(parinputnode_ptr);
	// 			// DEBUG...
				
				//debug(&parinputnode_ptr);
			}
			initializedFlag=true;
		}
		catch ( A_DBException & de ) 
		{
			lout << de.preface() << de.explain() << endl;
			initializedFlag=false;
		}
		
		db = QSqlDatabase::database();
		
		closeDB();

	}
	else
	{
		initializedFlag=false;
	}
	
	return;
}

void NodeConfigHandler::debug(const PAR_INPUT_NODE *parinputnode_ptr)
{
	lout3 << "ADDING PAR_INPUT_NODE: " << endl
		<< "iIndice:               " << parinputnode_ptr->iIndice << endl
		<< "iActive:               " << parinputnode_ptr->iActive << endl
		<< "acPrefix:              " << parinputnode_ptr->acPrefix << endl
		<< "acSuffix:              " << parinputnode_ptr->acSuffix << endl					
		<< "acName:                " << parinputnode_ptr->acName << endl
		<< "acNameNodo             " << parinputnode_ptr->acNameNodo << endl
		<< "typeNode               " << parinputnode_ptr->typeNode << endl
		<< "latitude_orientation:  " << parinputnode_ptr->latitude_orientation << endl
		<< "latitude_degrees:      " << parinputnode_ptr->latitude_degrees << endl
		<< "latitude_first:        " << parinputnode_ptr->latitude_first << endl
		<< "latitude_second:       " << parinputnode_ptr->latitude_second << endl
		<< "longitude_orientation: " << parinputnode_ptr->longitude_orientation << endl
		<< "longitude_degrees:     " << parinputnode_ptr->longitude_degrees << endl
		<< "longitude_first:       " << parinputnode_ptr->longitude_first << endl
		<< "longitude_second:      " << parinputnode_ptr->longitude_second << endl;
}

void NodeConfigHandler::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

NodeConfigHandler::~NodeConfigHandler()
{

}
