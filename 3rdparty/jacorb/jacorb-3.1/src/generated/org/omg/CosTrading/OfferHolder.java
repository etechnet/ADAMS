package org.omg.CosTrading;

/**
 * Generated from IDL struct "Offer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class OfferHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.Offer value;

	public OfferHolder ()
	{
	}
	public OfferHolder(final org.omg.CosTrading.Offer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.OfferHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.OfferHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.OfferHelper.write(_out, value);
	}
}
