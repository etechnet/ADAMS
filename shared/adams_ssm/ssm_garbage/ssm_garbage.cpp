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

#include "ssm_garbage.h"

#include <applogger.h>
#include <wakeuphandle.h>
#include <adamsserver.h>

WakeupHandle wakeuphandle;

ssm_garbage::ssm_garbage ( QObject* parent ) : QThread ( parent )
{
	wakeuphandle.activateWakeupHandler();
}

ssm_garbage::~ssm_garbage()
{

}

void ssm_garbage::run()
{
	garbage();
}

void ssm_garbage::garbage()
{
	lout << "*** Garbage collector started ***" << endl;
	SET_PROCESS_STATUS ( "Running", "-" );

	while ( true ) {
		lout << "Performing garbage collection..." << endl;

		lout << "Operation completed." << endl;
		wakeuphandle.waitForScheduler();
	}
}
