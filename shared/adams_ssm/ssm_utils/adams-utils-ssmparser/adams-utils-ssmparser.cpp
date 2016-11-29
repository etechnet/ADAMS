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


#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qdatetime.h>
#include <Qt/qfileinfo.h>
#include <QtSql/QSqlRecord>
#include "ssm_logger_if.h"
#include <unistd.h>
#include <iostream>
#include <applogger.h>
#include <configparser.h>
#include <apidb_node.h>
#include <apidb_process.h>
#include <apidb_node_process.h>

using namespace std;

void usage();
QString nextArg ( QStringList &, int, char );

char mode = 0;
QString me;
// AppLogger * AppLogStream;

int main ( int argc, char** argv )
{
	QFileInfo me ( argv[0] );
	QString process_name;
	QString tag;
	QString node;
	QString table_name;

	if ( argc < 2 ) {
		usage();
		exit ( 1 );
	}

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
		else if ( arg == "--process" )
			c = 'p';
		else if ( arg == "--value_tag" )
			c = 'v';
		else if ( arg == "--node" )
			c = 'n';
		else if ( arg == "--table" )
			c = 't';

		switch ( c ) {

			case 'p':
				mode = c;
				process_name = nextArg ( args, i, c );
				break;

			case 'v':
				tag = nextArg ( args, i, 'v' );
				break;

			case 'n':
				node = nextArg ( args, i, 'n' );
				break;

			case 't':
				table_name = nextArg ( args, i, 't' );
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


// 	AppLogStream = new AppLogger("ssm_logger_send", node);


	__pid_t pid = ::getpid();
	QDateTime now = QDateTime::currentDateTime();

// 	QString msg;
// 	if (!args.isEmpty())
// 		msg = args.takeFirst();

// 	ConfigParser parser;
//
// 	if ( !parser.locateFile() ) {
// 		cerr << "ADAMS .ini file not found." << endl;
// 		exit(1);
// 	}

	if ( table_name.isEmpty() ) {
		cerr << "ERROR: You must specify a table name." << endl;
		exit ( 1 );
	}

	if ( node.isEmpty() ) {
		cerr << "ERROR: You must specify a node name." << endl;
		exit ( 1 );
	}

	if ( process_name.isEmpty() ) {
		cerr << "ERROR: You must specify a process name." << endl;
		exit ( 1 );
	}

	if ( tag.isEmpty() ) {
		cerr << "ERROR: You must specify a column." << endl;
		exit ( 1 );
	}

	APIDB_NodeTable api_NodeTable;
	NodeTable * node_table = api_NodeTable.getNode ( node );
	if ( !node_table ) {
		cerr << "ERROR: unable to get node table." << endl;
		exit ( 1 );
	}

	int node_id = node_table->node_id;

	if ( table_name == "t_process" ) {

		APIDB_ProcessTable api_ProcessTable;

		api_ProcessTable.openDB ( nameDB, loginDB, pswdDB );

		QString query_string = "SELECT * FROM t_process where process_name = '" +
		                       process_name + "'";

		QSqlQuery query ( query_string, api_ProcessTable.getDb() );

		int fieldNo = query.record().indexOf ( tag );
		if ( fieldNo < 0 ) {
			cerr << "ERROR: unable to get column by tag_value." << endl;
			exit ( 1 );
		}

		while ( query.next() ) {
			QString output = query.value ( fieldNo ).toString();
			cout << qPrintable ( output ) << endl;
		}

		exit ( 0 );

	} else if ( table_name == "t_node_process" ) {

		APIDB_NodeProcess api_NodeProcess;
		api_NodeProcess.open_connection();

		QString query_string = "SELECT * FROM t_node_process WHERE process_name = '" +
		                       process_name + "' AND node_id = '" +
		                       QString::number ( node_id ) + "'";

		if ( ! api_NodeProcess.getDb().isOpen() ) {
			cerr << "ERROR: database is not open." << endl;
			exit ( 1 );
		}

		QSqlQuery query ( query_string, api_NodeProcess.getDb() );

		int fieldNo = query.record().indexOf ( tag );
		if ( fieldNo < 0 ) {
			cerr << "ERROR: unable to get column by tag_value." << endl;
			exit ( 1 );
		}

		while ( query.next() ) {
			QString output = query.value ( fieldNo ).toString();
			cout << qPrintable ( output ) << endl;
		}

		exit ( 0 );


	} else {
		cerr << "ERROR: unsupported table." << endl;
		exit ( 1 );
	}


	exit ( 0 );

}


void usage()
{
	cerr << "Usage: adams-utils-iniparser --process <process_name> --value_tag <column_name>" << endl;
	cerr << "Options:" << endl;
	cerr << "\t-h or --help\t this help" << endl;
	cerr << "\t-p or --process\t requested process configuration" << endl;
	cerr << "\t-v or --value_tag\t requested column" << endl;
	cerr << "\t-n or --node\t requested node name" << endl;
	cerr << "\t-v or --table\t SSM table name" << endl;
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

