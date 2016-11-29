/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

#include "mdm_server_factory.h"

#include <applogger.h>
#include <wakeuphandle.h>
#include <adamsserver.h>

WakeupHandle wakeuphandle;

mdm_server_factory::mdm_server_factory ( QObject* parent ) : QThread ( parent )
{
	wakeuphandle.activateWakeupHandler();
}

mdm_server_factory::~mdm_server_factory()
{

}

void mdm_server_factory::run()
{
	server_factory();
}

void mdm_server_factory::server_factory()
{
	SET_PROCESS_STATUS ( "Running", "-" );
	mon << "* mdm_server_factory started *" << endl;

	while ( true ) {
		lout << "Performing server_factory operations..." << endl;

		lout << "Operation completed." << endl;
		wakeuphandle.waitForScheduler();
	}
}
