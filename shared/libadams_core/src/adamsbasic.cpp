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

#include <adamsbasic.h>
#include <scriptbasic/report.h>
#include <scriptbasic/errcodes.h>
#include <applogger.h>

// #include <qmessagebox.h>
#include <qmutex.h>

/* For the sake of god, WHY i put this here ????? */
// char **_environ;

static int sbInstancesCounter = 0;
static QString sbConfigFile;
static pSbProgram sbConfigInst;
static bool stopInstanceInit = false;
static QMutex sbinsts_mtx;
static QString lastErrorMsg;
AdamsBasic::nbReportModes reportMode = AdamsBasic::nbToLogFile;

extern void report_ntm ( void *filepointer, char *FileName, long LineNumber, unsigned int iErrorCode, int iErrorSeverity,
                         int *piErrorCounter, char *szErrorString, unsigned long *fFlags );

AdamsBasic::AdamsBasic ( QObject *parent, const char *name ) : QObject ( parent ), status ( nbSuccess ), scriptStore ( 0 )
{
	if ( stopInstanceInit )		// this flag goes true to definitively inhibit further operations
		return;

	bytebucketsize = 8192;
	bytebucket = new char [bytebucketsize + 1];

	sbinsts_mtx.lock();

	if ( sbConfigFile.isEmpty() ) {
		sbConfigFile = AdamsServer::basicConfigFile();
		sbConfigInst = scriba_new ( malloc, free );
		if ( scriba_LoadConfiguration ( sbConfigInst, nonConstCharPtr ( sbConfigFile ) ) ) {
			lout << "AdamsBasic::AdamsBasic : FATAL : could not load configuration file. (" << sbConfigFile << ")" << endl;
			stopInstanceInit = true;
			status = nbFatalError;
		}
		if ( scriba_InitModuleInterface ( sbConfigInst ) ) {
			lout << "AdamsBasic::AdamsBasic : FATAL : could not initialize module interface." << endl;
			stopInstanceInit = true;
			status = nbFatalError;
		}
	}
	sbInst = scriba_new ( malloc, free );

	++sbInstancesCounter;
	sbInstId = sbInstancesCounter;

	scriba_SetProcessSbObject ( sbInst, sbConfigInst );
	scriba_SetReportFunction ( sbInst, ( void * ) report_ntm );
	sbinsts_mtx.unlock();
}

AdamsBasic::~AdamsBasic()
{
	scriba_destroy ( sbInst );
	if ( sbInstancesCounter > 0 ) {
		sbinsts_mtx.lock();
		--sbInstancesCounter;
		sbinsts_mtx.unlock();
	}
//	sbinsts_mtx.lock();
//	if (sbInstancesCounter == 0) {
//		scriba_destroy(sbConfigInst);
//		sbConfigFile.setLength(0);
//	}
//	sbinsts_mtx.unlock();
}

void AdamsBasic::setReportMode ( nbReportModes rm )
{
	reportMode = rm;
}

void AdamsBasic::resetStopInstanceInit()
{
	stopInstanceInit = false;
	status = nbSuccess;
}

char * AdamsBasic::nonConstCharPtr ( const QString & str )
{
	if ( str.length() > bytebucketsize ) {
		if ( bytebucket )
			delete bytebucket;
		bytebucketsize = str.length();
		bytebucket = new char [bytebucketsize + 1];
	}
	qstrncpy ( bytebucket, qPrintable ( str ), bytebucketsize + 1 );
	return bytebucket;
}


bool AdamsBasic::setProgramString ( const QString & txt )
{
	QString img;

	img = "declare option DeclareVars\n";
	for ( QStringList::Iterator it = variablesList.begin(); it != variablesList.end(); ++it ) {
		img += *it;
	}
	img += QString ( "\n" ) + txt;
	for ( QStringList::Iterator it = otherList.begin(); it != otherList.end(); ++it ) {
		img += *it;
	}
	img += QString ( "\n" ) + txt;

	if ( scriba_LoadProgramString ( sbInst, nonConstCharPtr ( img ), img.length() ) ) {
		status = nbError;
		return false;
	}
	scriba_NoRun ( sbInst );
	return true;
}

bool AdamsBasic::setProgramScript ( const Script & scr )
{
	scriptStore = &scr;
	for ( QStringList::ConstIterator it = scr.data.variables.begin(); it != scr.data.variables.end(); ++it ) {
		addInitVariable ( ( *it ).toLower(), DRF_long );
	}
	addInitVariable ( scr.data.resultVariableName.toLower(), DRF_long );
	setResultName ( scr.data.resultVariableName.toLower() );
	return setProgramString ( scr.data.scriptText.join ( "\n" ).toLower() );
}


bool AdamsBasic::execute()
{
	cmdBuffer[0] = '\0';
	if ( scriba_Run ( sbInst, cmdBuffer ) ) {
		status = nbError;
		return false;
	}
	return true;
}

bool AdamsBasic::getResultVariable()
{
	long vserial = scriba_LookupVariableByName ( sbInst, resultName );
	if ( vserial ) {
		retValType = scriba_GetVariableType ( sbInst, vserial );
		scriba_GetVariable ( sbInst, vserial, &retVal );
		status = nbSuccess;
		return true;
	} else {
		lout << "AdamsBasic::getResultVariable : ERROR : requested non-existent global variable. (" << resultName << ")" << endl;
		status = nbError;
		return false;
	}
}

long AdamsBasic::probeResultType()
{
	long vserial = scriba_LookupVariableByName ( sbInst, resultName );
	if ( vserial ) {
		return ( scriba_GetVariableType ( sbInst, vserial ) );
	}
	return -1;
}

bool AdamsBasic::getResult ( long & rvar )
{
	if ( getResultVariable() ) {
		if ( retValType == SBT_LONG ) {
			rvar = scriba_GetLong ( sbInst, *retVal );
			return true;
		}
	}
	return false;
}

bool AdamsBasic::getResult ( double & rvar )
{
	if ( getResultVariable() ) {
		if ( retValType == SBT_DOUBLE ) {
			rvar = scriba_GetDouble ( sbInst, *retVal );
			return true;
		}
	}
	return false;
}

bool AdamsBasic::getResult ( QString & rvar )
{
	if ( getResultVariable() ) {
		if ( retValType == SBT_STRING ) {
			rvar = ( char * ) scriba_GetString ( sbInst, *retVal );
			return true;
		}
	}
	return false;
}

void AdamsBasic::addInitVariable ( const QString & vname, DRF_Types vtype )
{
	QString app = QString ( "global " ) + vname + QString ( "\n" );
	variablesList << app;
}

bool AdamsBasic::setValue ( const QString & vname, long vvalue )
{
	long * pvserial;

	if ( ( pvserial = ( *varCache.find ( vname ) ) ) == 0 ) {
		long vserial = scriba_LookupVariableByName ( sbInst, nonConstCharPtr ( QString ( "main::" ) + vname ) );
		if ( !vserial ) {
			return false;
		}
		varCache.insert ( vname, ( pvserial = new long ( vserial ) ) );
	}

	if ( scriba_SetVariable ( sbInst, *pvserial, SBT_LONG, vvalue, 0.0, 0, 0 ) != SCRIBA_ERROR_SUCCESS ) {
		return false;
	}
	return true;
}

bool AdamsBasic::setValue ( const QString & vname, const QStringList & arr )
{
	int idx = 0;
	QString app;

	for ( QStringList::ConstIterator it = arr.begin(); it != arr.end(); ++it ) {
		app = vname + QString ( "[" ) + QString::number ( idx++ ) + QString ( "]" ) + "=" + ( *it );
		otherList << app;
	}
	return true;
}


bool AdamsBasic::setValue ( const QString & vname, double vvalue )
{
	long * pvserial;

	if ( ( pvserial = ( *varCache.find ( vname ) ) ) == 0 ) {
		long vserial = scriba_LookupVariableByName ( sbInst, nonConstCharPtr ( QString ( "main::" ) + vname ) );
		if ( !vserial ) {
			return false;
		}
		varCache.insert ( vname, ( pvserial = new long ( vserial ) ) );
	}

	if ( scriba_SetVariable ( sbInst, *pvserial, SBT_DOUBLE, 0, vvalue, 0, 0 ) != SCRIBA_ERROR_SUCCESS ) {
		return false;
	}
	return true;
}

bool AdamsBasic::setValue ( const QString & vname, const QString & vvalue )
{
	long * pvserial;

	if ( ( pvserial = ( *varCache.find ( vname ) ) ) == 0 ) {
		long vserial = scriba_LookupVariableByName ( sbInst, nonConstCharPtr ( QString ( "main::" ) + vname ) );
		if ( !vserial ) {
			return false;
		}
		varCache.insert ( vname, ( pvserial = new long ( vserial ) ) );
	}

	if ( scriba_SetVariable ( sbInst, *pvserial, SBT_ZCHAR, 0, 0.0, nonConstCharPtr ( vvalue ), 0 ) != SCRIBA_ERROR_SUCCESS ) {
		return false;
	}
	return true;
}

bool AdamsBasic::setValue ( const QString & vname, char * vvalue )
{
	long * pvserial;

	if ( ( pvserial = ( *varCache.find ( vname ) ) ) == 0 ) {
		long vserial = scriba_LookupVariableByName ( sbInst, nonConstCharPtr ( QString ( "main::" ) + vname ) );
		if ( !vserial ) {
			return false;
		}
		varCache.insert ( vname, ( pvserial = new long ( vserial ) ) );
	}

	if ( scriba_SetVariable ( sbInst, *pvserial, SBT_ZCHAR, 0, 0.0, vvalue, 0 ) != SCRIBA_ERROR_SUCCESS ) {
		return false;
	}
	return true;
}


/*noverbatim
Aguments:
=itemize

=item T<filepointer> is a T<void *> pointer. The default value of this pointer is T<stderr> unless the
variation sets it different. This implementation uses this pointer as a T<FILE *> pointer. Other implementations
of this function may use it for any other purpose so long as long the usage of this pointer fits the variation.

=item T<FileName> is the name of the source file where the error was detected. This parameter is T<NULL> in case of
a run-time error. The reporting function is encouraged to display this information for the user.

=item T<LineNumber> is the line number within the source file where the error has happened. This parameter is valid
only in case the parameter T<FileName> is not T<NULL>

=item T<iErrorCode> is the error code.

=item T<iErrorSeverity> should define the severity of the error. It can be
T<REPORT_INFO>,
T<REPORT_WARNING>,
T<REPORT_ERROR>,
T<REPORT_FATAL>,
T<REPORT_INTERNAL>.
Whenever the error severity is above the warning level the T<*piErrorCounter> has to be incremented.

=item T<piErrorCounter> points to an T<int> counter that counts the number of errors. If there are errors
during syntax analysis the ScriptBasic interpreter stops its execution before starting execution.

=item T<szErrorString> is an optional error parameter string and not the displayable error message.
The error message is stored in the global constant array T<en_error_messages>. This string may
contain a T<%s> control referring to the error parameter string.

=item T<fFlags> is an T<unsigned long> bit field. The bits currently used are:
T<REPORT_F_CGI> is set if the error is to be reported as a CGI script. See the code for more details.
T<REPORT_F_FRST> is reset when the report function is called first time and is set by the report function.
This allows the report function to report a header in case it needs.
Other bits are reserved for later use.

=noitemize
CUT*/

void report_ntm ( void *filepointer, char *FileName, long LineNumber, unsigned int iErrorCode, int iErrorSeverity,
                  int *piErrorCounter, char *szErrorString, unsigned long *fFlags )
{
	// this should be used to intercept CGI operations... We won't it for now
//	if( ((*fFlags) & REPORT_F_CGI) && !((*fFlags) & REPORT_F_FRST) ){
//	fprintf((FILE *)filepointer,"Status: 200 OK\nContent-Type: text/html\n\n"
//	                            "<HTML><HEAD>\n"
//	                            "<title>Error page, syntax error</title>\n"
//	                            "</HEAD><BODY>\n"
//	                            "<H1>Error has happened in the code</H1>"
//	                            "<pre>\n");
//	}

	if ( iErrorSeverity >= REPORT_ERROR && piErrorCounter )
		( *piErrorCounter ) ++;

	QString errMsg;

	if ( FileName ) {
		errMsg = QString ( FileName ) + "(" + QString::number ( LineNumber ) + "):\n";
	}
	errMsg += "Error 0x" + QString::number ( iErrorCode, 16 ) + ":\n";

	if ( iErrorCode >= MAX_ERROR_CODE )
		iErrorCode = COMMAND_ERROR_EXTENSION_SPECIFIC;
	if ( szErrorString ) {
		QString errFmt;
		errFmt.sprintf ( en_error_messages[iErrorCode], szErrorString );
		errMsg += errFmt;
	} else {
		errMsg += QString ( en_error_messages[iErrorCode] );
	}

	if ( reportMode == AdamsBasic::nbToLogFile ) {
		errMsg += "\n";
		lout << errMsg << endl;
	} else if ( reportMode == AdamsBasic::nbToExternal ) {
		AdamsBasic::setLastErrorMessage ( errMsg );
	} else {
// 		QMessageBox mb ( QString ( "ADAMS Basic" ), errMsg,
// 		                 ( iErrorSeverity >= REPORT_ERROR ) ? QMessageBox::Warning : QMessageBox::Information,
// 		                 QMessageBox::Ok,
// 		                 Qt::NoButton,
// 		                 Qt::NoButton );
// 		mb.exec();
		errMsg += "\n";
		lout << errMsg << endl;
	}

	*fFlags |= REPORT_F_FRST;
}

const QString & AdamsBasic::getLastErrorMessage()
{
	return lastErrorMsg;
}

void AdamsBasic::setLastErrorMessage ( const QString & msg )
{
	lastErrorMsg = msg;
}
