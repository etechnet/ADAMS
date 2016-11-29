package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL exception "AdminNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class AdminNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyChannelAdmin.AdminNotFound value;

	public AdminNotFoundHolder ()
	{
	}
	public AdminNotFoundHolder(final org.omg.CosNotifyChannelAdmin.AdminNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyChannelAdmin.AdminNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyChannelAdmin.AdminNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyChannelAdmin.AdminNotFoundHelper.write(_out, value);
	}
}
