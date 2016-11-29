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

#ifndef APIDB_RELATION_H
#define APIDB_RELATION_H

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <QtSql/QSqlDriver>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qvariant.h>
#include <Qt/qlist.h>
#include <Qt/qstringlist.h>
#include <apidb_define.h>
#include <apidb_base.h>
#include <configuretypedefs.h>

typedef QList<int>::iterator restrictionDefaultList_it;

typedef QMap<QString,int>::iterator relationList_it;
typedef QMap<QString,relation *>::iterator relationIDList_it;

typedef QList<int> restrictions;
typedef QList<int>::iterator restrictions_it;
typedef QMap<QString,restrictions>::iterator restrictionList_it;

typedef QList<int> tiedRestrictions;
typedef QList<int>::iterator tiedRestrictions_it;
typedef QMap<QString,tiedRestrictions>::iterator tiedRestrictionList_it;

/**
 * Classe gestione connesione con DataBase
 */
class APIDB_RelationTable: public APIDB_Base
{
public:
  
	/**
	 * Costruttore della classe API_DataBase: crea una connessione al Database con l'utente specificato nel
	 * parametro str
	 * @param str puntatore a char contenente la login per effettuare la connessione ad Database.
	 */
	APIDB_RelationTable ();
	APIDB_RelationTable (AdamsCompleteConfig *adc);
	

	/**
	 * Distruttore della classe API_DataBase: chiude la connessione con il DataBase.
	 */
	~APIDB_RelationTable();

	void setAdamsCompleteConfig(AdamsCompleteConfig *adc);
	void load(QString nameConfig);
	
	void fillFromDB (QString nameConfig);
	
	void getRestrictions(QString nameConfig);
	void getRestrictionsDefault(QString nameConfig);
	int *getIDRestriction(QString nameRelation);
	void getTiedRestrictions(QString nameConfig);
	int *getIDTiedRestriction(QString nameRelation);
	void getRelations(QString nameConfig);
	void getGhostRelations(QString nameConfig);
	int getIDRelations(QString nameRelation);
	QString getNameRelations(int key);
	void makeListRelations(QString nameConfig);
	relation *getRelation(QString nameConfig);
	
	void debug();

private:

	inline int getExitStatusDB() {
		return last_result_db;
	}

	bool openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();
	bool isConnect();

	void setError ( QSqlDatabase &dbError );

	bool toBoolean ( QString str );
	bool toBoolean(int value);
	void updateNextLevel(relation *rel,int idRel);
	void addRestrictionsDefaul(int *appRest,int idRel);
	void normalizeRestrictions();
	QString firstElement(QString relName);
	QString secondElement(QString relName);
	QString parentRelation(QString relName);
	
	QSqlDatabase 	db;
	QString 	m_connection;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	QString     	strSelect;

	AdamsCompleteConfig *local_adc;
	
	QMap<QString,int> relationList;
	QMap<QString,relation *> relationIDList;
	QMap<QString,restrictions> restrictionList;
	QMap<QString,tiedRestrictions> tiedRestrictionList;
	QList<int> restrictionDefaultList;
	
	int idMaxRelation;
};

#endif
