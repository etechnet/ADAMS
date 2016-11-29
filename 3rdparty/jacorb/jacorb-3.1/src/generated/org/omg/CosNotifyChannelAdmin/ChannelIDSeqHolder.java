package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL alias "ChannelIDSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ChannelIDSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public ChannelIDSeqHolder ()
	{
	}
	public ChannelIDSeqHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ChannelIDSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ChannelIDSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ChannelIDSeqHelper.write (out,value);
	}
}
