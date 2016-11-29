package org.omg.BridgeTransactionMgmt;

/**
 * Generated from IDL exception "TransactionActive".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransactionActiveHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.BridgeTransactionMgmt.TransactionActive value;

	public TransactionActiveHolder ()
	{
	}
	public TransactionActiveHolder(final org.omg.BridgeTransactionMgmt.TransactionActive initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.BridgeTransactionMgmt.TransactionActiveHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.BridgeTransactionMgmt.TransactionActiveHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.BridgeTransactionMgmt.TransactionActiveHelper.write(_out, value);
	}
}
