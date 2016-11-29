package org.omg.CSI;

/**
 * Generated from IDL struct "MessageInContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class MessageInContextHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSI.MessageInContext value;

	public MessageInContextHolder ()
	{
	}
	public MessageInContextHolder(final org.omg.CSI.MessageInContext initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSI.MessageInContextHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSI.MessageInContextHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSI.MessageInContextHelper.write(_out, value);
	}
}
