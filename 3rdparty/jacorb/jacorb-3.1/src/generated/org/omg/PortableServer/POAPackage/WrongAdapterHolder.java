package org.omg.PortableServer.POAPackage;

/**
 * Generated from IDL exception "WrongAdapter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class WrongAdapterHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.POAPackage.WrongAdapter value;

	public WrongAdapterHolder ()
	{
	}
	public WrongAdapterHolder(final org.omg.PortableServer.POAPackage.WrongAdapter initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.POAPackage.WrongAdapterHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.POAPackage.WrongAdapterHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.POAPackage.WrongAdapterHelper.write(_out, value);
	}
}
