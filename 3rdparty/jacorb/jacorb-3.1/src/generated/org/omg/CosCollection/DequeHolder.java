package org.omg.CosCollection;

/**
 * Generated from IDL interface "Deque".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class DequeHolder	implements org.omg.CORBA.portable.Streamable{
	 public Deque value;
	public DequeHolder()
	{
	}
	public DequeHolder (final Deque initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DequeHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DequeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DequeHelper.write (_out,value);
	}
}