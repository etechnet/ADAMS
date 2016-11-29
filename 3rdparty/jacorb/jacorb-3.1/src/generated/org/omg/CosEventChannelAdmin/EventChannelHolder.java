package org.omg.CosEventChannelAdmin;

/**
 * Generated from IDL interface "EventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EventChannelHolder	implements org.omg.CORBA.portable.Streamable{
	 public EventChannel value;
	public EventChannelHolder()
	{
	}
	public EventChannelHolder (final EventChannel initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EventChannelHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EventChannelHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EventChannelHelper.write (_out,value);
	}
}
