package org.omg.CosNotification;

/**
 * Generated from IDL alias "NamedPropertyRangeSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class NamedPropertyRangeSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.NamedPropertyRange[] value;

	public NamedPropertyRangeSeqHolder ()
	{
	}
	public NamedPropertyRangeSeqHolder (final org.omg.CosNotification.NamedPropertyRange[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return NamedPropertyRangeSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = NamedPropertyRangeSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		NamedPropertyRangeSeqHelper.write (out,value);
	}
}
