package org.omg.CosCollection;

/**
 * Generated from IDL interface "EqualitySortedCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EqualitySortedCollectionHolder	implements org.omg.CORBA.portable.Streamable{
	 public EqualitySortedCollection value;
	public EqualitySortedCollectionHolder()
	{
	}
	public EqualitySortedCollectionHolder (final EqualitySortedCollection initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EqualitySortedCollectionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EqualitySortedCollectionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EqualitySortedCollectionHelper.write (_out,value);
	}
}
