package org.omg.CosNotifyComm;

/**
 * Generated from IDL interface "StructuredPullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class StructuredPullConsumerHolder	implements org.omg.CORBA.portable.Streamable{
	 public StructuredPullConsumer value;
	public StructuredPullConsumerHolder()
	{
	}
	public StructuredPullConsumerHolder (final StructuredPullConsumer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StructuredPullConsumerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructuredPullConsumerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StructuredPullConsumerHelper.write (_out,value);
	}
}
