package org.omg.BridgeTransactionMgmt;

/**
 * Generated from IDL exception "UnsupportedTransaction".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class UnsupportedTransactionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.BridgeTransactionMgmt.UnsupportedTransaction value;

	public UnsupportedTransactionHolder ()
	{
	}
	public UnsupportedTransactionHolder(final org.omg.BridgeTransactionMgmt.UnsupportedTransaction initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.BridgeTransactionMgmt.UnsupportedTransactionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.BridgeTransactionMgmt.UnsupportedTransactionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.BridgeTransactionMgmt.UnsupportedTransactionHelper.write(_out, value);
	}
}
