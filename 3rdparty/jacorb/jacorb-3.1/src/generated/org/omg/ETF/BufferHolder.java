package org.omg.ETF;

/**
 * Generated from IDL alias "Buffer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BufferHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public BufferHolder ()
	{
	}
	public BufferHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return BufferHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BufferHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		BufferHelper.write (out,value);
	}
}
