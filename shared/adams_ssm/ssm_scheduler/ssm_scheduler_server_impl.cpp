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


#include "ssm_scheduler_server_impl.h"
#include "ssm_scheduler.h"
#include "ssm_scheduler_bus.h"
#include <applogger.h>
#include <namedsharedpointer.h>
#include <configparser.h>

ssm_scheduler_server_impl::ssm_scheduler_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy )
{

	serverID = idServer;

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}

	m_scheduler_bus = NamedSharedPointer<ssm_scheduler_bus>::retrieve("ssm_scheduler_bus");
	m_scheduler = NamedSharedPointer<ssm_scheduler>::retrieve("ssm_scheduler");
}


/**
 */

ssm_scheduler_server_impl::~ssm_scheduler_server_impl()
{
}

// IDL implementation interfaces

CORBA::Boolean ssm_scheduler_server_impl::dummy (  )  throw ( CORBA::SystemException )
{
	lout << "------- dummy called ------" << endl;
	return true;
}

// received when adams configuration has changed
void ssm_scheduler_server_impl::adamsConfigurationChanged ()
{
	lout << "------- adamsConfigurationChanged called ------" << endl;
	m_scheduler_bus->emit_configurationChanged();
	lout3 << "** Notifying childs about change." << endl;
}

// received when processes configuration has changed
void ssm_scheduler_server_impl::processConfigurationChanged ()
{
	lout << "------- processConfigurationChanged called ------" << endl;
	m_scheduler->processConfigHasChanged();
	lout3 << "** Notified scheduler thread about change." << endl;
}

// received when adams.ini has changed
void ssm_scheduler_server_impl::iniFileChanged ()
{
	lout << "------- iniFileChanged called ------" << endl;
	m_scheduler_bus->emit_iniChanged();
	lout3 << "** Notifying childs about change." << endl;
	// Reload params
	ConfigParser::invalidateCache();
	m_scheduler->get_params_from_ini();
}
