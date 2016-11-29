package org.omg.RTCORBA;

/**
 * Generated from IDL interface "ServerProtocolPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ServerProtocolPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServerProtocolPolicy value;
	public ServerProtocolPolicyHolder()
	{
	}
	public ServerProtocolPolicyHolder (final ServerProtocolPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServerProtocolPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServerProtocolPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServerProtocolPolicyHelper.write (_out,value);
	}
}
