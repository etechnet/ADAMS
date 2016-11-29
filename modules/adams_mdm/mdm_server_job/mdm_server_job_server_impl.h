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

#ifndef _MDM_SERVER_JOB_SERVER_H
#define _MDM_SERVER_JOB_SERVER_H

#include <poahierarchy.h>
#include <mdmS.h>
#include <p_orb.h>
#include <configparser.h>
#include <jobqueue.h>

#include <Qt/qstring.h>
#include <Qt/qdir.h>
#include <Qt/qhash.h>

using namespace net::etech;
using namespace net::etech::MDM;

class mdm_server_job_server_impl: public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::MDM::mdm_server_job_server
{

public:

	/**
	 * Constructor
	 */
	mdm_server_job_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );

	/**
	 * Destructor
	 */
	~mdm_server_job_server_impl();

	// IDL implementation methods

	CORBA::Boolean getUsersList ( ::net::etech::JobUserSeq_out USER ) throw ( CORBA::SystemException );
	CORBA::Boolean putUsersList ( const ::net::etech::JobUserSeq & USER) throw ( CORBA::SystemException );
	CORBA::Boolean getAllJobs ( ::net::etech::JOBSeq_out JOBS, const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean insertJob ( const ::net::etech::STRUCT_PARAMS & query, const ::net::etech::STRUCT_JOB & InfoJOB ) throw ( CORBA::SystemException );
	CORBA::Boolean modifyJob ( const ::net::etech::STRUCT_JOB & InfoJOB ) throw ( CORBA::SystemException );
	CORBA::Boolean getJob ( ::net::etech::STRUCT_PARAMS_out query, const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean removeJob ( const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException );
	CORBA::Boolean removeAllJobs ( const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException );

private:

	ConfigParser cnfparser;
	PoaHierarchy * m_poa_hierarchy;
	int serverID;
	QString m_queues_data_path;
	QString m_queues_config_filename;

	JobUsersMap m_job_users_map;

	inline void dir_check ( const QString & a_path, bool isFilePath = false ) {
		QString path = a_path;
		if ( path.mid ( 0, 1 ) == "~" ) {				// home relative path
			QString homedir = ::getenv ( "HOME" );
			if ( !homedir.isEmpty() ) {
				path.replace ( 0, 1, homedir );
			}
		}
		if ( isFilePath ) {
			int slashpos = path.lastIndexOf ( '/' );
			if ( slashpos >= 0 )
				path = path.left ( slashpos );
		}

		QDir dir ( path );
		if ( ! dir.exists() )
			dir.mkpath ( path );
	}
};

#endif
