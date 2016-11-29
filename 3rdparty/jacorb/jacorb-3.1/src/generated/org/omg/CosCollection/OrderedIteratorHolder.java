package org.omg.CosCollection;

/**
 * Generated from IDL interface "OrderedIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OrderedIteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public OrderedIterator value;
	public OrderedIteratorHolder()
	{
	}
	public OrderedIteratorHolder (final OrderedIterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return OrderedIteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OrderedIteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		OrderedIteratorHelper.write (_out,value);
	}
}
