package org.omg.CORBA;

/**
 * Generated from IDL alias "ULongLongSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ULongLongSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public long[] value;

	public ULongLongSeqHolder ()
	{
	}
	public ULongLongSeqHolder (final long[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ULongLongSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ULongLongSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ULongLongSeqHelper.write (out,value);
	}
}
