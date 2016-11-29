package org.omg.Messaging;

/**
 * Generated from IDL interface "RequestEndTimePolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RequestEndTimePolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public RequestEndTimePolicy value;
	public RequestEndTimePolicyHolder()
	{
	}
	public RequestEndTimePolicyHolder (final RequestEndTimePolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RequestEndTimePolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestEndTimePolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RequestEndTimePolicyHelper.write (_out,value);
	}
}
