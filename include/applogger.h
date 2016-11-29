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


#ifndef APPLOGGER_H
#define APPLOGGER_H

#include <Qt/qobject.h>
#include <Qt/qstring.h>

#include "ssm_logger_if.h"

#include <ltextstream.h>

// ssm_logger Logger destination

#define lout	(*(AppLogger::logStream())) << tologger << logerror
#define lout1	(*(AppLogger::logStream())) << tologger << lognotice
#define lout2	(*(AppLogger::logStream())) << tologger << loginfo
#define lout3	(*(AppLogger::logStream())) << tologger << logdebug

// ssm_logger Monitor destination

#define mon	(*(AppLogger::logStream())) << tomonitor


/**
 * This class implements a stream that interface application to ssm_logger daemon to produce log data.
@author Piergiorgio Betti
@short Log stream
*/

class AppLogger
{
public:
	AppLogger( const QString & name, const QString & node = "" );
	~AppLogger();

	static LTextStream * logStream();
	void refreshLogLevel();
	inline bool isValid() { return valid_connection; }

private:
	int application_pid;
	int log_level;
	bool valid_connection;
	QString ssm_logger_instance;
};

#endif
