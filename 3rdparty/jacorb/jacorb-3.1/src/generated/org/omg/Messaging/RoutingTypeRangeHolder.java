package org.omg.Messaging;

/**
 * Generated from IDL struct "RoutingTypeRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RoutingTypeRangeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Messaging.RoutingTypeRange value;

	public RoutingTypeRangeHolder ()
	{
	}
	public RoutingTypeRangeHolder(final org.omg.Messaging.RoutingTypeRange initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.Messaging.RoutingTypeRangeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.Messaging.RoutingTypeRangeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.Messaging.RoutingTypeRangeHelper.write(_out, value);
	}
}