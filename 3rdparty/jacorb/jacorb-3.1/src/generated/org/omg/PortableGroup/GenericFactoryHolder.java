package org.omg.PortableGroup;

/**
 * Generated from IDL interface "GenericFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class GenericFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public GenericFactory value;
	public GenericFactoryHolder()
	{
	}
	public GenericFactoryHolder (final GenericFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return GenericFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = GenericFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		GenericFactoryHelper.write (_out,value);
	}
}
