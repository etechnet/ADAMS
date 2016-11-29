package org.omg.CosCollection;

/**
 * Generated from IDL interface "KeySortedSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeySortedSetHolder	implements org.omg.CORBA.portable.Streamable{
	 public KeySortedSet value;
	public KeySortedSetHolder()
	{
	}
	public KeySortedSetHolder (final KeySortedSet initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KeySortedSetHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KeySortedSetHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KeySortedSetHelper.write (_out,value);
	}
}
