package org.omg.RTCORBA;

/**
 * Generated from IDL struct "Protocol".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ProtocolHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.RTCORBA.Protocol value;

	public ProtocolHolder ()
	{
	}
	public ProtocolHolder(final org.omg.RTCORBA.Protocol initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.RTCORBA.ProtocolHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.RTCORBA.ProtocolHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.RTCORBA.ProtocolHelper.write(_out, value);
	}
}
