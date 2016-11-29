package org.omg.RTCORBA;

/**
 * Generated from IDL interface "ClientProtocolPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ClientProtocolPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ClientProtocolPolicy value;
	public ClientProtocolPolicyHolder()
	{
	}
	public ClientProtocolPolicyHolder (final ClientProtocolPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ClientProtocolPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ClientProtocolPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ClientProtocolPolicyHelper.write (_out,value);
	}
}
