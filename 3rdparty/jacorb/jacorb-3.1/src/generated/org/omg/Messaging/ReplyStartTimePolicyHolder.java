package org.omg.Messaging;

/**
 * Generated from IDL interface "ReplyStartTimePolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ReplyStartTimePolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ReplyStartTimePolicy value;
	public ReplyStartTimePolicyHolder()
	{
	}
	public ReplyStartTimePolicyHolder (final ReplyStartTimePolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ReplyStartTimePolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ReplyStartTimePolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ReplyStartTimePolicyHelper.write (_out,value);
	}
}
