package org.omg.CosCollection;

/**
 * Generated from IDL interface "EqualityKeyIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EqualityKeyIteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public EqualityKeyIterator value;
	public EqualityKeyIteratorHolder()
	{
	}
	public EqualityKeyIteratorHolder (final EqualityKeyIterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EqualityKeyIteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EqualityKeyIteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EqualityKeyIteratorHelper.write (_out,value);
	}
}
