package org.omg.CSIIOP;

/**
 * Generated from IDL alias "ServiceConfigurationList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ServiceConfigurationListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.ServiceConfiguration[] value;

	public ServiceConfigurationListHolder ()
	{
	}
	public ServiceConfigurationListHolder (final org.omg.CSIIOP.ServiceConfiguration[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ServiceConfigurationListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServiceConfigurationListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ServiceConfigurationListHelper.write (out,value);
	}
}
