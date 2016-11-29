package org.omg.CORBA;

/**
 * Generated from IDL interface "ExtLocalInterfaceDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtLocalInterfaceDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ExtLocalInterfaceDef value;
	public ExtLocalInterfaceDefHolder()
	{
	}
	public ExtLocalInterfaceDefHolder (final ExtLocalInterfaceDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ExtLocalInterfaceDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExtLocalInterfaceDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ExtLocalInterfaceDefHelper.write (_out,value);
	}
}
