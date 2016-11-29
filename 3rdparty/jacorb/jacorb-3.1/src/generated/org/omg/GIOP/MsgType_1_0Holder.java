package org.omg.GIOP;
/**
 * Generated from IDL enum "MsgType_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class MsgType_1_0Holder
	implements org.omg.CORBA.portable.Streamable
{
	public MsgType_1_0 value;

	public MsgType_1_0Holder ()
	{
	}
	public MsgType_1_0Holder (final MsgType_1_0 initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return MsgType_1_0Helper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MsgType_1_0Helper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		MsgType_1_0Helper.write (out,value);
	}
}
