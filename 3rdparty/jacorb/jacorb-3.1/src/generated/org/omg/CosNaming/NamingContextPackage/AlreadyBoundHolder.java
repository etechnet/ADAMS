package org.omg.CosNaming.NamingContextPackage;

/**
 * Generated from IDL exception "AlreadyBound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class AlreadyBoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNaming.NamingContextPackage.AlreadyBound value;

	public AlreadyBoundHolder ()
	{
	}
	public AlreadyBoundHolder(final org.omg.CosNaming.NamingContextPackage.AlreadyBound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write(_out, value);
	}
}
