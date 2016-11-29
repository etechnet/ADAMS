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
#include <ssm_logger.h>
#include "ssm_loggeradaptor.h"
#include "ssm_logger_server_impl.h"
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <adamsserver.h>
#include <applogger.h>

#include <iostream>
using namespace std;

static ssm_logger_server_impl * servant = 0;

// This is for linked classes
AppLogger * AppLogStream;


int main ( int argc, char** argv )
{
	int exit_status = 0;
	AdamsServer::setIsLoggerServer();
	AdamsServer adams_server ( argc, argv, "ssm_logger" );

	// we probably need to run as a daemon
	if ( adams_server.runAsDaemon() )
		adams_server.daemonize();

	// DBus init
	ssm_logger * logger = new ssm_logger ( adams_server.getNode() );
	new Ssm_loggerAdaptor ( logger );

	QDBusConnection connection = QDBusConnection::sessionBus();
	connection.registerObject ( "/ssm_logger", logger );
	connection.registerService ( "net.etech.adams.ssm_logger" );

	// applogger (me) connect
	AppLogStream = new AppLogger ( adams_server.getServerQualifiedName(), adams_server.getNode() );
	// flush any startup message from AdamsServer initialization
	foreach ( QString m, adams_server.getEarlyMessages() )
		lout << m << endl;


	try {
		// Create the servant.
		servant = new ssm_logger_server_impl ( adams_server.poa_hierarchy(), "ssm_logger_server", true , 0 );
		servant->_remove_ref();

		// Export IOR in ADAMS processes table
		adams_server.activate ( servant->_this() );

		// Tells the world about us
		mon << "*** Logger started ***" << endl;
		SET_PROCESS_STATUS ( "Running", "-" );

		// Lets run event loop
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
	        << " terminating at " << QDateTime::currentDateTime().toString()
	        << endl << "----------" << endl;

	return exit_status;

}
