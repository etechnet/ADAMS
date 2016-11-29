package org.omg.CosTrading;

/**
 * Generated from IDL exception "UnknownServiceType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnknownServiceTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.UnknownServiceType value;

	public UnknownServiceTypeHolder ()
	{
	}
	public UnknownServiceTypeHolder(final org.omg.CosTrading.UnknownServiceType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.UnknownServiceTypeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.UnknownServiceTypeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.UnknownServiceTypeHelper.write(_out, value);
	}
}
