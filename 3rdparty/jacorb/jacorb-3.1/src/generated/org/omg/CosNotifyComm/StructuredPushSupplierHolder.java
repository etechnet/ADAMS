package org.omg.CosNotifyComm;

/**
 * Generated from IDL interface "StructuredPushSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class StructuredPushSupplierHolder	implements org.omg.CORBA.portable.Streamable{
	 public StructuredPushSupplier value;
	public StructuredPushSupplierHolder()
	{
	}
	public StructuredPushSupplierHolder (final StructuredPushSupplier initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StructuredPushSupplierHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructuredPushSupplierHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StructuredPushSupplierHelper.write (_out,value);
	}
}
