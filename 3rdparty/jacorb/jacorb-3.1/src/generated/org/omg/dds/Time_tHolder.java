package org.omg.dds;

/**
 * Generated from IDL struct "Time_t".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class Time_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.Time_t value;

	public Time_tHolder ()
	{
	}
	public Time_tHolder(final org.omg.dds.Time_t initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.Time_tHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.Time_tHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.Time_tHelper.write(_out, value);
	}
}
