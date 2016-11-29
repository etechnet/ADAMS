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
#include <etss_server_trapper_bus.h>
#include "etss_server_trapper_busadaptor.h"
#include "etss_server_trapper_server_impl.h"
#include "etss_server_trapper.h"
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <adamsserver.h>
#include <applogger.h>

using namespace std;

static etss_server_trapper_server_impl * servant = 0;

// ssm_logger stream interface
AppLogger * AppLogStream;


int main ( int argc, char** argv )
{
	int exit_status = 0;
	AdamsServer adams_server ( argc, argv, "etss_server_trapper" );

	AppLogStream = adams_server.logger();

	// DBus init
	etss_server_trapper_bus * server_trapper_bus = new etss_server_trapper_bus();
	new Etss_server_trapper_busAdaptor ( server_trapper_bus );

	QDBusConnection connection = QDBusConnection::sessionBus();
	connection.registerObject ( "/etss_server_trapper_bus", server_trapper_bus );
	connection.registerService ( "net.etech.adams.etss_server_trapper_bus" );

	// Instance main deployer class as a separate thread
	etss_server_trapper server_trapper;

	try {

		// Create the servant.
		servant = new etss_server_trapper_server_impl ( adams_server.poa_hierarchy(), "etss_server_trapper_server", true , 0 );
		servant->_remove_ref();

		// Export IOR in ADAMS processes table
		adams_server.activate ( servant->_this() );

		// Start the schedule and lets run event loop
		server_trapper.start();
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
