package org.omg.CosTransactions;

/**
 * Generated from IDL interface "TransactionFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TransactionFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public TransactionFactory value;
	public TransactionFactoryHolder()
	{
	}
	public TransactionFactoryHolder (final TransactionFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TransactionFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TransactionFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TransactionFactoryHelper.write (_out,value);
	}
}
