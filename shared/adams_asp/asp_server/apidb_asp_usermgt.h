/*
#
#                $$$$$$$$                   $$
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

#ifndef APIDB_ASP_USERMGT_H
#define APIDB_ASP_USERMGT_H

#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <QtSql/QSqlDriver>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <Qt/qvariant.h>
#include <Qt/qlist.h>
#include <Qt/qstringlist.h>
#include <idlincludes.h>
#include "cnfglobals.h"
#include <apidb_define.h>
#include <apidb_base.h>
#include <aspS.h>

using namespace net::etech;

#define INSERT_INTO_T_USER_PROFILE 	1
#define INSERT_INTO_T_PROFILE_CLASS	2


typedef QList<S_ROLE>::iterator roleList_it;
typedef QList<S_CLASS>::iterator classList_it;
typedef QList<S_FUNCTION>::iterator functionList_it;
typedef QList<S_PROFILE>::iterator profileList_it;
typedef QList<S_USER>::iterator userList_it;


class APIDB_AspUserMgt: public APIDB_Base
{
public:
	APIDB_AspUserMgt();
	~APIDB_AspUserMgt();

	bool openDB(QString hstrDBNAME, QString hstrUSER, QString hstrPSWD);
	void closeDB();
	bool isConnect();
	
	int idRole(const int idClass,const QString login);
	
	bool getRoleList();
	bool getProfileList();
	bool getClassList();
	bool getFunctionList();
	bool getUsersList();
	bool getUser(const QString slogin,S_USER *&User);
	bool getUserProfile(const QString slogin,long id_Class,S_PROFILE *&Profile,int &contProfile);
	bool getProfileUser(const QString sprofilo,S_LOGIN *&Login,int &contUser);
	bool getProfileFunction(const QString sprofilo,S_FUNCTION *&Function,int &numFunction);
	bool getConfig(S_ROLE *&Role,S_PROFILE *&Profile,S_CLASS *&Class,S_FUNCTION *&Function,S_USER *&User,int &l1,int &l2,int &l3,int &l4,int &l5);
	bool deleteRecordInTable ( const QString strDelete );
	bool deleteProfile ( const QString login );
	void insertPrepare ( int type );
	bool insertCreate ( int occurrenceNum );
	bool writeData ( int type );
	
	QStringList 	strValues;
	
private:
	void setError ( QSqlDatabase &dbError );
	inline int getExitStatusDB() {
		return last_result_db;
	}
	
	bool insertRecordInTable_BLOC();
	QString getTableName ( int t );
	QString formatValue ( int itType, QString value );
	bool toBoolean(int value);
	
	
	QSqlDatabase 	db;
	QString 	m_connection;
	int 		last_result_db;
	bool 		enablePSWD;
	QString 	strUSER;
	QString 	strPSWD;
	QString		strDBNAME;

	/* campi generici per costruzione insert */
	QString 	strTableName;
	QStringList 	strFields;
	QString     	strInsert;
	QString     	strSelect;
	QList<int>	strType;
	int 		fieldsNum;
	
	QList<S_ROLE> roleList;
	QList<S_PROFILE> profileList;
	QList<S_CLASS> classList;
	QList<S_FUNCTION> functionList;
	QList<S_USER> userList;
	
	//QMap<QString,functions> functionList;


	
};

#endif //APIDB_ASP_LOG_H
