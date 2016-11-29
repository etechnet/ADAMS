package org.omg.PortableServer.POAManagerPackage;

/**
 * Generated from IDL exception "AdapterInactive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class AdapterInactiveHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.POAManagerPackage.AdapterInactive value;

	public AdapterInactiveHolder ()
	{
	}
	public AdapterInactiveHolder(final org.omg.PortableServer.POAManagerPackage.AdapterInactive initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.POAManagerPackage.AdapterInactiveHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.POAManagerPackage.AdapterInactiveHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.POAManagerPackage.AdapterInactiveHelper.write(_out, value);
	}
}
