package org.omg.CORBA;


/**
 * Generated from IDL interface "OperationDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public interface OperationDefOperations
	extends org.omg.CORBA.ContainedOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.TypeCode result();
	org.omg.CORBA.IDLType result_def();
	void result_def(org.omg.CORBA.IDLType arg);
	org.omg.CORBA.ParameterDescription[] params();
	void params(org.omg.CORBA.ParameterDescription[] arg);
	org.omg.CORBA.OperationMode mode();
	void mode(org.omg.CORBA.OperationMode arg);
	java.lang.String[] contexts();
	void contexts(java.lang.String[] arg);
	org.omg.CORBA.ExceptionDef[] exceptions();
	void exceptions(org.omg.CORBA.ExceptionDef[] arg);
}
