package org.omg.CosNotifyFilter;

/**
 * Generated from IDL alias "ConstraintIDSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintIDSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public ConstraintIDSeqHolder ()
	{
	}
	public ConstraintIDSeqHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ConstraintIDSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConstraintIDSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ConstraintIDSeqHelper.write (out,value);
	}
}
