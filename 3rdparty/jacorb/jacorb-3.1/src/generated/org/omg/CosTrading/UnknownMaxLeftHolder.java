package org.omg.CosTrading;

/**
 * Generated from IDL exception "UnknownMaxLeft".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnknownMaxLeftHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.UnknownMaxLeft value;

	public UnknownMaxLeftHolder ()
	{
	}
	public UnknownMaxLeftHolder(final org.omg.CosTrading.UnknownMaxLeft initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.UnknownMaxLeftHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.UnknownMaxLeftHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.UnknownMaxLeftHelper.write(_out, value);
	}
}
