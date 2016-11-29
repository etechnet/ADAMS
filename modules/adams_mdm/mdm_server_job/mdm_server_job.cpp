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

#include "mdm_server_job.h"

#include <applogger.h>
#include <wakeuphandle.h>
#include <adamsserver.h>

WakeupHandle wakeuphandle;

mdm_server_job::mdm_server_job ( QObject* parent ) : QThread ( parent )
{
	wakeuphandle.activateWakeupHandler();
}

mdm_server_job::~mdm_server_job()
{

}

void mdm_server_job::run()
{
	serverjob();
}

void mdm_server_job::serverjob()
{
	lout << "*** MDM Server Job started ***" << endl;
	SET_PROCESS_STATUS ( "Running", "-" );

	while ( true ) {
		lout << "Performing serverjob operations..." << endl;

		lout << "Operation completed." << endl;
		wakeuphandle.waitForScheduler();
	}
}
