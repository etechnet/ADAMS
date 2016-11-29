package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL interface "SequenceProxyPullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SequenceProxyPullConsumerHolder	implements org.omg.CORBA.portable.Streamable{
	 public SequenceProxyPullConsumer value;
	public SequenceProxyPullConsumerHolder()
	{
	}
	public SequenceProxyPullConsumerHolder (final SequenceProxyPullConsumer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SequenceProxyPullConsumerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SequenceProxyPullConsumerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SequenceProxyPullConsumerHelper.write (_out,value);
	}
}
