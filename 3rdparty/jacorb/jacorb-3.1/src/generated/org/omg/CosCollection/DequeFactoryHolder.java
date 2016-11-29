package org.omg.CosCollection;

/**
 * Generated from IDL interface "DequeFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class DequeFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public DequeFactory value;
	public DequeFactoryHolder()
	{
	}
	public DequeFactoryHolder (final DequeFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DequeFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DequeFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DequeFactoryHelper.write (_out,value);
	}
}
