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

#include <apidb_asp_usermgt.h>
#include <applogger.h>
#include <iostream>

using namespace std;

#define DEBUG_APIDB_ASP_LOG_HR                                  1


APIDB_AspUserMgt::APIDB_AspUserMgt ( ): m_connection ( "APIDB_AspUserMgt" )
{
}

bool APIDB_AspUserMgt::openDB ( QString hstrDBNAME, QString hstrUSER, QString hstrPSWD )
{
	bool noDbError = false;

	method ( "openDB(APIDB_AspUserMgt): " );	
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
			lout3 << "Connected to DB APIDB_AspUserMgt."<< endl;
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

bool APIDB_AspUserMgt::isConnect()
{
	QSqlDatabase db_test = QSqlDatabase::database(m_connection);
	//lout3 << "APIDB_AspUserMgt.isOpen=" << db_test.isOpen() << endl;	
	return db_test.isOpen();
}

void APIDB_AspUserMgt::setError ( QSqlDatabase &dbError )
{
	last_result_db = dbError.lastError().number();
}

void APIDB_AspUserMgt::closeDB()
{
	QString connection;
	connection = db.connectionName();
	db.close();
	db = QSqlDatabase();
	db.removeDatabase ( connection );
}


APIDB_AspUserMgt::~APIDB_AspUserMgt()
{
	closeDB();
}

int APIDB_AspUserMgt::idRole(const int idClass,const QString login)
{
	method ( "getRuolo("+QString::number(idClass)+","+login+"): " );	
	//lout3 << "getRuolo(" << idClass << "," << login << ")" << endl;
	bool noDbError=false;
	int idRole=-1;

	QString strFieldName="";
	QString tableName="t_user a,t_user_profile b, t_profile_class c";
	QString fieldName[]={"a.id_role",""};
	QString strWhere="a.login=b.login and b.profile=c.profile and a.login='"+login+"' and c.id_class="+QString::number(idClass);
	QString strOrderBy="a.id_role asc";
	
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
		
		if ( query.next() == true ) 
		{		
			idRole=query.value(fieldName_toPos.value("a.id_role")).toInt();
			//lout3 << "ID_ROLE=" << idRole << endl;
		}
		else
		{
			idRole=-1;			
		}
		
	}
	catch ( A_DBException & de ) 
	{
		idRole=-1;	
		lout << de.preface() << de.explain() << endl;
	}
	
	return idRole;
}

bool APIDB_AspUserMgt::getRoleList()
{
	method ( "getRoleList(): " );	
	bool noDbError=false;
	
	lout3 << "getRoleList()" << endl;

	QString strFieldName="";
	QString tableName="t_role_lib";
	QString fieldName[]={"id_role","desc_role",""};
	QString strWhere="1";
	QString strOrderBy="id_role asc";
	
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
	
	lout << strSelect << endl;
	
	try {
		roleList.clear();
		
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		
		while ( query.next() ) 
		{	
			S_ROLE role;
			role.idRole=query.value(fieldName_toPos.value("id_role")).toInt();
			c_qstrncpy(role.descRole,qPrintable(query.value(fieldName_toPos.value("desc_role")).toString()),MAX_DESC_ROLE);
			lout3 << role.idRole <<"=" << role.descRole << endl;
			
			roleList.append(role);
		}
		noDbError=true;
	}
	catch ( A_DBException & de ) 
	{
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	return noDbError;
}

bool APIDB_AspUserMgt::getClassList()
{
	method ( "getClassList(): " );	
	bool noDbError=false;
	
	lout3 << "getClassList()" << endl;

	QString strFieldName="";
	QString tableName="t_class_lib";
	QString fieldName[]={"id_class","desc_class",""};
	QString strWhere="1";
	QString strOrderBy="id_class asc";
	
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
	
	lout << strSelect << endl;
	
	try {
		classList.clear();
		
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		
		while ( query.next() ) 
		{	
			S_CLASS classe;
			
			classe.idClass=query.value(fieldName_toPos.value("id_class")).toInt();
			c_qstrncpy(classe.descClass,qPrintable(query.value(fieldName_toPos.value("desc_class")).toString()),MAX_DESC_CLASS);
			
			lout3 << classe.idClass <<"=" << classe.descClass << endl;
			
			classList.append(classe);
		}
		noDbError=true;
	}
	catch ( A_DBException & de ) 
	{
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	return noDbError;
}

bool APIDB_AspUserMgt::getFunctionList()
{
	method ( "getFunctionList(): " );	
	bool noDbError=false;
	
	lout3 << "getFunctionList()" << endl;

	QString strFieldName="";
	QString tableName="t_function_lib";
	QString fieldName[]={"id_class",
				"id_funtion",
				"function_name",
				"function_desc",
				"version",
				"released_data",
				"author",
				"vendor",
				""};
	QString strWhere="1";
	QString strOrderBy="id_funtion asc";
	
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
	
	lout << strSelect << endl;
	
	try {
		functionList.clear();
		
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		
		while ( query.next() ) 
		{	
			S_FUNCTION f;
			
			f.idClass=query.value(fieldName_toPos.value("id_class")).toInt();
			f.idFunction=query.value(fieldName_toPos.value("id_funtion")).toInt();
			c_qstrncpy(f.nameFunction,qPrintable(query.value(fieldName_toPos.value("function_name")).toString()),MAX_NAME_FUNCTION);
			c_qstrncpy(f.descFunction,qPrintable(query.value(fieldName_toPos.value("function_desc")).toString()),MAX_DESC_FUNCTION);
			c_qstrncpy(f.releasedData,qPrintable(query.value(fieldName_toPos.value("releasedData")).toString()),MAX_VERSION);
			c_qstrncpy(f.version,qPrintable(query.value(fieldName_toPos.value("version")).toString()),MAX_RELEASED_DATA);
			c_qstrncpy(f.author,qPrintable(query.value(fieldName_toPos.value("author")).toString()),MAX_AUTHOR);
			c_qstrncpy(f.vendor,qPrintable(query.value(fieldName_toPos.value("vendor")).toString()),MAX_VENDOR);
			
			lout3 << f.idFunction <<"=" << f.nameFunction << endl;
			
			functionList.append(f);
		}
		noDbError=true;
	}
	catch ( A_DBException & de ) 
	{
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	return noDbError;
}

bool APIDB_AspUserMgt::getUsersList()
{
	method ( "getUsersList(): " );	
	bool noDbError=false;
	
	lout3 << "getUsersList()" << endl;

	QString strFieldName="";
	QString tableName="t_user";
	QString fieldName[]={"login",
				"passwd",
				"data_token",
				"name",
				"desc_user",
				"id_role",
				"enable_user",
				""};
	QString strWhere="login != 'TPL'";
	QString strOrderBy="login asc";
	
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
	
	lout3 << strSelect << endl;
	
	try {
		userList.clear();
		
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		
		while ( query.next() ) 
		{	
			S_USER u;
			
			
			c_qstrncpy(u.login,qPrintable(query.value(fieldName_toPos.value("login")).toString()),MAX_LOGIN_LEN);
			c_qstrncpy(u.password,qPrintable(query.value(fieldName_toPos.value("passwd")).toString()),MAX_PASSWORD_LEN);
			c_qstrncpy(u.nomeUtente,qPrintable(query.value(fieldName_toPos.value("name")).toString()),MAX_NOMEUTENTE_LEN);
			c_qstrncpy(u.descUser,qPrintable(query.value(fieldName_toPos.value("desc_user")).toString()),MAX_DESC_USER_LEN);
			u.idRole=query.value(fieldName_toPos.value("id_role")).toInt();
			u.abilitazioneUtente=toBoolean(query.value(fieldName_toPos.value("enable_user")).toInt());
			
			lout3 << u.login <<"," << u.idRole << endl;
			
			userList.append(u);
		}
		noDbError=true;
	}
	catch ( A_DBException & de ) 
	{
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	return noDbError;
}

bool APIDB_AspUserMgt::getUser(const QString slogin,S_USER *&User)
{
	method ( "getUser("+slogin+"): " );	
	getUsersList();
	lout3 << "getUser("<< slogin <<")" << endl;
	userList_it it_userList;
	bool flagTrovato=false;
	
	User=new S_USER;
	memset(User,0,sizeof(S_USER)*1);
			
	for (it_userList = userList.begin(); it_userList != userList.end(); ++it_userList)
	{
		S_USER userAppo;
		userAppo=*it_userList;
		if ( QString(userAppo.login)==slogin )
		{
			c_qstrncpy(User->login,userAppo.login,MAX_LOGIN_LEN);
			c_qstrncpy(User->password,userAppo.password,MAX_PASSWORD_LEN);
			c_qstrncpy(User->nomeUtente,userAppo.nomeUtente,MAX_NOMEUTENTE_LEN);
			c_qstrncpy(User->descUser,userAppo.descUser,MAX_DESC_USER_LEN);
			User->idRole=userAppo.idRole;
			User->abilitazioneUtente=userAppo.abilitazioneUtente;
			flagTrovato=true;
			break;
		}
	}

	return flagTrovato;
}

/*
 SELECT  distinct concat(a.profile,a.id_class),count(distinct(1))
from t_profile_class a, t_user_profile b where a.profile=b.profile and b.login='raffo';
 */
bool APIDB_AspUserMgt::getUserProfile(const QString slogin,long id_Class,S_PROFILE *&Profile,int &contProfile)
{
	method ( "getUserProfile("+slogin+"): " );	
	bool noDbError=false;
	
	lout3 << "getUserProfile(" << slogin << ")" << endl;

	QString tableName="t_profile_class a, t_user_profile b";
	QString strWhere="";
	
	if( id_Class==-1 )
	{
		strWhere="a.profile=b.profile and b.login='"+slogin+"'";
	}else
	{
		strWhere="a.profile=b.profile and b.login='"+slogin+"' and a.id_class="+QString::number(id_Class);
	}
	strSelect="SELECT concat(a.profile,a.id_class),count(distinct(1))";
	strSelect+=" FROM "+tableName;
	strSelect+=" WHERE "+strWhere;
	
	//lout3 << strSelect << endl;
	
	try 
	{
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		query.next();
		contProfile = query.value(1).toInt();
		noDbError=true;
	  
	}
	catch ( A_DBException & de ) 
	{
		contProfile=0;
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	//lout3 << "contProfile(id_Class="<< id_Class << ")=" << contProfile<< endl;
	
	if(contProfile)
	{
		QString strFieldName="";
		tableName="t_profile_class a, t_user_profile b";
		QString fieldName[]={"distinct a.PROFILE",
					"a.id_class",
					""};
		if( id_Class==-1 )
		{
			strWhere="a.profile=b.profile and b.login='"+slogin+"'";
		}else
		{
			strWhere="a.profile=b.profile and b.login='"+slogin+"' and a.id_class="+QString::number(id_Class);
		}
		QString strOrderBy="1 asc";
		
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
		
		//lout3 << strSelect << endl;
		
		try 
		{
			Profile=new S_PROFILE[contProfile];
			memset(Profile,0,sizeof(S_PROFILE)*contProfile);
			
			QSqlQuery query(db);
			query.prepare( strSelect );
			query_exec( query );
			i=0;
			while ( query.next() ) 
			{	
				Profile[i].idClass=query.value(fieldName_toPos.value("a.id_class")).toInt();
				c_qstrncpy(Profile[i].profile,qPrintable(query.value(fieldName_toPos.value("distinct a.PROFILE")).toString()),MAX_PROFILE);

				//lout3 << Profile[i].idClass << "," <<  Profile[i].profile << endl;
				
				++i;

			}
			noDbError=true;
		  
		}
		catch ( A_DBException & de ) 
		{
			contProfile=0;
			if(Profile)
			{
				delete(Profile);
				Profile=NULL;
			}
			noDbError=false;
			lout << de.preface() << de.explain() << endl;
		}
	}
	
	
	return noDbError;
}

bool APIDB_AspUserMgt::getProfileUser(const QString sprofilo,S_LOGIN *&Login,int &contUser)
{
	method ( "getProfileUser("+sprofilo+"): " );	
	bool noDbError=false;
	
	lout3 << "getProfileUser(" << sprofilo << ")" << endl;

	QString tableName="t_user a,t_user_profile b";
	QString strWhere="a.login=b.login and b.profile = '"+sprofilo+"'";
	
	strSelect="SELECT COUNT(a.login)";
	strSelect+=" FROM "+tableName;
	strSelect+=" WHERE "+strWhere;
	
	//lout3 << strSelect << endl;
	
	try 
	{
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		query.next();
		contUser = query.value(0).toInt();
		noDbError=true;
	  
	}
	catch ( A_DBException & de ) 
	{
		contUser=0;
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	//lout3 << "contUser=" << contUser<< endl;
	
	if(contUser)
	{
		QString strFieldName="";
		tableName="t_user a,t_user_profile b";
		QString fieldName[]={"a.login",""};
		strWhere="a.login=b.login and b.profile = '"+sprofilo+"'";
		QString strOrderBy="UPPER(a.login)";
		
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
		
		//lout3 << strSelect << endl;
		
		try 
		{
			Login=new S_LOGIN[contUser];
			memset(Login,0,sizeof(S_LOGIN)*contUser);
			
			QSqlQuery query(db);
			query.prepare( strSelect );
			query_exec( query );
			i=0;
			while ( query.next() ) 
			{	
				c_qstrncpy(Login[i].login,qPrintable(query.value(fieldName_toPos.value("a.login")).toString()),MAX_LOGIN_LEN);
				++i;
				//lout3 << Login[i].login << endl;

			}
			noDbError=true;
		  
		}
		catch ( A_DBException & de ) 
		{
			contUser=0;
			if(Login)
			{
				delete(Login);
				Login=NULL;
			}
			noDbError=false;
			lout << de.preface() << de.explain() << endl;
		}
	}
	
	return noDbError;
}

bool APIDB_AspUserMgt::getProfileFunction(const QString sprofilo,S_FUNCTION *&Function,int &numFunction)
{
	method ( "getProfileFunction("+sprofilo+"): " );	
	bool noDbError=false;
	
	//lout3 << "getProfileFunction()" << endl;

	QString tableName="t_function_lib a,t_profile_class b";
	QString strWhere="a.id_class=b.id_class and b.profile='"+sprofilo+"' and a.id_funtion in (select id_funtion from t_profile_class where profile='"+sprofilo+"')";
	
	strSelect="SELECT COUNT(DISTINCT a.function_name)";
	strSelect+=" FROM "+tableName;
	strSelect+=" WHERE "+strWhere;
	
	//lout << strSelect << endl;
	
	try 
	{
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		query.next();
		numFunction = query.value(0).toInt();
		noDbError=true;
	  
	}
	catch ( A_DBException & de ) 
	{
		numFunction=0;
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	//lout3 << "numFunction=" << numFunction<< endl;
	
	if(numFunction)
	{
		QString strFieldName="";
		tableName="t_function_lib a,t_profile_class b";
		QString fieldName[]={"distinct a.id_class",
					"a.id_funtion",
					"a.function_name",
					"a.function_desc",
					"a.version",
					"a.released_data",
					"a.author",
					"a.vendor",
					""};
		strWhere="a.id_class=b.id_class and b.profile='"+sprofilo+"' and a.id_funtion in (select id_funtion from t_profile_class where profile='"+sprofilo+"')";
		QString strOrderBy="a.id_funtion asc";
		
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
		
		try 
		{
			Function=new S_FUNCTION[numFunction];
			memset(Function,0,sizeof(S_FUNCTION)*numFunction);
			
			QSqlQuery query(db);
			query.prepare( strSelect );
			query_exec( query );
			i=0;
			while ( query.next() ) 
			{	
				Function[i].idClass=query.value(fieldName_toPos.value("distinct a.id_class")).toInt();
				Function[i].idFunction=query.value(fieldName_toPos.value("a.id_funtion")).toInt();
				c_qstrncpy(Function[i].nameFunction,qPrintable(query.value(fieldName_toPos.value("a.function_name")).toString()),MAX_NAME_FUNCTION);
				c_qstrncpy(Function[i].descFunction,qPrintable(query.value(fieldName_toPos.value("a.function_desc")).toString()),MAX_DESC_FUNCTION);
				c_qstrncpy(Function[i].version,qPrintable(query.value(fieldName_toPos.value("a.version")).toString()),MAX_VERSION);
				c_qstrncpy(Function[i].releasedData,qPrintable(query.value(fieldName_toPos.value("a.released_data")).toString()),MAX_RELEASED_DATA);
				c_qstrncpy(Function[i].author,qPrintable(query.value(fieldName_toPos.value("a.author")).toString()),MAX_AUTHOR);
				c_qstrncpy(Function[i].vendor,qPrintable(query.value(fieldName_toPos.value("a.vendor")).toString()),MAX_VENDOR);
				
				//lout3 << Function[i].nameFunction << ": " << Function[i].idClass <<"," << Function[i].idFunction << endl;
				
				++i;

			}
			noDbError=true;
		  
		}
		catch ( A_DBException & de ) 
		{
			numFunction=0;
			if(Function)
			{
				delete(Function);
				Function=NULL;
			}
			noDbError=false;
			lout << de.preface() << de.explain() << endl;
		}
	}
	
	return noDbError;
}

bool APIDB_AspUserMgt::getProfileList()
{
	method ( "getProfileList(): " );	
	bool noDbError=false;
	
	lout3 << "getProfileList()" << endl;

	QString strFieldName="";
	QString tableName="t_profile_class";
	QString fieldName[]={"distinct profile","desc_profile","id_class",""};
	QString strWhere="1";
	QString strOrderBy="profile asc";
	
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
	
	lout3 << strSelect << endl;
	
	try {
		profileList.clear();
		
		QSqlQuery query(db);
		query.prepare( strSelect );
		query_exec( query );
		
		while ( query.next() ) 
		{	
			S_PROFILE profile;
			profile.idClass=query.value(fieldName_toPos.value("id_class")).toInt();
			c_qstrncpy(profile.profile,qPrintable(query.value(fieldName_toPos.value("distinct profile")).toString()),MAX_PROFILE);
			c_qstrncpy(profile.descProfile,qPrintable(query.value(fieldName_toPos.value("desc_profile")).toString()),MAX_DESC_PROFILE);
			
			QString key=query.value(fieldName_toPos.value("distinct profile")).toString()+"::"+QString::number(query.value(fieldName_toPos.value("id_class")).toInt());
			

			
			
//------------------------------------------------------------------- SEQUENZA FUNZIONI
			S_FUNCTION *appoFunction=NULL;
			SFunctionSeq localFunctionSeq;
			
			int l_sFunction=0;
			int bRet=getProfileFunction(profile.profile,appoFunction,l_sFunction);
		      
			if(bRet==true)
			{
				localFunctionSeq.length(l_sFunction);
				
				for(int k=0;k<l_sFunction;k++)
				{
					(localFunctionSeq)[k]=(appoFunction[k]);
				}
				profile.functionSeq=(localFunctionSeq);
			}

			if(appoFunction!=NULL)
			{
				delete [] appoFunction;
			}
//-------------------------------------------------------------------

//------------------------------------------------------------------- SEQUENZA LOGIN
			S_LOGIN *appoLogin=NULL;
			SLoginSeq localLoginSeq;
			
			int l_sLogin=0;
			bRet=getProfileUser(profile.profile,appoLogin,l_sLogin);
		      
			if(bRet==true)
			{
				localLoginSeq.length(l_sLogin);
				for(int k=0;k<l_sLogin;k++)
				{
					(localLoginSeq)[k]=(appoLogin[k]);
				}
				profile.loginSeq=(localLoginSeq);
			}

			if(appoLogin!=NULL)
			{
				delete [] appoLogin;
			}
//-------------------------------------------------------------------

			lout3 << profile.profile <<"," << profile.idClass << endl;
			
			profileList.append(profile);
		}
		noDbError=true;
	}
	catch ( A_DBException & de ) 
	{
		noDbError=false;
		lout << de.preface() << de.explain() << endl;
	}
	
	return noDbError;
}


bool APIDB_AspUserMgt::getConfig(S_ROLE *&Role,S_PROFILE *&Profile,S_CLASS *&Class,S_FUNCTION *&Function,S_USER *&User,int &l1,int &l2,int &l3,int &l4,int &l5)
{
  
	getRoleList();
	getClassList();
	getFunctionList();
	getUsersList();
	getProfileList();
	
	
	/* COPIO I RUOLI */
	l1=roleList.size();
	if(l1>0) 
	{
	      Role=new S_ROLE[l1];
	      memset(Role,0,sizeof(S_ROLE)*l1);
	      
	      for(int i=0;i<l1;i++)
	      {
			Role[i]=roleList.at(i);
	      }
	}
	
	/* COPIO I PROFILI */
	l2=profileList.size();
	if(l2>0)
	{ 
		Profile=new S_PROFILE[l2];
		memset(Profile,0,sizeof(S_PROFILE)*l2);
	      
		for(int i=0;i<l2;i++)
		{
			  Profile[i]=profileList.at(i);
		}
	}
	
	/* COPIO LE CLASSI */
	l3=classList.size();
	if(l3>0) 
	{
		Class=new S_CLASS[l3];
		memset(Class,0,sizeof(S_CLASS)*l3);
	      
		for(int i=0;i<l3;i++)
		{
			  Class[i]=classList.at(i);
		}
	}
	/* COPIO LE FUNZIONI */
	l4=functionList.size();
	if(l4>0) 
	{
		Function=new S_FUNCTION[l4];
		memset(Function,0,sizeof(S_FUNCTION)*l4);
	      
		for(int i=0;i<l4;i++)
		{
			  Function[i]=functionList.at(i);
		}
	}
	/* COPIO GLI UTENTI */
	l5=userList.size();
	if(l5>0)
	{
		User=new S_USER[l5];
		memset(User,0,sizeof(S_USER)*l5);
	      
		for(int i=0;i<l5;i++)
		{
			  User[i]=userList.at(i);
		}
	}
	return true;
}

bool APIDB_AspUserMgt::insertRecordInTable_BLOC()
{
	method ( "APIDB_AspUserMgt::insertRecordInTable_BLOC(): " );	
	
	bool noDbError = false;
	
	//lout << "-----> APIDB_AspUserMgt::insertRecordInTable_BLOC("<< qPrintable(strTableName)<< ") START" << endl;
	if ( isConnect() == true ) 
	{
		try {
			QSqlQuery query(db);
			query.prepare( strInsert );
			query_exec (query);
			noDbError=true;
			//lout << "-----> APIDB_AspUserMgt::strInsert("<< qPrintable(strInsert) << ")" << endl;
		}
		catch ( A_DBException & de ) 
		{
			lout << de.preface() << de.explain() << endl;
			noDbError=false;
		}
	}else
	{	
		lout << "-----> AgetExitStatusDB() = " << getExitStatusDB()  << endl;
		noDbError=false;
	}
	strValues.clear();
	//lout << "-----> APIDB_AspUserMgt::insertRecordInTable_BLOC("<< qPrintable(strTableName) << ") END" << endl;
	
	return noDbError;
}

void APIDB_AspUserMgt::insertPrepare ( int type )
{

	strFields.clear();
	strType.clear();
	
	strInsert = "";
	switch ( type ) {
		case INSERT_INTO_T_USER_PROFILE: { // insert into t_user_profile
				strTableName = getTableName ( type );

				strFields.append ( "login" );
				strType.append ( T_STRING );
				strFields.append ( "profile" );
				strType.append ( T_STRING );
			}
			break;
			
		case INSERT_INTO_T_PROFILE_CLASS: { // insert into t_profile_class
				strTableName = getTableName ( type );
				
				strFields.append ( "desc_profile" );
				strType.append ( T_STRING );
				strFields.append ( "id_class" );
				strType.append ( T_NUMBER );
				strFields.append ( "id_funtion" );
				strType.append ( T_NUMBER );
				strFields.append ( "profile" );
				strType.append ( T_STRING );
			}
			break;
			
/*INSERT_INTO_T_PROFILE_CLASS*/
		default: {
			} break;
	}

	//lout << "insertPrepare()= "<< strInsert << "," << strTableName  << endl;

	fieldsNum = strFields.count();
}

bool APIDB_AspUserMgt::insertCreate ( int occurrenceNum )
{
	unsigned long i = 0;

	QString values = "";
	strInsert = "";

	QStringList::Iterator itVal = strValues.begin();

	strInsert += "INSERT INTO ";
	strInsert += strTableName;
	strInsert += " VALUES";

	for ( int i = 0; i < occurrenceNum; i++ ) {
		QList<int>::Iterator itType = strType.begin();

		values = " (";
		for ( int k = 0; k < fieldsNum; k++ ) {
			values += formatValue ( *itType, *itVal );
			//lout3 << " valore("<< k << "," << fieldsNum<< ")=" << *itVal << ", " << formatValue(*itType,*itVal) << ","<< *itType << endl;
			if ( k != fieldsNum - 1 ) {
				values += ",";
			}
			++itVal;
			++itType;
		}
		if(i==occurrenceNum-1)
			values += ")";
		else
			values += "),";
		strInsert += values;
	}


	//lout3 << strInsert << endl;
	//lout3 << " len stringa= " << QString(strInsert).length() << endl;
}

bool APIDB_AspUserMgt::writeData ( int type )
{
	bool bRet2 = insertRecordInTable_BLOC();
}

QString APIDB_AspUserMgt::getTableName ( int t )
{
	QString appo = "UNDEFINED";

	switch ( t ) {
		case INSERT_INTO_T_USER_PROFILE: {
				appo = "t_user_profile";
			}
			break;
		case INSERT_INTO_T_PROFILE_CLASS: {
				appo = "t_profile_class";
			}
			break;
			
		default:
			{} break;
	}

	return appo;
}

QString APIDB_AspUserMgt::formatValue ( int itType, QString value )
{
	QString str = "";
	switch ( itType ) {
		case T_STRING: {
				str += "'" + value + "'";
			}
			break;

		case T_NUMBER: {
				str += value;
			}
			break;

		case T_DATE: {
				str += "to_date('" + value + "','yyyymmdd')";
			}
			break;
		
		case T_DATETIME: {
				str += "to_date('" + value + "','yyyymmdd hhmmss')";
			}
			break;
	}

	return str;
}

bool APIDB_AspUserMgt::toBoolean(int value) 
{
	if(value>0)
            return true;
        else
            return false;
}


bool APIDB_AspUserMgt::deleteProfile ( const QString login )
{
	QString strDelete = "delete from t_user_profile WHERE login='"+QString(login)+"'";
	lout3 << strDelete << endl;
	return deleteRecordInTable ( strDelete );	
}

bool APIDB_AspUserMgt::deleteRecordInTable ( const QString strDelete ) 
{
	method ( "deleteRecordInTable" );
	bool noDbError=false;
	
	try {
		QSqlQuery query ( db );
		query.prepare ( strDelete );
		query_exec ( query );
		noDbError=true;
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		noDbError=false;
	}

	return noDbError;
}

/*bool APIDB_Status::setProcessStatus ( const QString & node, const QString & process, const QString & status, const QString & msg )
{
	open_connection();
	method ( "setProcessStatus" );

	QSqlQuery query ( db );

	// Check for an entry in db

	try {
		query.prepare ( "SELECT * FROM t_proc_status "
		                "WHERE node_name=? "
		                "AND process_name=? " );

		query.addBindValue ( node );
		query.addBindValue ( process );

		query_exec ( query );

		if ( query.size() <= 0 )
			insert_my_row ( node, process );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	// update status

	try {
		query.prepare ( "UPDATE t_proc_status SET "
		                "status=?, "
		                "status_message=? "
		                "WHERE node_name=? "
		                "AND process_name=?" );

		query.addBindValue ( status );
		query.addBindValue ( msg );
		query.addBindValue ( node );
		query.addBindValue ( process );

		query_exec ( query );
	}
	catch ( A_DBException & de ) {
		lout << de.preface() << de.explain() << endl;
		return false;
	}

	return true;
}*/


