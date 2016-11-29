package org.omg.CosNotifyComm;

/**
 * Generated from IDL interface "NotifyPublish".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class NotifyPublishHolder	implements org.omg.CORBA.portable.Streamable{
	 public NotifyPublish value;
	public NotifyPublishHolder()
	{
	}
	public NotifyPublishHolder (final NotifyPublish initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return NotifyPublishHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = NotifyPublishHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		NotifyPublishHelper.write (_out,value);
	}
}
