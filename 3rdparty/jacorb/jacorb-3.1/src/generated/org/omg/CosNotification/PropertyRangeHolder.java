package org.omg.CosNotification;

/**
 * Generated from IDL struct "PropertyRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PropertyRangeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.PropertyRange value;

	public PropertyRangeHolder ()
	{
	}
	public PropertyRangeHolder(final org.omg.CosNotification.PropertyRange initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotification.PropertyRangeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotification.PropertyRangeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotification.PropertyRangeHelper.write(_out, value);
	}
}
