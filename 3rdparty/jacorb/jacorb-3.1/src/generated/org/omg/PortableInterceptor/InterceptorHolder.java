package org.omg.PortableInterceptor;

/**
 * Generated from IDL interface "Interceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class InterceptorHolder	implements org.omg.CORBA.portable.Streamable{
	 public Interceptor value;
	public InterceptorHolder()
	{
	}
	public InterceptorHolder (final Interceptor initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return InterceptorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = InterceptorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		InterceptorHelper.write (_out,value);
	}
}
