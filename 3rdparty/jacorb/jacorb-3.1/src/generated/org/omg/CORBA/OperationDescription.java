package org.omg.CORBA;

/**
 * Generated from IDL struct "OperationDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class OperationDescription
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public OperationDescription(){}
	public java.lang.String name;
	public java.lang.String id;
	public java.lang.String defined_in;
	public java.lang.String version;
	public org.omg.CORBA.TypeCode result;
	public org.omg.CORBA.OperationMode mode;
	public java.lang.String[] contexts;
	public org.omg.CORBA.ParameterDescription[] parameters;
	public org.omg.CORBA.ExceptionDescription[] exceptions;
	public OperationDescription(java.lang.String name, java.lang.String id, java.lang.String defined_in, java.lang.String version, org.omg.CORBA.TypeCode result, org.omg.CORBA.OperationMode mode, java.lang.String[] contexts, org.omg.CORBA.ParameterDescription[] parameters, org.omg.CORBA.ExceptionDescription[] exceptions)
	{
		this.name = name;
		this.id = id;
		this.defined_in = defined_in;
		this.version = version;
		this.result = result;
		this.mode = mode;
		this.contexts = contexts;
		this.parameters = parameters;
		this.exceptions = exceptions;
	}
}
