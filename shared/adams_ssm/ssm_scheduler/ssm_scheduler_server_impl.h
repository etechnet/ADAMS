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

#ifndef _SSM_SCHEDULER_SERVER_H
#define _SSM_SCHEDULER_SERVER_H

#include <poahierarchy.h>
#include <ssmS.h>
#include <p_orb.h>

class ssm_scheduler;
class ssm_scheduler_bus;

using namespace net::etech::SSM;

class ssm_scheduler_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::SSM::ssm_scheduler_server
{

public:

	/**
	 * Constructor
	 */
	ssm_scheduler_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );

	/**
	 * Destructor
	 */
	~ssm_scheduler_server_impl();

	// IDL implementation methods
	virtual CORBA::Boolean dummy ( ) throw ( CORBA::SystemException );

	// received when adams configuration has changed
	virtual void adamsConfigurationChanged ();

	// received when processes configuration has changed
	virtual void processConfigurationChanged ();

	// received when adams.ini has changed
	virtual void iniFileChanged ();

	// protected:

private:

	PoaHierarchy * m_poa_hierarchy;
	int serverID;
	ssm_scheduler * m_scheduler;
	ssm_scheduler_bus * m_scheduler_bus;

};

#endif
