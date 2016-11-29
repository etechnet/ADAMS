package org.omg.CosCollection;

/**
 * Generated from IDL interface "Iterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public Iterator value;
	public IteratorHolder()
	{
	}
	public IteratorHolder (final Iterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return IteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		IteratorHelper.write (_out,value);
	}
}
