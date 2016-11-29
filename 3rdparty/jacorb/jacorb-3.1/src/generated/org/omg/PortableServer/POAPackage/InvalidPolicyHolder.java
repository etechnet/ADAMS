package org.omg.PortableServer.POAPackage;

/**
 * Generated from IDL exception "InvalidPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InvalidPolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableServer.POAPackage.InvalidPolicy value;

	public InvalidPolicyHolder ()
	{
	}
	public InvalidPolicyHolder(final org.omg.PortableServer.POAPackage.InvalidPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableServer.POAPackage.InvalidPolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableServer.POAPackage.InvalidPolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableServer.POAPackage.InvalidPolicyHelper.write(_out, value);
	}
}
