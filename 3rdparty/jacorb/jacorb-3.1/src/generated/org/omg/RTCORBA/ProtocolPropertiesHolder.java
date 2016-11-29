package org.omg.RTCORBA;

/**
 * Generated from IDL interface "ProtocolProperties".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ProtocolPropertiesHolder	implements org.omg.CORBA.portable.Streamable{
	 public ProtocolProperties value;
	public ProtocolPropertiesHolder()
	{
	}
	public ProtocolPropertiesHolder (final ProtocolProperties initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ProtocolPropertiesHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ProtocolPropertiesHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ProtocolPropertiesHelper.write (_out,value);
	}
}
