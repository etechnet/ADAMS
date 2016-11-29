package org.omg.BridgeTransactionMgmt;

/**
 * Generated from IDL interface "TransactionManagement".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class TransactionManagementHolder	implements org.omg.CORBA.portable.Streamable{
	 public TransactionManagement value;
	public TransactionManagementHolder()
	{
	}
	public TransactionManagementHolder (final TransactionManagement initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TransactionManagementHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TransactionManagementHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TransactionManagementHelper.write (_out,value);
	}
}
