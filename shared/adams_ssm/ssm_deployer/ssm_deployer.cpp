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

#include "ssm_deployer.h"

#include <applogger.h>
#include <wakeuphandle.h>
#include <adamsserver.h>

WakeupHandle wakeuphandle;

ssm_deployer::ssm_deployer ( QObject* parent ) : QThread ( parent )
{
	wakeuphandle.activateWakeupHandler();
}

ssm_deployer::~ssm_deployer()
{

}

void ssm_deployer::run()
{
	deployer();
}

void ssm_deployer::deployer()
{
	lout << "*** SSM Deployer started ***" << endl;
	SET_PROCESS_STATUS ( "Running", "-" );

	while ( true ) {
		lout << "Performing deployer operations..." << endl;

		lout << "Operation completed." << endl;
		wakeuphandle.waitForScheduler();
	}
}
