package org.omg.CosNotification;

/**
 * Generated from IDL alias "EventTypeSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EventTypeSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosNotification.EventType[] value;

	public EventTypeSeqHolder ()
	{
	}
	public EventTypeSeqHolder (final org.omg.CosNotification.EventType[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return EventTypeSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EventTypeSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		EventTypeSeqHelper.write (out,value);
	}
}
