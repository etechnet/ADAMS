package org.omg.CosTrading.LinkPackage;

/**
 * Generated from IDL exception "IllegalLinkName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalLinkNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.LinkPackage.IllegalLinkName value;

	public IllegalLinkNameHolder ()
	{
	}
	public IllegalLinkNameHolder(final org.omg.CosTrading.LinkPackage.IllegalLinkName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.LinkPackage.IllegalLinkNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.LinkPackage.IllegalLinkNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.LinkPackage.IllegalLinkNameHelper.write(_out, value);
	}
}
