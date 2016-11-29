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
#include "apidb_relation.h"

#include <relationinterface.h>


using namespace std;

#define DEBUG_APIDB_REATION_HR                                  1


APIDB_RelationTable::APIDB_RelationTable ( ): m_connection ( "APIDB_RelationTable" )
{
  
}

APIDB_RelationTable::APIDB_RelationTable(AdamsCompleteConfig *adc): m_connection ( "APIDB_RelationTable" )
{
	local_adc=adc;
}



bool APIDB_RelationTable::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	return db_test.isOpen();
}

bool APIDB_RelationTable::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
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


APIDB_RelationTable::~APIDB_RelationTable()
{
	if(isConnect()==true) closeDB();
}

void APIDB_RelationTable::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_RelationTable::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}

bool APIDB_RelationTable::toBoolean(int value) 
{
	if(value>0)
            return true;
        else
            return false;
}

bool APIDB_RelationTable::toBoolean(QString str) 
{
	if(str == QString('Y'))
	    return true;
	else
	    return false;
}


void APIDB_RelationTable::makeListRelations(QString nameConfig)
{
	method ( "makeListRelations("+nameConfig+"): " );
	bool noDbError=false;
	bool ok;
	int i=0;
	relation *rel=NULL;
	//lout3 << "makeListRelations(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_relation";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
                                                "POSIZIONE_ELEMENTO",
                                                "RELATION_NAME",
						"DIREZIONE",
                                                "HEXOUTPUT",
                                                "NETWORK",
                                                "GHOST",
						""
						};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="RELATION_NAME asc";
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
			QString relName=query.value(fieldName_toPos.value("RELATION_NAME")).toString();
			//lout3 << relName << " count(::)=" << relName.count("::") << " ID=" << getIDRelations(relName) << endl;
			if( relName.count("::")==1 ) //relazione a 2 elementi
			{
				rel=new relation;
				for(int i=0;i<NEXTLEVEL_RELATIONS;i++)
				{
				      rel->data.nextLevelRelations[i]=0;
				}
				rel->data.idRelation=getIDRelations(relName);
				rel->data.idParentRelation=0;
				/*int el1=firstElement(relName).toInt(&ok,10);
				int el2=secondElement(relName).toInt(&ok,10);
				lout3 << "EL1=" << el1 << endl; 
				lout3 << "EL2=" << el2 << endl; */
				rel->data.idFirstElement=firstElement(relName).toInt(&ok,10);
				rel->data.idSecondElement=secondElement(relName).toInt(&ok,10);
				int idDirezione=query.value(fieldName_toPos.value("DIREZIONE")).toInt();
				if(idDirezione>=40) //Forzatura per gestione id direzione su restrizioni
				{
				    idDirezione=idDirezione-40;
				    //il 40 perchè attualmente gli id delle direzioni partono da 40 anziche 1.
				}
				rel->data.admittedDirections=idDirezione;
				rel->data.admitHexValues=toBoolean(query.value(fieldName_toPos.value("HEXOUTPUT")).toString());
				rel->data.admitNetworkAnalisys=toBoolean(query.value(fieldName_toPos.value("NETWORK")).toString());
				int *id_rest=getIDRestriction(relName);
				for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
				{
					rel->data.restrictions[i]=id_rest[i];
				}
				//addRestrictionsDefaul(rel->data.restrictions,rel->data.idRelation);
				int *id_tied_rest=getIDTiedRestriction(relName);
				for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
				{
					rel->data.tiedRestrictions[i]=id_tied_rest[i];
				}
				
				relationIDList.insert(relName,rel);
			}
			else if( relName.count("::") >1 ) //relazione con più di 2 elementi
			{
				QString relParentName=parentRelation(relName);
				int el2=secondElement(relName).toInt(&ok,10);
				//lout3 << "EL1=" << relParentName << endl; 
				//lout3 << "EL2=" << el2 << endl;
				
				relationIDList_it it_relationIDList = relationIDList.find(relParentName);
				relation *relationParent = *it_relationIDList;
				//lout3 << "relationParent("<< relParentName << ") id=" << relationParent->data.idRelation << endl;
				
				updateNextLevel(relationParent,getIDRelations(relName));
				
				rel=new relation;
				for(int i=0;i<NEXTLEVEL_RELATIONS;i++)
				{
				      rel->data.nextLevelRelations[i]=0;
				}
				rel->data.idRelation=getIDRelations(relName);
				rel->data.idParentRelation=getIDRelations(relParentName);
				rel->data.idFirstElement=secondElement(relName).toInt(&ok,10);
				rel->data.idSecondElement=0;
				int idDirezione=query.value(fieldName_toPos.value("DIREZIONE")).toInt();
				if(idDirezione>=40) //Forzatura per gestione id direzione su restrizioni
				{
				    idDirezione=idDirezione-40;
				    //il 40 perchè attualmente gli id delle direzioni partono da 40 anziche 1.
				}
				rel->data.admittedDirections=idDirezione;
				rel->data.admitHexValues=toBoolean(query.value(fieldName_toPos.value("HEXOUTPUT")).toInt());
				rel->data.admitNetworkAnalisys=toBoolean(query.value(fieldName_toPos.value("NETWORK")).toInt());
				rel->data.ghostRelation=toBoolean(query.value(fieldName_toPos.value("GHOST")).toInt());
				int *id_rest=getIDRestriction(relName);
				for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
				{
					rel->data.restrictions[i]=id_rest[i];
				}
				//addRestrictionsDefaul(rel->data.restrictions,rel->data.idRelation);
				int *id_tied_rest=getIDTiedRestriction(relName);
				for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
				{
					rel->data.tiedRestrictions[i]=id_tied_rest[i];
				}
				
				relationIDList.insert(relName,rel);
			}
			else // potrebbe essere una GHOST
			{
				bool ghost=toBoolean(query.value(fieldName_toPos.value("GHOST")).toInt());
				if(ghost)
				{
					rel=new relation;
					for(int i=0;i<NEXTLEVEL_RELATIONS;i++)
					{
					      rel->data.nextLevelRelations[i]=0;
					}
					rel->data.idRelation=getIDRelations(relName);
					rel->data.idParentRelation=0;
					rel->data.idFirstElement=0;
					rel->data.idSecondElement=0;
					int idDirezione=query.value(fieldName_toPos.value("DIREZIONE")).toInt();
					if(idDirezione>=40) //Forzatura per gestione id direzione su restrizioni
					{
					    idDirezione=idDirezione-40;
					    //il 40 perchè attualmente gli id delle direzioni partono da 40 anziche 1.
					}
					rel->data.admittedDirections=idDirezione;
					rel->data.admitHexValues=toBoolean(query.value(fieldName_toPos.value("HEXOUTPUT")).toInt());
					rel->data.admitNetworkAnalisys=toBoolean(query.value(fieldName_toPos.value("NETWORK")).toInt());
					rel->data.ghostRelation=ghost;
					int *id_rest=getIDRestriction(relName);
					for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
					{
						rel->data.restrictions[i]=id_rest[i];
					}
					//addRestrictionsDefaul(rel->data.restrictions,rel->data.idRelation);
					int *id_tied_rest=getIDTiedRestriction(relName);
					for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
					{
						rel->data.tiedRestrictions[i]=id_tied_rest[i];
					}
					
					relationIDList.insert(relName,rel);
				}
			}
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

void APIDB_RelationTable::addRestrictionsDefaul(int *appRest,int idRel) //UNUSED
{
	int i=0;
	
	restrictionDefaultList_it it_restrictionDefaultList;
	
	for (it_restrictionDefaultList = restrictionDefaultList.begin(); it_restrictionDefaultList != restrictionDefaultList.end(); ++it_restrictionDefaultList)
	{
		appRest[i]=*it_restrictionDefaultList;
		++i;
	}
	
}

void APIDB_RelationTable::updateNextLevel(relation *rela,int idRel)
{
	int i=0;
	while(rela->data.nextLevelRelations[i]!=0)
	{	
		++i;
	}
	rela->data.nextLevelRelations[i]=idRel;
}
QString APIDB_RelationTable::firstElement(QString relName)
{
	QString el1="";
	int ind=relName.indexOf("::");
	if(ind>0)
	{
		el1=relName.mid(0,ind);
	}
	return el1;
}

QString APIDB_RelationTable::secondElement(QString relName)
{
	QString el2="";
	int ind=relName.lastIndexOf("::");
	if(ind>0)
	{
		el2=relName.mid(ind+2,relName.length());
	}
	return el2;
}

QString APIDB_RelationTable::parentRelation(QString relName)
{
	QString elParent="";
	int ind=relName.lastIndexOf("::");
	if(ind>0)
	{
		elParent=relName.mid(0,ind);
	}
	return elParent;
}

void APIDB_RelationTable::getRestrictionsDefault(QString nameConfig)
{
	method ( "getRestrictionsDefault("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getRestrictionsDefault(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_config";
	QString fieldName[]={"POSIZIONE_ELEMENTO","DEFAULT_RESTRICTION","TAG",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' AND POSIZIONE_ELEMENTO !=-2";
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
			bool bDefault=toBoolean(query.value(fieldName_toPos.value("DEFAULT_RESTRICTION")).toString());
			
			if(bDefault==true)
			{
				restrictionDefaultList << query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt();
			}
			
			//lout3 << query.value(fieldName_toPos.value("POSIZIONE_ELEMENTO")).toInt() << "," << query.value(fieldName_toPos.value("TAG")).toString() << endl;	
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
  
}

void APIDB_RelationTable::getRestrictions(QString nameConfig)
{
	method ( "getRestrictions("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getRestrictions(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_restriction";
	QString fieldName[]={"RELATION_NAME","ELEMENTI_RESTRIZIONE",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="ELEMENTI_RESTRIZIONE asc";
	
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
			QString key=query.value(fieldName_toPos.value("RELATION_NAME")).toString();
			int value=query.value(fieldName_toPos.value("ELEMENTI_RESTRIZIONE")).toInt();
			
			restrictionList_it it_restrictionList = restrictionList.find(key);
			
			if(it_restrictionList == restrictionList.end() ) // key not found!!!!
			{
				restrictions rest;
				rest.append(value);
				restrictionList.insert(key,rest);
			}
			else // key found
			{
				it_restrictionList.value().append(value);
			}
			
			
			//lout3 << key << "," << value << endl;	
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int *APIDB_RelationTable::getIDRestriction(QString nameRelation)
{
	//lout3 << "getIDTiedRestriction(" << nameRelation << ")" << endl;	
	int i=0;
	int *rest=new int[MAX_RESTRICTIONS];
	for ( i = 0; i < MAX_RESTRICTIONS; i++ )
	{
		rest[i] = -1;
	}
	
	restrictionList_it it_restrictionList = restrictionList.find(nameRelation);
	
	if(it_restrictionList != restrictionList.end() ) // key found!!!!
	{	
		restrictions r = it_restrictionList.value();
		
		restrictions_it it_restrictions;
		i=0;
		for (it_restrictions = r.begin(); it_restrictions != r.end(); ++it_restrictions)
		{
			rest[i]=*it_restrictions;
			++i;
		}

	}
	
	return rest;
}

void APIDB_RelationTable::normalizeRestrictions()
{
	relationList_it it_relationList;
	
	for (it_relationList = relationList.begin(); it_relationList != relationList.end(); ++it_relationList)
	{
		QString rel=it_relationList.key();
		//lout3 << "RELAZIONE=" << rel << endl;
		
		restrictionList_it it_restrictionList=restrictionList.find(rel);
		
		if(it_restrictionList == restrictionList.end() ) // key not found!!!!
		{
			restrictions rest;
			restrictionDefaultList_it it_restrictionDefaultList;
			//lout3 << "REST DEFAULT nuova=(";
			for (it_restrictionDefaultList = restrictionDefaultList.begin(); it_restrictionDefaultList != restrictionDefaultList.end(); ++it_restrictionDefaultList)
			{
				rest << *it_restrictionDefaultList;
				//lout3 << *it_restrictionDefaultList << ",";
			}
			//lout3 <<")" <<endl;
			
			tiedRestrictionList_it it_tiedRestrictionList = tiedRestrictionList.find(rel);
			
			if(it_tiedRestrictionList == tiedRestrictionList.end() ) // key not found!!!!
			{
			}
			else // key found
			{
				tiedRestrictions tR=it_tiedRestrictionList.value();
				
				tiedRestrictions_it it_tiedRestrictions;
				//lout3 << "REST DEFAULT nuova + tied =(";
				for (it_tiedRestrictions = tR.begin(); it_tiedRestrictions != tR.end(); ++it_tiedRestrictions)
				{
					int index = rest.lastIndexOf(*it_tiedRestrictions);
					
					if( index == -1 ) // key not found!!!!
					{
						rest << *it_tiedRestrictions;
						//lout3 << *it_tiedRestrictions << ",";
					}
					else{
						//lout3 << "KEY FOUND(" << *it_tiedRestrictions<<")" << endl;
					}
					
					
					rest << *it_tiedRestrictions;
				}
				//lout3 <<")" <<endl;
			}
			restrictionList.insert(rel,rest);
		}
		else // key found
		{
			//it_restrictionList.value().append(value);
			
			restrictions rest=it_restrictionList.value();
			    
			restrictionDefaultList_it it_restrictionDefaultList;
			//lout3 << "REST DEFAULT esistente=(";
			for (it_restrictionDefaultList = restrictionDefaultList.begin(); it_restrictionDefaultList != restrictionDefaultList.end(); ++it_restrictionDefaultList)
			{
				int index = rest.lastIndexOf(*it_restrictionDefaultList);
				
				if( index == -1 ) // key not found!!!!
				{
					rest << *it_restrictionDefaultList;
					//lout3 << *it_restrictionDefaultList << ",";
				}
				else{
					//lout << "KEY FOUND" << endl;
				}
				
				
				rest << *it_restrictionDefaultList;
			}
			//lout3 <<")" <<endl;
			
			tiedRestrictionList_it it_tiedRestrictionList = tiedRestrictionList.find(rel);
			
			if(it_tiedRestrictionList == tiedRestrictionList.end() ) // key not found!!!!
			{
			}
			else // key found
			{
				tiedRestrictions tR=it_tiedRestrictionList.value();
				
				tiedRestrictions_it it_tiedRestrictions;
				//lout3 << "REST DEFAULT esistente + tied =(";
				for (it_tiedRestrictions = tR.begin(); it_tiedRestrictions != tR.end(); ++it_tiedRestrictions)
				{
					int index = rest.lastIndexOf(*it_tiedRestrictions);
					
					if( index == -1 ) // key not found!!!!
					{
						rest << *it_tiedRestrictions;
						//lout3 << *it_tiedRestrictions << ",";
					}
					else{
						//lout3 << "KEY FOUND" << endl;
					}
					
					
					rest << *it_tiedRestrictions;
				}
				//lout3 <<")" <<endl;
			}
			
		}
	}
}

void APIDB_RelationTable::getTiedRestrictions(QString nameConfig)
{
	method ( "getTiedRestrictions("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getTiedRestrictions(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_restriction_ob";
	QString fieldName[]={"RELATION_NAME","ELEMENTI_RESTRIZIONE_OBBL",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="ELEMENTI_RESTRIZIONE_OBBL asc";
	
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
			QString key=query.value(fieldName_toPos.value("RELATION_NAME")).toString();
			int value=query.value(fieldName_toPos.value("ELEMENTI_RESTRIZIONE_OBBL")).toInt();
			
			tiedRestrictionList_it it_tiedRestrictionList = tiedRestrictionList.find(key);
			
			if(it_tiedRestrictionList == tiedRestrictionList.end() ) // key not found!!!!
			{
				tiedRestrictions tiedRest;
				tiedRest.append(value);
				tiedRestrictionList.insert(key,tiedRest);
			}
			else // key found
			{
				it_tiedRestrictionList.value().append(value);
			}
			//lout3 << key << "," << value << endl;	
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

int *APIDB_RelationTable::getIDTiedRestriction(QString nameRelation)
{
	//lout3 << "getIDTiedRestriction(" << nameRelation << ")" << endl;	
	int i=0;
	int *tiedRest=new int[MAX_RESTRICTIONS];
	for ( i = 0; i < MAX_RESTRICTIONS; i++ )
	{
		tiedRest[i] = 0;
	}
	
	tiedRestrictionList_it it_tiedRestrictionList = tiedRestrictionList.find(nameRelation);
	
	if(it_tiedRestrictionList != tiedRestrictionList.end() ) // key found!!!!
	{	
		tiedRestrictions tr = it_tiedRestrictionList.value();
		
		tiedRestrictions_it it_tiedRestrictions;
		i=0;
		for (it_tiedRestrictions = tr.begin(); it_tiedRestrictions != tr.end(); ++it_tiedRestrictions)
		{
			tiedRest[i]=*it_tiedRestrictions;
			++i;
		}
	}
	
	return tiedRest;
}      

void APIDB_RelationTable::getGhostRelations(QString nameConfig)
{
	method ( "getHelps("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getGhostRelations(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_relation";
	QString fieldName[]={"RELATION_NAME",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' and POSIZIONE_ELEMENTO<0";
	QString strOrderBy="RELATION_NAME asc";
	
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
		
		i=idMaxRelation+1;
	
		while ( query.next() ) 
		{
			int value=i;
			QString key=query.value(fieldName_toPos.value("a.RELATION_NAME")).toString();
			
			relationList_it it_relationList = relationList.find(key);
			
			if(it_relationList == relationList.end() ) // key not found!!!!
			{
				relationList.insert(key,value);
			}
			//lout3 << "GHOST:  " << key << " ---> " << getIDRelations(key) << endl;
			idMaxRelation=i;
			++i;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}


void APIDB_RelationTable::getRelations(QString nameConfig)
{
	method ( "getRelations("+nameConfig+"): " );
	bool noDbError=false;
	//lout3 << "getRelations(" << nameConfig << ")" << endl;

	QString strFieldName="";
	QString tableName="adams_asp.tab_list_relation a";
	QString fieldName[]={"a.RELATION_NAME","(select TAG from adams_asp.tab_config b where a.POSIZIONE_ELEMENTO=b.POSIZIONE_ELEMENTO and b.TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' ) R_TAG",""};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"' and a.POSIZIONE_ELEMENTO>0";
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
			//lout3 << key << " ---> " << getIDRelations(key) << endl;
			idMaxRelation=i;
			++i;
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}
}

relation *APIDB_RelationTable::getRelation(QString nameRelation)
{
	relation *rel=NULL;
	relationIDList_it it_relationIDList = relationIDList.find(nameRelation);
	
	if(it_relationIDList == relationIDList.end() )
	{
		rel=NULL;
	}else
	{
		rel = *it_relationIDList;
	}

	return rel;
}

int APIDB_RelationTable::getIDRelations(QString nameRelation)
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

QString APIDB_RelationTable::getNameRelations(int key)
{
	QString nameRelation=relationList.key(key);
	return nameRelation;
}


void APIDB_RelationTable::debug()
{		
	/* debug dataelement list */
	int contRel=1;
	for ( RelationIterator it_rel = local_adc->relationInterface->getIterator(); it_rel != local_adc->relationInterface->hashEnd(); it_rel++ )
	{
		//if(it_rel.value()->data.idRelation!=847) continue;
		lout3 << "Relation #="<<contRel<<"          = " << getNameRelations(it_rel.value()->data.idRelation) << endl;
		lout3 << "idRelation           = " << it_rel.value()->data.idRelation << endl;
		lout3 << "idParentRelation     = " << it_rel.value()->data.idParentRelation << endl;
		lout3 << "idFirstElement       = " << it_rel.value()->data.idFirstElement << endl;
		lout3 << "idSecondElement      = " << it_rel.value()->data.idSecondElement << endl;
		lout3 << "admittedDirections   = " << it_rel.value()->data.admittedDirections << endl;
		lout3 << "admitHexValues       = " << it_rel.value()->data.admitHexValues << endl;
		lout3 << "admitNetworkAnalisys = " << it_rel.value()->data.admitNetworkAnalisys << endl;
		lout3 << "ghostRelation        = " << it_rel.value()->data.ghostRelation << endl;
		lout3 << "nextLevelRelations   = (";
		for ( int i = 0; i < NEXTLEVEL_RELATIONS; i++ )
		{
			lout << it_rel.value()->data.nextLevelRelations[i] << ",";
		}
		lout3 <<")" << endl;
		lout3 << "restrictions         = (";
		for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
		{
			lout << it_rel.value()->data.restrictions[i] << ",";
		}
		lout3 <<")" << endl;
		lout3 << "tiedRestrictions     = (";
		for ( int i = 0; i < MAX_RESTRICTIONS; i++ )
		{
			lout << it_rel.value()->data.tiedRestrictions[i] << ",";
		}
		lout3 <<")" << endl;;
		lout3 << endl;
		
		++contRel;
	  
	}
}

void APIDB_RelationTable::fillFromDB (QString nameConfig)
{
	method ( "fillFromDB("+nameConfig+"): " );
	bool noDbError=false;
	relation * rel;
	int i=0;
	QString strFieldName="";
	QString tableName="adams_asp.tab_list_relation";
	QString fieldName[]={"TIPO_DI_CONFIGURAZIONE",
                                                "POSIZIONE_ELEMENTO",
                                                "RELATION_NAME",
						"DIREZIONE",
                                                "HEXOUTPUT",
                                                "NETWORK",
                                                "GHOST",
						""
						};
	QString strWhere="TIPO_DI_CONFIGURAZIONE='"+nameConfig+"'";
	QString strOrderBy="RELATION_NAME asc";
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
			rel = getRelation(query.value(fieldName_toPos.value("RELATION_NAME")).toString());
			
			if(rel!=NULL)
			{
				local_adc->relationInterface->add(rel);
			}
		}
	}
	catch ( A_DBException & de ) 
	{
		lout << de.preface() << de.explain() << endl;
	}

	
}

void APIDB_RelationTable::setAdamsCompleteConfig(AdamsCompleteConfig *adc)
{
	local_adc=adc;
}

void APIDB_RelationTable::load(QString nameConfig)
{
	bool dbConnect = isConnect();
	
	if ( dbConnect == false ) {
		dbConnect = openDB(config_nameDB,config_loginDB,config_pswdDB);
	}
	
	if(dbConnect==true)
	{
		getRelations(nameConfig);
		getGhostRelations(nameConfig);
		getRestrictionsDefault(nameConfig);
		getRestrictions(nameConfig);
		getTiedRestrictions(nameConfig);
		normalizeRestrictions();
		makeListRelations(nameConfig);
		fillFromDB(nameConfig);
		closeDB();
	}
}

