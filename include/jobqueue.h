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

#ifndef JOBQUEUE_H
#define JOBQUEUE_H

#include <Qt/qobject.h>
#include <Qt/qlist.h>
#include <Qt/qhash.h>

#include <cnfglobals.h>
#include <adams_limits.h>
#include <importexport.h>
#include <queryparams.h>
#include <mdm_job_queues.h>

#include <server_stub_safe_include.h>

#define MAX_MDM_QUEUES			10			// max queues/users
#define MAX_JOB_PER_QUEUE		20			// max job in a queue
#define JOBQUEUE_HEADERTAG		"JQ_QUEUE_HDR"
#define JQ_PARAM_HEADERTAG		"JQ_PARAM_HDR"
#define JQ_RESTR_HEADERTAG		"JQ_RESTR_HDR"

class JobQueue
{
public:
	typedef struct {
		unsigned short new_job;
		unsigned short job_id;
		unsigned short user_id;
		unsigned long data_start;
		unsigned long data_end;
		unsigned short hour;
		short modality;
		unsigned short qnt;
		QueryParams req;
		char dest_fep[JQ_NODO_FEP_SIZE];
		unsigned long last_day;
		unsigned long last_month;
		unsigned short last_quarter;
		char dest_dir[JQ_DIR_DEST_SIZE];
		unsigned short locked;
		unsigned short period;
		unsigned short start_hour;
		unsigned short end_hour;
		char description[JQ_DESC_SIZE];
	} JOB_QUEUE;

	JOB_QUEUE data;

	friend QDataStream &operator<< ( QDataStream &out, const JobQueue & job_queue );
	friend QDataStream &operator>> ( QDataStream &in, JobQueue & job_queue );

	void copyToCorba ( STRUCT_JOB* rec );
	void fillFromCorba ( const STRUCT_JOB * ptr );

protected:
	QDataStream & read ( QDataStream & ds );
	QDataStream & write ( QDataStream & ds ) const;
};

typedef QHash<QString, JOB_CONFIG> JobUsersMapBase;
typedef QList<JobQueue *> JobListBase;

class QFile;

class JobUsersMap : public JobUsersMapBase
{
public:
	JobUsersMap();
	~JobUsersMap();

	bool useQueuesConfigFilename ( const QString & filename );
	bool loadJobUsersMap ();
	bool saveJobUsersMap ();


private:
	QString m_queues_config_filename;
	QFile * m_queues_config;
};

class JobList : public JobListBase
{
public:

	JobList();
	JobList ( const QString & path, const QString & user_name );
	~JobList();

	JobList loadUserQueue ( const QString & path, const QString & user_name );
	bool saveUserQueue ( const QString & path, const QString & user_name );
	bool removeUserQueue ( const QString & path, const QString & user_name );

	void clear();
	void copyToCorba ( JOBSeq * seqptr );
	void fillFromCorba ( const JOBSeq * seqptr );

private:
	inline QString build_queue_filepath ( const QString & queues_data_path, const QString & usr ) {
		return queues_data_path + QString ( "/queue_" ) + usr + ".dat";
	}
};

extern QDataStream &operator<< ( QDataStream &out, const JobQueue & job_queue );
extern QDataStream &operator>> ( QDataStream &in, JobQueue & job_queue );

#endif
