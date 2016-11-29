package org.omg.CosCollection;

/**
 * Generated from IDL interface "CollectionFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CollectionFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public CollectionFactory value;
	public CollectionFactoryHolder()
	{
	}
	public CollectionFactoryHolder (final CollectionFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CollectionFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CollectionFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CollectionFactoryHelper.write (_out,value);
	}
}
