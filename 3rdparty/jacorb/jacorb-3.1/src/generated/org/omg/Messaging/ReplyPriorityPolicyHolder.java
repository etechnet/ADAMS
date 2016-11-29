package org.omg.Messaging;

/**
 * Generated from IDL interface "ReplyPriorityPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ReplyPriorityPolicyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ReplyPriorityPolicy value;
	public ReplyPriorityPolicyHolder()
	{
	}
	public ReplyPriorityPolicyHolder (final ReplyPriorityPolicy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ReplyPriorityPolicyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ReplyPriorityPolicyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ReplyPriorityPolicyHelper.write (_out,value);
	}
}
