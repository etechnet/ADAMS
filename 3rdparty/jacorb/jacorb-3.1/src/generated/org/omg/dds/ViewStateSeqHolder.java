package org.omg.dds;

/**
 * Generated from IDL alias "ViewStateSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ViewStateSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public ViewStateSeqHolder ()
	{
	}
	public ViewStateSeqHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ViewStateSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ViewStateSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ViewStateSeqHelper.write (out,value);
	}
}
