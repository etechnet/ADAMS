package org.omg.CosNotifyChannelAdminAck;

/**
 * Generated from IDL interface "StructuredProxyPushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class StructuredProxyPushSupplierAckHolder	implements org.omg.CORBA.portable.Streamable{
	 public StructuredProxyPushSupplierAck value;
	public StructuredProxyPushSupplierAckHolder()
	{
	}
	public StructuredProxyPushSupplierAckHolder (final StructuredProxyPushSupplierAck initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StructuredProxyPushSupplierAckHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructuredProxyPushSupplierAckHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StructuredProxyPushSupplierAckHelper.write (_out,value);
	}
}
