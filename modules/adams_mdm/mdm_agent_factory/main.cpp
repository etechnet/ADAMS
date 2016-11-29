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
#include <mdm_agent_factory_bus.h>
#include "mdm_agent_factory_busadaptor.h"
#include "mdm_agent_factory_server_impl.h"
#include "mdm_agent_factory.h"
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <adamsserver.h>
#include <applogger.h>

#define AGENT_LAUNCH_TIMEOUT_SECS		30

using namespace std;

static mdm_agent_factory_server_impl * servant = 0;

// ssm_logger stream interface
AppLogger * AppLogStream;


int main ( int argc, char** argv )
{
	int exit_status = 0;
	AdamsServer adams_server ( argc, argv, "mdm_agent_factory" );

	AppLogStream = adams_server.logger();

	// DBus init
	mdm_agent_factory_bus * server_factory_bus = new mdm_agent_factory_bus();
	new Mdm_agent_factory_busAdaptor ( server_factory_bus );

	QDBusConnection connection = QDBusConnection::sessionBus();
	connection.registerObject ( "/mdm_agent_factory_bus", server_factory_bus );
	connection.registerService ( "net.etech.adams.mdm_agent_factory_bus" );

	// Instance main garbage class as a separate thread
	mdm_agent_factory server_factory;

	try {

		// Create the servant.
		servant = new mdm_agent_factory_server_impl ( adams_server.poa_hierarchy(), "mdm_agent_factory_server", true , AGENT_LAUNCH_TIMEOUT_SECS );
		servant->_remove_ref();

		// Export IOR in ADAMS processes table
		adams_server.activate ( servant->_this() );

		// Start the server and lets run event loop
		server_factory.start();
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
