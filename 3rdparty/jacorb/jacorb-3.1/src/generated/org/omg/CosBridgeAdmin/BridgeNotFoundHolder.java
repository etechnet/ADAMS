package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL exception "BridgeNotFound".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class BridgeNotFoundHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosBridgeAdmin.BridgeNotFound value;

	public BridgeNotFoundHolder ()
	{
	}
	public BridgeNotFoundHolder(final org.omg.CosBridgeAdmin.BridgeNotFound initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosBridgeAdmin.BridgeNotFoundHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosBridgeAdmin.BridgeNotFoundHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosBridgeAdmin.BridgeNotFoundHelper.write(_out, value);
	}
}
