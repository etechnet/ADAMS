package org.omg.DynamicAny;

/**
 * Generated from IDL alias "DynAnySeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynAnySeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.DynamicAny.DynAny[] value;

	public DynAnySeqHolder ()
	{
	}
	public DynAnySeqHolder (final org.omg.DynamicAny.DynAny[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DynAnySeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynAnySeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DynAnySeqHelper.write (out,value);
	}
}
