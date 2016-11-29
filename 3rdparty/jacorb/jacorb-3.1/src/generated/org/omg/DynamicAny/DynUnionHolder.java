package org.omg.DynamicAny;

/**
 * Generated from IDL interface "DynUnion".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class DynUnionHolder	implements org.omg.CORBA.portable.Streamable{
	 public DynUnion value;
	public DynUnionHolder()
	{
	}
	public DynUnionHolder (final DynUnion initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return DynUnionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DynUnionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DynUnionHelper.write (_out,value);
	}
}
