package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL exception "BridgeAlreadyStarted".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class BridgeAlreadyStartedHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosBridgeAdmin.BridgeAlreadyStarted value;

	public BridgeAlreadyStartedHolder ()
	{
	}
	public BridgeAlreadyStartedHolder(final org.omg.CosBridgeAdmin.BridgeAlreadyStarted initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosBridgeAdmin.BridgeAlreadyStartedHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosBridgeAdmin.BridgeAlreadyStartedHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosBridgeAdmin.BridgeAlreadyStartedHelper.write(_out, value);
	}
}
