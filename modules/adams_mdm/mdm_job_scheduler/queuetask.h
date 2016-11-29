/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */

#ifndef QUEUETASK_H
#define QUEUETASK_H

#include <Qt/qthread.h>
#include <Qt/qstring.h>
#include <Qt/qdatetime.h>

#include <mdmC.h>
#include <jobqueue.h>

using namespace net::etech;
using namespace net::etech::MDM;

#define	MJS_QUEUE_TASK_EXPIRY_TIME	( 1 * 60 * 60 )		// 1 hour

class QueueTask : public QThread
{
public:
	enum qtStatus {
		Starting,
		Running,
		Success,
		Failure
	};

	QueueTask(JobQueue * myjob);
	~QueueTask();

	int elapsed() {
		return timeStamp.time().elapsed() / 1000;
	}

	bool expired() {
		return elapsed() > MJS_QUEUE_TASK_EXPIRY_TIME;
	}

	inline JobQueue * taskPayload() { return task; }
	inline qtStatus getStatus() { return m_status; }

protected:
	virtual void run();

private:
	JobQueue * task;
	QString m_backend_processor;
	QString m_queues_data_path;
	qtStatus m_status;
	::net::etech::INVOKE_STATUS m_query_progress;
	QDateTime timeStamp;

};

#endif // QUEUETASK_H
