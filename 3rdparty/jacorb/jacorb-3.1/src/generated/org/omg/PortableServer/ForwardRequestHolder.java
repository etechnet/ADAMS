package org.omg.PortableServer;

/**
 * Generated from IDL exception "ForwardRequest".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ForwardRequestHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.ForwardRequest value;

	public ForwardRequestHolder ()
	{
	}
	public ForwardRequestHolder(final org.omg.PortableServer.ForwardRequest initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.ForwardRequestHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.ForwardRequestHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.ForwardRequestHelper.write(_out, value);
	}
}
