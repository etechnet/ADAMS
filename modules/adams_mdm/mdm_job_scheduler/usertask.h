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

#ifndef USERTASK_H
#define USERTASK_H

#include <Qt/qthread.h>
#include <Qt/qdatetime.h>
#include <Qt/qlist.h>
#include <jobqueue.h>
#include "queuetask.h"

#define	MJS_USER_TASK_EXPIRY_TIME	( 2 * 60 * 60 )		// 2 hours

class UserTask : public QThread
{
public:
	UserTask() : alive ( false ) {}
	~UserTask() {}

	int elapsed() {
		return timeStamp.time().elapsed() / 1000;
	}

	bool expired() {
		return elapsed() > MJS_USER_TASK_EXPIRY_TIME;
	}

	UserTask & operator= ( const UserTask & in ) {
		if ( this == &in )
			return *this;

		this->work = in.work;
		this->alive = in.alive;
		this->timeStamp = in.timeStamp;

		return *this;
	}


	JOB_CONFIG work;
	volatile bool alive;
	QDateTime timeStamp;

protected:
	virtual void run();

private:
	typedef QList<QueueTask *> TaskList;

	bool checkJobForScheduling(JobQueue * job);

	QString m_queues_data_path;
	TaskList m_task_list;
};

#endif // USERTASK_H
