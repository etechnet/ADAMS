package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL interface "SequenceProxyPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SequenceProxyPushConsumerHolder	implements org.omg.CORBA.portable.Streamable{
	 public SequenceProxyPushConsumer value;
	public SequenceProxyPushConsumerHolder()
	{
	}
	public SequenceProxyPushConsumerHolder (final SequenceProxyPushConsumer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SequenceProxyPushConsumerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SequenceProxyPushConsumerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SequenceProxyPushConsumerHelper.write (_out,value);
	}
}
