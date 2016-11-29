package org.omg.CORBA;


/**
 * Generated from IDL interface "UnionDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface UnionDefOperations
	extends org.omg.CORBA.TypedefDefOperations , org.omg.CORBA.ContainerOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.TypeCode discriminator_type();
	org.omg.CORBA.IDLType discriminator_type_def();
	void discriminator_type_def(org.omg.CORBA.IDLType arg);
	org.omg.CORBA.UnionMember[] members();
	void members(org.omg.CORBA.UnionMember[] arg);
}
