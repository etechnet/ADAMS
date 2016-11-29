package org.omg.CosNotifyComm;

/**
 * Generated from IDL interface "NotifySubscribe".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class NotifySubscribeHolder	implements org.omg.CORBA.portable.Streamable{
	 public NotifySubscribe value;
	public NotifySubscribeHolder()
	{
	}
	public NotifySubscribeHolder (final NotifySubscribe initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return NotifySubscribeHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = NotifySubscribeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		NotifySubscribeHelper.write (_out,value);
	}
}
