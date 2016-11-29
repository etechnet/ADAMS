package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL interface "ProxyPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ProxyPushConsumerHolder	implements org.omg.CORBA.portable.Streamable{
	 public ProxyPushConsumer value;
	public ProxyPushConsumerHolder()
	{
	}
	public ProxyPushConsumerHolder (final ProxyPushConsumer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ProxyPushConsumerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ProxyPushConsumerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ProxyPushConsumerHelper.write (_out,value);
	}
}
