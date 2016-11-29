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

#ifndef WAKEUPHANDLE_H
#define WAKEUPHANDLE_H

#include <unistd.h>
#include <signalevent.h>
#include <signalhandlers.h>
#include <applogger.h>
#include <Qt/qmutex.h>
#include <Qt/qwaitcondition.h>

static QWaitCondition wh_scheduler_cont;
static QMutex wh_mutex;

class sigcont_interceptor : public SignalEvent
{
public:
	sigcont_interceptor() : wakeups ( 0 ) {}

	int handle_signal ( int signum_ );
	int numWakeups() {
		return wakeups;
	}

private:
	int wakeups;
};


static sigcont_interceptor interceptor;

class WakeupHandle
{
public:
	WakeupHandle() {}

	void activateWakeupHandler() {
		SigAction sig_action;
		sig_action.flags ( SA_RESTART );

		sh.install ( SIGCONT, &interceptor, &sig_action );
	}

	void waitForScheduler() {
		lout1 << "Waiting for scheduler." << endl;
		wh_mutex.lock();
		wh_scheduler_cont.wait ( &wh_mutex );
		wh_mutex.unlock();
	}

private:
	SignalHandlers sh;
};


int sigcont_interceptor::handle_signal ( int signum_ )
{
	lout3 << "- Got Wakeup Signal -" << endl;
	++wakeups;
	wh_mutex.lock();
	wh_scheduler_cont.wakeAll();
	wh_mutex.unlock();
	return 0;
}


#endif // WAKEUPHANDLE_H
