package org.omg.PortableServer;
/**
 * Generated from IDL enum "RequestProcessingPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class RequestProcessingPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public RequestProcessingPolicyValue value;

	public RequestProcessingPolicyValueHolder ()
	{
	}
	public RequestProcessingPolicyValueHolder (final RequestProcessingPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return RequestProcessingPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestProcessingPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		RequestProcessingPolicyValueHelper.write (out,value);
	}
}
