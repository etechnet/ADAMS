package org.omg.PortableInterceptor.ORBInitInfoPackage;

/**
 * Generated from IDL exception "DuplicateName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DuplicateNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName value;

	public DuplicateNameHolder ()
	{
	}
	public DuplicateNameHolder(final org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateNameHelper.write(_out, value);
	}
}
