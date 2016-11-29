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
#  Author Name <raffale.ficcadenti@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--08-10-13 Raffaele Ficcadenti
#
*/
#include "servicelib.h"
#include "applogger.h"
ServiceLib::ServiceLib()
{

	QString nome_centrale 	= AdamsServer::getNode();
	lout << "	NOMECENTRALE = " << nome_centrale << endl;

	h = gethostbyname ( qPrintable(nome_centrale) );

	if ( !h ) lout << "***** ServiceLib::ServiceLib(): gethostbyname() errore per NOMECENTRALE!!"  << endl;
}

ServiceLib::~ServiceLib()
{}


void ServiceLib::stampaMsg ( QString msg, int err )
{
	if ( err == 0 ) {
		lout << msg << endl;
	}
	else {
		lout << "(" << err << ")  " << msg << endl;
	}
}

void ServiceLib::writeLogFile ( QString login, QString pswd )
{
	/* parte necessaria se si vuole creare un file di log per le connessioni al server */
	QFile f ( STR_FILE_LOG );
	QDateTime dt = QDateTime::currentDateTime();

	//lout << "Connected to server at: "<< dt.date().year() << "-" << dt.date().month() << "-" << dt.date().day() << "   " << dt.time().hour() << ":" << dt.time().minute() << ":" << dt.time().second() << endl;
	if ( f.open ( QIODevice::WriteOnly | QIODevice::Append ) ) {
		QTextStream t ( &f );
		t << "LOGIN=" << qPrintable ( login )
		<< " PASSWORD=" << qPrintable ( pswd )
		<< "   DATE: " << dt.date().year() << "-" << dt.date().month() << "-" << dt.date().day() << "   " << dt.time().hour() << ":" << dt.time().minute() << ":" << dt.time().second() << endl;
		f.close();
	}
}

QString ServiceLib::getHostName()
{
	return QString ( h->h_name );
}


QString ServiceLib::getHostIP()
{
	return QString ( inet_ntoa ( * ( ( struct in_addr * ) h->h_addr ) ) );
}

QString ServiceLib::currDateTime()
{
	QDateTime dt_current = QDateTime::currentDateTime();

	int aa = dt_current.date().year();
	int mm = dt_current.date().month();
	int gg = dt_current.date().day();
	int hh = dt_current.time().hour();
	int min = dt_current.time().minute();
	int sec = dt_current.time().second();
	QString sTime = "";
	sTime.sprintf ( "%04d-%02d-%02d-%02d:%02d:%02d", aa, mm, gg, hh, min, sec );

	return sTime;
}
// kate: indent-mode cstyle; replace-tabs off; tab-width 8; 
