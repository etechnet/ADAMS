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
#include "asp_entry_impl.h"
#include <applogger.h>

asp_entry_impl::asp_entry_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy )
{

	serverID = idServer;

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}
	apidb_asplog.setDBNameCnnection("asp_entry_impl");
}


/**
 */

asp_entry_impl::~asp_entry_impl()
{
	lout3 << "------- asp_entry_impl::closeDB------" << endl;
	apidb_asplog.closeDB();
}

void asp_entry_impl::init_asp_entry()
{
}

// IDL implementation interfaces

CORBA::Boolean asp_entry_impl::writeAPP_LOG ( const S_APP_LOG & AppLog ) throw ( CORBA::SystemException )
{
	lout3 << "------- writeAPP_LOG called ------" << endl;
	bool FLAG = true;

	S_APP_LOG tempAppLog;
	copyToCorba ( AppLog, &tempAppLog );

	dbConnect = apidb_asplog.isConnect();
	if ( dbConnect == false ) {
		dbConnect = apidb_asplog.openDB ( "adams_log", "adams", "adams" );
	}

	if ( dbConnect == true ) {
		try {
			strcpy ( tempAppLog.timeStamp, qPrintable ( serviceLib.currDateTime() ) );
			strcpy ( tempAppLog.ip_server, qPrintable ( serviceLib.getHostIP() ) );
			strcpy ( tempAppLog.hostname_server, qPrintable ( serviceLib.getHostName() ) );

			apidb_asplog.writeOnDB ( tempAppLog );
			apidb_asplog.debug ( tempAppLog );
			FLAG = true;
		}
		catch ( const CORBA::Exception &e ) {
			FLAG = false;
		}
	}
	else {
		lout3 << "Unable to DB connect." << endl;
	}
	return ( CORBA::Boolean ) FLAG;
}

CORBA::Boolean asp_entry_impl::getPswd ( const char * nomeNodo, const char * typePassword, const char * userLogin, ::CORBA::String_out userPassword ) throw ( CORBA::SystemException )
{
	lout << "------- getPswd called ------" << endl;
	return true;
}


CORBA::Boolean asp_entry_impl::dummy ( )  throw ( CORBA::SystemException )
{
	lout3 << "------- dummy asp_entry called ------" << endl;
	lout3 << "***************** START TEST SERVER *******************" << endl;
	S_APP_LOG appo;
	strcpy ( appo.ip_client, "127.0.0.1" );
	strcpy ( appo.hostname_client, "localhost" );
	strcpy ( appo.application_user, "raffo" );
	strcpy ( appo.client_user, "raffo" );
	strcpy ( appo.application, "asp_client" );
	strcpy ( appo.action, "test" );
	strcpy ( appo.action_object, "test" );
	strcpy ( appo.parameter, "null" );
	strcpy ( appo.successful, "TRUE" );
	appo.return_code = 1;

	writeAPP_LOG ( appo );
	lout3 << "***************** END TEST SERVER *******************" << endl;
	return true;
}

void asp_entry_impl::copyToCorba ( const S_APP_LOG &sorg, S_APP_LOG *dest )
{
	strncpy ( dest->timeStamp, sorg.timeStamp, MAX_TIME_STAMP );
	strncpy ( dest->ip_server, sorg.ip_server, MAX_IP_GENERATORE );
	strncpy ( dest->hostname_server, sorg.hostname_server, MAX_HOSTNAME_GENERATORE );
	strncpy ( dest->ip_client, sorg.ip_client, MAX_IP_CLIENT );
	strncpy ( dest->hostname_client, sorg.hostname_client, MAX_HOSTNAME_CLIENT );
	strncpy ( dest->application_user, sorg.application_user, MAX_UTENZA_APPLICATIVA );
	strncpy ( dest->client_user, sorg.client_user, MAX_UTENZA_CLIENT );
	strncpy ( dest->application, sorg.application, MAX_APPLICATIVO_CLIENT );
	strncpy ( dest->action, sorg.action, MAX_AZIONE );
	strncpy ( dest->action_object, sorg.action_object, MAX_OGGETTO );
	strncpy ( dest->parameter, sorg.parameter, MAX_PARAMETRI );
	strncpy ( dest->successful, sorg.successful, MAX_ESITO );
	dest->return_code = sorg.return_code;
}
