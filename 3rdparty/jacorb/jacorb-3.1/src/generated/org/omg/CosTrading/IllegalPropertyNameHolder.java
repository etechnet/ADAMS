package org.omg.CosTrading;

/**
 * Generated from IDL exception "IllegalPropertyName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class IllegalPropertyNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.IllegalPropertyName value;

	public IllegalPropertyNameHolder ()
	{
	}
	public IllegalPropertyNameHolder(final org.omg.CosTrading.IllegalPropertyName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.IllegalPropertyNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.IllegalPropertyNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.IllegalPropertyNameHelper.write(_out, value);
	}
}
