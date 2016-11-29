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


#ifndef CORBACLIENT_H
#define CORBACLIENT_H

#include <tao/Utils/ORB_Manager.h>
#include <orbsvcs/Naming/Naming_Client.h>
#include <tao/Intrusive_Ref_Count_Handle_T.h>
#include <Qt/qstring.h>
#include <applogger.h>
#include <apidb_ior.h>

/**
 * A template client implementation for a single server/client
 * model.
 */

template <class ServerInterface>
class CorbaClient
{
public:

	CorbaClient () {}
	~CorbaClient () {
		orb_->destroy ();
	}

	/** Initialize the client communication endpoint with server. */
	bool connect ( const QString & name, const QString & node ) {
		char ** targv = 0;
		int targc = 0;

		try {
			if ( !read_ior ( name, node ) )
				return false;

			// Retrieve the ORB.
			orb_ = CORBA::ORB_init ( targc, targv );

			CORBA::Object_var server_object = orb_->string_to_object ( qPrintable ( ior_ ) );

			if ( CORBA::is_nil ( server_object.in () ) ) {
				lout << "CorbaClient: invalid IOR: " << ior_ << endl;
				return false;
			}

			server_ = ServerInterface::_narrow ( server_object.in () );

			if ( CORBA::is_nil ( server_.in () ) ) {
				lout << "CorbaClient: cannot instantiate server object." << endl;
				return false;
			}


		}
		catch ( const CORBA::Exception& ex ) {
			lout << "CorbaClient: caught CORBA exception:" << ex._info().c_str() << endl;
			return false;
		}

		return true;
	}

	/** Return the interface object pointer. */
	ServerInterface * operator-> () {
		return server_.in ();
	}

	CORBA::ORB_ptr orb ( void ) {
		return CORBA::ORB::_duplicate ( orb_.in () );
	}

private:
	bool read_ior ( const QString & name, const QString & node ) {
		APIDB_IorTable apidb_ior;

		IorTable * iortable = apidb_ior.getIor ( name, node ); // prende uno ior

		if ( iortable ) {
			ior_ = iortable->ior;
			return true;
		}

		return false;

	}

	CORBA::ORB_var orb_;
	QString ior_;
	typedef TAO_Intrusive_Ref_Count_Handle<ServerInterface> ServerInterface_var;
	ServerInterface_var server_;
};

#endif // CORBACLIENT_H
