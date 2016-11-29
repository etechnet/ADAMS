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

#ifndef _MDM_SERVER_SERVER_H
#define _MDM_SERVER_SERVER_H

#include <poahierarchy.h>
#include <mdmS.h>
#include <p_orb.h>
#include <Qt/qthread.h>
#include <Qt/qhash.h>
#include <Qt/qstringbuilder.h>

#include "collectionthread.h"
#include <pivotfile.h>

#include <userinterface.h>
#include <configuretypedefs.h>
#include <nodeconfighandler.h>
#include <configparser.h>
#include <queryparams.h>
#include <pluginbase.h>
#include <pluginsapi.h>
#include <httpimpl.h>
#include <timeout.h>
#include <btreenetworkrt.h>
#include <commontypes.h>
#include <adamsbasic.h>

class DefineRelation;

using namespace net::etech;
using namespace net::etech::MDM;

class mdm_agent_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::MDM::mdm_agent_server
{

public:

	mdm_agent_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );
	~mdm_agent_server_impl();

	CORBA::Boolean startMDMAgent ( const ::net::etech::STRUCT_PARAMS & UserInput, const ::net::etech::ADAMS_COMPLETE_CONFIG & AdamsConf) throw ( CORBA::SystemException );
	CORBA::Long getProgressPercentage ( ::net::etech::BtreeID_out Btree_id, ::net::etech::BtreeSeq_out BtreeData) throw ( CORBA::SystemException );
	void shutDown() throw(CORBA::SystemException);

private:
	inline void dir_check ( const QString & a_path, bool isFilePath = false ) {
		QString path = a_path;
		if ( isFilePath ) {
			int slashpos = path.lastIndexOf ( '/' );
			if ( slashpos >= 0 )
				path = path.left ( slashpos );
		}

		QDir dir ( path );
		if ( ! dir.exists() )
			dir.mkpath ( path );
	}

	inline void expand_plugin_dir ( QString & plug_dir ) {
		if ( plug_dir.mid ( 0, 1 ) == "~" ) {				// home relative path
			QString homedir = ::getenv ( "HOME" );
			if ( !homedir.isEmpty() ) {
				plug_dir.replace ( 0, 1, homedir );
			}
		}
		else if ( plug_dir.mid ( 0, 1 ) != "/" ) {			// not an absolute path
			plug_dir = AdamsServer::getInstallPath() % "/" % plug_dir;
		}

	}

	PoaHierarchy * m_poa_hierarchy;
	int serverID;
	BtreeSeq emptyBtreeData;
	CollectionThread * worker;
	StoreConf storeconf;
};

#endif
