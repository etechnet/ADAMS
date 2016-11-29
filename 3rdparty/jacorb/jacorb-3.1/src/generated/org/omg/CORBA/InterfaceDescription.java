package org.omg.CORBA;

/**
 * Generated from IDL struct "InterfaceDescription".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InterfaceDescription
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public InterfaceDescription(){}
	public java.lang.String name;
	public java.lang.String id;
	public java.lang.String defined_in;
	public java.lang.String version;
	public java.lang.String[] base_interfaces;
	public InterfaceDescription(java.lang.String name, java.lang.String id, java.lang.String defined_in, java.lang.String version, java.lang.String[] base_interfaces)
	{
		this.name = name;
		this.id = id;
		this.defined_in = defined_in;
		this.version = version;
		this.base_interfaces = base_interfaces;
	}
}
