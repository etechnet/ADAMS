package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL exception "UnknownTraderName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnknownTraderNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.RegisterPackage.UnknownTraderName value;

	public UnknownTraderNameHolder ()
	{
	}
	public UnknownTraderNameHolder(final org.omg.CosTrading.RegisterPackage.UnknownTraderName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.RegisterPackage.UnknownTraderNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.RegisterPackage.UnknownTraderNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.RegisterPackage.UnknownTraderNameHelper.write(_out, value);
	}
}
