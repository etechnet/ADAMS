package org.omg.CosTrading.ProxyPackage;

/**
 * Generated from IDL exception "NotProxyOfferId".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class NotProxyOfferIdHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.ProxyPackage.NotProxyOfferId value;

	public NotProxyOfferIdHolder ()
	{
	}
	public NotProxyOfferIdHolder(final org.omg.CosTrading.ProxyPackage.NotProxyOfferId initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.ProxyPackage.NotProxyOfferIdHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.ProxyPackage.NotProxyOfferIdHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.ProxyPackage.NotProxyOfferIdHelper.write(_out, value);
	}
}
