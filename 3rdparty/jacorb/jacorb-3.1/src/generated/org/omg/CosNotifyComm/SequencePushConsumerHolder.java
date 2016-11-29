package org.omg.CosNotifyComm;

/**
 * Generated from IDL interface "SequencePushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SequencePushConsumerHolder	implements org.omg.CORBA.portable.Streamable{
	 public SequencePushConsumer value;
	public SequencePushConsumerHolder()
	{
	}
	public SequencePushConsumerHolder (final SequencePushConsumer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SequencePushConsumerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SequencePushConsumerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SequencePushConsumerHelper.write (_out,value);
	}
}
