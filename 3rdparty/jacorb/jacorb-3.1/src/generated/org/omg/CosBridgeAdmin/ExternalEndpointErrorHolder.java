package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL struct "ExternalEndpointError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointErrorHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosBridgeAdmin.ExternalEndpointError value;

	public ExternalEndpointErrorHolder ()
	{
	}
	public ExternalEndpointErrorHolder(final org.omg.CosBridgeAdmin.ExternalEndpointError initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosBridgeAdmin.ExternalEndpointErrorHelper.write(_out, value);
	}
}
