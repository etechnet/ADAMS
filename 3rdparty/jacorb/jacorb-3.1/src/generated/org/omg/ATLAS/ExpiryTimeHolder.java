package org.omg.ATLAS;

/**
 * Generated from IDL alias "ExpiryTime".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExpiryTimeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.TimeBase.UtcT[] value;

	public ExpiryTimeHolder ()
	{
	}
	public ExpiryTimeHolder (final org.omg.TimeBase.UtcT[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExpiryTimeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExpiryTimeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExpiryTimeHelper.write (out,value);
	}
}
