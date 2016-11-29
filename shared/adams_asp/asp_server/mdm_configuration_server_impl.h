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
#ifndef MDM_CONFIGURATION_SERVER_IMPL_H
#define MDM_CONFIGURATION_SERVER_IMPL_H

#include <poahierarchy.h>
#include <aspS.h>
#include <p_orb.h>
#include "servicelib.h"
#include "apidb_asp_log.h"

using namespace net::etech;


class mdm_configuration_server_impl : public virtual POA_net::etech::ASP::mdm_configuration_server
{
public:
	/**
	 * Constructor
	 */
	mdm_configuration_server_impl ();
	
	mdm_configuration_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );

	/**
	 * Destructor
	 */
	~mdm_configuration_server_impl();

	// IDL implementation methods
	virtual CORBA::Boolean getProfileConfigurationMDM (const char * profileName,ADAMS_PROFILE_GRANT_out profileADAMS,const S_APP_LOG & AppLog) throw ( CORBA::SystemException );
	virtual CORBA::Boolean setProfileConfigurationMDM (CORBA::Long flag, const ADAMS_PROFILE_GRANT & profileADAMS, const S_APP_LOG & AppLog) throw ( CORBA::SystemException );
	virtual CORBA::Boolean setConfiguration (CORBA::Long flag, const S_FUNCTION & newConfiguration, const S_APP_LOG & AppLog) throw ( CORBA::SystemException );

private:
	
	static void copyToCorba ( const S_APP_LOG &sorg, S_APP_LOG *dest );
	void testConnection();

	PoaHierarchy * m_poa_hierarchy;
	int serverID;

	APIDB_AspLog apidb_asplog;
	ServiceLib serviceLib;

	bool	dbConnect_asplog;
};

#endif // ASP_SERVER_IMPL_H

