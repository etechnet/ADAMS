package org.omg.DynamicAny;

/**
 * Generated from IDL interface "DynArray".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynArrayHolder	implements org.omg.CORBA.portable.Streamable{
	 public DynArray value;
	public DynArrayHolder()
	{
	}
	public DynArrayHolder (final DynArray initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DynArrayHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynArrayHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DynArrayHelper.write (_out,value);
	}
}
