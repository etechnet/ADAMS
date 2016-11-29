package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "InvalidValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyFilter.InvalidValue value;

	public InvalidValueHolder ()
	{
	}
	public InvalidValueHolder(final org.omg.CosNotifyFilter.InvalidValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyFilter.InvalidValueHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyFilter.InvalidValueHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyFilter.InvalidValueHelper.write(_out, value);
	}
}
