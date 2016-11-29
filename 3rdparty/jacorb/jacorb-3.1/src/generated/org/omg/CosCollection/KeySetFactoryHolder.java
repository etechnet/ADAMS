package org.omg.CosCollection;

/**
 * Generated from IDL interface "KeySetFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class KeySetFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public KeySetFactory value;
	public KeySetFactoryHolder()
	{
	}
	public KeySetFactoryHolder (final KeySetFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return KeySetFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = KeySetFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		KeySetFactoryHelper.write (_out,value);
	}
}
