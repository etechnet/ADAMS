package org.omg.RTCORBA;

/**
 * Generated from IDL interface "TCPProtocolProperties".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class TCPProtocolPropertiesHolder	implements org.omg.CORBA.portable.Streamable{
	 public TCPProtocolProperties value;
	public TCPProtocolPropertiesHolder()
	{
	}
	public TCPProtocolPropertiesHolder (final TCPProtocolProperties initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TCPProtocolPropertiesHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TCPProtocolPropertiesHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TCPProtocolPropertiesHelper.write (_out,value);
	}
}
