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

#include "usertask.h"
#include <configparser.h>
#include <applogger.h>


void UserTask::run()
{
	setTerminationEnabled ( true );
	timeStamp = QDateTime::currentDateTime();
	timeStamp.time().start();
	alive = true;

	ConfigParser parser;
	parser.locateFile();
	m_queues_data_path = parser.parQTxtGetValue ( "JobQueuesPath", "MDM", ConfigParser::PathValue );


	JobList job_list;
	job_list.loadUserQueue ( m_queues_data_path, work.user );

	foreach ( JobQueue * job, job_list ) {

		if ( ! checkJobForScheduling ( job ) )
			continue;

		// start JobProcessor
		QueueTask * q_task = new QueueTask ( job );
		m_task_list.append ( q_task );

		lout << "Starting MDM Server for user queue: " << work.user
		     << " job_id: " << job->data.job_id
		     << " (" << job->data.description << ")"
		     << endl;

		q_task->start();
	}

	// check for expired / running
	bool all_done = true;

	do {
		sleep ( 10 );
		all_done = true;

		foreach ( QueueTask * q_task, m_task_list ) {
			if ( q_task->isRunning() && q_task->expired() ) {
				lout << "Terminating queue task id: " << q_task->taskPayload()->data.job_id
				     << " after " << q_task->elapsed() << " sec."
				     << endl;

				q_task->terminate();	// connected mdm_server will die after timeout
				q_task->wait();
				all_done = false;
			}
			else if ( q_task->isRunning() )
				all_done = false;
		}
	} while ( ! all_done );

	lout << "Job queue for user " << work.user << " processed." << endl;

	foreach ( QueueTask * q_task, m_task_list )
		delete q_task;
	m_task_list.clear();

	alive = false;
}

bool UserTask::checkJobForScheduling ( JobQueue* job )
{

}
