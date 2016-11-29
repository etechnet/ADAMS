package org.omg.CosCollection;

/**
 * Generated from IDL interface "StackFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class StackFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public StackFactory value;
	public StackFactoryHolder()
	{
	}
	public StackFactoryHolder (final StackFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StackFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StackFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StackFactoryHelper.write (_out,value);
	}
}
