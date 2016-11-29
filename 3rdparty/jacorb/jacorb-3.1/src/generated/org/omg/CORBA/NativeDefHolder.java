package org.omg.CORBA;

/**
 * Generated from IDL interface "NativeDef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class NativeDefHolder	implements org.omg.CORBA.portable.Streamable{
	 public NativeDef value;
	public NativeDefHolder()
	{
	}
	public NativeDefHolder (final NativeDef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return NativeDefHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = NativeDefHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		NativeDefHelper.write (_out,value);
	}
}
