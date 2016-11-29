package org.omg.PortableServer;


/**
 * Generated from IDL interface "POAManagerFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface POAManagerFactoryOperations
{
	/* constants */
	/* operations  */
	org.omg.PortableServer.POAManager create_POAManager(java.lang.String id, org.omg.CORBA.Policy[] policies) throws org.omg.PortableServer.POAManagerFactoryPackage.ManagerAlreadyExists,org.omg.CORBA.PolicyError;
	org.omg.PortableServer.POAManager[] list();
	org.omg.PortableServer.POAManager find(java.lang.String id);
}
