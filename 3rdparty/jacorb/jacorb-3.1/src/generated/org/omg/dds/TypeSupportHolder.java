package org.omg.dds;

/**
 * Generated from IDL interface "TypeSupport".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TypeSupportHolder	implements org.omg.CORBA.portable.Streamable{
	 public TypeSupport value;
	public TypeSupportHolder()
	{
	}
	public TypeSupportHolder (final TypeSupport initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TypeSupportHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TypeSupportHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TypeSupportHelper.write (_out,value);
	}
}
