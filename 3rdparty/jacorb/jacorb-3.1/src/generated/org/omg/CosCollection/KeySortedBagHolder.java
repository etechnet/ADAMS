package org.omg.CosCollection;

/**
 * Generated from IDL interface "KeySortedBag".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeySortedBagHolder	implements org.omg.CORBA.portable.Streamable{
	 public KeySortedBag value;
	public KeySortedBagHolder()
	{
	}
	public KeySortedBagHolder (final KeySortedBag initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KeySortedBagHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KeySortedBagHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KeySortedBagHelper.write (_out,value);
	}
}
