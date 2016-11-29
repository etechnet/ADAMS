package org.omg.CosNotifyFilter;

/**
 * Generated from IDL exception "InvalidConstraint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidConstraintHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyFilter.InvalidConstraint value;

	public InvalidConstraintHolder ()
	{
	}
	public InvalidConstraintHolder(final org.omg.CosNotifyFilter.InvalidConstraint initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyFilter.InvalidConstraintHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyFilter.InvalidConstraintHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyFilter.InvalidConstraintHelper.write(_out, value);
	}
}
