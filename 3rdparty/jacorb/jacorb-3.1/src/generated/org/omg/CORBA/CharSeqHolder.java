package org.omg.CORBA;

/**
 * Generated from IDL alias "CharSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class CharSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public char[] value;

	public CharSeqHolder ()
	{
	}
	public CharSeqHolder (final char[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return CharSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CharSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CharSeqHelper.write (out,value);
	}
}
