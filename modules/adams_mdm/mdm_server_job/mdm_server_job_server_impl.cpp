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


#include "mdm_server_job_server_impl.h"
#include <adams_limits.h>
#include <applogger.h>
#include <adamsserver.h>
#include <cnfglobals.h>
#include <Qt/qfile.h>

mdm_server_job_server_impl::mdm_server_job_server_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer )
	: m_poa_hierarchy ( g_poa_hierarchy ),
	  serverID ( idServer )
{
	cnfparser.locateFile();
	cnfparser.addMarkerTagPath ( QString ( "MDM_Server_Job." ) + AdamsServer::getNode() );

	m_queues_data_path = cnfparser.parQTxtGetValue ( "JobQueuesPath", "MDM", ConfigParser::PathValue );
	if ( m_queues_data_path.isEmpty() ) {
		lout << "Undefined job queues storage path." << endl;
		::exit ( 1 );
	}

	dir_check ( m_queues_data_path );

	m_queues_config_filename = cnfparser.parQTxtGetValue ( "JobQueuesDefinitionFile", "MDM", ConfigParser::PathValue );
	if ( m_queues_config_filename.isEmpty() ) {
		lout << "Undefined queues configuration file path." << endl;
		::exit ( 1 );
	}
	else {
		dir_check ( m_queues_config_filename, true );
		if ( ! m_job_users_map.useQueuesConfigFilename ( m_queues_config_filename ) ) {
			lout << "Cannot open queues configuration file." << endl;
			::exit ( 1 );
		}
	}

	if ( activate ) {
		PortableServer::ObjectId_var oid;
		oid = PortableServer::string_to_ObjectId ( name );
		m_poa_hierarchy->CoreProcessing()->activate_object_with_id ( oid.in(), this );
	}

}


/**
 */

mdm_server_job_server_impl::~mdm_server_job_server_impl()
{
}

// IDL implementation interfaces

CORBA::Boolean mdm_server_job_server_impl::getUsersList ( ::net::etech::JobUserSeq_out USER ) throw ( CORBA::SystemException )
{
	lout2 << "mdm_server_job_server_impl::getUsersList()" << endl;

	STRUCT_USER * user_struct;
	JobUserSeq * l_user_seq = new JobUserSeq;

	if ( ! m_job_users_map.loadJobUsersMap() )
		return ( CORBA::Boolean ) false;

	l_user_seq->length ( ( CORBA::ULong ) m_job_users_map.size() );

	int idx = 0;
	foreach ( JOB_CONFIG job_config, m_job_users_map ) {

		user_struct = new STRUCT_USER;

		c_qstrncpy ( user_struct->user, job_config.user, JOB_USR_LOGIN_LEN );
		c_qstrncpy ( user_struct->password, job_config.password, JOB_USR_PASSWD_LEN );
		user_struct->job = job_config.job;
		user_struct->frequence = job_config.frequence;
		user_struct->iID_User = job_config.id_user;
		( *l_user_seq ) [ idx ] = *user_struct;
		idx++;
	}

	USER = l_user_seq;
	return ( CORBA::Boolean ) true;
}

CORBA::Boolean mdm_server_job_server_impl::putUsersList ( const ::net::etech::JobUserSeq & USER ) throw ( CORBA::SystemException )
{
	lout2 << "mdm_server_job_server_impl::putUsersList()" << endl;

	m_job_users_map.clear();

	for ( int i = 0; i < USER.length(); i++ ) {
		JOB_CONFIG job_config;

		c_qstrncpy ( job_config.user, USER[i].user, JOB_USR_LOGIN_LEN );
		c_qstrncpy ( job_config.password, USER[i].password, JOB_USR_PASSWD_LEN );
		job_config.job = USER[i].job;
		job_config.frequence = USER[i].frequence;
		job_config.id_user = USER[i].iID_User;

		m_job_users_map.insert ( job_config.user, job_config );
	}

	return ( CORBA::Boolean ) m_job_users_map.saveJobUsersMap();
}


CORBA::Boolean mdm_server_job_server_impl::getAllJobs ( ::net::etech::JOBSeq_out JOBS, const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException )
{
	lout1 << "mdm_server_job_server_impl::getAllJobs()" << endl;

	JOBSeq * job_seq = new JOBSeq;
	JOBS = job_seq;

	JobList job_list ( m_queues_data_path, InfoUser.user );
	if ( job_list.isEmpty() )
		return ( CORBA::Boolean ) false;

	job_list.copyToCorba ( job_seq );

	job_list.clear();
	return ( CORBA::Boolean ) true;
}

CORBA::Boolean mdm_server_job_server_impl::modifyJob ( const ::net::etech::STRUCT_JOB & InfoJOB ) throw ( CORBA::SystemException )
{
	lout2 << "modifyJob called" << endl;

	// get user id
	if ( ! m_job_users_map.loadJobUsersMap() )
		return ( CORBA::Boolean ) false;
	if ( ! m_job_users_map.contains ( InfoJOB.user_str ) )
		return ( CORBA::Boolean ) false;

	int user_id = m_job_users_map.value ( InfoJOB.user_str ).id_user;

	// modify job
	JobList job_list ( m_queues_data_path, InfoJOB.user_str );
	if ( job_list.isEmpty() )
		return ( CORBA::Boolean ) false;


	int jdx;
	for ( jdx = 0; jdx < job_list.size(); jdx++ ) {
		JobQueue * jq = job_list.at ( jdx );
		if ( jq->data.locked != 5 && jq->data.job_id == InfoJOB.job_id )
			break;
	}

	if ( jdx == job_list.size() )
		return ( CORBA::Boolean ) false;

	JobQueue * jq = new JobQueue;
	jq->fillFromCorba ( &InfoJOB );
	jq->data.user_id = user_id;

	job_list.replace ( jdx, jq );
	job_list.saveUserQueue ( m_queues_data_path, InfoJOB.user_str );

	return ( CORBA::Boolean ) true;
}

CORBA::Boolean mdm_server_job_server_impl::insertJob ( const ::net::etech::STRUCT_PARAMS & query, const ::net::etech::STRUCT_JOB & InfoJOB ) throw ( CORBA::SystemException )
{
	lout2 << "insertJob() called." << endl;

	// get user id
	if ( ! m_job_users_map.loadJobUsersMap() )
		return ( CORBA::Boolean ) false;
	if ( ! m_job_users_map.contains ( InfoJOB.user_str ) )
		return ( CORBA::Boolean ) false;

	int user_id = m_job_users_map.value ( InfoJOB.user_str ).id_user;

	// insert job
	JobList job_list ( m_queues_data_path, InfoJOB.user_str );
	if ( job_list.isEmpty() )
		return ( CORBA::Boolean ) false;

	JobQueue * jq = new JobQueue;
	jq->fillFromCorba ( &InfoJOB );
	jq->data.user_id = user_id;
	jq->data.req.fillFromCorba ( &query );

	job_list.append ( jq );

	// re-assign job ids
	for ( int i = 0; i < job_list.size(); i++ )
		job_list[i]->data.job_id = i + 1;

	job_list.saveUserQueue ( m_queues_data_path, InfoJOB.user_str );

	return ( CORBA::Boolean ) true;

}


CORBA::Boolean mdm_server_job_server_impl::getJob ( ::net::etech::STRUCT_PARAMS_out query, const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException )
{
	lout2 << "Called getJob()" << endl;

	query = new STRUCT_PARAMS;

	JobList job_list ( m_queues_data_path, InfoUser.user );
	if ( job_list.isEmpty() )
		return ( CORBA::Boolean ) false;


	int jdx;
	for ( jdx = 0; jdx < job_list.size(); jdx++ ) {
		JobQueue * jq = job_list.at ( jdx );
		if ( jq->data.job_id == InfoUser.job )
			break;
	}

	if ( jdx == job_list.size() )
		return ( CORBA::Boolean ) false;

	job_list[jdx]->data.req.copyToCorba ( query );

	return ( CORBA::Boolean ) true;
}


CORBA::Boolean mdm_server_job_server_impl::removeJob ( const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException )
{
	lout2 << "Called removeJob()" << endl;

	JobList job_list ( m_queues_data_path, InfoUser.user );
	if ( job_list.isEmpty() )
		return ( CORBA::Boolean ) false;

	int jdx;
	for ( jdx = 0; jdx < job_list.size(); jdx++ ) {
		JobQueue * jq = job_list.at ( jdx );
		if ( jq->data.job_id == InfoUser.job )
			break;
	}

	if ( jdx == job_list.size() )
		return ( CORBA::Boolean ) false;

	job_list.removeAt ( jdx );

	job_list.saveUserQueue ( m_queues_data_path, InfoUser.user );

	return ( CORBA::Boolean ) true;
}

CORBA::Boolean mdm_server_job_server_impl::removeAllJobs ( const ::net::etech::STRUCT_USER & InfoUser ) throw ( CORBA::SystemException )
{
	lout2 << "Called removeJob()" << endl;

	JobList job_list;

	return ( CORBA::Boolean ) job_list.removeUserQueue ( m_queues_data_path, InfoUser.user );
}


//-------
