package org.omg.CORBA;

/**
 * Generated from IDL alias "UShortSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class UShortSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public short[] value;

	public UShortSeqHolder ()
	{
	}
	public UShortSeqHolder (final short[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return UShortSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = UShortSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		UShortSeqHelper.write (out,value);
	}
}
