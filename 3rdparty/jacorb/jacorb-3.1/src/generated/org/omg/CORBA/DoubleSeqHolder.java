package org.omg.CORBA;

/**
 * Generated from IDL alias "DoubleSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class DoubleSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public double[] value;

	public DoubleSeqHolder ()
	{
	}
	public DoubleSeqHolder (final double[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DoubleSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DoubleSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DoubleSeqHelper.write (out,value);
	}
}
