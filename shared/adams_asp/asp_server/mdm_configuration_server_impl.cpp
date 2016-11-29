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
#include "mdm_configuration_server_impl.h"
#include "../../../include/generated/aspC.h"
#include <applogger.h>

mdm_configuration_server_impl::mdm_configuration_server_impl ()
{
	dbConnect_asplog = false;
}


/**
 */

mdm_configuration_server_impl::~mdm_configuration_server_impl()
{
	lout3 << "------- ~mdm_configuration_server_impl() ------" << endl;
	apidb_asplog.closeDB();
}

void mdm_configuration_server_impl::testConnection()
{
	dbConnect_asplog = apidb_asplog.isConnect();
	if ( dbConnect_asplog == false ) {
		dbConnect_asplog = apidb_asplog.openDB ( "adams_log", "adams", "adams" );
	}
}

void mdm_configuration_server_impl::copyToCorba ( const S_APP_LOG &sorg, S_APP_LOG *dest )
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

// IDL implementation interfaces

CORBA::Boolean mdm_configuration_server_impl::getProfileConfigurationMDM (const char * profileName,ADAMS_PROFILE_GRANT_out profileADAMS,const S_APP_LOG & AppLog) throw ( CORBA::SystemException )
{
	return ( CORBA::Boolean ) true;
}
CORBA::Boolean mdm_configuration_server_impl::setProfileConfigurationMDM (CORBA::Long flag, const ADAMS_PROFILE_GRANT & profileADAMS, const S_APP_LOG & AppLog) throw ( CORBA::SystemException )
{
	return ( CORBA::Boolean ) true;
}

CORBA::Boolean mdm_configuration_server_impl::setConfiguration (CORBA::Long flag, const S_FUNCTION & newConfiguration, const S_APP_LOG & AppLog) throw ( CORBA::SystemException )
{
	return ( CORBA::Boolean ) true;
}
