package org.omg.CosNotifyFilter;

/**
 * Generated from IDL struct "ConstraintInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyFilter.ConstraintInfo value;

	public ConstraintInfoHolder ()
	{
	}
	public ConstraintInfoHolder(final org.omg.CosNotifyFilter.ConstraintInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyFilter.ConstraintInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyFilter.ConstraintInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyFilter.ConstraintInfoHelper.write(_out, value);
	}
}
