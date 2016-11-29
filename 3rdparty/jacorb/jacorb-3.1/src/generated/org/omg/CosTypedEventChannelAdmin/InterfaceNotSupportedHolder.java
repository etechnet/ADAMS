package org.omg.CosTypedEventChannelAdmin;

/**
 * Generated from IDL exception "InterfaceNotSupported".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class InterfaceNotSupportedHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported value;

	public InterfaceNotSupportedHolder ()
	{
	}
	public InterfaceNotSupportedHolder(final org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTypedEventChannelAdmin.InterfaceNotSupportedHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTypedEventChannelAdmin.InterfaceNotSupportedHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTypedEventChannelAdmin.InterfaceNotSupportedHelper.write(_out, value);
	}
}
