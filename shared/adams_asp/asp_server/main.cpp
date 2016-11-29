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
#include <asp_entry_bus.h>
#include "asp_entry_busadaptor.h"
#include "asp_entry_impl.h"
#include "asp_usermanagement_server_impl.h"
#include "asp_server.h"
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <adamsserver.h>
#include <applogger.h>

using namespace std;

static asp_entry_impl * servant = 0;

// asp_server stream interface
AppLogger * AppLogStream;


int main ( int argc, char** argv )
{
	int exit_status = 0;
	AdamsServer adams_server ( argc, argv, "asp_server" );

	AppLogStream = adams_server.logger();

	// DBus init
	asp_entry_bus * aspentry_bus = new asp_entry_bus();

	QDBusConnection connection = QDBusConnection::sessionBus();
	connection.registerObject ( "/asp_entry_bus", aspentry_bus );
	connection.registerService ( "net.etech.adams.asp_entry_bus" );

	// Instance main aspserver class as a separate thread
	//asp_server aspserver;

	try {

		// Create the servant.
		servant = new asp_entry_impl ( adams_server.poa_hierarchy(), "asp_server", true , 0 );

		servant->_remove_ref();

		// Export IOR in ADAMS processes table
		adams_server.activate ( servant->_this() );

		//servant->dummy();  // asp entry
		//servant->test(); //asp_usermgt

		// Start the schedule and lets run event loop
		//aspserver.start();
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
