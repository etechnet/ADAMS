package org.omg.dds;

/**
 * Generated from IDL struct "Duration_t".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class Duration_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.Duration_t value;

	public Duration_tHolder ()
	{
	}
	public Duration_tHolder(final org.omg.dds.Duration_t initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.dds.Duration_tHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.dds.Duration_tHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.dds.Duration_tHelper.write(_out, value);
	}
}
