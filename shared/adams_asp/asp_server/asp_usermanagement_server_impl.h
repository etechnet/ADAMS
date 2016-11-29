/***************************************************************************
                          stsbasicconfigurationimpl.h  -  description
                             -------------------
    begin                : Mon Oct 21 2002
    copyright            : (C) 2002 by Raffaele Ficcadenti
    email                : rficcad@tin.it
 ***************************************************************************/

#ifndef _ASP_USERMGT_SERVER_H
#define _ASP_USERMGT_SERVER_H

#include <poahierarchy.h>
#include <aspS.h>
#include <p_orb.h>
#include "servicelib.h"
#include "apidb_asp_log.h"
#include "apidb_asp_usermgt.h"

using namespace net::etech;

class asp_usermanagement_server_impl: public virtual POA_net::etech::ASP::asp_usermanagement_server
{
public:
	/**
	 * Constructor
	 */
	asp_usermanagement_server_impl ();

	/**
	 * Destructor
	 */
	~asp_usermanagement_server_impl();

	void init_asp_usermanagement();

	// IDL implementation methods

	bool test();
	virtual CORBA::Boolean LoginUser ( CORBA::Long idClass, const char*  login, const char*  password, CORBA::Boolean disablePassCheck, const S_APP_LOG&  AppLog, CORBA::Long_out ruolo ) throw ( CORBA::SystemException );
	//virtual CORBA::Boolean getConfigLib(CORBA::Long flag, S_CONFIG_LIB_out  ConfigLib, const S_APP_LOG&  AppLog) throw (CORBA::SystemException);
	virtual CORBA::Boolean getConfigLib ( CORBA::Long flag, S_CONFIG_LIB_out  ConfigLib ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean getUserConfiguration ( CORBA::Long flag, const char*  login, CORBA::Long idClass, S_USER_out  user, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean getProfileConfiguration ( CORBA::Long flag, const char*  profileName, S_PROFILE_out  profile, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean setUserConfiguration ( CORBA::Long flag, const S_USER&  user, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean setProfileConfiguration ( CORBA::Long flag, const S_PROFILE&  profile, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean getNode ( ConfNodeSeq_out nodeList, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );

	virtual CORBA::Boolean imd_Role ( CORBA::Long flag, const S_ROLE&  role, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean imd_Class ( CORBA::Long flag, const S_CLASS&  modulo, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean imd_Function ( CORBA::Long flag, const S_FUNCTION&  function, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean imd_Profile ( CORBA::Long flag, const S_PROFILE&  profile, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );
	virtual CORBA::Boolean imd_User ( CORBA::Long flag, const S_USER&  user, const S_APP_LOG&  AppLog ) throw ( CORBA::SystemException );

private:
	void copyToCorba ( const S_APP_LOG &sorg, S_APP_LOG *dest );
	void testConnection();
	QString getNameClass ( int idClass );

	APIDB_AspUserMgt 	apidb_usermgt;
	APIDB_AspLog 		apidb_asplog;
	ServiceLib 		serviceLib;
	bool 			dbConnect_usermgt;
	bool			dbConnect_asplog;
};

#endif // _ASP_USERMGT_SERVER_H
