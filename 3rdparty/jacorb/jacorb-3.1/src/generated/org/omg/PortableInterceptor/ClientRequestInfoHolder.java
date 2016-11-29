package org.omg.PortableInterceptor;

/**
 * Generated from IDL interface "ClientRequestInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ClientRequestInfoHolder	implements org.omg.CORBA.portable.Streamable{
	 public ClientRequestInfo value;
	public ClientRequestInfoHolder()
	{
	}
	public ClientRequestInfoHolder (final ClientRequestInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ClientRequestInfoHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ClientRequestInfoHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ClientRequestInfoHelper.write (_out,value);
	}
}
