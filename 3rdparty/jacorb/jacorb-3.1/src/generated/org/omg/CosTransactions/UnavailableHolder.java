package org.omg.CosTransactions;

/**
 * Generated from IDL exception "Unavailable".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class UnavailableHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTransactions.Unavailable value;

	public UnavailableHolder ()
	{
	}
	public UnavailableHolder(final org.omg.CosTransactions.Unavailable initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTransactions.UnavailableHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTransactions.UnavailableHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTransactions.UnavailableHelper.write(_out, value);
	}
}
