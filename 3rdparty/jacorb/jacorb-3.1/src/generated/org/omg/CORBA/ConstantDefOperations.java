package org.omg.CORBA;


/**
 * Generated from IDL interface "ConstantDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface ConstantDefOperations
	extends org.omg.CORBA.ContainedOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.TypeCode type();
	org.omg.CORBA.IDLType type_def();
	void type_def(org.omg.CORBA.IDLType arg);
	org.omg.CORBA.Any value();
	void value(org.omg.CORBA.Any arg);
}
