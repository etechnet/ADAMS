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

#include <adamsserver.h>
#include <commandlineoptions.h>
#include <configparser.h>
#include "ssm_scheduler_bus_if.h"
#include <tao/SystemException.h>
#include <tao/Basic_Types.h>
#include <tao/ORB_Constants.h>
#include <tao/Object.h>
#include <p_orb.h>
#include <qtcoreresource_loader.h>
#include <apidb_ior.h>
#include <signalhandlers.h>
#include <Qt/qdatetime.h>
#include <Qt/qtextstream.h>
#include <Qt/qfile.h>

#include <iostream>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

#define TAO_ARGS_RESERVED_SPACE			50

static bool already_initialized = false;
static bool isLoggerServer = false;
static bool isSchedulerServer = false;
static QString node;
static QString exe_name;
static QString exe_path;
static QString basiconf;
static QString port;
static QString ipaddress;
static QString server_name;
static QString qualified_server_name;
static QString tao_svc_conf;
static AppLogger * logger_stream = 0;
static CORBA::ORB_var orbv;
static PoaHierarchy * poahierarchy;
static QTextStream * tmp_log_stream = 0;
static QCoreApplication * qcoreapplication;
static TAO::QtCoreResource_Loader * qt_coreresources;
static QString logger_pid;
static QString dbus_session;
static bool run_as_a_daemon = false;
static QString install_dir;
static bool conf_changed = false;
static bool ini_changed = false;
static net::etech::adams::ssm_scheduler_bus * scheduler_if = 0;
static ASSchedulerReceiver scheduler_receiver;
static bool delayed_termination = false;
static APIDB_Status * g_apidb_status = 0;
ASGenericSignalHandler as_generic_signal_handler;
ASTerminationSignalHandler as_termination_signal_handler;

static const char *_SSdsc[] = {
	"Hangup",			// SIGHUP
	"User Interrupt",		// SIGINT
	"Quit",				// SIGQUIT
	"Illegal Instruction",		// SIGILL
	"Trace/Breakpoint Trap",	// SIGTRAP
	"Abort",			// SIGABRT
	"Bus Error",			// SIGBUS
	"Floating Exception",		// SIGFPE
	"Kill",				// SIGKILL
	"USR 1",			// SIGUSR1
	"Segmentation Violation",	// SIGSEGV
	"USR 2",			// SIGUSR2
	"Broken Pipe",			// SIGPIPE
	"Alarm",			// SIGALRM
	"Software Termination",		// SIGTERM
	"Stack Fault",			// SIGSTKFLT
	"Child Status",			// SIGCHLD
	"Continue",			// SIGCONT
	"Stopped",			// SIGSTOP
	"User Stopped",			// SIGTSTP
	"Stopped TTY Input",		// SIGTTIN
	"Stopped TTY Output",		// SIGTTOU
	"Urgent Socket Condition",	// SIGURG
	"CPU Time Limit Exeeded",	// SIGXCPU
	"File Size Limit Exceeded",	// SIGXFSZ
	"Virtual Timer Expired",	// SIGVTALRM
	"Profiling Timer Expired",	// SIGPROF
	"Window Size Change",		// SIGWINCH
	"Pollable Event",		// SIGIO
	"Power Failure",		// SIGPWR
	"Bad System Call"		// SIGSYS
};

using namespace std;

AdamsServer::AdamsServer ( int & argc, char **& argv, const QString & server_base_name )
{
	if ( already_initialized )
		return;

	// reset all signals dispositions
	SigAction * sig_action_default = new SigAction ( SIG_DFL, 0, SA_RESTART );
	for ( int s = 1; s <= NSIG; s++ ) {
		if ( s == SIGTERM )
			continue;
		sig_action_default->register_action ( s, 0 );
	}
	delete sig_action_default;


	QString m;

	// Leave behind a welcome msg
	early_messages << "-----------------------------------------------------------";
	m = "--- ADAMS Server instance " % server_base_name % " starting...";
	early_messages << m;
	early_messages << "-----------------------------------------------------------";

	// get standard ADAMS arguments from command line

	string a_node = "";
	string a_port = "";
	string a_ipaddress = "";
	string a_dbus_session = "";
	string a_logger_pid = "";

	CommandLineOptions cmdopt ( argc, argv );

	cmdopt.addOptionString ( 'n', "", "--node", &a_node, "Processing node name." );
	cmdopt.addOptionString ( 'p', "", "--port", &a_port, "CORBA listen port number." );
	cmdopt.addOptionString ( 'i', "-ip", "--ipaddress", &a_ipaddress, "Processing node IP address." );
	cmdopt.addOptionString ( 'b', "", "--dbus", &a_dbus_session, "D-BUS session address." );
	cmdopt.addOptionString ( 'l', "", "--logger-pid", &a_logger_pid, "SSM Logger pid." );
	cmdopt.addOptionSet ( 'd', "", "--daemon", &run_as_a_daemon, "Daemonize server on startup." );

	bool eval_opts = cmdopt.get_cmdline_options();

	if ( a_node == "" ) {
		early_messages << "ADAMS arguments warning: --node option (Processing node name) must be specified.";
	}

	node = QString::fromStdString ( a_node );
	port = QString::fromStdString ( a_port );
	ipaddress = QString::fromStdString ( a_ipaddress );
	logger_pid = QString::fromStdString ( a_logger_pid );
	dbus_session = QString::fromStdString ( a_dbus_session );

	setExeName ( argv[0] );
	char * aid = ::getenv ( "ADAMS_INSTALL_DIR" );
	if ( aid ) install_dir = aid;
	server_name = server_base_name;
	qualified_server_name = server_name;

	if ( dbus_session.isEmpty() ) {
		dbus_session = ::getenv ( "DBUS_SESSION_BUS_ADDRESS" );
	}

// 	if ( dbus_session.isEmpty() ) {
// 		early_messages << "No D-BUS session specified.";
// 	}

	if ( node.isEmpty() && ! ipaddress.isEmpty() )
		node = ipaddress;

	if ( node.isEmpty() ) {
		// unset node name is an almost fatal error.
		// Try to recover using hostname...
		char hostname_buffer[256];

		if ( gethostname ( hostname_buffer, 255 ) < 0 ) {
			m = server_name + ": unable to get valid hostname. SEVERE ERROR.";
			early_messages << m;
		}
		else
			node = hostname_buffer;
	}
	else
		qualified_server_name += QString ( "_" ) + node;

	if ( !port.isEmpty() && ( port.toInt() < 1024 || port.toInt() > 65535 ) ) {
		QString m = QString ( "Invalid port number: " ) + port;
		port.clear();
	}

	// Update status to db

	g_apidb_status = new APIDB_Status;
	g_apidb_status->setProcessStatus ( node, server_base_name, "Starting", "-" );

	// retrieve informations from adams.ini

	ConfigParser parser;
	QString corba_protocol;
	QString corba_svc_conf;

	// setup a defalt path for the server
	parser.addMarkerTagPath ( node );

	if ( !parser.locateFile() ) {
		m = QString ( "Configuration file: " ) + parser.getConfigFileName() + QString ( " not found." );
		early_messages << m;
	}
	else {
		corba_protocol = parser.parQTxtGetValue ( "CorbaProtocol", "ADAMS_Globals" );
		if ( corba_protocol.isEmpty() ) {
			// default to iiop
			corba_protocol = "iiop";
			early_messages << "CorbaProtocol unspecified, defaulting to 'iiop'";
		}

		corba_svc_conf = parser.parQTxtGetValue ( "CorbaSvcConfFile", "ADAMS_Globals" );
		if ( corba_svc_conf.isEmpty() ) {
			early_messages << "CorbaSvcConfFile unspecified, running with TAO defaults";
		}
		else {
			if ( corba_svc_conf == "none" )
				corba_svc_conf.clear();
			else {
				QString svc_path = parser.locateUserFile ( corba_svc_conf );
				if ( !svc_path.isEmpty() )
					corba_svc_conf = svc_path;
			}
		}
	}

	// initialize QT application

	qcoreapplication = new QCoreApplication ( argc, argv );
	qt_coreresources = new TAO::QtCoreResource_Loader ( qcoreapplication );


	// creates argument expansions for TAO
	char ** targv = new char * [argc + TAO_ARGS_RESERVED_SPACE];
	int targc = argc;

	for ( int a = 0; a < argc; a++ )
		targv[a] = strdup ( argv[a] );

	targv[targc] = 0;

	// we need to add -ORBListenEndpoints and -ORBSvcConf at least

	QString endpoint_node = ( node.isEmpty() ) ? "localhost" : node;
	QString listen_endpoint = corba_protocol + QString ( "://" ) + endpoint_node;
	if ( !port.isEmpty() )
		listen_endpoint += QString ( ":" ) + port;

	targv[targc++] = strdup ( "-ORBListenEndpoints" );
	targv[targc++] = strdup ( qPrintable ( listen_endpoint ) );
	targv[targc] = 0;

	m = QString ( "CORBA Endpoint: " ) + listen_endpoint;
	early_messages << m;

	if ( !corba_svc_conf.isEmpty() ) {
		targv[targc++] = strdup ( "-ORBSvcConf" );
		targv[targc++] = strdup ( qPrintable ( corba_svc_conf ) );
	}

	try {
		// Initialise the ORB.
		orbv = CORBA::ORB_init ( targc, targv );
		// Create a POA hierarchy on the stack
		poahierarchy = new PoaHierarchy ( orbv.in(), qPrintable ( qualified_server_name ) );
	}
	catch ( const CORBA::Exception &e ) {
		m = QString ( "Caught CORBA exception:" ) + e._info().c_str();
		early_messages << m;
	} // catch a new exception from the poa utility
	catch ( const corbautil::PoaUtilityException & poe ) {
		m = QString ( "Unexpected Poa Utility exception" ) + poe.msg;
		early_messages << m;
	}

	for ( int a = 0; a < targc; a++ )
		free ( targv[a] );
	delete [] targv;

	// Since we are in a very early stage of adams process startup we need a place
	// to log error that can be reach by the logger subsequwntly IF it is not already
	// available and IF we are ssm_logger or ssm_scheduler.
	// In any other case not reaching the logger is a fatal error

	if ( !isLoggerServer ) {
		logger_stream = new AppLogger ( qualified_server_name, node );
		if ( !logger_stream->isValid() ) {
			// logger unreachable...
			early_messages << "FATAL: Connection to ssm_logger failed. Aborting.";
			if ( !isSchedulerServer ) {
				// scheduler and logger do things their own way
				QFile tmp_log_file ( build_temporary_log_filename() );
				if ( tmp_log_file.open ( QFile::WriteOnly | QFile::Truncate ) ) {
					tmp_log_stream = new QTextStream ( &tmp_log_file );
					// flush queued messages to boot log
					foreach ( m, early_messages )
						*tmp_log_stream << m << endl;

					tmp_log_file.close();
					delete tmp_log_stream;
				}

				::exit ( 1 );
			}
		}
		else {
			// logger ok, flush queued messages
			if ( !early_messages.isEmpty() )
				foreach ( m, early_messages )
					lout << m << endl;
		}
	}

	// setup signals handling

	SignalHandlers signal_handlers;

	signal_handlers.install ( SIGHUP, &as_termination_signal_handler );
	signal_handlers.install ( SIGQUIT, &as_termination_signal_handler );
	// 	signal_handlers.install (SIGKILL, &as_termination_signal_handler);
	signal_handlers.install ( SIGTERM, &as_termination_signal_handler );
	signal_handlers.install ( SIGINT, &as_termination_signal_handler );

	signal_handlers.install ( SIGILL, &as_generic_signal_handler );
	signal_handlers.install ( SIGFPE, &as_generic_signal_handler );
	signal_handlers.install ( SIGBUS, &as_generic_signal_handler );
	// 	signal_handlers.install (SIGSEGV, &as_generic_signal_handler);
	signal_handlers.install ( SIGABRT, &as_generic_signal_handler );

	//almost done

	if ( ( isLoggerServer || isSchedulerServer ) && dbus_session.isEmpty() ) {
		cerr << "Unknown D-BUS session." << endl;
// 		::exit ( 1 );
	}

	if ( !isLoggerServer && !isSchedulerServer )
		early_messages.clear();

	if ( !isSchedulerServer ) {	// not being the scheduler itself, we need an interface to it
		scheduler_if = new net::etech::adams::ssm_scheduler_bus ( "net.etech.adams.ssm_scheduler_bus", "/ssm_scheduler", QDBusConnection::sessionBus() );
		if ( scheduler_if->isValid() ) {
			QObject::connect ( scheduler_if, SIGNAL ( configurationChanged() ), &scheduler_receiver, SLOT ( configurationHasChanged() ) );
			QObject::connect ( scheduler_if, SIGNAL ( iniChanged() ), &scheduler_receiver, SLOT ( iniHasChanged() ) );
		}
		else {
			lout << "*** ssm_scheduler_bus connection failed." << endl;
		}
	}

	already_initialized = true;
}


AdamsServer::~AdamsServer()
{
	if (g_apidb_status)
		g_apidb_status->close_connection();
}

QString AdamsServer::build_temporary_log_filename()
{
	QString logname = qualified_server_name + ".log.boot";
	return logname;
}


bool AdamsServer::activate ( CORBA::Object_ptr obj_ptr )
{
	// Export IOR in ADAMS processes table
	CORBA::Object_var obj = obj_ptr;
	CORBA::String_var ior = orbv->object_to_string ( obj.in() );

	APIDB_IorTable apidb_ior;
	bool dbret = apidb_ior.putIor ( server_name, node, ior.in() );
	//lout3 << "dbret =" << dbret << endl;

	lout << qualified_server_name << " started" << endl;
	lout3 << "corba ior is:" << endl << ior.in() << endl;
	lout1 << "Running on: " << node << endl;

	// Activate POA managers and go into the main event loop
	cout << qPrintable ( qualified_server_name ) << " entering run loop..." << endl;

	try {
		poahierarchy->core_functionality()->activate();

	}
	catch ( const CORBA::Exception &e ) {
// 		e._tao_print_exception ( "Caught CORBA exception:" );
		lout << "Caught CORBA exception:" << e._info().c_str() << endl;
		return false;
	} // catch a new exception from the poa utility
	catch ( const corbautil::PoaUtilityException & poe ) {
		lout << "Unexpected Poa Utility exception" << poe.msg << endl;
		return false;
	}
}

void AdamsServer::daemonize()
{
	pid_t pid, sid;

	// already a daemon
	if ( getppid() == 1 ) return;

	// close status db
	g_apidb_status->close_connection();

	// Fork off the parent process
	pid = fork();
	if ( pid < 0 ) {
		cerr << "damonize fork failed." << endl;
		::exit ( 1 );
	}
	// If we got a good PID, then we can exit the parent process.
	if ( pid > 0 ) {
		::exit ( 0 );
	}
	// At this point we are executing as the child process
	cerr << "Server daemonized." << endl;

	// Create a new SID for the child process
	sid = setsid();
	if ( sid < 0 ) {
		cerr << "damonize setsid failed." << endl;
		::exit ( 1 );
	}

	if ( ! write_pid_file ( getpid() ) ) {
		cerr << "daemonize: cannot write pid file." << endl;
		::exit ( 1 );
	}
	// Redirect standard files to /dev/null
	freopen ( "/dev/null", "r", stdin );
	freopen ( "/dev/null", "w", stdout );
	freopen ( "/dev/null", "w", stderr );
}

bool AdamsServer::write_pid_file ( int pid )
{
	QString idir;
	if ( install_dir.isEmpty() )
		idir = exe_path + "/..";
	else
		idir = install_dir;

	QString pid_fname = idir + QString ( "/tmp/.adams-" ) + server_name + QString ( "-pid-" ) + node;
	QFile pid_file ( pid_fname );
	if ( pid_file.open ( QFile::WriteOnly | QFile::Truncate ) ) {

		QTextStream pid_strem ( &pid_file );
		pid_strem << pid << endl;

		pid_file.close();
		return true;
	}

	return false;
}

int ASGenericSignalHandler::handle_signal ( int signum_ )
{
	QString sig_desc = ( _SSdsc[signum_ - 1] ) ? _SSdsc[signum_ - 1] : "unknown to me";

	switch ( signum_ ) {
		case SIGILL:
		case SIGFPE:
		case SIGBUS:
		case SIGSEGV:
		case SIGABRT:
			lout << "Catch System Error Signal " << signum_ << " (" << sig_desc << ")." << endl;
// 			orbv->shutdown ( 0 );
			qcoreapplication->exit ( 1 );
	}

	return 0;
}

int ASTerminationSignalHandler::handle_signal ( int signum_ )
{
	QString sig_desc = ( _SSdsc[signum_ - 1] ) ? _SSdsc[signum_ - 1] : "unknown to me";

	switch ( signum_ ) {
		case SIGHUP:
		case SIGQUIT:
		case SIGKILL:
		case SIGTERM:
		case SIGINT:
			lout << "Catch Software Termination Signal " << signum_ << " (" << sig_desc << ")." << endl;
			m_graceful_quit = 1;
			emit terminationRequestReceived();
// 			orbv->shutdown ( 0 );
			if ( ! delayed_termination )
				qcoreapplication->exit ( 0 );
	}

	return 0;
}

void ASSchedulerReceiver::configurationHasChanged()
{
	conf_changed = true;
}

void ASSchedulerReceiver::iniHasChanged()
{
	ini_changed = true;
	ConfigParser::invalidateCache();
}

// --- world interfaces

bool AdamsServer::configurationChangePending()
{
	return conf_changed;
}

void AdamsServer::resetConfigurationChangeStatus()
{
	conf_changed = false;
}

bool AdamsServer::iniChangePending()
{
	return ini_changed;
}

void AdamsServer::resetIniChangeStatus()
{
	ini_changed = false;
}


sig_atomic_t AdamsServer::terminationRequestPending()
{
	return as_termination_signal_handler.graceful_quit_pending();
}

void AdamsServer::delayTermination()
{
	delayed_termination = true;
}

AppLogger * AdamsServer::logger()
{
	return logger_stream;
}

CORBA::ORB_var & AdamsServer::orb()
{
	return orbv;
}

PoaHierarchy * AdamsServer::poa_hierarchy()
{
	return poahierarchy;
}

QCoreApplication & AdamsServer::qtapp()
{
	return *qcoreapplication;
}

int AdamsServer::exec()
{
	return qcoreapplication->exec();
}

bool AdamsServer::runAsDaemon()
{
	return run_as_a_daemon;
}

void AdamsServer::setNode ( const QString & name )
{
	node = name;
}

const QString & AdamsServer::getNode()
{
	return node;
}

QString AdamsServer::getDbusSession()
{
	return dbus_session;
}

QString AdamsServer::getLoggerPid()
{
	return logger_pid;
}

void AdamsServer::setExeName ( const QString & exe_namee )
{
	exe_name = exe_namee;
	int slashpos = exe_name.lastIndexOf ( '/' );
	if ( slashpos > 0 )
		exe_path = exe_name.left ( slashpos );
	else
		exe_path = "./";
}

QString AdamsServer::exeName()
{
	return exe_name;
}

QString AdamsServer::getInstallPath()
{
	return install_dir;
}

QString AdamsServer::exePath()
{
	return exe_path;
}

void AdamsServer::setBasicConfigFile ( const QString & path )
{
	basiconf = path;
}

QString AdamsServer::basicConfigFile()
{
	return basiconf;
}

QString AdamsServer::getServerBaseName()
{
	return server_name;
}

QString AdamsServer::getServerQualifiedName()
{
	return qualified_server_name;
}

void AdamsServer::setTAOSvcConf ( const QString & svc_path )
{
	tao_svc_conf = svc_path;
}

QString AdamsServer::getTAOSvcConf()
{
	return tao_svc_conf;
}


void AdamsServer::setIsLoggerServer()
{
	isLoggerServer = true;
}

void AdamsServer::setIsSchedulerServer()
{
	isSchedulerServer = true;
}

APIDB_Status & AdamsServer::tStatus()
{
	return *g_apidb_status;
}

// ------------

