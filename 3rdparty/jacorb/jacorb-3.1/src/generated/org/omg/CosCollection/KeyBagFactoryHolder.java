package org.omg.CosCollection;

/**
 * Generated from IDL interface "KeyBagFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeyBagFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public KeyBagFactory value;
	public KeyBagFactoryHolder()
	{
	}
	public KeyBagFactoryHolder (final KeyBagFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KeyBagFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KeyBagFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KeyBagFactoryHelper.write (_out,value);
	}
}
