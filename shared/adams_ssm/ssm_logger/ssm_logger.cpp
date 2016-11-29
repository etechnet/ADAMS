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


#include <ssm_logger.h>
#include <applogger.h>
#include <configparser.h>
#include <Qt/qstring.h>
#include <Qt/qstringbuilder.h>
#include <Qt/qchar.h>
#include <Qt/qsemaphore.h>
#include <iostream>

static QString SSM_NEWLINE ( "\n" );
static QString SSM_RBL ( "(" );
static QString SSM_RBR ( ")" );
static QString SSM_SBL ( "[" );
static QString SSM_SBR ( "]" );
static QString SSM_SLA ( "/" );

static QSemaphore table_load_semaphore ( 1 );

using namespace std;

ssm_logger::ssm_logger ( const QString & node_name ) :
	bind_node ( node_name ),
	m_error_msgs_pending ( false )
{
	register_name_myself = QString ( "ssm_logger" );
	if ( ! bind_node.isEmpty() )
		register_name_myself += QString ( "_" ) + bind_node;

	ConfigParser parser;
	parser.locateFile();
	parser.addMarkerTagPath ( node_name );

	log_files_path = parser.parQTxtGetValue ( "LogFilesPath", "ADAMS_Globals", ConfigParser::PathValue );

	msg_flusher = new _ssm_logger_msg_flusher ( this );
	msg_flusher->start();

	// load configuration
	load_process_table();

	// Register myself for log (to myself) if not already configured
	if ( process_table.find ( register_name_myself ) == process_table.end() ) {
		registerProcessSimple ( register_name_myself );
	}
}

ssm_logger::~ssm_logger()
{}

int ssm_logger::getLogLevel ( QString process )
{
	log_Config * process_entry = process_table.find ( process ).value();
	if ( process_entry == process_table.end().value() ) {
		// This normally should not happen so set unknown process to LogDebug
		return LTextStream::LogDebug;
	}
	else {
		return process_entry->level;
	}
}

void ssm_logger::toLogger ( int level, QDateTime time, QString process, int processPid, QString msg )
{
	// block if table refresh runnning
	while ( ! table_load_semaphore.tryAcquire ( 1, 500 ) )
		;
	table_load_semaphore.release();

	log_Config * process_entry = process_table.find ( process ).value();

	if ( !check_process ( process_entry, process ) ) {
		lout << "Unable to create output queue for process " << process << endl;
		return;
	}

	if ( process_entry->destination == log_ToDB ) {
		if ( ! log_logger_db.insertLog ( bind_node, process, time, processPid, msg ) ) {
			m_error_msgs << log_logger_db.getErrorMsg();
			m_error_msgs_pending = true;
		}
		return;
	}

	if ( process_entry->destination == log_ToFile || process_entry->destination == log_ToProcess ) {
		switch ( level ) {
			case LTextStream::LogError:
			case LTextStream::LogNotice:
				process_entry->buffer = QString ( "@" ) % time.toString() %
				                        QString ( ", proc: " ) % process %
				                        QString ( " (" ) % QString::number ( processPid ) % SSM_RBR % SSM_NEWLINE +
				                        msg;
				break;
			case LTextStream::LogInfo:
				process_entry->buffer = SSM_SBL % QString::number ( processPid ) % SSM_SBR % QString ( ": " ) %
				                        msg;
				break;
			case LTextStream::LogDebug:
				process_entry->buffer = msg;
				break;
		}
	}

	write_log ( process_entry );
}

void ssm_logger::toMonitor ( QDateTime time, QString process, QString msg )
{
	// block if table refresh runnning
	while ( ! table_load_semaphore.tryAcquire ( 1, 500 ) )
		;
	table_load_semaphore.release();

	log_Config * process_entry = process_table.find ( process ).value();
	if ( process_entry == process_table.end().value() ) {
		return;		// ignored
	}

	if ( ! log_logger_db.insertMon ( bind_node, process, time, 0, msg ) ) {
		m_error_msgs << log_logger_db.getErrorMsg();
		m_error_msgs_pending = true;
	}
}


bool ssm_logger::check_process ( log_Config *& process_entry, const QString & process_name )
{
	// may be something wrong in global configuration...
	if ( process_entry == process_table.end().value() ) {
		registerProcessSimple ( process_name );
		lout << "registering unconfigured " << process_name << endl;
		process_entry = process_table.find ( process_name ).value();
	}

	switch ( process_entry->destination ) {
		case log_ToFile: {
			if ( process_entry->outputReady ) {
				if ( process_entry->logDate != QDate::currentDate() ) {
					process_entry->logDate = QDate::currentDate();
					process_entry->file.close();
					process_entry->outputReady = open_log_file ( process_entry );
					return process_entry->outputReady;
				}
				else
					return true;
			}
			else {
				process_entry->logDate = QDate::currentDate();
				process_entry->outputReady = open_log_file ( process_entry );
				return process_entry->outputReady;
			}
		}
		case log_ToDB:
			break;
		case log_ToProcess:
			;	//TODO missing part
	}

	return true;
}

bool ssm_logger::open_log_file ( log_Config * process_entry )
{
	QString file_name = log_files_path % SSM_SLA %
	                    process_entry->logDate.toString ( "yyyyMMdd" ) %
	                    QString ( "_" ) %
	                    process_entry->processName %
	                    ".log";

	process_entry->file.setFileName ( file_name );
	return process_entry->file.open ( QIODevice::Unbuffered | QIODevice::WriteOnly | QIODevice::Append );
}

void ssm_logger::write_log ( log_Config * process_entry )
{
	switch ( process_entry->destination ) {
		case log_ToFile:
			if ( process_entry->file.write ( qPrintable ( process_entry->buffer ), process_entry->buffer.length() ) < 0 ) {
				m_error_msgs << QString ( "log write error: " ) % process_entry->file.errorString();
				m_error_msgs_pending = true;
			}
			break;
		case log_ToDB:		// unused
			break;
		case log_ToProcess:
			;		//TODO missing part
	}

}

bool ssm_logger::load_process_table()
{
	if ( ! nodeprocess_db.getNodeProcessList ( bind_node, m_processes, true ) ) {
		m_error_msgs << nodeprocess_db.getErrorMsg();
		m_error_msgs_pending = true;
		return false;
	}

	QHash<QString, log_Config *> new_process_table;

	// setup new
	for ( int p = 0; p < m_processes.count(); p++ ) {

		NodeProcessTable & process = m_processes[p];
		log_Config * process_log_conf = new log_Config();

		process_log_conf->processName = process.process_name % "_" % bind_node;
		process_log_conf->level = ( LTextStream::LogLevels ) process.log_level;
		if ( process.log_output == "File" )
			process_log_conf->destination = log_ToFile;
		else if ( process.log_output == "Table" )
			process_log_conf->destination = log_ToDB;
		else if ( process.log_output == "Pipe" ) {
			process_log_conf->destination = log_ToProcess;
			process_log_conf->logProcess = process.log_pipe_cmd;
		}

		new_process_table.insert ( process_log_conf->processName, process_log_conf );

		QString m_log = QString ( "Registered process: " ) % process_log_conf->processName
		                % " with output on: " % process.log_output
		                % QString ( " (" ) % QString::number ( process_log_conf->destination ) % SSM_RBR;
		m_error_msgs << m_log;
	}


	table_load_semaphore.acquire();

	// clear list
	foreach ( log_Config * lc, process_table )
		delete lc;
	process_table.clear();
	process_table = new_process_table;

	table_load_semaphore.release();
	m_error_msgs_pending = true;

	return true;
}

// ---
