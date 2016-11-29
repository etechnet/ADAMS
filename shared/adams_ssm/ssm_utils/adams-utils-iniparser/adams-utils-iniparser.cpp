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
#include "ssm_logger_if.h"
#include <unistd.h>
#include <iostream>
#include <applogger.h>
#include <configparser.h>

using namespace std;

void usage();
QString nextArg ( QStringList &, int, char );

char mode = 0;
QString me;
// AppLogger * AppLogStream;

int main ( int argc, char** argv )
{
	QFileInfo me(argv[0]);
	QString section_name;
	QString tag;

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
		else if ( arg == "--value" )
			c = 'v';
		else if ( arg == "--list" )
			c = 'l';
		else if ( arg == "--value-tag" )
			c = 't';

		switch ( c ) {

			case 'v':
			case 'l':
				mode = c;
				section_name = nextArg(args, i, c);
				break;

			case 't':
				tag = nextArg(args, i, 't');
				break;

// 			case 'p':
// 				mode = c;
// 				break;

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

	ConfigParser parser;
	
	if ( !parser.locateFile() ) {
		cerr << "ADAMS .ini file not found." << endl;
		exit(1);
	}

	if (mode == 'l') {
		QStringList list = parser.parQTxtGetSection ( section_name );
		if (list.isEmpty()) {
			cerr << "No value found required list: [" << qPrintable(section_name) << "]" << endl;
			exit(1);
		}
		else {
			foreach(QString s, list)
				cout << qPrintable(s) << endl;
		}
	}
	else if (mode == 'v') {
		if (tag.isEmpty()) {
			cerr << "No tag specified for required value" << endl;
			exit(1);			
		}
		QString val = parser.parQTxtGetValue ( tag, section_name );
		if (val.isEmpty()) {
			cerr << "No value found for required tag: [" << qPrintable(tag) << "] from [" << qPrintable(section_name) << "]" << endl;
			exit(1);
		}
		else {
			cout << qPrintable(val) << endl;
		}
	}
// 	else
// 		lout << msg << endl;

	exit(0);

}


void usage()
{
	cerr << "Usage: adams-utils-iniparser --list | --value <section> [--value_tag] [<tag>]" << endl;
	cerr << "Options:" << endl;
	cerr << "\t-h or --help\t this help" << endl;
	cerr << "\t-l or --list\t extract value list from <section>" << endl;
	cerr << "\t-v or --value\t extract a tagged value from <section>" << endl;
	cerr << "\t-t or --value-tag\t required tag for --value" << endl;
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

