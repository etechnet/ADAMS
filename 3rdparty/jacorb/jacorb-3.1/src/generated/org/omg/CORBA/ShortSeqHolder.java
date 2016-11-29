package org.omg.CORBA;

/**
 * Generated from IDL alias "ShortSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ShortSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public short[] value;

	public ShortSeqHolder ()
	{
	}
	public ShortSeqHolder (final short[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ShortSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ShortSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ShortSeqHelper.write (out,value);
	}
}
