package org.omg.CORBA;

/**
 * Generated from IDL alias "BooleanSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class BooleanSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public boolean[] value;

	public BooleanSeqHolder ()
	{
	}
	public BooleanSeqHolder (final boolean[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return BooleanSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BooleanSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		BooleanSeqHelper.write (out,value);
	}
}
