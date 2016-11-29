package org.omg.CORBA;

/**
 * Generated from IDL interface "InterfaceDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InterfaceDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public InterfaceDef value;
	public InterfaceDefHolder()
	{
	}
	public InterfaceDefHolder (final InterfaceDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return InterfaceDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = InterfaceDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		InterfaceDefHelper.write (_out,value);
	}
}
