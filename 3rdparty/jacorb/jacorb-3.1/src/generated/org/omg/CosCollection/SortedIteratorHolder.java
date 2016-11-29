package org.omg.CosCollection;

/**
 * Generated from IDL interface "SortedIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SortedIteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public SortedIterator value;
	public SortedIteratorHolder()
	{
	}
	public SortedIteratorHolder (final SortedIterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return SortedIteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SortedIteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SortedIteratorHelper.write (_out,value);
	}
}
