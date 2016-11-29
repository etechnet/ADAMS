package org.omg.CosNotifyComm;

/**
 * Generated from IDL exception "InvalidEventType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class InvalidEventTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotifyComm.InvalidEventType value;

	public InvalidEventTypeHolder ()
	{
	}
	public InvalidEventTypeHolder(final org.omg.CosNotifyComm.InvalidEventType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotifyComm.InvalidEventTypeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotifyComm.InvalidEventTypeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotifyComm.InvalidEventTypeHelper.write(_out, value);
	}
}
