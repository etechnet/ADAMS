package org.omg.IOP;

/**
 * Generated from IDL struct "ServiceContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ServiceContextHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.IOP.ServiceContext value;

	public ServiceContextHolder ()
	{
	}
	public ServiceContextHolder(final org.omg.IOP.ServiceContext initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.IOP.ServiceContextHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.IOP.ServiceContextHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.IOP.ServiceContextHelper.write(_out, value);
	}
}
