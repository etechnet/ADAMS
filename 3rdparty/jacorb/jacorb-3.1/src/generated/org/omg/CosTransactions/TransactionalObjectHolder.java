package org.omg.CosTransactions;

/**
 * Generated from IDL interface "TransactionalObject".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class TransactionalObjectHolder	implements org.omg.CORBA.portable.Streamable{
	 public TransactionalObject value;
	public TransactionalObjectHolder()
	{
	}
	public TransactionalObjectHolder (final TransactionalObject initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TransactionalObjectHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TransactionalObjectHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TransactionalObjectHelper.write (_out,value);
	}
}
