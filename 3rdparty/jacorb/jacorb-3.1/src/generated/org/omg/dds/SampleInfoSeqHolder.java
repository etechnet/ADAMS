package org.omg.dds;

/**
 * Generated from IDL alias "SampleInfoSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SampleInfoSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.dds.SampleInfo[] value;

	public SampleInfoSeqHolder ()
	{
	}
	public SampleInfoSeqHolder (final org.omg.dds.SampleInfo[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return SampleInfoSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SampleInfoSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		SampleInfoSeqHelper.write (out,value);
	}
}
