package org.omg.CosCollection;

/**
 * Generated from IDL interface "KeyCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeyCollectionHolder	implements org.omg.CORBA.portable.Streamable{
	 public KeyCollection value;
	public KeyCollectionHolder()
	{
	}
	public KeyCollectionHolder (final KeyCollection initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KeyCollectionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KeyCollectionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KeyCollectionHelper.write (_out,value);
	}
}
