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

#ifndef SSM_SCHEDULER_H
#define SSM_SCHEDULER_H

#include <signalevent.h>
#include <apidb_node_process.h>
#include <Qt/qthread.h>
#include <sys/types.h>

#define MAX_NUM_RESTART		1000			// Max restarts for a process
#define MAX_ARGV_VECTOR_SIZE	128			// Spawned process max argv size
#define SHELL			"/bin/sh"               // Default shell

class GenericSignalHandler;
class CONTSignalHandler;
class STOPSignalHandler;
class CHLDSignalHandler;
class TERMSignalHandler;
class SEGVSignalHandler;
class ssm_scheduler_server_impl;

class ssm_scheduler :  public QThread
{
public:
	friend class GenericSignalHandler;
	friend class CONTSignalHandler;
	friend class STOPSignalHandler;
	friend class CHLDSignalHandler;
	friend class TERMSignalHandler;
	friend class SEGVSignalHandler;
	friend class ssm_scheduler_server_impl;

	explicit ssm_scheduler ( QObject* parent = 0 );
	virtual ~ssm_scheduler();

	virtual void run();
	void processConfigHasChanged();

	typedef enum {
	        AlwaysRunning = 0,
	        Minutes_5 = 1,
	        Minutes_15 = 2,
	        Hourly = 3,
	        ChangeDay = 4,
	        Dayly = 5,
		PrivilegedAlwaysRunning = 6
	} ProcessAwakeTypes;

private:

	typedef struct {
		QDateTime loop;
		QDateTime time_5minutesProcess;
		QDateTime time_15minutesProcess;
		QDateTime time_1hourProcess;
		QDateTime time_ChangeDayProcess;
	} LastAwakeInfo;

	void schedule();
	static void safe_sleep ( int sec );
	void kill_by_name_node ( const QString& process_name, const QString& node_name, int term_mode );
	void check_or_spawn();
	void stop_if_needed();
	bool spawn ( NodeProcessTable & process );
	char ** init_buildenv();
	void init_freeenv ( char ** e );
	void align_to_time_window ( int time_window );
	void init_awake_table();
	bool to_be_waked ( NodeProcessTable & process );
	void update_last_awake();
	void check_process_updates ();
	void get_params_from_ini();

	inline bool process_can_restart ( NodeProcessTable & process ) {
		if ( process.num_restart == MAX_NUM_RESTART ) {
			process.num_restart = 0;
			return false;
		}
		else {
			process.num_restart ++;
			return true;
		}
	}

	inline NodeProcessList & getNodeProcessList() {
		return m_processes;
	}


	pid_t scheduler_pid;
	APIDB_NodeProcess nodeprocess_db;
	NodeProcessList m_processes;
	QDateTime current_time;
	QDateTime actual_time;
	LastAwakeInfo last_awake;
};


// -----------------------
// signal handler classes
// -----------------------

class GenericSignalHandler : public SignalEvent
{
public:
	GenericSignalHandler() {}

	int handle_signal ( int signum_ );
};

class CONTSignalHandler : public SignalEvent
{
public:
	CONTSignalHandler() {}

	int handle_signal ( int signum_ );
};

class STOPSignalHandler : public SignalEvent
{
public:
	STOPSignalHandler() {}

	int handle_signal ( int signum_ );
};

class CHLDSignalHandler : public SignalEvent
{
public:
	CHLDSignalHandler() {}

	int handle_signal ( int signum_ );
};

class TERMSignalHandler : public SignalEvent
{
public:
	TERMSignalHandler() {}

	int handle_signal ( int signum_ );
};


class SEGVSignalHandler : public SignalEvent
{
public:
	SEGVSignalHandler() {}

	int handle_signal ( int signum_ );
};

#endif // SSM_SCHEDULER_H
