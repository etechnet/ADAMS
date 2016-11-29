package org.omg.PortableInterceptor;

/**
 * Generated from IDL interface "RequestInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class RequestInfoHolder	implements org.omg.CORBA.portable.Streamable{
	 public RequestInfo value;
	public RequestInfoHolder()
	{
	}
	public RequestInfoHolder (final RequestInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RequestInfoHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RequestInfoHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RequestInfoHelper.write (_out,value);
	}
}
