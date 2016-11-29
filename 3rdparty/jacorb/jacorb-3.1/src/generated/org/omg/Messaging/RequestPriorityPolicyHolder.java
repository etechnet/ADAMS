package org.omg.Messaging;

/**
 * Generated from IDL interface "RequestPriorityPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RequestPriorityPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public RequestPriorityPolicy value;
	public RequestPriorityPolicyHolder()
	{
	}
	public RequestPriorityPolicyHolder (final RequestPriorityPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RequestPriorityPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestPriorityPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RequestPriorityPolicyHelper.write (_out,value);
	}
}
