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

#ifndef _SSM_GARBAGE_SERVER_H
#define _SSM_GARBAGE_SERVER_H

#include <poahierarchy.h>
#include <mdmS.h>
#include <p_orb.h>
#include <posix_gsp_timed_prodcons.h>
#include <deque>
#include <Qt/qstring.h>

using namespace net::etech::MDM;

class mdm_server_factory_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::MDM::mdm_server_factory_server
{

public:

	mdm_server_factory_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int timeout );
	~mdm_server_factory_server_impl();

	// The CORBA _default_POA() API
	virtual PortableServer::POA_ptr	_default_POA();
	// Signatures of IDL operations and attributes
	virtual mdm_server_server_ptr get_server() throw(CORBA::SystemException, net::etech::MDM::mdm_server_factory_server::LaunchFailure);

	virtual void put_ior(mdm_server_server_ptr srv) throw(CORBA::SystemException);
	// accessor for currently configured timeout
	inline int timeout() const;
	// modifier for timeout
	inline void timeout(int time);

protected:
	mdm_server_server_ptr get_ior() throw (GSP_Timeout);
	// Producer Consumer synchronisation variable
	GSP_Timed_ProdCons m_sync;

	// Store IORs of newly created config servers
	std::deque<mdm_server_server_ptr> m_cfg_server_queue;

	// timeout for launched servers i.e. the amount of time in seconds
	// we should wait for a newly launched server before sending an
	// exception back to the client
	int m_timeout;

private:

	PoaHierarchy * m_poa_hierarchy;
	int serverID;
	QString m_install_bin;
	QString m_svc_file;
};

#endif
