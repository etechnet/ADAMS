package org.omg.CosNotification;

/**
 * Generated from IDL struct "NamedPropertyRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class NamedPropertyRangeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.NamedPropertyRange value;

	public NamedPropertyRangeHolder ()
	{
	}
	public NamedPropertyRangeHolder(final org.omg.CosNotification.NamedPropertyRange initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotification.NamedPropertyRangeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotification.NamedPropertyRangeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotification.NamedPropertyRangeHelper.write(_out, value);
	}
}
