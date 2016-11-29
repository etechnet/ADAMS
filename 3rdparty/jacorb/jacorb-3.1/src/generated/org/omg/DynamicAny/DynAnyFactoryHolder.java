package org.omg.DynamicAny;

/**
 * Generated from IDL interface "DynAnyFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynAnyFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public DynAnyFactory value;
	public DynAnyFactoryHolder()
	{
	}
	public DynAnyFactoryHolder (final DynAnyFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DynAnyFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynAnyFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DynAnyFactoryHelper.write (_out,value);
	}
}
