package org.omg.PortableGroup;

/**
 * Generated from IDL exception "InterfaceNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InterfaceNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableGroup.InterfaceNotFound value;

	public InterfaceNotFoundHolder ()
	{
	}
	public InterfaceNotFoundHolder(final org.omg.PortableGroup.InterfaceNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableGroup.InterfaceNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableGroup.InterfaceNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableGroup.InterfaceNotFoundHelper.write(_out, value);
	}
}
