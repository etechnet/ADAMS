package org.omg.CSIIOP;

/**
 * Generated from IDL struct "ServiceConfiguration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ServiceConfigurationHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.ServiceConfiguration value;

	public ServiceConfigurationHolder ()
	{
	}
	public ServiceConfigurationHolder(final org.omg.CSIIOP.ServiceConfiguration initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSIIOP.ServiceConfigurationHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSIIOP.ServiceConfigurationHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSIIOP.ServiceConfigurationHelper.write(_out, value);
	}
}
