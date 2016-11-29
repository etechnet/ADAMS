package org.omg.RTCORBA;

/**
 * Generated from IDL alias "ProtocolList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ProtocolListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.RTCORBA.Protocol[] value;

	public ProtocolListHolder ()
	{
	}
	public ProtocolListHolder (final org.omg.RTCORBA.Protocol[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ProtocolListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ProtocolListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ProtocolListHelper.write (out,value);
	}
}
