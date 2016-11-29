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


#include "mdm_agent_factory_server_impl.h"
#include <applogger.h>
#include <configparser.h>
#include <adamsserver.h>
#include <Qt/qfile.h>
#include <sys/types.h>
#include <unistd.h>

mdm_agent_factory_server_impl::mdm_agent_factory_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int timeout )
	: m_poa_hierarchy ( g_poa_hierarchy ),
	  m_timeout ( timeout )
{

	serverID = 0;

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}

	m_install_bin = AdamsServer::getInstallPath() + "/bin/";
	m_svc_file = AdamsServer::getTAOSvcConf();
}


/**
 */

mdm_agent_factory_server_impl::~mdm_agent_factory_server_impl()
{
}

PortableServer::POA_ptr mdm_agent_factory_server_impl::_default_POA()
{
	return PortableServer::POA::_duplicate ( m_poa_hierarchy->CoreProcessing() );

}

int mdm_agent_factory_server_impl::timeout() const
{
	return m_timeout;
}

void mdm_agent_factory_server_impl::timeout ( int time )
{
	m_timeout = time;
}

// Description: reads from Producer Consumer queue

mdm_agent_server_ptr mdm_agent_factory_server_impl::get_ior() throw ( GSP_Timeout )
{
	GSP_Timed_ProdCons::GetOp var ( m_sync, m_timeout );

	lout2 << "mdm_agent_factory_server_impl in get_ior" << endl;

	// read references from the front of the queue
	mdm_agent_server_ptr cf = m_cfg_server_queue.front();

	// remove the entry from the front of the queue
	m_cfg_server_queue.pop_front();

	//return cf._retn();
	return cf;

}

// Description: writes to Producer Consumer queue
void mdm_agent_factory_server_impl::put_ior ( mdm_agent_server_ptr srv ) throw ( CORBA::SystemException )
{
	GSP_Timed_ProdCons::PutOp var ( m_sync );
	lout2 << "mdm_agent_factory_server_impl in put_ior" << endl;
	// write new references to the back of the queue
	m_cfg_server_queue.push_back ( mdm_agent_server::_duplicate ( srv ) );

	return;
}



// Description: Implementation of the corresponding IDL operation.
//              passes IOR of new config server back to the client

mdm_agent_server_ptr mdm_agent_factory_server_impl::get_server ( CORBA::Boolean inretry ) throw ( CORBA::SystemException, net::etech::MDM::mdm_agent_factory_server::LaunchFailure )
{
	if ( !inretry ) {
		try {
			QString mdm_agent_bin = m_install_bin + "mdm_agent";

			if ( !QFile::exists ( mdm_agent_bin ) ) {
				net::etech::MDM::mdm_agent_factory_server::LaunchFailure lf;
				lf.reason = CORBA::string_dup ( "MDM server could not be not launched - file doesn't exist" );
				throw lf;
			}

			pid_t pid;

			if ( ( pid = fork() ) == 0 ) {

				execl ( qPrintable ( mdm_agent_bin ),
				        qPrintable ( mdm_agent_bin ),
				        "--node",
				        qPrintable ( AdamsServer::getNode() ),
				        0
				      );
			}
		} catch ( ... ) {
			lout << "exception while launching new config server" << endl;
			throw;
		}
	}

	mdm_agent_server_var cs;
	try {
		cs = get_ior();
		return cs._retn();

	} catch ( const GSP_Timeout& gsptime ) {
		string s = gsptime.reason();
		lout << "*** mdm_agent_factory_server_impl: Timeout exception : " << s.c_str() << endl;
		net::etech::MDM::mdm_agent_factory_server::LaunchFailure lf;
		lf.reason = CORBA::string_dup ( s.c_str() );
		throw lf;

	} catch ( ... ) {
		lout << "*** mdm_agent_factory_server_impl: caught unknown exception" << endl;
		net::etech::MDM::mdm_agent_factory_server::LaunchFailure lf;
		lf.reason = CORBA::string_dup ( "mdm_agent_factory_server_impl: caught unknown exception trying to launch server" );
		throw lf;

	}

}

