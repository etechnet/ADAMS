package org.omg.PortableServer.POAPackage;

/**
 * Generated from IDL exception "NoServant".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class NoServantHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.POAPackage.NoServant value;

	public NoServantHolder ()
	{
	}
	public NoServantHolder(final org.omg.PortableServer.POAPackage.NoServant initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.POAPackage.NoServantHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.POAPackage.NoServantHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.POAPackage.NoServantHelper.write(_out, value);
	}
}
