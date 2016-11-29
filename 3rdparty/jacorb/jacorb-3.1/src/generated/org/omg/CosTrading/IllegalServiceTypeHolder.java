package org.omg.CosTrading;

/**
 * Generated from IDL exception "IllegalServiceType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalServiceTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.IllegalServiceType value;

	public IllegalServiceTypeHolder ()
	{
	}
	public IllegalServiceTypeHolder(final org.omg.CosTrading.IllegalServiceType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.IllegalServiceTypeHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.IllegalServiceTypeHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.IllegalServiceTypeHelper.write(_out, value);
	}
}
