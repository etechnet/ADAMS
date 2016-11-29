package org.omg.CosNotification;

/**
 * Generated from IDL struct "EventType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EventTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.EventType value;

	public EventTypeHolder ()
	{
	}
	public EventTypeHolder(final org.omg.CosNotification.EventType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosNotification.EventTypeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosNotification.EventTypeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosNotification.EventTypeHelper.write(_out, value);
	}
}
