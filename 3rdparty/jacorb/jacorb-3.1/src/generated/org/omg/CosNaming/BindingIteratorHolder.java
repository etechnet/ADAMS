package org.omg.CosNaming;

/**
 * Generated from IDL interface "BindingIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class BindingIteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public BindingIterator value;
	public BindingIteratorHolder()
	{
	}
	public BindingIteratorHolder (final BindingIterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return BindingIteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = BindingIteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		BindingIteratorHelper.write (_out,value);
	}
}
