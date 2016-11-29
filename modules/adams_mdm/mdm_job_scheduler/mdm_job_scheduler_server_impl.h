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

#ifndef _MDM_JOB_SCHEDULER_SERVER_H
#define _MDM_JOB_SCHEDULER_SERVER_H

#include <poahierarchy.h>
#include <mdmS.h>
#include <p_orb.h>

using namespace net::etech::MDM;

class mdm_job_scheduler_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::MDM::mdm_job_scheduler_server
{

public:

	/**
	 * Constructor
	 */
	mdm_job_scheduler_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );

	/**
	 * Destructor
	 */
	~mdm_job_scheduler_server_impl();

	// IDL implementation methods
	virtual CORBA::Boolean dummy (  ) throw ( CORBA::SystemException );

private:

	PoaHierarchy * m_poa_hierarchy;
	int serverID;

};

#endif
