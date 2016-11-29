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

/***************************************************************************
                          timeout.cpp  -  description
                             -------------------
    begin                : Mon Sep 3 2001
    copyright            : (C) 2001 by Piergiorgio Betti
    email                : pbetti@j-linux.it
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/

#include "timeout.h"
// #include <signal.h>
#include <unistd.h>

#include "applogger.h"
#include <Qt/qdatetime.h>

TimeOut::TimeOut()
{
	iProcessKilled = 0;
	iActive = false;
	iTimeOut = 0;
	exitNow = false;
	exitEnforced = false;
	a_thread = 0;
}

TimeOut::~TimeOut()
{
}

void TimeOut::run()
{
	while ( true ) {
		sleep ( iTimeOut );

		if ( exitNow )
			return;

		if ( iActive == false ) {
			lout << "--------->> Timeout in Server WatchDog... Terminating #" << iProcessKilled << endl;

			if (a_thread) {
				lout << "Waiting for running thread termination..." << endl;
				int nsec = 0;
				if (a_thread->isRunning()) {
					while (!a_thread->isFinished()) {
						QThread::sleep(1);
						++nsec;
					}
				}

				lout2 << "timeout pending shutdown for " << nsec << " seconds." << endl;
			}

			orb->shutdown ( 0 );
			if ( exitEnforced ) {

				sleep ( 60 );	// One minute grace period

				if ( !CORBA::is_nil ( orb ) ) {
					try {
						orb->destroy();
					}
					catch ( const CORBA::Exception & ex ) {
						lout	<< "WatchDog orb->destroy() failed: "
						<< ex._name() << endl;
					}
				}

				lout << "--- WATCHDOG --- :" << endl
				     << "Server terminated at " << QDateTime::currentDateTime().toString()
				     << "------------------" << endl;

				::exit ( 0 );
			}
		}
		else
			iActive = false;
	}
}
