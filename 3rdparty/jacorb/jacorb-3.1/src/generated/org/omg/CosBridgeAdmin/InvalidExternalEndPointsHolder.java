package org.omg.CosBridgeAdmin;

/**
 * Generated from IDL exception "InvalidExternalEndPoints".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class InvalidExternalEndPointsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosBridgeAdmin.InvalidExternalEndPoints value;

	public InvalidExternalEndPointsHolder ()
	{
	}
	public InvalidExternalEndPointsHolder(final org.omg.CosBridgeAdmin.InvalidExternalEndPoints initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosBridgeAdmin.InvalidExternalEndPointsHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosBridgeAdmin.InvalidExternalEndPointsHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosBridgeAdmin.InvalidExternalEndPointsHelper.write(_out, value);
	}
}
