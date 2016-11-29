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


#include <Qt/qcoreapplication.h>
#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include "mdm_agent_server_impl.h"
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <adamsserver.h>
#include <corbaclient.h>
#include <applogger.h>

using namespace std;

static mdm_agent_server_impl * servant = 0;

// ssm_logger stream interface
AppLogger * AppLogStream;


int main ( int argc, char** argv )
{
	int exit_status = 0;
	AdamsServer adams_server ( argc, argv, "mdm_agent" );

	AppLogStream = adams_server.logger();

	try {

		// Create the servant.
		// This is done differently here because we run under control of the factory
		servant = new mdm_agent_server_impl ( adams_server.poa_hierarchy(), "mdm_agent_server", true , 0 );
		mdm_agent_server_var servervar = servant->_this();
		servant->_remove_ref();

		// narrow the server factory and push ior back to it
		CorbaClient<mdm_agent_factory_server> my_factory;
		if ( !my_factory.connect ( "mdm_agent_factory", adams_server.getNode() ) ) {
			lout << "*** Connection to factory failed. Terminating." << endl;
			::exit ( 1 );
		}

		my_factory->put_ior(servervar.in());

		// Activate the servant
		adams_server.poa_hierarchy()->core_functionality()->activate();

		// Start the schedule and lets run event loop
		adams_server.exec();

	}
	catch ( const CORBA::Exception &e ) {
		lout << "Caught CORBA exception:" << e._info().c_str() << endl;
		exit_status = 1;
	} // catch a new exception from the poa utility
	catch ( const corbautil::PoaUtilityException & poe ) {
		lout << "Unexpected Poa Utility exception" << poe.msg << endl;
		exit_status = 1;
	}
	catch ( ... ) {
		lout << "Unexpected c++ exception " << endl;
		exit_status = 1;
	}

	if ( !CORBA::is_nil ( adams_server.orb() ) ) {
		try {
			adams_server.orb()->destroy();
		}
		catch ( const CORBA::Exception & ex ) {
			lout << "orb->destroy() failed::" << ex._info().c_str() << endl;
			exit_status = 1;
		}
	}

	// Termination diagnostics
	lout	<< endl
	        << "Server " <<  adams_server.getServerQualifiedName()
	        << " terminating at " << QDateTime::currentDateTime().toString() << endl;

	return exit_status;

}
