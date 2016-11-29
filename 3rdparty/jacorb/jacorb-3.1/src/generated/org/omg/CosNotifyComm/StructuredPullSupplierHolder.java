package org.omg.CosNotifyComm;

/**
 * Generated from IDL interface "StructuredPullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class StructuredPullSupplierHolder	implements org.omg.CORBA.portable.Streamable{
	 public StructuredPullSupplier value;
	public StructuredPullSupplierHolder()
	{
	}
	public StructuredPullSupplierHolder (final StructuredPullSupplier initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StructuredPullSupplierHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructuredPullSupplierHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StructuredPullSupplierHelper.write (_out,value);
	}
}
