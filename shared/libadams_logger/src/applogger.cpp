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

#include <applogger.h>
#include <ltextstream.h>
#include <Qt/qdatetime.h>
#include <Qt/qthread.h>
#include <Qt/qmutex.h>
#include <Qt/qthreadstorage.h>

#include <sys/types.h>
#include <unistd.h>
#include <iostream>

// static QMutex mtx;
static QThreadStorage<LTextStream *> __logstreams;
static net::etech::adams::ssm_logger * ssm_logger = 0;
static QString application_name;

using namespace std;

AppLogger::AppLogger ( const QString & name, const QString & node ) :
	log_level ( LTextStream::LogDebug )
{
	ssm_logger_instance = "/ssm_logger";

	application_name = name;

	ssm_logger = new net::etech::adams::ssm_logger ( "net.etech.adams.ssm_logger", ssm_logger_instance, QDBusConnection::sessionBus() );
	valid_connection = ssm_logger->isValid();

	LTextStream::setSSMLogger(ssm_logger);
	LTextStream::setApplicationName(application_name);

	if ( ! valid_connection ) {
		cerr << "AppLogger: connection to instance " << qPrintable ( ssm_logger_instance ) << " service failed." << endl;
	}
	else
		refreshLogLevel();

	if ( !__logstreams.hasLocalData() ) {
		__logstreams.setLocalData ( new LTextStream ( application_name, new QString, ssm_logger ) );
	}
}


AppLogger::~AppLogger()
{
}

LTextStream * AppLogger::logStream()
{
	if ( !__logstreams.hasLocalData() ) {
		__logstreams.setLocalData ( new LTextStream ( application_name, new QString, ssm_logger ) );
	}
	return __logstreams.localData();
}

void AppLogger::refreshLogLevel()
{
	log_level = ssm_logger->getLogLevel ( application_name );
	LTextStream::setReferenceLogLevel((LTextStream::LogLevels)log_level);
	lout << application_name << " current log level: " << log_level << endl;
}

