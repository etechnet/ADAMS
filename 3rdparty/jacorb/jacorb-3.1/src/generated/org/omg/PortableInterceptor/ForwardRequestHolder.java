package org.omg.PortableInterceptor;

/**
 * Generated from IDL exception "ForwardRequest".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ForwardRequestHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableInterceptor.ForwardRequest value;

	public ForwardRequestHolder ()
	{
	}
	public ForwardRequestHolder(final org.omg.PortableInterceptor.ForwardRequest initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableInterceptor.ForwardRequestHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableInterceptor.ForwardRequestHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableInterceptor.ForwardRequestHelper.write(_out, value);
	}
}
