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


#include <Qt/qcoreapplication.h>
#include <Qt/qstring.h>
#include <Qt/qdatetime.h>
#include <qtcoreresource_loader.h>
#include <adamsserver.h>
#include <apidb_ior.h>
#include <apidb_process.h>
#include <apidb_config.h>
#include <applogger.h>
#include <configparser.h>
#include "nodeconfighandler.h"


#include <iostream>
using namespace std;

// ssm_logger stream interface
AppLogger * AppLogStream;

int main ( int argc, char** argv )
{
	bool bRet;
	QString m;
	
	
	AdamsServer adams_server ( argc, argv, "test_apidb" );
	AppLogStream = adams_server.logger();
	
	lout << "TEST APIDB" << endl;
	
	/*
	ConfigParser parser;
	if ( !parser.locateFile() ) {
		m = QString ( "Configuration file: " ) + parser.getConfigFileName() + QString ( " not found." );
		lout << m << endl;
	}
	else
	{
		lout << "ConfigFileName=" << parser.getConfigFileName() <<  endl;
		lout << "CorbaProtocol=" << parser.parQTxtGetValue ( "CorbaProtocol", "ADAMS_Globals" ) << endl;;
	}
	
	*/
	lout << "***************************************" << endl;
	
	// TEST-Raffo APIDB_IOR

	QString conf_ini_file;
	conf_ini_file = ADAMSINIFILE;

	//NodeConfigHandler nodeConfigHandler(conf_ini_file);
	
	
	APIDB_ConfigTable 	apidb_config;
	AdamsCompleteConfig *adc=apidb_config.loadConfig("N.T.M.-Voce-Standard");
	
	
	//APIDB_IorTable 	apidb_ior;
	//APIDB_ProcessTable 	apidb_process;
	
	
	/*bRet=apidb_ior.putIor("ssm_logger","IOR_TEST_2"); // salva uno ior
	IorTable *iortable=apidb_ior.getIor("ssm_logger"); // prende uno ior
	apidb_ior.debugIor(iortable); // stampa lo ior
	*/
	
	
	//ProcessTable *processtable=apidb_process.getProcess("ssm_logger"); // prende un processo
	//apidb_process.debugProcess(processtable); // stampa il processo
	

	return 0;

}
