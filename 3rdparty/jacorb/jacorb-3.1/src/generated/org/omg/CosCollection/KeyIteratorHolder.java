package org.omg.CosCollection;

/**
 * Generated from IDL interface "KeyIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeyIteratorHolder	implements org.omg.CORBA.portable.Streamable{
	 public KeyIterator value;
	public KeyIteratorHolder()
	{
	}
	public KeyIteratorHolder (final KeyIterator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KeyIteratorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KeyIteratorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KeyIteratorHelper.write (_out,value);
	}
}
