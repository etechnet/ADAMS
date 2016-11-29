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
#include "nodeconfighandler.h"
#include "cnfglobals.h"
#include "asp_entry_impl.h"
#include "asp_usermanagement_server_impl.h"
#include "../../../include/generated/aspC.h"
#include <QtCore/qtextstream.h>
#include <applogger.h>

#define DEBUG_ASP_USERMGT 	1

asp_usermanagement_server_impl::asp_usermanagement_server_impl()
{
	dbConnect_usermgt = false;
	dbConnect_asplog = false;
	apidb_asplog.setDBNameCnnection("asp_usermanagement_server_impl");
}

asp_usermanagement_server_impl::~asp_usermanagement_server_impl()
{
}

void asp_usermanagement_server_impl::init_asp_usermanagement()
{

}

void asp_usermanagement_server_impl::testConnection()
{
	dbConnect_asplog = apidb_asplog.isConnect();
	if ( dbConnect_asplog == false ) {
		dbConnect_asplog = apidb_asplog.openDB ( "adams_log", "adams", "adams" );
	}
	
	dbConnect_usermgt = apidb_usermgt.isConnect();
	if ( dbConnect_usermgt == false ) {
		dbConnect_usermgt = apidb_usermgt.openDB ( "adams_asp_usermgt", "adams", "adams" );
	}  
}

bool asp_usermanagement_server_impl::test()
{
	S_APP_LOG AppLog, tempAppLog;
	strcpy ( AppLog.ip_client, "10.0.0.1" );
	strcpy ( AppLog.hostname_client, "jeeg" );
	strcpy ( AppLog.application_user, "nello" );
	strcpy ( AppLog.client_user, "nello" );
	strcpy ( AppLog.application, "client_test" );

	int ruolo;

	lout3 << "***************** START TEST SERVER USERMGT *******************" << endl;
	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );
	LoginUser ( 1, "nello", "nello$", false, AppLog, ruolo );

	testConnection();

	if ( dbConnect_usermgt == true ) {
		apidb_usermgt.idRole ( 3, "raffo" );
		apidb_usermgt.getProfileList();
	}
	else {
		lout3 << "Error DB connect" << endl;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );
	
	lout3 << "***************** END TEST SERVER USERMGT *******************" << endl;

	return true;

}

QString asp_usermanagement_server_impl::getNameClass ( int idClass )
{
	testConnection();
	
	if ( dbConnect_usermgt == true ) {
		return "NOME CLASS";//apidb_usermgt.getNameClass(idClass);
	}
	else {
		return "UNDEFINED";
	}
}


// IDL implementation interfaces



CORBA::Boolean asp_usermanagement_server_impl::LoginUser ( CORBA::Long idClass, const char*  login, const char*  password, CORBA::Boolean disablePassCheck, const S_APP_LOG&  AppLog, CORBA::Long_out ruolo ) throw ( CORBA::SystemException )
{
	bool bFlag = true;

	S_APP_LOG tempAppLog;
	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action, "LOGIN" );
	strcpy ( tempAppLog.action_object, "LOGIN" );
	strcpy ( tempAppLog.parameter, login );

#ifdef DEBUG_ASP_USERMGT
	lout3 << "----->  asp_usermanagement_server_impl::LoginUser() " << endl;
	lout3 << "	idClass=" << idClass << endl;
	lout3 << "	login=" << login << endl;
	lout3 << "	password=" << password << endl;
	lout3 << "	disablePassCheck=" << disablePassCheck << endl;
#endif

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			if ( disablePassCheck == true ) {
				lout3 << "Login without authentication." << endl;
				strcpy ( tempAppLog.action_object, "LOGIN WITHOUT AUTHENTICATION" );
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;
				ruolo = apidb_usermgt.idRole ( idClass, login );
				lout3 << "	ruolo=" << ruolo << endl;
				bFlag = true;
			}
			else {
				lout3 << "Login with authentication." << endl;
				ruolo = 777; //apidb_usermgt->userAuthenticationNew(idClass,login,password);

				if ( ruolo >= 1 ) {

					if ( idClass == ID_MOD_COMMON_ADMIN ) {
						bFlag = true;
						strcpy ( tempAppLog.successful, "TRUE" );
						tempAppLog.return_code = 1;
					}
					else {
						if ( ruolo == 1 ) { //CASO USERADMINISTRATOR in autenticazione dei client
							bFlag = false;
							strcpy ( tempAppLog.successful, "FALSE" );
							tempAppLog.return_code = -1;
						}
						else {
							bFlag = true;
							strcpy ( tempAppLog.successful, "TRUE" );
							tempAppLog.return_code = 1;
						}
					}
				}
				else {
					bFlag = false;
					strcpy ( tempAppLog.successful, "FALSE" );
					tempAppLog.return_code = -1;
				}
			}

			

		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
		
	}else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp_usermgt,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );
	

	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::getConfigLib ( CORBA::Long flag, S_CONFIG_LIB_out  ConfigLib ) throw ( CORBA::SystemException )
{

	S_APP_LOG AppLog;
	strcpy ( AppLog.ip_client, "10.0.0.1" );
	strcpy ( AppLog.hostname_client, "jeeg" );
	strcpy ( AppLog.application_user, "nello" );
	strcpy ( AppLog.client_user, "nello" );
	strcpy ( AppLog.application, "client_test" );

	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout3 << "----->  asp_usermanagement_server_impl::getConfigLib() START" << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action, "GET CONFIG" );
	strcpy ( tempAppLog.action_object, "GET CONFIG" );
	strcpy ( tempAppLog.parameter, "<none>" );


	ConfigLib = new S_CONFIG_LIB;

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			SRoleSeq localRoleSeq;
			SClassSeq localClassSeq;
			SFunctionSeq localFunctionSeq;
			SProfileSeq localProfileSeq;
			SUserSeq localUserSeq;

			S_ROLE *sRole = NULL;
			S_PROFILE *sProfile = NULL;
			S_CLASS *sClass = NULL;
			S_FUNCTION *sFunction = NULL;
			S_USER *sUser = NULL;

			int l_sRole, l_sProfile, l_sClass, l_sFunction, l_sUser;

			bool bRet = apidb_usermgt.getConfig ( sRole, sProfile, sClass, sFunction, sUser, l_sRole, l_sProfile, l_sClass, l_sFunction, l_sUser );

			if ( bRet == true ) {
				strcpy ( tempAppLog.action, "GET ALL CONFIG" );
				strcpy ( tempAppLog.action_object, "GET ALL CONFIG" );
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;

				localRoleSeq.length ( l_sRole );
				for ( int i = 0; i < l_sRole; i++ ) {
					( localRoleSeq ) [i] = ( sRole[i] );
				}
				ConfigLib->roleSeq = ( localRoleSeq );

				localProfileSeq.length ( l_sProfile );
				for ( int i = 0; i < l_sProfile; i++ ) {
					( localProfileSeq ) [i] = ( sProfile[i] );
				}
				ConfigLib->profileSeq = ( localProfileSeq );

				localClassSeq.length ( l_sClass );
				for ( int i = 0; i < l_sClass; i++ ) {
					( localClassSeq ) [i] = ( sClass[i] );
				}
				ConfigLib->classSeq = ( localClassSeq );

				localFunctionSeq.length ( l_sFunction );
				for ( int i = 0; i < l_sFunction; i++ ) {
					( localFunctionSeq ) [i] = ( sFunction[i] );
				}
				ConfigLib->functionSeq = ( localFunctionSeq );

				localUserSeq.length ( l_sUser );
				for ( int i = 0; i < l_sUser; i++ ) {
					( localUserSeq ) [i] = ( sUser[i] );
				}
				ConfigLib->userSeq = ( localUserSeq );

				if ( sRole )
					delete [] sRole;
				if ( sProfile )
					delete [] sProfile;
				if ( sClass )
					delete [] sClass;
				if ( sFunction )
					delete [] sFunction;
				if ( sUser )
					delete [] sUser;
			}

		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp_usermgt,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

#ifdef DEBUG_ASP_USERMGT
	lout3 << "----->  asp_usermanagement_server_impl::getConfigLib() END " << endl;
#endif
	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::getProfileConfiguration ( CORBA::Long flag, const char*  profileName, S_PROFILE_out  profile, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "------>  getProfileConfiguration() " << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action, "GET PROFILE CONFIGURATION" );
	strcpy ( tempAppLog.action_object, "PROFILE" );
	strcpy ( tempAppLog.parameter, profileName );

	profile = new S_PROFILE;

	profile->idClass = 1;
	c_qstrncpy ( profile->profile, profileName, MAX_PROFILE );
	c_qstrncpy ( profile->descProfile, profileName, MAX_DESC_PROFILE );

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {

			S_FUNCTION *sFunction = NULL;
			SFunctionSeq localFunctionSeq;
			int l_sFunction;

			bool bRet = apidb_usermgt.getProfileFunction ( profileName, sFunction, l_sFunction );

			if ( bRet == true ) {
				localFunctionSeq.length ( l_sFunction );
				for ( int i = 0; i < l_sFunction; i++ ) {
					profile->idClass = sFunction[i].idClass;
					( localFunctionSeq ) [i] = ( sFunction[i] );
				}
				profile->functionSeq = ( localFunctionSeq );
				bFlag = true;
			}
			else {
				bFlag = false;
			}


			S_LOGIN *sLogin = NULL;
			SLoginSeq localLoginSeq;
			int l_sLogin = 0;

			bool bRet2 = apidb_usermgt.getProfileUser ( profileName, sLogin, l_sLogin );

			if ( bRet2 > 0 ) {
				localLoginSeq.length ( l_sLogin );
				for ( int i = 0; i < l_sLogin; i++ ) {
					( localLoginSeq ) [i] = ( sLogin[i] );
				}
				profile->loginSeq = ( localLoginSeq );
				bFlag = true;
			}
			else {
				bFlag = false;
			}

			if ( sLogin != NULL ) {
				delete [] sLogin;
			}

			if ( bFlag == true ) {
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;
			}
			else {
				strcpy ( tempAppLog.successful, "FALSE" );
				tempAppLog.return_code = -1;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "ERROR DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp_usermgt,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::getUserConfiguration ( CORBA::Long flag, const char*  login, CORBA::Long idClass, S_USER_out  user, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "------>  getUserConfiguration() " << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action, "GET USER CONFIGURATION" );
	strcpy ( tempAppLog.action_object, "USER" );
	strcpy ( tempAppLog.parameter, qPrintable ( QString ( login ) + "," + QString::number ( idClass ) ) );


	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			bool bRet = apidb_usermgt.getUser ( login, user );

			if ( bRet > 0 ) { // carico eventuali profili
				int l_sProfile;
				S_PROFILE *sProfile = NULL;
				SProfileSeq localProfileSeq;

				bool bRet1 = apidb_usermgt.getUserProfile ( login, idClass, sProfile, l_sProfile );
				localProfileSeq.length ( l_sProfile );

				for ( int i = 0; i < l_sProfile; i++ ) {
					int l_sFunction = 0;
					SFunctionSeq localFunctionSeq;
					S_FUNCTION *sFunction = NULL;

					if ( idClass != -1 ) {
						bool bRet2 = apidb_usermgt.getProfileFunction ( sProfile[i].profile, sFunction, l_sFunction );
						localFunctionSeq.length ( l_sFunction );
						for ( int j = 0; j < l_sFunction; j++ ) {
							( localFunctionSeq ) [j] = ( sFunction[j] );
						}

						sProfile[i].functionSeq = ( localFunctionSeq );
					}

					( localProfileSeq ) [i] = ( sProfile[i] );

					if ( sFunction != NULL ) {
						delete [] sFunction;
					}

				}
				user->profileSeq = ( localProfileSeq );

				if ( sProfile != NULL ) {
					delete [] sProfile;
				}

				bFlag = true;
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;
			}
			else {
				lout << "Utente non trovato" << endl;
				strcpy ( tempAppLog.successful, "FALSE" );
				tempAppLog.return_code = -1;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "ERROR DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp_usermgt,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::setUserConfiguration ( CORBA::Long flag, const S_USER&  user, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "------>  setUserConfiguration(" + QString ( user.login ) + ") " << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, "USER" );
	strcpy ( tempAppLog.parameter, qPrintable ( QString ( user.login ) ) );


	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			/*QStringList strCampi;
			QStringList strValues;
			Q3ValueList<int> strType;*/

			switch ( flag ) {
				case OP_INSERT:
				case OP_MODIFY: {
					strcpy ( tempAppLog.action, "INSERT/MODIFY USER-PROFILE" );
					
					/* DELETE */
					QString strSelect="DELETE FROM t_user_profile WHERE LOGIN='"+QString(user.login)+"'";

					if(user.profileSeq.length()>0)
					{
						strSelect+=" AND PROFILE NOT in (";
						
						for(int i=0;i<user.profileSeq.length();i++)
						{
							strSelect+="'"+QString(user.profileSeq[i].profile)+"'";
							
							if(i==user.profileSeq.length()-1)
							{
								strSelect+=")";
							}else
							{
								strSelect+=",";
							}
						}
					}
					lout3 << strSelect << endl;
					bFlag=apidb_usermgt.deleteRecordInTable(strSelect);

					/* INSERT */
					if(user.profileSeq.length()>0)
					{
						apidb_usermgt.insertPrepare(INSERT_INTO_T_USER_PROFILE);
						for(int i=0;i<user.profileSeq.length();i++)
						{
							apidb_usermgt.strValues.append(QString(user.login));
							apidb_usermgt.strValues.append(QString(user.profileSeq[i].profile));
						}
						apidb_usermgt.insertCreate ( 1 );
						bFlag = apidb_usermgt.writeData ( INSERT_INTO_T_USER_PROFILE );
					}
					
					//QString strSelect2="UPDATE t_user SET ID_ROLE ="+QString::number(user.idRole)+" WHERE LOGIN='"+QString(user.login)+"'";
					//bool bRet2=apidb_stsconf->executeSelect(strSelect2);

				}
				break;

				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE USER-PROFILE" );
					bFlag=apidb_usermgt.deleteProfile(user.login);
				}
				break;
			}

			if ( bFlag == true ) {
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;
			}
			else {
				strcpy ( tempAppLog.successful, "FALSE" );
				tempAppLog.return_code = -1;
			}

		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "ERROR DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::setProfileConfiguration ( CORBA::Long flag, const S_PROFILE&  profile, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "------>  setProfileConfiguration(" + QString ( profile.profile ) + ") " << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, "PROFILE" );
	strcpy ( tempAppLog.parameter, qPrintable ( getNameClass ( profile.idClass ) + "," + QString ( profile.profile ) ) );


	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			switch ( flag ) {
				case OP_INSERT: {
					strcpy ( tempAppLog.action, "INSERT PROFILE" );

					if(profile.functionSeq.length()>0)
					{
						apidb_usermgt.insertPrepare(INSERT_INTO_T_PROFILE_CLASS);
						for(int i=0;i<profile.functionSeq.length();i++)
						{
							apidb_usermgt.strValues.append(QString(profile.profile));
							apidb_usermgt.strValues.append(QString(profile.descProfile));
							apidb_usermgt.strValues.append(QString::number(profile.idClass));
							apidb_usermgt.strValues.append(QString::number(profile.functionSeq[i].idFunction));
						}
						apidb_usermgt.insertCreate ( 1 );
						bFlag = apidb_usermgt.writeData ( INSERT_INTO_T_PROFILE_CLASS );
					}
					
					
				}
				break;

				case OP_MODIFY: {
					strcpy ( tempAppLog.action, "MODIFY PROFILE" );

					/*// Cancello tutte le funzioni del profilo non passate dal client.
					QString strSelect="DELETE FROM TSTS_PROFILE_CLASS WHERE PROFILE='"+QString(profile.profile)+"'";
					if(profile.functionSeq.length()>0)
					{
						strSelect+=" AND ID_FUNCTION NOT in (";
						for(int i=0;i<profile.functionSeq.length();i++)
						{
							strSelect+=QString::number(profile.functionSeq[i].idFunction);
							if(i==profile.functionSeq.length()-1)
							{
								strSelect+=")";
							}else
							{
								strSelect+=",";
							}
						}
					}
					bool bRet1=apidb_stsconf->deleteRecordInTable(strSelect);

					//modifico eventuale descrizione del profilo.
					//strSelect="UPDATE TSTS_PROFILE_LIB SET DESC_PROFILE='"+QString(profile.descProfile)+"' WHERE PROFILE='"+QString(profile.profile)+"'";
					bool bRet3=true;//apidb_stsconf->executeSelect(strSelect);
					if(bRet3)
					{

						//inserisco le nuove funzioni passate dal client.
						if(profile.functionSeq.length()>0)
						{
							strCampi.append("PROFILE");
							strType.append(T_STRING);
							strCampi.append("DESC_PROFILE");
							strType.append(T_STRING);
							strCampi.append("ID_CLASS");
							strType.append(T_NUMBER);
							strCampi.append("ID_FUNCTION");
							strType.append(T_NUMBER);

							for(int i=0;i<profile.functionSeq.length();i++)
							{
								strValues.append(QString(profile.profile));
								strValues.append(QString(profile.descProfile));
								strValues.append(QString::number(profile.idClass));
								strValues.append(QString::number(profile.functionSeq[i].idFunction));

								bool bRet2=apidb_stsconf->insertRecordInTable("TSTS_PROFILE_CLASS",strCampi,strValues,strType);
					//								if(bRet2==false)
					//								{
					//									FLAG=false;
					//								}
								strValues.clear();
							}
						}

						strSelect="UPDATE TSTS_PROFILE_CLASS SET DESC_PROFILE='"+QString(profile.descProfile)+"' WHERE PROFILE='"+QString(profile.profile)+"'";
						bool bRet4=apidb_stsconf->executeSelect(strSelect);

						FLAG=bRet4;
					}
					else
					{
						FLAG=false;
					}*/
				}
				break;


				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE PROFILE" );

					/*//QString strSelect="DELETE FROM TSTS_PROFILE_LIB WHERE PROFILE='"+QString(profile.profile)+"'";
					QString strSelect="DELETE FROM TSTS_PROFILE_CLASS WHERE PROFILE='"+QString(profile.profile)+"'";
					bool bRet=apidb_stsconf->deleteRecordInTable(strSelect);

					if(profile.idClass==3) //MONITOR
					{
						#ifdef DEBUG_STSBasicConfigurationImpl
							lout << "Cancellato profilo da TMONITOR_USER_GRANT_NEW" << endl;
						#endif
						QString strSelect2="DELETE FROM TMONITOR_USER_GRANT_NEW WHERE PROFILE='"+QString(profile.profile)+"'";
						bool bRet2=apidb_stsconf->deleteRecordInTable(strSelect);
					}

					if(profile.idClass==1) //TNTM
					{
						#ifdef DEBUG_STSBasicConfigurationImpl
							lout << "Cancellato profilo da TMONITOR_USER_GRANT_NEW" << endl;
						#endif
						QString strSelect2="DELETE FROM TNTM_GRANT WHERE PROFILE='"+QString(profile.profile)+"'";
						bool bRet2=apidb_stsconf->deleteRecordInTable(strSelect);
					}

					if(profile.idClass==2) //IMAGE
					{
						#ifdef DEBUG_STSBasicConfigurationImpl
							lout << "Cancellato profilo da TIMAGE_LAYOUT_GRANT_NEW" << endl;
							lout << "Cancellato showKpi da TIMAGE_SHOW_KPI" << endl;
						#endif
						QString strSelect2="DELETE FROM TIMAGE_LAYOUT_GRANT_NEW WHERE PROFILE='"+QString(profile.profile)+"'";
						bool bRet2=apidb_stsconf->deleteRecordInTable(strSelect);
						strSelect2="DELETE FROM TIMAGE_SHOW_KPI WHERE PROFILE='"+QString(profile.profile)+"'";
						bool bRet3=apidb_stsconf->deleteRecordInTable(strSelect);
					}


					FLAG=bRet;*/
				}
				break;
			}

			if ( bFlag == true ) {
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;
			}
			else {
				strcpy ( tempAppLog.successful, "FALSE" );
				tempAppLog.return_code = -1;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "ERROR DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::getNode ( ConfNodeSeq_out nodeList, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "----->  getCentrali() START" << endl;
#endif

	ConfNodeSeq *localNodeList = new ConfNodeSeq;
	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action, "GET NODES" );
	strcpy ( tempAppLog.action_object, "ALL NODES" );
	strcpy ( tempAppLog.parameter, "<NONE>" );

	QString conf_ini_file;
	conf_ini_file = ADAMSINIFILE;

	NodeConfigHandler nodeConfigHandler ( conf_ini_file );
	InputNodeList nodeListDB = nodeConfigHandler.getInputNodeList();

	int len = nodeListDB.size();
	lout << "len=" << len << endl;

	if ( len > 0 ) 
	{
		testConnection();

		if ( dbConnect_usermgt == true ) 
		{
			try {
				int i = 0;

				localNodeList->length ( len );

				InputNodeList_it it_InputNodeList_it;

				for ( it_InputNodeList_it = nodeListDB.begin(); it_InputNodeList_it != nodeListDB.end(); ++it_InputNodeList_it ) {
					PAR_INPUT_NODE node = *it_InputNodeList_it;

					( *localNodeList ) [i].idNode = ( node.iIndice );
					c_qstrncpy ( ( *localNodeList ) [i].nameNode, qPrintable ( node.acName ), MAX_NAME_NODE );
					c_qstrncpy ( ( *localNodeList ) [i].descNode, qPrintable ( node.acNameNodo ), MAX_DESC_NODE );
					c_qstrncpy ( ( *localNodeList ) [i].tipoNode, qPrintable ( node.typeNode ), MAX_TIPO_NODE );
#ifdef DEBUG_ASP_USERMGT
					lout << node.acName << "   " << node.acNameNodo << endl;
#endif
					++i;
				}
				strcpy ( tempAppLog.successful, "TRUE" );
				tempAppLog.return_code = 1;
				bFlag = true;
			}
			catch ( const CORBA::Exception &e ) {
				strcpy ( tempAppLog.action_object, "ERROR CORBA" );
				strcpy ( tempAppLog.successful, "FALSE" );
				tempAppLog.return_code = -1;
				bFlag = false;
			}
		}
		else {
			bFlag = false;
			strcpy ( tempAppLog.action_object, "ERROR DB" );
			strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;

		}
	}
	
	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );
	
	nodeList = localNodeList;
	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::imd_Role ( CORBA::Long flag, const S_ROLE&  role, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Role(" + QString ( role.descRole ) + ") START" << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, qPrintable ( QString::number ( flag ) ) );

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			strcpy ( tempAppLog.parameter, role.descRole );

			switch ( flag ) {
				case OP_INSERT: {
					strcpy ( tempAppLog.action, "INSERT NEW ROLE" );

				}
				break;

				case OP_MODIFY:
					strcpy ( tempAppLog.action, "MODIFY NEW ROLE" );
					{
					} break;

				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE ROLE" );
				}
				break;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;

	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Role(" + QString ( role.descRole ) + ") START" << endl;
#endif
	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::imd_Class ( CORBA::Long flag, const S_CLASS&  modulo, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Class(" + QString ( modulo.descClass ) + ") START" << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, qPrintable ( QString::number ( flag ) ) );

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			strcpy ( tempAppLog.parameter, modulo.descClass );

			switch ( flag ) {
				case OP_INSERT: {
					strcpy ( tempAppLog.action, "INSERT NEW CLASS" );

				}
				break;

				case OP_MODIFY:
					strcpy ( tempAppLog.action, "MODIFY CLASS" );
					{
					} break;

				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE CLASS" );
				}
				break;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;

	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Class(" + QString ( modulo.descClass ) + ") START" << endl;
#endif
	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::imd_Function ( CORBA::Long flag, const S_FUNCTION&  function, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Function(" + QString ( function.nameFunction ) + ") START" << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, qPrintable ( QString::number ( flag ) ) );

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			strcpy ( tempAppLog.parameter, function.nameFunction );

			switch ( flag ) {
				case OP_INSERT: {
					strcpy ( tempAppLog.action, "INSERT NEW FUNCTION" );

				}
				break;

				case OP_MODIFY:
					strcpy ( tempAppLog.action, "MODIFY FUNCTION" );
					{
					} break;

				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE FUNCTION" );
				}
				break;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;

	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Function(" + QString ( function.nameFunction ) + ") START" << endl;
#endif
	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::imd_Profile ( CORBA::Long flag, const S_PROFILE&  profile, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Profile(" + QString ( profile.profile ) + ") START" << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, qPrintable ( QString::number ( flag ) ) );

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			strcpy ( tempAppLog.parameter, profile.profile );

			switch ( flag ) {
				case OP_INSERT: {
					strcpy ( tempAppLog.action, "INSERT NEW PROFILE" );

				}
				break;

				case OP_MODIFY:
					strcpy ( tempAppLog.action, "MODIFY PROFILE" );
					{
					} break;

				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE PROFILE" );
				}
				break;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;
		apidb_asplog.writeOnDB ( tempAppLog );
	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_Profile(" + QString ( profile.profile ) + ") START" << endl;
#endif
	return ( CORBA::Boolean ) bFlag;
}

CORBA::Boolean asp_usermanagement_server_impl::imd_User ( CORBA::Long flag, const S_USER&  user, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException )
{
	bool bFlag = true;
#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_User(" + QString ( user.login ) + ") START" << endl;
#endif

	S_APP_LOG tempAppLog;

	asp_entry_impl::copyToCorba ( AppLog, &tempAppLog );

	strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
	strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
	strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );
	strcpy ( tempAppLog.action_object, qPrintable ( QString::number ( flag ) ) );

	testConnection();

	if ( dbConnect_usermgt == true ) 
	{
		try {
			strcpy ( tempAppLog.parameter, user.login );

			switch ( flag ) {
				case OP_INSERT: {
					strcpy ( tempAppLog.action, "INSERT NEW USER" );

				}
				break;

				case OP_MODIFY:
					strcpy ( tempAppLog.action, "MODIFY USER" );
					{
					} break;

				case OP_DELETE: {
					strcpy ( tempAppLog.action, "DELETE USER" );
				}
				break;
			}
		}
		catch ( const CORBA::Exception &e ) {
			strcpy ( tempAppLog.action_object, "ERROR CORBA" );
			strcpy ( tempAppLog.successful, "FALSE" );
			tempAppLog.return_code = -1;
			bFlag = false;
		}
	}
	else {
		bFlag = false;
		strcpy ( tempAppLog.action_object, "CONNESSIONE DB" );
		strcpy ( tempAppLog.parameter, "SCHEMA:adams_asp,LOGIN=adams" );
		strcpy ( tempAppLog.successful, "FALSE" );
		tempAppLog.return_code = -1;

	}

	if(dbConnect_asplog==true) apidb_asplog.writeOnDB ( tempAppLog );

#ifdef DEBUG_ASP_USERMGT
	lout << "----->  imd_User(" + QString ( user.login ) + ") START" << endl;
#endif
	return ( CORBA::Boolean ) bFlag;
}
