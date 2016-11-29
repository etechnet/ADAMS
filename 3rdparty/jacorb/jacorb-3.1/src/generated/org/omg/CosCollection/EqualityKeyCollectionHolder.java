package org.omg.CosCollection;

/**
 * Generated from IDL interface "EqualityKeyCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EqualityKeyCollectionHolder	implements org.omg.CORBA.portable.Streamable{
	 public EqualityKeyCollection value;
	public EqualityKeyCollectionHolder()
	{
	}
	public EqualityKeyCollectionHolder (final EqualityKeyCollection initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EqualityKeyCollectionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EqualityKeyCollectionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EqualityKeyCollectionHelper.write (_out,value);
	}
}
