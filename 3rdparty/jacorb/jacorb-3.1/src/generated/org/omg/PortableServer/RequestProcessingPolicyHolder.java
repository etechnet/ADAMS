package org.omg.PortableServer;

/**
 * Generated from IDL interface "RequestProcessingPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class RequestProcessingPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public RequestProcessingPolicy value;
	public RequestProcessingPolicyHolder()
	{
	}
	public RequestProcessingPolicyHolder (final RequestProcessingPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RequestProcessingPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestProcessingPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RequestProcessingPolicyHelper.write (_out,value);
	}
}
