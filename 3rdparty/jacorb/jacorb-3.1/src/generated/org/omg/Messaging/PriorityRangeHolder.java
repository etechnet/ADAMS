package org.omg.Messaging;

/**
 * Generated from IDL struct "PriorityRange".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PriorityRangeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Messaging.PriorityRange value;

	public PriorityRangeHolder ()
	{
	}
	public PriorityRangeHolder(final org.omg.Messaging.PriorityRange initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.Messaging.PriorityRangeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.Messaging.PriorityRangeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.Messaging.PriorityRangeHelper.write(_out, value);
	}
}
