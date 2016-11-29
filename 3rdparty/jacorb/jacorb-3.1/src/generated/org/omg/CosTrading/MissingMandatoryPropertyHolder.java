package org.omg.CosTrading;

/**
 * Generated from IDL exception "MissingMandatoryProperty".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class MissingMandatoryPropertyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.MissingMandatoryProperty value;

	public MissingMandatoryPropertyHolder ()
	{
	}
	public MissingMandatoryPropertyHolder(final org.omg.CosTrading.MissingMandatoryProperty initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.MissingMandatoryPropertyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.MissingMandatoryPropertyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.MissingMandatoryPropertyHelper.write(_out, value);
	}
}
