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


#include "mdm_agent_server_impl.h"
#include <applogger.h>
#include <timeout.h>
#include <adamsserver.h>
#include <storeconf.h>

#include <sys/types.h>
#include <sys/wait.h>
#include <arpa/inet.h>

#define WATCHDOG_TIMEOUT	(60*3)

static TimeOut timeout;
static bool runningOneRequest = false;

mdm_agent_server_impl::mdm_agent_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate, int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy )
{
	// if activate is true then create an object id and activate the servant in the correct POA
	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing() ->activate_object_with_id ( oid.in(), this );
	}

	// Initialize watchdog
	timeout.setProcess ( ::getpid() );
	timeout.setTimeOut ( WATCHDOG_TIMEOUT );
	timeout.start();		// watchdog active
	timeout.setiActive(true);
}


/**
 */

mdm_agent_server_impl::~mdm_agent_server_impl()
{
}

// IDL implementation interfaces

void mdm_agent_server_impl::shutDown() throw ( CORBA::SystemException )
{
	lout << "exiting due to remote shutDown request" << endl;
	AdamsServer::qtapp().exit(0);
}

CORBA::Long mdm_agent_server_impl::getProgressPercentage ( ::net::etech::BtreeID_out Btree_id, ::net::etech::BtreeSeq_out BtreeData) throw(CORBA::SystemException)
{
	static int numDays = 0;
	CORBA::Long progress = 0;
	const int * progArr;

	timeout.setiActive(true);

	if (numDays == 0) {
		numDays = StoreConf::params()->data.ElaborationData.count();
	}
	BtreeData = new BtreeSeq(1);
	BtreeData->length(0);

	if (!runningOneRequest)
		progress = 998;			// error
		else if (worker->complete()) {
			progress = 999;			// complete
		}
		else {
			progArr = worker->getProgress();
			for (int i = 0; i < numDays; i++)
				progress += progArr[i];
			progress /= numDays;
		}


		lout << "ProgressPercentage: " << progress  << endl;

		if (progress == 999)
			BtreeData = worker->getBtree();

		return progress;
}

CORBA::Boolean mdm_agent_server_impl::startMDMAgent ( const ::net::etech::STRUCT_PARAMS & UserInput, const ::net::etech::ADAMS_COMPLETE_CONFIG & AdamsConf) throw(CORBA::SystemException)
{
	// 	lout << "executing InvokeDataServer" << endl;
	runningOneRequest = true;

	ADAMS_COMPLETE_wrapper::copyTo(storeconf.config(), &AdamsConf);
	storeconf.params()->fillFromCorba(&UserInput);

	worker = new CollectionThread();
	worker->start();

	return true;
}
