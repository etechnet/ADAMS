package org.omg.CORBA;


/**
 * Generated from IDL interface "ExtAttributeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface ExtAttributeDefOperations
	extends org.omg.CORBA.AttributeDefOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.ExceptionDescription[] get_exceptions();
	void get_exceptions(org.omg.CORBA.ExceptionDescription[] arg);
	org.omg.CORBA.ExceptionDescription[] set_exceptions();
	void set_exceptions(org.omg.CORBA.ExceptionDescription[] arg);
	org.omg.CORBA.ExtAttributeDescription describe_attribute();
}
