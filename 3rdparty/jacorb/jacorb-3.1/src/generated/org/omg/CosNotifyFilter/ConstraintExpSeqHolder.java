package org.omg.CosNotifyFilter;

/**
 * Generated from IDL alias "ConstraintExpSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ConstraintExpSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyFilter.ConstraintExp[] value;

	public ConstraintExpSeqHolder ()
	{
	}
	public ConstraintExpSeqHolder (final org.omg.CosNotifyFilter.ConstraintExp[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ConstraintExpSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ConstraintExpSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ConstraintExpSeqHelper.write (out,value);
	}
}
