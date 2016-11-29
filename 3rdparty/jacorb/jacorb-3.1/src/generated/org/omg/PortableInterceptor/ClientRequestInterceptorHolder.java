package org.omg.PortableInterceptor;

/**
 * Generated from IDL interface "ClientRequestInterceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ClientRequestInterceptorHolder	implements org.omg.CORBA.portable.Streamable{
	 public ClientRequestInterceptor value;
	public ClientRequestInterceptorHolder()
	{
	}
	public ClientRequestInterceptorHolder (final ClientRequestInterceptor initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ClientRequestInterceptorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ClientRequestInterceptorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ClientRequestInterceptorHelper.write (_out,value);
	}
}
