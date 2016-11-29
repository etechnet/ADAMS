package org.omg.CosEventChannelAdmin;

/**
 * Generated from IDL exception "AlreadyConnected".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class AlreadyConnectedHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosEventChannelAdmin.AlreadyConnected value;

	public AlreadyConnectedHolder ()
	{
	}
	public AlreadyConnectedHolder(final org.omg.CosEventChannelAdmin.AlreadyConnected initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosEventChannelAdmin.AlreadyConnectedHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosEventChannelAdmin.AlreadyConnectedHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosEventChannelAdmin.AlreadyConnectedHelper.write(_out, value);
	}
}
