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


#include "etss_server_trapper_server_impl.h"
#include <adams_limits.h>
#include <applogger.h>
#include <adamsserver.h>
#include <cnfglobals.h>
#include <Qt/qfile.h>

etss_server_trapper_server_impl::etss_server_trapper_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy ),
	  serverID ( idServer )
{
	cnfparser.locateFile();
// 	cnfparser.addMarkerTagPath ( QString ( "MDM_Server_Job." ) + AdamsServer::getNode() );

// 	m_queues_data_path = cnfparser.parQTxtGetValue ( "JobQueuesPath", "MDM", ConfigParser::PathValue );
// 	if ( m_queues_data_path.isEmpty() ) {
// 		lout << "Undefined job queues storage path." << endl;
// 		::exit ( 1 );
// 	}
// 
// 	dir_check ( m_queues_data_path );

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}

}


/**
 */

etss_server_trapper_server_impl::~etss_server_trapper_server_impl()
{
}

// IDL implementation interfaces



//-------
