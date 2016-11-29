package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL exception "AdminLimitExceeded".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class AdminLimitExceededHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyChannelAdmin.AdminLimitExceeded value;

	public AdminLimitExceededHolder ()
	{
	}
	public AdminLimitExceededHolder(final org.omg.CosNotifyChannelAdmin.AdminLimitExceeded initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyChannelAdmin.AdminLimitExceededHelper.write(_out, value);
	}
}
