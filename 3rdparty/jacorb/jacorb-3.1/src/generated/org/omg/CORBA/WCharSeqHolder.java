package org.omg.CORBA;

/**
 * Generated from IDL alias "WCharSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class WCharSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public char[] value;

	public WCharSeqHolder ()
	{
	}
	public WCharSeqHolder (final char[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return WCharSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = WCharSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		WCharSeqHelper.write (out,value);
	}
}
