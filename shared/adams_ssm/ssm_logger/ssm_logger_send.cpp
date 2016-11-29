

#include <Qt/qcoreapplication.h>
#include <Qt/qstringlist.h>
#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <Qt/qfileinfo.h>
#include <Qt/qprocess.h>
#include <Qt/qregexp.h>
#include "ssm_logger_if.h"
#include <unistd.h>
#include <iostream>
#include <applogger.h>
#include <configparser.h>
#include <signalhandlers.h>
#include <ssmC.h>
#include <corbaclient.h>
#include <apidb_status.h>

using namespace std;
using namespace net::etech::SSM;

void usage();
QString nextArg ( QStringList &, int, char );

char mode = 0;
QString me;
AppLogger * AppLogStream;

class SIGCONTHandler : public SignalEvent
{
public:
	SIGCONTHandler() : m_graceful_quit ( 0 ) {}

	int handle_signal ( int signum_ );

	inline sig_atomic_t graceful_quit() {
		return m_graceful_quit;
	}
	inline void resetState() {
		m_graceful_quit = 0;
	}


private:
	sig_atomic_t m_graceful_quit;
};

inline int SIGCONTHandler::handle_signal ( int signum_ )
{
	if ( signum_ == SIGCONT ) {
		m_graceful_quit = 1;
		cerr << "received SIGCONT." << endl;
		return 0;
	}
	else
		cerr << "received signal: " << signum_ << endl;
	return -1;
}


int main ( int argc, char** argv )
{
	QFileInfo me ( argv[0] );
	QString node;

	if ( argc < 2 ) {
		usage();
		exit ( 1 );
	}

	// setup signal handling
	SigSet bs;
	bs.add ( SIGHUP );

	SigAction bact;
// 	bact.mask (bs);

	SignalHandlers sh;
	SIGCONTHandler sch;

	sh.install ( SIGCONT, &sch );
	sh.install ( SIGTERM, &sch );

	QStringList args;

	for ( int i = 1; i < argc; ++i ) {
		args.append ( QString::fromLocal8Bit ( argv[i] ) );
	}

	int i = 0;
	while ( i < args.count() ) {

		if ( !args.at ( i ).startsWith ( QLatin1Char ( '-' ) ) ) {
			++i;
			continue;
		}

		QString arg = args.takeAt ( i );

		char c = '\0';

		if ( arg.length() == 2 )
			c = arg.at ( 1 ).toLatin1();
		else if ( arg == "--help" )
			c = 'h';

		switch ( c ) {

			case 'n':
				node = nextArg ( args, i, 'n' );
				break;

			case 'l':
			case 'm':
			case 'a':
			case 'p':
			case 'c':
				mode = c;
				break;

			case '?':
			case 'h':
				usage();
				exit ( 0 );
				break;

			default:
				printf ( "unknown option: '%s'\n", qPrintable ( arg ) );
				usage();
				exit ( 1 );

		}

	}

	net::etech::adams::ssm_logger * ssm_logger = new net::etech::adams::ssm_logger ( "net.etech.adams.ssm_logger", "/ssm_logger",
	                QDBusConnection::sessionBus() );

	if ( !ssm_logger->isValid() ) {
		cout << "Failed to connect to ssm_logger service." << endl;
		::exit ( 1 );
	}

	if ( node.isEmpty() ) {
		// unset node name is an almost fatal error.
		// Try to recover using hostname...
		char hostname_buffer[256];

		if ( gethostname ( hostname_buffer, 255 ) < 0 ) {
			cout << "Unable to get valid hostname" << endl;
		}
		else
			node = hostname_buffer;
	}

	AppLogStream = new AppLogger ( "ssm_logger_send", node );


	__pid_t pid = ::getpid();
	QDateTime now = QDateTime::currentDateTime();

	QString msg;
	if ( !args.isEmpty() )
		msg = args.takeFirst();


	if ( mode == 'l' ) {
		ssm_logger->toLogger ( LTextStream::LogError, now, me.baseName(), pid, msg );
		ssm_logger->toLogger ( LTextStream::LogNotice, now, me.baseName(), pid, msg );
		ssm_logger->toLogger ( LTextStream::LogInfo, now, me.baseName(), pid, msg );
		ssm_logger->toLogger ( LTextStream::LogDebug, now, me.baseName(), pid, msg );
	}
	else if ( mode == 'm' )
		ssm_logger->toMonitor ( now, me.baseName(), msg );
	else if ( mode == 'p' ) {
		cout << "pinging..." << endl;
		int cnt = 0;
		while ( true ) {
			lout3 << "ping: " << cnt++ << endl;
			::sleep ( 1 );
		}
	}
	else if ( mode == 'c' ) {

		CorbaClient<ssm_logger_server> server;
		if ( !server.connect ( "ssm_logger", node ) ) {
			cerr << "server connection failed." << endl;
			::exit ( 1 );
		}

		server->get_logs ( "fake" );

	}
	else
		lout << msg << endl;

	exit ( 0 );
}


void usage()
{
	cout << "Usage: ssm_logger_send [options] message" << endl;
	cout << "Options:" << endl;
	cout << "\t-l  send message to logger" << endl;
	cout << "\t-m  send message to monitor" << endl;
	cout << "\t-a  send message to logger using applogger" << endl;
	cout << "\t-c  get logs via corba call" << endl;
	cout << "\t-p  ping logger with a message every 1 sec." << endl;
	cout << "\t-n  node name" << endl;
	cout << endl << "At least -l or -m should be specified." << endl;
}

QString nextArg ( QStringList &args, int i, char opt )
{

	QString arg = args.value ( i );

	if ( arg.isEmpty() ) {
		printf ( "-%c needs at least one argument\n", opt );
		exit ( 1 );
	}

	return args.takeAt ( i );
}

