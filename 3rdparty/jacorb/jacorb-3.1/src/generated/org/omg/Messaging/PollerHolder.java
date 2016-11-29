package org.omg.Messaging;

/**
 * Generated from IDL valuetype "Poller".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PollerHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Messaging.Poller value;
	public PollerHolder () {}
	public PollerHolder (final org.omg.Messaging.Poller initial)
	{
		value = initial;
	}
	public void _read (final org.omg.CORBA.portable.InputStream is)
	{
		value = org.omg.Messaging.PollerHelper.read (is);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream os)
	{
		org.omg.Messaging.PollerHelper.write (os, value);
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return value._type ();
	}
}
