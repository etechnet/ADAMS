package org.omg.DynamicAny;

/**
 * Generated from IDL alias "AnySeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class AnySeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.Any[] value;

	public AnySeqHolder ()
	{
	}
	public AnySeqHolder (final org.omg.CORBA.Any[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AnySeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AnySeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AnySeqHelper.write (out,value);
	}
}
