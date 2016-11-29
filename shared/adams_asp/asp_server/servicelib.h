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
#ifndef SERVICELIB_H
#define SERVICELIB_H


#include <netdb.h>
#include <arpa/inet.h>
#include <Qt/qstring.h>
#include <adamsserver.h>

#define STR_FILE_LOG	"STSConfServ.log"
#define DIR_DESCRITTORI	"/tmp_rda/rda/WEB/apache/share/htdocs/Monitor/Descrittori/"

class ServiceLib
{
public:
	ServiceLib();
	~ServiceLib();

	void stampaMsg ( QString msg, int err );
	void writeLogFile ( QString login, QString pswd );

	QString getHostName();
	QString getHostIP();
	QString currDateTime();

private:
	struct hostent *h;

};

#endif //SERVICELIB_H
