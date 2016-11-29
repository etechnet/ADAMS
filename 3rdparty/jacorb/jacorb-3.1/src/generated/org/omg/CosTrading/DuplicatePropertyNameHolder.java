package org.omg.CosTrading;

/**
 * Generated from IDL exception "DuplicatePropertyName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class DuplicatePropertyNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.DuplicatePropertyName value;

	public DuplicatePropertyNameHolder ()
	{
	}
	public DuplicatePropertyNameHolder(final org.omg.CosTrading.DuplicatePropertyName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.DuplicatePropertyNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.DuplicatePropertyNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.DuplicatePropertyNameHelper.write(_out, value);
	}
}
