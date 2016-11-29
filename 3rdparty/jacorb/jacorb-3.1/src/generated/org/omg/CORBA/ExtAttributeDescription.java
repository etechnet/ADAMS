package org.omg.CORBA;

/**
 * Generated from IDL struct "ExtAttributeDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtAttributeDescription
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ExtAttributeDescription(){}
	public java.lang.String name;
	public java.lang.String id;
	public java.lang.String defined_in;
	public java.lang.String version;
	public org.omg.CORBA.TypeCode type;
	public org.omg.CORBA.AttributeMode mode;
	public org.omg.CORBA.ExceptionDescription[] get_exceptions;
	public org.omg.CORBA.ExceptionDescription[] put_exceptions;
	public ExtAttributeDescription(java.lang.String name, java.lang.String id, java.lang.String defined_in, java.lang.String version, org.omg.CORBA.TypeCode type, org.omg.CORBA.AttributeMode mode, org.omg.CORBA.ExceptionDescription[] get_exceptions, org.omg.CORBA.ExceptionDescription[] put_exceptions)
	{
		this.name = name;
		this.id = id;
		this.defined_in = defined_in;
		this.version = version;
		this.type = type;
		this.mode = mode;
		this.get_exceptions = get_exceptions;
		this.put_exceptions = put_exceptions;
	}
}
