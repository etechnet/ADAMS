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

#include "ssm_scheduler.h"

#include <applogger.h>
#include <signalhandlers.h>
#include <adamsserver.h>
#include <pstream.h>
#include <configparser.h>

#include <Qt/qregexp.h>
#include <Qt/qstringbuilder.h>
#include <Qt/qstringlist.h>

#include <unistd.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <sys/select.h>
#include <signal.h>

#define	INTERSIGNAL_GRACE_PERIOD	5		// Grace period seconds
#define INITIAL_AWAKE_WINDOW_GAP 	5 		// Init phase awake window gap minutes
#define AWAKE_LOOP_WINDOW_GAP 		1 		// Awake loop window gap

typedef enum {
        ONE_DAY = 86400,
        ONE_HOUR = 3600,
        FIFTEEN_MINUTES = 900,
        FIVE_MINUTES = 300,
        ONE_MINUTE = 60
} WakeIntervalSeconds;

static sig_atomic_t got_cont = 0;
static sig_atomic_t got_term_signal = 0;		// TERMination signal flag
static ssm_scheduler * ssm_scheduler_instance = 0;
static volatile bool config_changed = false;

// signal handlers
static GenericSignalHandler generic_sh;
static CONTSignalHandler cont_sh;
static STOPSignalHandler stop_sh;
static CHLDSignalHandler chld_sh;
static TERMSignalHandler term_sh;
static SEGVSignalHandler segv_sh;


ssm_scheduler::ssm_scheduler ( QObject* parent )
	: QThread ( parent )
{
	scheduler_pid = ::getpid();
	ssm_scheduler_instance = this;

	get_params_from_ini();
}

ssm_scheduler::~ssm_scheduler()
{

}

void ssm_scheduler::get_params_from_ini()
{
	// Configuration values from ini
	ConfigParser parser;
	parser.locateFile();
}


void ssm_scheduler::run()
{
	schedule();
}

void ssm_scheduler::schedule()
{

	lout << "*** Scheduler started ***" << endl;
	mon << "*** Scheduler started ***" << endl;
	SET_PROCESS_STATUS ( "Running", "-" );

	// Setup signal handling. Disable all signals first.

	SigAction * sig_action_ignore = new SigAction ( SIG_IGN, 0, SA_RESTART );
	for ( int s = 1; s <= NSIG; s++ ) {
		if ( s == SIGTERM )
			continue;
		sig_action_ignore->register_action ( s, 0 );
	}
	delete sig_action_ignore;

	// now dispose new actions

	SignalHandlers signal_handlers;
	SigAction sig_action;
	sig_action.flags ( SA_RESTART );

	signal_handlers.install ( SIGALRM, &generic_sh );
	signal_handlers.install ( SIGHUP, &generic_sh );
	signal_handlers.install ( SIGINT, &generic_sh );
	signal_handlers.install ( SIGCHLD, &chld_sh );
	signal_handlers.install ( SIGTERM, &term_sh );
	signal_handlers.install ( SIGWINCH, &generic_sh );
	signal_handlers.install ( SIGUSR1, &generic_sh );
	signal_handlers.install ( SIGUSR2, &generic_sh );
	signal_handlers.install ( SIGSTOP, &stop_sh, &sig_action );
	signal_handlers.install ( SIGTSTP, &stop_sh, &sig_action );
	signal_handlers.install ( SIGCONT, &cont_sh, &sig_action );
	signal_handlers.install ( SIGSEGV, &segv_sh, &sig_action );

	// load process list
	m_processes = nodeprocess_db.getNodeProcessList(AdamsServer::getNode());
	lout << "Initial process configuration loaded. " << m_processes.count() << " applications to manage." << endl;

	// Check for zombies ... i.e. try to kill all processes
	// registered into our process list.
	// Since we cannot rely on pid number during startup (to avoid killing extraneous process)
	// we do all scanning using a subshell and searching for process name and
	// node name in (mandatory) adams option --node.

	for ( int round = 0; round < 2; round++ ) {
		for ( int p = 0; p < m_processes.count(); p++ ) {

			NodeProcessTable & process = m_processes[p];
			if ( process.schedule_ignore )	// out of control
				continue;

			if ( process.pid != 0 ) {

				if ( round == 0 )
					lout << "Killing zombie child process " << process.process_name << endl;
				if ( round == 0 )
					lout << "TERM signal round..." << endl;
				if ( round == 1 )
					lout << "KILL signal round..." << endl;

				switch ( round ) {
					case 0: 	// Send TERM signal
						kill_by_name_node ( process.process_name, AdamsServer::getNode(), SIGTERM );
						break;
					case 1: 	// Send KILL signal and collect status */
						kill_by_name_node ( process.process_name, AdamsServer::getNode(), SIGKILL );
						process.pid = 0;
						process.num_restart = 0;
						lout3 << "process reset process=" << process.process_name << " pid=" << process.pid << endl;;
						break;
				}
			}
		}

		safe_sleep ( 2 );	// Wait for a clean shutdown
	}

	// synchronize table
	nodeprocess_db.updateNodeProcessTable ( m_processes );

	// Begun process fire-up
	check_or_spawn();

	// Let processes stabilize
	safe_sleep ( 5 );

	// wait for first 5 minutes
	align_to_time_window ( INITIAL_AWAKE_WINDOW_GAP );

	// Initalize process awake table
	init_awake_table();

	// Enter awake/spawn loop
	while ( true ) {

		while ( waitpid ( 0, NULL, WNOHANG ) > 0 );		// Some dead child ?

		for ( int p = 0; p < m_processes.count(); p++ ) {

			NodeProcessTable & process = m_processes[p];
			if ( process.schedule_ignore )	// out of control
				continue;

			if ( process.active_flag == 1 && to_be_waked ( process ) ) {
				if ( process.pid != 0 ) {
					lout2 << "Send wakeup signal to " << process.process_name << " pid: " << process.pid << endl;

					if ( ::kill ( process.pid, SIGCONT ) < 0 ) {
						lout << "*** " << process.process_name << " (pid " << process.pid << ") wakeup failure." << endl;
						mon << "*** " << process.process_name << " (pid " << process.pid << ") wakeup failure." << endl;
					}
				}
			}
		}

		update_last_awake();

		// Take account for process configuration changes
		if ( config_changed ) {
			check_process_updates();
			config_changed = false;
		}

		check_or_spawn();

		// Lets processes stabilize
		safe_sleep ( 5 );

		// wait till next minute
		align_to_time_window ( AWAKE_LOOP_WINDOW_GAP );

		// On wait error retry at next 5 minutes
		if ( last_awake.loop.addSecs ( AWAKE_LOOP_WINDOW_GAP * 60 ) == actual_time ) {
			last_awake.loop = actual_time;
		}
		else {
			lout << "Warning: System time has been changed." << endl;
			mon << "Warning: System time has been changed." << endl;

			check_or_spawn();
			safe_sleep ( 5 );
			align_to_time_window ( AWAKE_LOOP_WINDOW_GAP );
			init_awake_table();
		}
	}
}

void ssm_scheduler::update_last_awake()
{
	if ( actual_time == last_awake.time_5minutesProcess.addSecs ( FIVE_MINUTES ) ) {
		lout3 << "Updated 5 mins. processes" << endl;
		last_awake.time_5minutesProcess = actual_time;
	}

	if ( actual_time == last_awake.time_15minutesProcess.addSecs ( FIFTEEN_MINUTES ) ) {
		lout3 << "Updated 15 mins. processes" << endl;
		last_awake.time_15minutesProcess = actual_time;
	}

	if ( actual_time == last_awake.time_1hourProcess.addSecs ( ONE_HOUR ) ) {
		lout3 << "Updated hourly processes" << endl;
		last_awake.time_1hourProcess = actual_time;
	}

	if ( actual_time == last_awake.time_ChangeDayProcess.addSecs ( ONE_DAY ) ) {
		lout3 << "Updated hourly processes" << endl;
		last_awake.time_ChangeDayProcess = actual_time;
	}
}



bool ssm_scheduler::to_be_waked ( NodeProcessTable& process )
{
	switch ( process.process_type ) {

		case Minutes_5:
			lout3 << "Proc. 5 minutes, name=" << process.process_name
			      << " type=" << process.process_type
			      << " actual_time=" << actual_time.time().toString()
			      << " test=" << last_awake.time_5minutesProcess.addSecs ( FIVE_MINUTES ).time().toString()
			      << endl;

			return ( actual_time == last_awake.time_5minutesProcess.addSecs ( FIVE_MINUTES ) );

		case Minutes_15:
			lout3 << "Proc. 15 minutes, name=" << process.process_name
			      << " type=" << process.process_type
			      << " actual_time=" << actual_time.time().toString()
			      << " test=" << last_awake.time_15minutesProcess.addSecs ( FIFTEEN_MINUTES ).time().toString()
			      << endl;

			return ( actual_time == last_awake.time_15minutesProcess.addSecs ( FIFTEEN_MINUTES ) );

		case Hourly:
			lout3 << "Proc. hourly, name=" << process.process_name
			      << " type=" << process.process_type
			      << " actual_time=" << actual_time.time().toString()
			      << " test=" << last_awake.time_1hourProcess.addSecs ( ONE_HOUR ).time().toString()
			      << endl;

			return ( actual_time == last_awake.time_1hourProcess.addSecs ( ONE_HOUR ) );

		case ChangeDay:
			lout3 << "Proc. change day, name=" << process.process_name
			      << " type=" << process.process_type
			      << " actual_time=" << actual_time.time().toString()
			      << " test=" << last_awake.time_ChangeDayProcess.addSecs ( ONE_DAY ).time().toString()
			      << endl;

			return ( actual_time == last_awake.time_ChangeDayProcess.addSecs ( ONE_DAY ) );

		case Dayly:	// awake at fixed time
			lout3 << "Proc. dayly, name=" << process.process_name
			      << " type=" << process.process_type
			      << " actual_time=" << actual_time.time().toString()
			      << " test=" << process.wake_time.toString()
			      << endl;

			return ( actual_time.time() == process.wake_time );
	}

	return false;
}


void ssm_scheduler::init_awake_table()
{
	int seconds_to_midnight = actual_time.toTime_t() % ONE_DAY ;
	int change_day_time = actual_time.toTime_t() - seconds_to_midnight;

	actual_time = current_time;
	last_awake.loop = actual_time;

	last_awake.time_5minutesProcess = actual_time.addSecs ( - FIVE_MINUTES );
	lout3 << "init: time_5minutesProcess is " << last_awake.time_5minutesProcess.toString() << endl;

	last_awake.time_15minutesProcess.setTime_t ( actual_time.toTime_t() - ( actual_time.toTime_t() % FIFTEEN_MINUTES ) );
	lout3 << "init: time_15minutesProcess is " << last_awake.time_15minutesProcess.toString() << endl;

	last_awake.time_1hourProcess.setTime_t ( actual_time.toTime_t() - ( actual_time.toTime_t() % ONE_HOUR ) );
	lout3 << "init: time_1hourProcess is " << last_awake.time_1hourProcess.toString() << endl;

	last_awake.time_ChangeDayProcess.setTime_t ( change_day_time );
	lout3 << "init: time_ChangeDayProcess is " << last_awake.time_ChangeDayProcess.toString() << endl;
}


void ssm_scheduler::align_to_time_window ( int time_window )
{
	current_time = QDateTime::currentDateTime();

	int time_diff_seconds = current_time.toTime_t() % ( time_window * ONE_MINUTE );
	int wait_seconds = ( ONE_MINUTE * time_window ) - time_diff_seconds - 2;

	lout3 << "Align time start at " << current_time.time().toString() << " " << ( wait_seconds + 2 ) << " to wait..." << endl;

	safe_sleep ( wait_seconds );

	do {
		current_time = QDateTime::currentDateTime();
	}
	while ( current_time.time().second() != 0 );

	actual_time = current_time;

	lout3 << "Align time exit at " << current_time.time().toString() << endl;
}


void ssm_scheduler::kill_by_name_node ( const QString& process_name, const QString& node_name, int term_mode )
{
	QString ps_process = "ps" % QString ( " -C " ) % process_name % QString ( " -o" ) % " pid,args";

	SignalHandlers signal_handlers;
	SigAction sig_action_ignore ( SIG_IGN );
	signal_handlers.remove ( SIGCHLD, 0, &sig_action_ignore );

	redi::ipstream in ( qPrintable ( ps_process ) );

	QString ps_out;
	std::string std_ps_line;
	QStringList result = ps_out.split ( '\n' );

	while ( std::getline ( in, std_ps_line ) ) {
		ps_out = QString::fromStdString ( std_ps_line );
		result << ps_out;
	}

	QString filter_string = "--node " + node_name;

	foreach ( const QString & str, result ) {
		if ( str.contains ( filter_string ) ) {

			QString pid = str.simplified().split ( " " ).at ( 0 );

			if ( ! pid.isEmpty() ) {
				lout3 << "kill to pid: " << pid << endl;
				::kill ( - ( ( pid_t ) pid.toUInt() ), term_mode );
			}
		}
	}
	signal_handlers.install ( SIGCHLD, &chld_sh );
}

void ssm_scheduler::safe_sleep ( int sec )
{
	struct timeval tv;

	tv.tv_sec = sec;
	tv.tv_usec = 0;

	while ( select ( 0, NULL, NULL, NULL, &tv ) < 0 && errno == EINTR )
		;
}


void ssm_scheduler::check_or_spawn()
{
	// Now check for process to stop
	stop_if_needed();

	// ... start or restart new processes

	for ( int idx = 0; idx < m_processes.count(); idx++ ) {

		NodeProcessTable & process = m_processes[idx];
		if ( process.schedule_ignore )		// out of control
			continue;
		if ( process.start_cmd.isEmpty() )	// void entry
			continue;

		if ( process.active_flag ) {
			if ( process.pid != 0 ) {			// valid process in running
				lout3 << "check_or_spawn (info): process \"" << process.process_name << "\" has pid " << process.pid << endl;
				// Reset num_restart
				current_time = QDateTime::currentDateTime();

				if ( current_time > process.last_restart.addDays ( 1 ) )
					process.num_restart = 1;
			}
			else {						// un-running process need schedule
				lout3 << "check_or_spawn (info): process \"" << process.process_name << "\" not running." << endl;
				mon << "check_or_spawn (info): process \"" << process.process_name << "\" not running." << endl;

				// Check if the process can be restarted
				current_time = QDateTime::currentDateTime();

				if ( process_can_restart ( process ) ) {
					lout1 << "Spawning process " << process.process_name << endl;

					if ( ! spawn ( process ) ) {	// let's go...
						lout << "*** UNABLE TO SPAWN PROCESS " << process.process_name << endl;
						mon << "*** UNABLE TO SPAWN PROCESS " << process.process_name << endl;
					}

					process.last_restart = current_time;
				}
				else {					/* Faulty process: will be disabled */
					lout << "Faulty process " << process.process_name << " will be disabled..." << endl;
					mon << "Faulty process " << process.process_name << " will be disabled..." << endl;

					process.active_flag = 0;
				}
			}
		}
	}

	// synchronize table
	nodeprocess_db.updateNodeProcessTable ( m_processes );
}

// Fork and execute.

bool ssm_scheduler::spawn ( NodeProcessTable& process )
{
	char *args[MAX_ARGV_VECTOR_SIZE];		// Argv array
	pid_t pid;					// spawned pid
	SigSet nmask, omask;				// For blocking SIGCHLD

	// Finalize command line
	QString final_start_cmd = AdamsServer::getInstallPath() % "/bin/"
	                          % process.start_cmd
	                          % " --node "
	                          % AdamsServer::getNode()
				  % " --port "
				  % QString::number( process.assigned_port );
	lout3 << "final start_cmd is: " << final_start_cmd << endl;

	// Split up command line arguments
	QString q_cmd_line = final_start_cmd.simplified();
	QStringList q_arg_list = q_cmd_line.split ( " ", QString::SkipEmptyParts );

	// builds argv
	for ( int i = 0; i < ( MAX_ARGV_VECTOR_SIZE - 1 ) && i < q_arg_list.count(); i++ ) {
		args[i + 1] = qstrdup ( qPrintable ( q_arg_list[i] ) );
		args[i + 2] = 0;
// 		lout3 << args[i + 1] << endl;
	}

	args[0] = args[1];
	int s_try = 3;

	QFile tmp_log_file ( "fork.error.log" );
	QTextStream tmp_log_stream;
	if ( tmp_log_file.open ( QFile::WriteOnly | QFile::Truncate ) ) {
		tmp_log_stream.setDevice ( &tmp_log_file );
	}

	while ( true ) {
		// Block sigchild while forking. Raw method here
		nmask.add ( SIGCHLD );
		sigprocmask ( SIG_BLOCK, nmask, omask );

		if ( ( pid = fork() ) == 0 ) {

			// Oh well... i'm a little new kid

			sigprocmask ( SIG_SETMASK, omask, 0 );
			setsid();

			// Reset all the signals, set up environment
			SigAction * sig_action_default = new SigAction ( SIG_DFL, 0, SA_RESTART );
			for ( int s = 1; s <= NSIG; s++ )
				sig_action_default->register_action ( s, 0 );
			delete sig_action_default;

			environ = init_buildenv ();

			tmp_log_stream << "--- Child migration ---" << endl;
			tmp_log_stream << "0 - [" << args[0] << "]" << endl;
			int i = 1;
			while ( args[i] ) {
				tmp_log_stream << i << " - [" << args[i] << "]" << endl;
				++i;
			}

			// Execute prog. In case of ENOEXEC try again
			// as a shell script.
			execvp ( args[1], args + 1 );
			tmp_log_stream << "ERRNO=" << errno << endl;
			if ( errno == ENOEXEC ) {
				args[1] = qstrdup ( SHELL );
				args[2] = qstrdup ( "-c" );
				QString exec_cmd = "exec ";
				exec_cmd += final_start_cmd;
				args[3] = qstrdup ( qPrintable ( exec_cmd ) );
				args[4] = 0;
				execvp ( args[1], args + 1 );
			}
			tmp_log_stream << "** Cannot execute \"" << process.process_name << "\"" << endl;
			::_exit ( 1 );
// 			pthread_exit(0);
		}

		process.pid = pid;
		sigprocmask ( SIG_SETMASK, omask, NULL );

		if ( pid == -1 ) {
			lout << "Cannot fork, retry..." << endl;
			safe_sleep ( 5 );
			if ( --s_try > 0 )
				continue;
			else
				break;;
		}

		break;
	}
	tmp_log_file.close();
	tmp_log_file.remove();

	for ( int i = 1; i < ( MAX_ARGV_VECTOR_SIZE - 1 ) && i < q_arg_list.count(); i++ )
		delete [] args[i];

	if ( pid > 0 ) {
		lout1 << "Started process " << process.process_name << " (pid " << pid
		      << ") using \"" <<  process.start_cmd << "\"" << endl;
		return true;
	}

	return false;
}

char ** ssm_scheduler::init_buildenv ()
{
	char **e;
	int n, i;

	for ( n = 0; environ[n]; n++ )
		;

	e = new char * [n];

	for ( n = 0; environ[n]; n++ )
		e[n] = qstrdup ( environ[n] );

	e[n++] = 0;

	return e;

}

void ssm_scheduler::init_freeenv ( char** e )
{
	int n;

	for ( n = 0; e[n]; n++ )
		delete [] e[n];
	delete [] e;
}


void ssm_scheduler::stop_if_needed()
{
// 	int		f;			/* Counter */
	bool foundOne = false;		// No killing no sleep

	// Loop through the list of children, and see if they need to
	// be killed.

	lout1 << "Checking for children to kill..." << endl;

	for ( int round = 0; round < 2; round++ ) {
		for ( int idx = 0; idx < m_processes.count(); idx++ ) {

			NodeProcessTable & process = m_processes[idx];
			if ( process.schedule_ignore )	// out of control
				continue;

			if ( ( process.active_flag == 0 || got_term_signal ) && process.pid != 0 ) {

				lout2 << "Killing " << process.process_name << endl;

				switch ( round ) {
					case 0:		// Send TERM signal
						lout2 << "Sending TERM." << endl;
						::kill ( - ( process.pid ), SIGTERM );
						foundOne = true;
						break;
					case 1:		// Send KILL signal
						lout2 << "Sending TERM." << endl;
						::kill ( - ( process.pid ), SIGKILL );
						break;
				}
			}
		}

		// See if we have to wait 5 seconds (of grace period)
		if ( foundOne && round == 0 ) {
			// Yup, but check every second if we still have children.
			for ( int f = 0; f < INTERSIGNAL_GRACE_PERIOD; f++ ) {
				int idx = 0;
				for ( idx = 0; idx < m_processes.count(); idx++ ) {
					NodeProcessTable & process = m_processes[idx];
					if ( process.schedule_ignore )	// out of control
						continue;
					if ( process.active_flag == 0 && process.pid != 0 )
						break;
				}
				if ( idx >= m_processes.count() ) {
					// No running children, skip SIGKILL
					round = 1;
					foundOne = false; /* Skip the sleep below. */
					break;
				}

				safe_sleep ( 1 );
			}
		}
	}

	// Now give all processes the chance to die and collect exit statuses.
	if ( foundOne )
		safe_sleep ( 1 );

	for ( int idx = 0; idx < m_processes.count(); idx++ ) {
		NodeProcessTable & process = m_processes[idx];
		if ( process.schedule_ignore )	// out of control
			continue;

		if ( process.active_flag == 0 && process.pid != 0 ) {
			lout << "Pid " << process.pid << "[id " << process.process_name << "] seems to hang." << endl;
			/* Abandon the process... */
			process.pid = 0;
			process.num_restart = 0;
		}
	}

	if ( got_term_signal )
		return;		// no more to do

	// Check for zombies ...

	for ( int idx = 0; idx < m_processes.count(); idx++ ) {
		NodeProcessTable & process = m_processes[idx];
		if ( process.schedule_ignore )	// out of control
			continue;

		if ( process.active_flag == 1 && process.pid != 0 ) {

			lout3 << "Checking \"" << process.process_name << "\" for run." << endl;

			if ( ::kill ( process.pid, 0 ) < 0 ) {

				lout << "Invalid child process \"" << process.process_name << "\" pid " << process.pid << endl;

				lout1 << "Sending TERM" << endl;
				::kill ( - ( process.pid ), SIGTERM );
				safe_sleep ( 1 );

				lout1 << "Sending KILL" << endl;
				::kill ( - ( process.pid ), SIGKILL );
				process.pid = 0;
				process.num_restart = 0;
			}
		}
	}
}

void ssm_scheduler::processConfigHasChanged()
{
	config_changed = true;
}

void ssm_scheduler::check_process_updates ()
{
	NodeProcessList upd_list = nodeprocess_db.getNodeProcessList (AdamsServer::getNode());

	// mark deleted processes in old table
	for ( int idx = 0; idx < m_processes.count(); idx++ ) {

		NodeProcessTable & process = m_processes[idx];
		if ( process.schedule_ignore )	// out of control
			continue;

		bool p_deleted = true;

		foreach ( NodeProcessTable p, upd_list ) {
			if ( p.process_id == process.process_id ) {
				p_deleted = false;
				// will kill old process if it has critical changes
				if ( p.process_name != process.process_name
				  || p.start_cmd != process.start_cmd
				)
					process.active_flag = 0;
				break;
			}
		}

		if ( p_deleted )
			process.active_flag = 0;	// to be killed
	}

	// Kills old
	stop_if_needed();

	// work on new list
	m_processes = upd_list;
}


// -----------------------
// signal handler classes
// -----------------------

int GenericSignalHandler::handle_signal ( int signum_ )
{
	lout << "Catch unhandled signal:  " << signum_ << endl;
	return 0;
}

int CONTSignalHandler::handle_signal ( int signum_ )
{
	lout3 << "Catch CONT signal:  " << endl;
	++got_cont;
	return 0;
}

int STOPSignalHandler::handle_signal ( int signum_ )
{
	lout3 << "Catch STOP signal." << endl;
	int	saved_errno = errno;

	got_cont = 0;
	while ( !got_cont ) ::pause();
	got_cont = 0;

	errno = saved_errno;

	return 0;
}

int CHLDSignalHandler::handle_signal ( int signum_ )
{
	lout3 << "Catch CHLD signal." << endl;
	int saved_errno = errno;
	int st;
	int pid;

	// Find out which process(es) this was (were)
	while ( ( pid = ::waitpid ( -1, &st, WNOHANG ) ) != 0 ) {

		if ( errno == ECHILD ) break;

		int idx;
		for ( idx = 0; idx < ssm_scheduler_instance->getNodeProcessList().count(); idx++ ) {

			NodeProcessTable & process = ssm_scheduler_instance->getNodeProcessList() [idx];
			if ( process.schedule_ignore )	// out of control
				continue;

			if ( process.pid == pid ) {
				lout << "Process " << process.process_name << " PID " << pid << " has died" << endl;

				process.pid = 0;
				// check if this process has been killed by me...
				if ( process.active_flag == 0 )
					process.num_restart = 0;

				break;
			}
		}
		if ( idx >= ssm_scheduler_instance->getNodeProcessList().count() )
			lout << "CHLDSignalHandler: unknown child " << pid << " exited." << endl;
	}

	errno = saved_errno;
	return 0;
}

int TERMSignalHandler::handle_signal ( int signum_ )
{
	lout3 << "Catch TERM signal." << endl;
	int	saved_errno = errno;

	SignalHandlers signal_handlers;
	SigAction sig_action_ignore ( SIG_IGN );
	signal_handlers.remove ( SIGCHLD, 0, &sig_action_ignore );

	got_term_signal = 1;
	ssm_scheduler_instance->stop_if_needed();

	errno = saved_errno;

	return 0;
}


int SEGVSignalHandler::handle_signal ( int signum_ )
{
	int saved_errno = errno;

	lout << "PANIC: segmentation violation! sleeping for 30 seconds." << endl;
	ssm_scheduler::safe_sleep ( 30 );

	errno = saved_errno;

	return 0;
}

