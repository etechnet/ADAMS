package org.omg.DynamicAny;

/**
 * Generated from IDL interface "DynValueBox".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynValueBoxHolder	implements org.omg.CORBA.portable.Streamable{
	 public DynValueBox value;
	public DynValueBoxHolder()
	{
	}
	public DynValueBoxHolder (final DynValueBox initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DynValueBoxHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynValueBoxHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DynValueBoxHelper.write (_out,value);
	}
}
