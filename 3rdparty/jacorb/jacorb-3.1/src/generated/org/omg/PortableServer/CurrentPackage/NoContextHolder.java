package org.omg.PortableServer.CurrentPackage;

/**
 * Generated from IDL exception "NoContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class NoContextHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.CurrentPackage.NoContext value;

	public NoContextHolder ()
	{
	}
	public NoContextHolder(final org.omg.PortableServer.CurrentPackage.NoContext initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.CurrentPackage.NoContextHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.CurrentPackage.NoContextHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.CurrentPackage.NoContextHelper.write(_out, value);
	}
}
