/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */


#include "ssm_deployer_server_impl.h"
#include <applogger.h>

ssm_deployer_server_impl::ssm_deployer_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy )
{

	serverID = idServer;

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}

}


/**
 */

ssm_deployer_server_impl::~ssm_deployer_server_impl()
{
}

// IDL implementation interfaces

CORBA::Boolean ssm_deployer_server_impl::dummy (  )  throw ( CORBA::SystemException )
{
	lout << "------- dummy called ------" << endl;
	return true;
}

