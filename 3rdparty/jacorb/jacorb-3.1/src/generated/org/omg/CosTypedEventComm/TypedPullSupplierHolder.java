package org.omg.CosTypedEventComm;

/**
 * Generated from IDL interface "TypedPullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public final class TypedPullSupplierHolder	implements org.omg.CORBA.portable.Streamable{
	 public TypedPullSupplier value;
	public TypedPullSupplierHolder()
	{
	}
	public TypedPullSupplierHolder (final TypedPullSupplier initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TypedPullSupplierHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TypedPullSupplierHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TypedPullSupplierHelper.write (_out,value);
	}
}
