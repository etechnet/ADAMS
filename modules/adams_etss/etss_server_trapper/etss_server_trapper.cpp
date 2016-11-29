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

#include "etss_server_trapper.h"

#include <applogger.h>
#include <wakeuphandle.h>
#include <adamsserver.h>

WakeupHandle wakeuphandle;

etss_server_trapper::etss_server_trapper ( QObject* parent ) : QThread ( parent )
{
	wakeuphandle.activateWakeupHandler();
}

etss_server_trapper::~etss_server_trapper()
{

}

void etss_server_trapper::run()
{
	serverjob();
}

void etss_server_trapper::serverjob()
{
	lout << "*** ETSS Server Trapper Job started ***" << endl;
	SET_PROCESS_STATUS ( "Running", "-" );

	while ( true ) {
		lout << "Performing trapperjob operations..." << endl;

		lout << "Operation completed." << endl;
		wakeuphandle.waitForScheduler();
	}
}
