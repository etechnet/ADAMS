package org.omg.CosCollection;

/**
 * Generated from IDL interface "EqualitySequentialIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class EqualitySequentialIteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public EqualitySequentialIterator value;
	public EqualitySequentialIteratorHolder()
	{
	}
	public EqualitySequentialIteratorHolder (final EqualitySequentialIterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return EqualitySequentialIteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EqualitySequentialIteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		EqualitySequentialIteratorHelper.write (_out,value);
	}
}
