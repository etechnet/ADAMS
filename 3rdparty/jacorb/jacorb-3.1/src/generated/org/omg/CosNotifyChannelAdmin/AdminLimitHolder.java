package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL struct "AdminLimit".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class AdminLimitHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyChannelAdmin.AdminLimit value;

	public AdminLimitHolder ()
	{
	}
	public AdminLimitHolder(final org.omg.CosNotifyChannelAdmin.AdminLimit initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyChannelAdmin.AdminLimitHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyChannelAdmin.AdminLimitHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyChannelAdmin.AdminLimitHelper.write(_out, value);
	}
}
