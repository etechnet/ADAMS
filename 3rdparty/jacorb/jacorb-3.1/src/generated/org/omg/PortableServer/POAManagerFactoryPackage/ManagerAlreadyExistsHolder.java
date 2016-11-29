package org.omg.PortableServer.POAManagerFactoryPackage;

/**
 * Generated from IDL exception "ManagerAlreadyExists".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ManagerAlreadyExistsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.POAManagerFactoryPackage.ManagerAlreadyExists value;

	public ManagerAlreadyExistsHolder ()
	{
	}
	public ManagerAlreadyExistsHolder(final org.omg.PortableServer.POAManagerFactoryPackage.ManagerAlreadyExists initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.POAManagerFactoryPackage.ManagerAlreadyExistsHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.POAManagerFactoryPackage.ManagerAlreadyExistsHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.POAManagerFactoryPackage.ManagerAlreadyExistsHelper.write(_out, value);
	}
}
