package org.omg.CosNotifyChannelAdmin;

/**
 * Generated from IDL interface "ProxySupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ProxySupplierHolder	implements org.omg.CORBA.portable.Streamable{
	 public ProxySupplier value;
	public ProxySupplierHolder()
	{
	}
	public ProxySupplierHolder (final ProxySupplier initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ProxySupplierHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ProxySupplierHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ProxySupplierHelper.write (_out,value);
	}
}
