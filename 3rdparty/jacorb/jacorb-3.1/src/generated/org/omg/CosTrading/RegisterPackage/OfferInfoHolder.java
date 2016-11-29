package org.omg.CosTrading.RegisterPackage;

/**
 * Generated from IDL struct "OfferInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OfferInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.RegisterPackage.OfferInfo value;

	public OfferInfoHolder ()
	{
	}
	public OfferInfoHolder(final org.omg.CosTrading.RegisterPackage.OfferInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.RegisterPackage.OfferInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.RegisterPackage.OfferInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.RegisterPackage.OfferInfoHelper.write(_out, value);
	}
}
