package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL struct "ExternalEndpoint".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosBridgeAdmin.ExternalEndpoint value;

	public ExternalEndpointHolder ()
	{
	}
	public ExternalEndpointHolder(final org.omg.CosBridgeAdmin.ExternalEndpoint initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosBridgeAdmin.ExternalEndpointHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosBridgeAdmin.ExternalEndpointHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosBridgeAdmin.ExternalEndpointHelper.write(_out, value);
	}
}
