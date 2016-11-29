package org.omg.CosCollection;

/**
 * Generated from IDL interface "Queue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class QueueHolder	implements org.omg.CORBA.portable.Streamable{
	 public Queue value;
	public QueueHolder()
	{
	}
	public QueueHolder (final Queue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return QueueHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = QueueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		QueueHelper.write (_out,value);
	}
}
