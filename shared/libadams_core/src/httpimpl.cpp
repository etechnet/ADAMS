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

#include <httpimpl.h>
#include <applogger.h>
#include <stdio.h>
#include <unistd.h>
#include <Qt/qpixmap.h>

#include <adamsserver.h>
#include <cnfglobals.h>

//TODO: Sostituire socket con QSocket !!!


HttpImpl::HttpImpl ( int idUser, int idJob, int isScheduled , const char *Description ) : scheduledReport ( false )
{
	ConfigParser cnfparser;
	QString ptr, ptr1, ptr2;
	QString target_url, str;
	unsigned int i;
	bool use_secure = false;

	http_prefix_string = "http://";

	if ( !cnfparser.locateFile() ) {
		lout << "File not Found : " << ADAMSINIFILE << endl;
		return;
	}

	//TODO: Adjust to new configparser syntax
// 	ptr = cnfparser.parQTxtGetValue ( "Secure_Protocol", AdamsServer::combine ( "Ntm_MasterServer_" ) );
	if ( !ptr.isEmpty() ) {

		if ( ptr.toLower() == "yes" ) {
			use_secure = true;
			http_prefix_string = "https://";
		}


		lout << "Using SSL = " << ptr << endl;
	}


	if ( use_secure )
		httpUrlPath = httpUrlSubPath = "https://";
	else
		httpUrlPath = httpUrlSubPath = "http://";

	//TODO: Adjust to new configparser syntax
// 	ptr = cnfparser.parQTxtGetValue ( "ADAMS_Http_Host", AdamsServer::combine ( "Ntm_MasterServer_" ) );
// 	ptr1 = cnfparser.parQTxtGetValue ( "ADAMS_Http_UrlPath", AdamsServer::combine ( "Ntm_MasterServer_" ) );
// 	ptr2 = cnfparser.parQTxtGetValue ( "ADAMS_Http_UrlPathQueue", AdamsServer::combine ( "Ntm_MasterServer_" ) );

	if ( isScheduled ) {
		if ( use_secure )
			httpUrlPath.sprintf ( "https://%s/%s/%i/%i/", qPrintable(ptr), qPrintable(ptr2), idUser, idJob + 1 );
		else
			httpUrlPath.sprintf ( "http://%s/%s/%i/%i/", qPrintable(ptr), qPrintable(ptr2), idUser, idJob + 1 );

		report_description = Description;
		scheduledReport = true;
	}
	else {
		if ( use_secure )
			httpUrlPath.sprintf ( "https://%s/%s/", qPrintable(ptr), qPrintable(ptr1) );
		else
			httpUrlPath.sprintf ( "http://%s/%s/", qPrintable(ptr), qPrintable(ptr1) );
	}

	url_status = false;
	url = httpUrlPath;
	hostport = HTTP_DEFAULT_PORT;
	httpmethod = QString ( HTTP_DEFAULT_METHOD );
	urlParse();
};


HttpImpl::~HttpImpl()
{
}

/* parse user URL */

void HttpImpl::urlParse()
{
	int idx, idx1;
	QString refurl ( url );							// init a reference copy of the user url

	if ( ( idx = refurl.indexOf ( QRegExp ( http_prefix_string ) ) ) != -1 ) {		// there is a prefix
		refurl.remove ( idx, http_prefix_string.length() );
	}
	// get host name
	idx = refurl.indexOf ( '/' );								// search first '/'
	idx1 = refurl.indexOf ( ':' );							// search first ':'
	// if : is before the first / should indicate an optional
	// port number...
	if ( idx1 != -1 ) {
		if ( idx != -1 && idx1 < idx ) {
			urlhost = refurl.left ( idx1 );
			if ( urlhost.length() == 0 )
				return;
		}
		else {		// a : after the / means a : outside the host name. What is it ??
			return;
		}

		hostport = refurl.mid ( idx1 + 1, idx - idx1 - 1 ).toInt();	// re-assign port number
	}
	else if ( idx != -1 ) {						// normal case
		urlhost = refurl.left ( idx );
		if ( urlhost.length() == 0 )
			return;
	}
	else
		return;							// failed
	// get path
	refurl.remove ( 0, idx );
	urlpath = refurl;

	url_status = true;	// OK !
}

/* (try to) establish server connection */

bool HttpImpl::openConnection()
{
	char addr[2000];

	if ( !urlOk() )
		return ( true );
	QString conhost = urlhost;
	int dotpos = urlhost.indexOf ( '.' );
	if ( dotpos >= 0 ) {							// path in exe, try it
		conhost = urlhost.left ( dotpos );
	}
	/*	sprintf(addr,"%s:%d",conhost.ascii(),hostport);
		open(addr);*/
	Connect ( qPrintable ( conhost ), hostport, 512 );
	if ( isConnected() )
		return false;
	else
		return true;
}

/* complete host connection */

void HttpImpl::initiateDoc ( QString UrlDoc )
{
	send ( QString ( *getHttpMethod() + " " + urlpath + UrlDoc + " HTTP/1.0\r\n" ) );
}

/* send a string */

void HttpImpl::send ( const QString & snd )
{
	write ( qPrintable(snd), snd.length() );
}
/* send a string and terminate */

void HttpImpl::sendString ( const QString & snd )
{
	write ( qPrintable(QString ( snd ) + "\r\n"), snd.length() );
}

/* receive a string */

const char * HttpImpl::recv()
{
	char *ptr = line_buffer;
	cin.getline ( ptr, sizeof ( line_buffer ) );

	return ( ( const char * ) line_buffer );
}

// bring up connection

bool HttpImpl::initialize ( const QString & urlref, bool onsubpath, bool justSendDesc )
{
	QString target_url, str;
	unsigned int i;

//	target_url = (onsubpath) ? httpUrlSubPath : httpUrlPath;
	target_url = httpUrlPath + urlref;
	lout << "HttpImpl::initialize : URL:" << target_url << endl;

	if ( scheduledReport && justSendDesc ) {
		sendReportDescription2Web ( urlref, qPrintable(report_description), false );
		return false;
	}

	setHttpMethod ( "PUT" );

	if ( !urlOk() ) {
		return true;
	}
	if ( openConnection() ) {
		return true;
	}

	initiateDoc ( QString ( urlref ) );
	// write html header
	str.sprintf ( "Content-Length: %d \r\n", FAKE_PAGE_LENGHT );
	send ( str );
	send ( QString ( "\r\n" ) );

	return false;
}

/* work on a qstrlist to send it to the web */

bool HttpImpl::sendHtmlPage2Web ( QStringList & strlist, const char *urlref, bool onsubpath )
{
	QString target_url, str;
	unsigned int i;

	target_url = ( onsubpath ) ? httpUrlSubPath : httpUrlPath;
	target_url += urlref;
	lout << "URL:" << target_url << endl;

	setHttpMethod ( "PUT" );
	if ( !urlOk() ) {
		return ( true );
	}
	if ( openConnection() ) {
		return ( true );
	}
	initiateDoc ( QString ( urlref ) );
	// write html header
	str.sprintf ( "Content-Length: %d \r\n", FAKE_PAGE_LENGHT );
	send ( str );
	send ( QString ( "\r\n" ) );
	// standard preamble

	for ( QStringList::Iterator it = strlist.begin(); it != strlist.end(); ++it ) {
		send ( ( *it ) );
//		lout << "send:(" << (*it) << ")" << endl;
	}
	//close page
	send ( "</PRE>\r\n" );
	send ( "</TT>\r\n" );
	// standard postamble
	send ( "</BODY>\r\n" );
	send ( "</HTML>\r\n" );
	endStream();
	return ( false );
}
/* send on web a descritpion user entered description of the report */

bool HttpImpl::sendReportDescription2Web ( const QString & repourl, const QString & Description , bool onsubpath )
{
	QString str;
	QString target_url;

	target_url = repourl;
	target_url.replace ( ".html", ".dsc" );

	setHttpMethod ( "PUT" );
	if ( !urlOk() ) {
		return ( true );
	}
	if ( openConnection() ) {
		return ( true );
	}
	initiateDoc ( QString ( target_url ) );
	// write html header
	str.sprintf ( "Content-Length: %d \r\n", FAKE_PAGE_LENGHT );
	send ( str );
	send ( QString ( "\r\n" ) );

	str = repourl + " - " + Description;
	send ( str );

	endStream();
	return ( false );
}

/* connect errors goes here */

void HttpImpl::showConnectError ( int errcode )
{

}
// kate: indent-mode cstyle; replace-tabs off; tab-width 8;
