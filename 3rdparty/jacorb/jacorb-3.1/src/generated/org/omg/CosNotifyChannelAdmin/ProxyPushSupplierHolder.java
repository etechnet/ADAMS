package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL interface "ProxyPushSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ProxyPushSupplierHolder	implements org.omg.CORBA.portable.Streamable{
	 public ProxyPushSupplier value;
	public ProxyPushSupplierHolder()
	{
	}
	public ProxyPushSupplierHolder (final ProxyPushSupplier initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ProxyPushSupplierHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ProxyPushSupplierHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ProxyPushSupplierHelper.write (_out,value);
	}
}
