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

#include "mdm_job_scheduler.h"

#include <applogger.h>
#include <wakeuphandle.h>
#include <adamsserver.h>

WakeupHandle wakeuphandle;

mdm_job_scheduler::mdm_job_scheduler ( QObject* parent ) : QThread ( parent )
{
	wakeuphandle.activateWakeupHandler();

	cnfparser.locateFile();
	cnfparser.addMarkerTagPath ( QString ( "MDM_Job_Scheduler." ) + AdamsServer::getNode() );

	m_queues_data_path = cnfparser.parQTxtGetValue ( "JobQueuesPath", "MDM", ConfigParser::PathValue );
	if ( m_queues_data_path.isEmpty() ) {
		lout << "Undefined job queues storage path." << endl;
		::exit ( 1 );
	}

	dir_check ( m_queues_data_path );

	m_queues_config_filename = cnfparser.parQTxtGetValue ( "JobQueuesDefinitionFile", "MDM", ConfigParser::PathValue );
	if ( m_queues_config_filename.isEmpty() ) {
		lout << "Undefined queues configuration file path." << endl;
		::exit ( 1 );
	}
	else {
		dir_check ( m_queues_config_filename, true );
		if ( ! m_job_users_map.useQueuesConfigFilename ( m_queues_config_filename ) ) {
			lout << "Cannot open queues configuration file." << endl;
			::exit ( 1 );
		}
	}

}

mdm_job_scheduler::~mdm_job_scheduler()
{

}

void mdm_job_scheduler::run()
{
	jobscheduler();
}

void mdm_job_scheduler::jobscheduler()
{
	lout << "*** MDM Job Scheduler started ***" << endl;
	SET_PROCESS_STATUS ( "Checking", "-" );

	while ( true ) {
		SET_PROCESS_STATUS ( "Running", "-" );
		lout << "Performing jobscheduler operations..." << endl;

		// Load queues map
		m_job_users_map.loadJobUsersMap();

		// Prepare map for user processors and starts them
		m_user_tasks_expired.clear();

		foreach ( JOB_CONFIG jc, m_job_users_map ) {
			QString user = jc.user;

			if (! m_user_tasks.contains(user)) {
				UserTask * utask = new UserTask;
				utask->work = jc;
				m_user_tasks.insert(user, utask);
				utask->start();
			}
			else {
				// is alive ?
				if (m_user_tasks.value(user)->alive) {
					// alive and can continue
					if (!m_user_tasks.value(user)->elapsed()) {
						lout << "Job queue for user: " << user << " still alive. Rescheduling at next round." << endl;
						continue;
					}
					else {	// alive and expired
						lout << "Job queue for user: " << user << " has expired. New schedule will start now." << endl;
						mon << "Job queue for user: " << user << " has expired. New schedule will start now." << endl;
						UserTask * expired_task = new UserTask;
						*expired_task = *m_user_tasks.value(user);
						m_user_tasks_expired.insert(user, expired_task );
					}
				}
				// remove old
				delete m_user_tasks.value(user);
				m_user_tasks.remove(user);
				// insert new
				UserTask * utask = new UserTask;
				utask->work = jc;
				m_user_tasks.insert(user, utask);
				utask->start();
			}
		}

		// clean up stale processors
		terminate_expired_user_processors();

		lout << "Operation completed." << endl;
		SET_PROCESS_STATUS ( "Waiting", "-" );
		wakeuphandle.waitForScheduler();
	}
}

void mdm_job_scheduler::terminate_expired_user_processors()
{
	foreach ( UserTask * task, m_user_tasks_expired ) {
		lout << "Terminating expired task for user: " << task->work.user << endl;
		task->terminate();
		sleep(3);
	}
}

// ----