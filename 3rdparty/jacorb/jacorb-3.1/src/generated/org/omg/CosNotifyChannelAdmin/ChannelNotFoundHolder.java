package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL exception "ChannelNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ChannelNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyChannelAdmin.ChannelNotFound value;

	public ChannelNotFoundHolder ()
	{
	}
	public ChannelNotFoundHolder(final org.omg.CosNotifyChannelAdmin.ChannelNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyChannelAdmin.ChannelNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyChannelAdmin.ChannelNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyChannelAdmin.ChannelNotFoundHelper.write(_out, value);
	}
}
