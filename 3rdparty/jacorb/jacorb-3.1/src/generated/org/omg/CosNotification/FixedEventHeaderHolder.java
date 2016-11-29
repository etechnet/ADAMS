package org.omg.CosNotification;

/**
 * Generated from IDL struct "FixedEventHeader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class FixedEventHeaderHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.FixedEventHeader value;

	public FixedEventHeaderHolder ()
	{
	}
	public FixedEventHeaderHolder(final org.omg.CosNotification.FixedEventHeader initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotification.FixedEventHeaderHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotification.FixedEventHeaderHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotification.FixedEventHeaderHelper.write(_out, value);
	}
}
