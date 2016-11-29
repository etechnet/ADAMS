package org.omg.CORBA;


/**
 * Generated from IDL interface "ArrayDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface ArrayDefOperations
	extends org.omg.CORBA.IDLTypeOperations
{
	/* constants */
	/* operations  */
	int length();
	void length(int arg);
	org.omg.CORBA.TypeCode element_type();
	org.omg.CORBA.IDLType element_type_def();
	void element_type_def(org.omg.CORBA.IDLType arg);
}
