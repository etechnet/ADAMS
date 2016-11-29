package org.omg.CosNotifyCommAck;

/**
 * Generated from IDL interface "StructuredPullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class StructuredPullSupplierAckHolder	implements org.omg.CORBA.portable.Streamable{
	 public StructuredPullSupplierAck value;
	public StructuredPullSupplierAckHolder()
	{
	}
	public StructuredPullSupplierAckHolder (final StructuredPullSupplierAck initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StructuredPullSupplierAckHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructuredPullSupplierAckHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StructuredPullSupplierAckHelper.write (_out,value);
	}
}
