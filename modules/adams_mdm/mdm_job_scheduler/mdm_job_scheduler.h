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

#ifndef MDM_JOB_SCHEDULER_H
#define MDM_JOB_SCHEDULER_H

#include <Qt/qthread.h>
#include <Qt/qdir.h>
#include <Qt/qdatetime.h>
#include <Qt/qhash.h>

#include <jobqueue.h>
#include <configparser.h>
#include "usertask.h"

class mdm_job_scheduler :  public QThread
{
public:
	explicit mdm_job_scheduler ( QObject* parent = 0 );
	virtual ~mdm_job_scheduler();

	virtual void run();

private:
	typedef QHash<QString, UserTask *> UserTaskMap;

	//----

	ConfigParser cnfparser;
	QString m_queues_data_path;
	QString m_queues_config_filename;
	JobUsersMap m_job_users_map;
	UserTaskMap m_user_tasks;
	UserTaskMap m_user_tasks_expired;

	//----

	void jobscheduler();
	void terminate_expired_user_processors();

	inline void dir_check ( const QString & a_path, bool isFilePath = false ) {
		QString path = a_path;
		if ( path.mid ( 0, 1 ) == "~" ) {				// home relative path
			QString homedir = ::getenv ( "HOME" );
			if ( !homedir.isEmpty() ) {
				path.replace ( 0, 1, homedir );
			}
		}
		if ( isFilePath ) {
			int slashpos = path.lastIndexOf ( '/' );
			if ( slashpos >= 0 )
				path = path.left ( slashpos );
		}

		QDir dir ( path );
		if ( ! dir.exists() )
			dir.mkpath ( path );
	}
};



#endif // MDM_JOB_SCHEDULER_H
