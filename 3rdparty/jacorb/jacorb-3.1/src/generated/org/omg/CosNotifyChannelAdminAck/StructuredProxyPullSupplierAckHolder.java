package org.omg.CosNotifyChannelAdminAck;

/**
 * Generated from IDL interface "StructuredProxyPullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class StructuredProxyPullSupplierAckHolder	implements org.omg.CORBA.portable.Streamable{
	 public StructuredProxyPullSupplierAck value;
	public StructuredProxyPullSupplierAckHolder()
	{
	}
	public StructuredProxyPullSupplierAckHolder (final StructuredProxyPullSupplierAck initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return StructuredProxyPullSupplierAckHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructuredProxyPullSupplierAckHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		StructuredProxyPullSupplierAckHelper.write (_out,value);
	}
}
