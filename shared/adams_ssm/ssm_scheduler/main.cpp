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
#include <ssm_scheduler_bus.h>
#include "ssm_scheduler_busadaptor.h"
#include "ssm_scheduler_server_impl.h"
#include "ssm_scheduler.h"
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <adamsserver.h>
#include <applogger.h>
#include <namedsharedpointer.h>

using namespace std;

static ssm_scheduler_server_impl * servant = 0;

// ssm_logger stream interface
AppLogger * AppLogStream;


int main ( int argc, char** argv )
{
	int exit_status = 0;
	AdamsServer::setIsSchedulerServer();
	AdamsServer adams_server ( argc, argv, "ssm_scheduler" );

	AppLogStream = adams_server.logger();

	// we probably need to run as a daemon
	if ( adams_server.runAsDaemon() )
		adams_server.daemonize();

	// DBus init
	ssm_scheduler_bus * scheduler_bus = new ssm_scheduler_bus();
	new Ssm_scheduler_busAdaptor ( scheduler_bus );

	QDBusConnection connection = QDBusConnection::sessionBus();
	connection.registerObject ( "/ssm_scheduler_bus", scheduler_bus );
	connection.registerService ( "net.etech.adams.ssm_scheduler_bus" );

	// Instance main scheduler class as a separate thread
	ssm_scheduler scheduler;

	// Store pointers for class share
	NamedSharedPointer<ssm_scheduler_bus>::store("ssm_scheduler_bus", scheduler_bus);
	NamedSharedPointer<ssm_scheduler>::store("ssm_scheduler", &scheduler);

	try {

		// Create the servant.
		servant = new ssm_scheduler_server_impl ( adams_server.poa_hierarchy(), "ssm_scheduler_server", true , 0 );
		servant->_remove_ref();

		// Export IOR in ADAMS processes table
		adams_server.activate ( servant->_this() );

		// Start the schedule and lets run event loop
		scheduler.start();
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
