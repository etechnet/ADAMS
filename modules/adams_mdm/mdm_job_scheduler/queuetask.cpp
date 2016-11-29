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

#include "queuetask.h"
#include <corbaclient.h>
#include <configparser.h>

QueueTask::QueueTask ( JobQueue * myjob )
	: task ( myjob ),
	  m_status ( Starting )
{
	ConfigParser parser;
	parser.locateFile();

	m_backend_processor = parser.parQTxtGetValue ( "BackEndProcessorNodeName", "ADAMS_Globals" );
	if ( m_backend_processor.isEmpty() ) {
		lout << "Unassigned backend processor name. Will default to \"BEP\"." << endl;
		m_backend_processor = "BEP";
	}

	m_queues_data_path = parser.parQTxtGetValue ( "JobQueuesPath", "MDM", ConfigParser::PathValue );
}

QueueTask::~QueueTask()
{

}

void QueueTask::run()
{
	setTerminationEnabled(true);

	try {
		CorbaClient<mdm_server_factory_server> server_factory;
		if ( !server_factory.connect ( "mdm_server_factory", m_backend_processor ) ) {
			lout << m_backend_processor << ": server connection failed." << endl;
			return;
		}

		sleep ( 2 );

		int nRetry = 4;
		mdm_server_server_var mdm_server;
		net::etech::STRUCT_PARAMS query_params;
		task->data.req.copyToCorba ( &query_params );

		while ( nRetry > 0 ) {
			try {
				mdm_server = server_factory->get_server ();

				if ( CORBA::is_nil ( mdm_server ) ) {
					lout << "Invalid connection to " << m_backend_processor << " server." << endl;
					m_status = Failure;
					return;
				}
				else
					lout1 << "Got " << m_backend_processor << " server reference" << endl;

				mdm_server->runMDMServerNonBlocking ( query_params );
				nRetry = 0;
			}
			catch ( const CORBA::TRANSIENT & lf ) {
				lout << "Got CORBA::TRANSIENT for " << m_backend_processor << " reason:" << lf._info().c_str() << endl;
				if ( --nRetry <= 0 )
					return ;
			}
			catch ( const CORBA::OBJECT_NOT_EXIST & lf ) {
				lout << "Got CORBA::OBJECT_NOT_EXIST for " << m_backend_processor << " reason:" << lf._info().c_str() << endl;
				if ( --nRetry <= 0 )
					return ;
			}
		}

		m_status = Running;
		sleep ( 5 );
		int progress = 0;
		bool completed = false;

		do {
			mdm_server->getRunningStatus ( m_query_progress, completed );

			// TODO: calculate progress...
			sleep ( 5 );
		}
		while ( ! completed );

		mdm_server->shutDown();
		m_status = Success;
		return;
	}
	catch ( const mdm_server_factory_server::LaunchFailure & lf ) {
		lout << "+++ Server launch failure: " << lf.reason << endl;
		m_status = Failure;
		return;
	}
	catch ( const CORBA::SystemException &e ) {
		lout << "+++ Unexpected CORBA SystemException:" << e._info().c_str() << endl;
		m_status = Failure;
		return ;
	}
	catch ( const CORBA::Exception &e ) {
		lout << "+++ Unexpected exception: " << e._name() << endl;
		m_status = Failure;
		return ;
	}
	catch ( ... ) {
		lout << "+++ Unexpected exception: general failure" << endl;
		m_status = Failure;
		return ;
	}

}
