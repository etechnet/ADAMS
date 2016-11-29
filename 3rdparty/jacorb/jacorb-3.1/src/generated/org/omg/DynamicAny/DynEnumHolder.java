package org.omg.DynamicAny;

/**
 * Generated from IDL interface "DynEnum".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynEnumHolder	implements org.omg.CORBA.portable.Streamable{
	 public DynEnum value;
	public DynEnumHolder()
	{
	}
	public DynEnumHolder (final DynEnum initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DynEnumHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynEnumHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DynEnumHelper.write (_out,value);
	}
}
