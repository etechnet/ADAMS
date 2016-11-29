package org.omg.CosCollection;

/**
 * Generated from IDL interface "OrderedCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OrderedCollectionHolder	implements org.omg.CORBA.portable.Streamable{
	 public OrderedCollection value;
	public OrderedCollectionHolder()
	{
	}
	public OrderedCollectionHolder (final OrderedCollection initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return OrderedCollectionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OrderedCollectionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		OrderedCollectionHelper.write (_out,value);
	}
}
