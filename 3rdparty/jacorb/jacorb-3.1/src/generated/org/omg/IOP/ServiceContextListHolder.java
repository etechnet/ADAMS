package org.omg.IOP;

/**
 * Generated from IDL alias "ServiceContextList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ServiceContextListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.IOP.ServiceContext[] value;

	public ServiceContextListHolder ()
	{
	}
	public ServiceContextListHolder (final org.omg.IOP.ServiceContext[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ServiceContextListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServiceContextListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ServiceContextListHelper.write (out,value);
	}
}
