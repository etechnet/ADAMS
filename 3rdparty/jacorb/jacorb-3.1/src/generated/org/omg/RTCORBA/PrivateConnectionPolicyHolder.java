package org.omg.RTCORBA;

/**
 * Generated from IDL interface "PrivateConnectionPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PrivateConnectionPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public PrivateConnectionPolicy value;
	public PrivateConnectionPolicyHolder()
	{
	}
	public PrivateConnectionPolicyHolder (final PrivateConnectionPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PrivateConnectionPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrivateConnectionPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PrivateConnectionPolicyHelper.write (_out,value);
	}
}
