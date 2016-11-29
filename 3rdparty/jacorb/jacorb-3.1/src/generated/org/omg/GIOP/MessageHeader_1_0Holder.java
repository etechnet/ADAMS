package org.omg.GIOP;

/**
 * Generated from IDL struct "MessageHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class MessageHeader_1_0Holder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.GIOP.MessageHeader_1_0 value;

	public MessageHeader_1_0Holder ()
	{
	}
	public MessageHeader_1_0Holder(final org.omg.GIOP.MessageHeader_1_0 initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.GIOP.MessageHeader_1_0Helper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.GIOP.MessageHeader_1_0Helper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.GIOP.MessageHeader_1_0Helper.write(_out, value);
	}
}
