package org.omg.CosTime;

/**
 * Generated from IDL exception "TimeUnavailable".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TimeUnavailableHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTime.TimeUnavailable value;

	public TimeUnavailableHolder ()
	{
	}
	public TimeUnavailableHolder(final org.omg.CosTime.TimeUnavailable initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTime.TimeUnavailableHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTime.TimeUnavailableHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTime.TimeUnavailableHelper.write(_out, value);
	}
}
