package org.omg.CORBA;

/**
 * Generated from IDL alias "LongSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class LongSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public LongSeqHolder ()
	{
	}
	public LongSeqHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return LongSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LongSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		LongSeqHelper.write (out,value);
	}
}
