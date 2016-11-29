package org.omg.CosTransactions;

/**
 * Generated from IDL exception "SubtransactionsUnavailable".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class SubtransactionsUnavailableHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTransactions.SubtransactionsUnavailable value;

	public SubtransactionsUnavailableHolder ()
	{
	}
	public SubtransactionsUnavailableHolder(final org.omg.CosTransactions.SubtransactionsUnavailable initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTransactions.SubtransactionsUnavailableHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTransactions.SubtransactionsUnavailableHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTransactions.SubtransactionsUnavailableHelper.write(_out, value);
	}
}
