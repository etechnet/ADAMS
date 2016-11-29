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

#ifndef ssm_logger_H
#define ssm_logger_H

#include <Qt/qobject.h>
#include <Qt/qdatetime.h>
#include <Qt/qstring.h>
#include <Qt/qhash.h>
#include <Qt/qfile.h>
#include <Qt/qthread.h>
#include <ltextstream.h>
#include <apidb_node_process.h>
#include <apidb_log_logger.h>
#include <unistd.h>

#define SSM_INITIAL_BUFFER_SIZE		( 8 * 1024)		// default reserved buffer size

class _ssm_logger_msg_flusher;

class ssm_logger : public QObject
{
	Q_OBJECT
	Q_CLASSINFO ( "D-Bus Interface", "net.etech.adams.ssm_logger" )

public:
	friend class _ssm_logger_msg_flusher;

	ssm_logger ( const QString & node_name );
	virtual ~ssm_logger();

	inline void registerProcessSimple ( const QString & name, const QString file_name = "" ) {
		log_Config * process_log_conf = new log_Config();

		process_log_conf->processName = name;
		process_log_conf->level = LTextStream::LogDebug;
		process_log_conf->destination = log_ToFile;
		if ( ! file_name.isEmpty() )
			process_log_conf->logFile = file_name;

		process_table.insert ( name, process_log_conf );
	}

	typedef enum {
	        log_ToFile,
	        log_ToDB,
	        log_ToProcess
	} log_Destination;

// 	LTextStream * loggerLog();

public Q_SLOTS:
	void toLogger ( int level, QDateTime time, QString process, int processPid, QString msg );
	void toMonitor ( QDateTime time, QString process, QString msg );
	int getLogLevel ( QString process );

private:

	class log_Config
	{
	public:
		log_Config() :
			outputReady ( false ),
			level ( LTextStream::LogError ) {
			buffer.reserve ( SSM_INITIAL_BUFFER_SIZE );
		}

		QString processName;
		LTextStream::LogLevels level;
		bool outputReady;
		QDate logDate;
		log_Destination destination;
		QString logFile;
		QString logProcess;
		QFile file;
		QString buffer;
	};

	QHash<QString, log_Config *> process_table;
	QString bind_node;
	QString register_name_myself;
	QString log_files_path;
	APIDB_NodeProcess nodeprocess_db;
	APIDB_Log_Logger log_logger_db;
	NodeProcessList m_processes;
	bool m_error_msgs_pending;
	QStringList m_error_msgs;
	_ssm_logger_msg_flusher * msg_flusher;

	bool check_process ( log_Config *& process_entry, const QString & process_name );
	bool open_log_file ( log_Config * process_entry );
	void write_log ( log_Config * process_entry );
	bool load_process_table();
};

class _ssm_logger_msg_flusher : public QThread
{
public:
	_ssm_logger_msg_flusher ( ssm_logger * logger_ptr ) {
		my_logger = logger_ptr;
	}

	void run() {
		my_logger->m_error_msgs << "Internal message flusher started.";

		forever {
			sleep ( 1 );

			if ( ! my_logger->m_error_msgs_pending )
				continue;

			my_logger->m_error_msgs_pending = false;
			foreach ( QString m, my_logger->m_error_msgs ) {
				m += "\n";
				my_logger->toLogger ( LTextStream::LogDebug,
						      QDateTime::currentDateTime(),
						      my_logger->register_name_myself, ::getpid(),
						      m );
			}
			my_logger->m_error_msgs.clear();
		}
	}

private:
	ssm_logger * my_logger;
};



#endif // ssm_logger_H
