package org.omg.CosEventComm;

/**
 * Generated from IDL interface "PullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PullConsumerHolder	implements org.omg.CORBA.portable.Streamable{
	 public PullConsumer value;
	public PullConsumerHolder()
	{
	}
	public PullConsumerHolder (final PullConsumer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PullConsumerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PullConsumerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PullConsumerHelper.write (_out,value);
	}
}
