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
#ifndef ASP_SERVER_IMPL_H
#define ASP_SERVER_IMPL_H

#include <poahierarchy.h>
#include <aspS.h>
#include <p_orb.h>
#include <idlincludes.h>
#include "asp_usermanagement_server_impl.h"
#include "mdm_configuration_server_impl.h"
#include "servicelib.h"
#include "apidb_asp_log.h"
#include "../../../include/generated/aspC.h"

using namespace net::etech::ASP;

class asp_entry_impl : public virtual PortableServer::RefCountServantBase,
	public virtual POA_net::etech::ASP::asp_entry,
	public virtual asp_usermanagement_server_impl,
	public virtual mdm_configuration_server_impl
{
public:
	/**
	 * Constructor
	 */
	asp_entry_impl ( PoaHierarchy * g_poa_hierarchy, const char * name, CORBA::Boolean activate , int idServer );

	/**
	 * Destructor
	 */
	~asp_entry_impl();

	// IDL implementation methods
	virtual CORBA::Boolean writeAPP_LOG ( const S_APP_LOG & AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean getPswd ( const char * nomeNodo, const char * typePassword, const char * userLogin, ::CORBA::String_out userPassword ) throw ( CORBA::SystemException );

	virtual CORBA::Boolean dummy ( ) throw ( CORBA::SystemException );

	void init_asp_entry();
	static void copyToCorba ( const S_APP_LOG &sorg, S_APP_LOG *dest );

private:


	PoaHierarchy * m_poa_hierarchy;
	int serverID;

	APIDB_AspLog apidb_asplog;
	ServiceLib serviceLib;

	bool dbConnect;
};

#endif // ASP_SERVER_IMPL_H

