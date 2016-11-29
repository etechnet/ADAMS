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

#ifndef ADAMSSERVER_H
#define ADAMSSERVER_H

#include <Qt/qstring.h>
#include <Qt/qstringlist.h>
#include <Qt/qcoreapplication.h>
#include <Qt/qobject.h>
#include <tao/ORB.h>
#include <poahierarchy.h>
#include <signalevent.h>
#include <applogger.h>
#include <apidb_status.h>


#define SET_PROCESS_STATUS( status, msg )	{ AdamsServer::tStatus().setProcessStatus(AdamsServer::getNode(), \
						  AdamsServer::getServerBaseName(), status, msg ); }



/** This class implement a generic signal handler for ADAMS servers */

class ASGenericSignalHandler : public SignalEvent
{
public:
	ASGenericSignalHandler() {}

	int handle_signal (int signum_);
};

/** This class implement a generic signal handler for ADAMS servers */

class ASTerminationSignalHandler : public QObject, public SignalEvent
{
	Q_OBJECT
public:
	ASTerminationSignalHandler() : m_graceful_quit (0) {}

	int handle_signal (int signum_);

	inline sig_atomic_t graceful_quit_pending() { return m_graceful_quit; }
	inline void resetState() { m_graceful_quit = 0; }

Q_SIGNALS:
	void terminationRequestReceived();

private:
	sig_atomic_t m_graceful_quit;
};

class ASSchedulerReceiver: public QObject
{
	Q_OBJECT
public:
	ASSchedulerReceiver() : QObject() {}
	virtual ~ASSchedulerReceiver() {}

public Q_SLOTS:
	void configurationHasChanged();
	void iniHasChanged();
};


/** This class define shared values for adams environment at runtime storing them in static area.
  * It also provides methods for evaluate command line parameter needed by ADAMS servers
  *@short Environ init and share
  *@author Piergiorgio Betti
  */

class AdamsServer
{
public:
	AdamsServer ( int & argc, char **& argv, const QString & server_base_name );
	~AdamsServer();

	void initialize(int & argc, char **& argv, const QString & server_base_name);

	/** Set executable name */
	static void setExeName ( const QString & exename );
	/** Set ntmbasic configuration file path */
	static void setBasicConfigFile ( const QString & path );
	/** Set running node (hostname) name */
	static void setNode ( const QString & name );
	/** Return running node name */
	static const QString & getNode();
	/** Return current executable name */
	static QString exeName();
	/** Return current executable path */
	static QString exePath();
// 	/** Return ntmbasic configuratione file name */
	static QString basicConfigFile();

	static QString getServerBaseName();

	static QString getServerQualifiedName();

	static void setTAOSvcConf(const QString & svc_path);

	static QString getTAOSvcConf();

	static QString getDbusSession();

	static QString getLoggerPid();

	static QString getInstallPath();

	static sig_atomic_t terminationRequestPending();

	static void delayTermination();

	static bool configurationChangePending();

	static void resetConfigurationChangeStatus();

	static bool iniChangePending();

	static void resetIniChangeStatus();

	bool runAsDaemon();

	static void setIsLoggerServer();
	static void setIsSchedulerServer();

	static AppLogger * logger();
	static CORBA::ORB_var & orb();
	static PoaHierarchy * poa_hierarchy();
	static QCoreApplication & qtapp();
	static APIDB_Status & tStatus();

	bool activate( CORBA::Object_ptr obj_ptr );
	int exec();
	inline QStringList & getEarlyMessages() { return early_messages; }
	void daemonize();

private:
	void usage();
	QString build_temporary_log_filename();
	bool write_pid_file(int pid);

	QStringList early_messages;
	QString temporary_log_filename;
};

#endif

