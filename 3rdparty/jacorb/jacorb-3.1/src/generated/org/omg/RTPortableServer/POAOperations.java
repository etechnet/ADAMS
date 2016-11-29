package org.omg.RTPortableServer;


/**
 * Generated from IDL interface "POA".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface POAOperations
	extends org.omg.PortableServer.POAOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.Object create_reference_with_priority(java.lang.String intf, short priority_) throws org.omg.PortableServer.POAPackage.WrongPolicy;
	org.omg.CORBA.Object create_reference_with_id_and_priority(byte[] oid, java.lang.String intf, short priority_) throws org.omg.PortableServer.POAPackage.WrongPolicy;
	byte[] activate_object_with_priority(org.omg.PortableServer.Servant p_servant, short priority_) throws org.omg.PortableServer.POAPackage.ServantAlreadyActive,org.omg.PortableServer.POAPackage.WrongPolicy;
	void activate_object_with_id_and_priority(byte[] oid, org.omg.PortableServer.Servant p_servant, short priority_) throws org.omg.PortableServer.POAPackage.ServantAlreadyActive,org.omg.PortableServer.POAPackage.ObjectAlreadyActive,org.omg.PortableServer.POAPackage.WrongPolicy;
}
