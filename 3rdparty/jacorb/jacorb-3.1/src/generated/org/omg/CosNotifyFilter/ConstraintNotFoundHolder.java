package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "ConstraintNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyFilter.ConstraintNotFound value;

	public ConstraintNotFoundHolder ()
	{
	}
	public ConstraintNotFoundHolder(final org.omg.CosNotifyFilter.ConstraintNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyFilter.ConstraintNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyFilter.ConstraintNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyFilter.ConstraintNotFoundHelper.write(_out, value);
	}
}
