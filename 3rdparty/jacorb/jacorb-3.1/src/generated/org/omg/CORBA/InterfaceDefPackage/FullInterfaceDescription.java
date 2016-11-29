package org.omg.CORBA.InterfaceDefPackage;

/**
 * Generated from IDL struct "FullInterfaceDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class FullInterfaceDescription
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public FullInterfaceDescription(){}
	public java.lang.String name;
	public java.lang.String id;
	public java.lang.String defined_in;
	public java.lang.String version;
	public org.omg.CORBA.OperationDescription[] operations;
	public org.omg.CORBA.AttributeDescription[] attributes;
	public java.lang.String[] base_interfaces;
	public org.omg.CORBA.TypeCode type;
	public FullInterfaceDescription(java.lang.String name, java.lang.String id, java.lang.String defined_in, java.lang.String version, org.omg.CORBA.OperationDescription[] operations, org.omg.CORBA.AttributeDescription[] attributes, java.lang.String[] base_interfaces, org.omg.CORBA.TypeCode type)
	{
		this.name = name;
		this.id = id;
		this.defined_in = defined_in;
		this.version = version;
		this.operations = operations;
		this.attributes = attributes;
		this.base_interfaces = base_interfaces;
		this.type = type;
	}
}
