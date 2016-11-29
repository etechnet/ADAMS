package org.omg.CORBA;

/**
 * Generated from IDL interface "ExtAbstractInterfaceDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ExtAbstractInterfaceDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public ExtAbstractInterfaceDef value;
	public ExtAbstractInterfaceDefHolder()
	{
	}
	public ExtAbstractInterfaceDefHolder (final ExtAbstractInterfaceDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ExtAbstractInterfaceDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExtAbstractInterfaceDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ExtAbstractInterfaceDefHelper.write (_out,value);
	}
}
