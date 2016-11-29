package org.omg.dds;

/**
 * Generated from IDL alias "InstanceHandleSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InstanceHandleSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public InstanceHandleSeqHolder ()
	{
	}
	public InstanceHandleSeqHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return InstanceHandleSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = InstanceHandleSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		InstanceHandleSeqHelper.write (out,value);
	}
}
