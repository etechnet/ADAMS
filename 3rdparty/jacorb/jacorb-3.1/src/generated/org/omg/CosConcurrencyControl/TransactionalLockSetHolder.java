package org.omg.CosConcurrencyControl;

/**
 * Generated from IDL interface "TransactionalLockSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TransactionalLockSetHolder	implements org.omg.CORBA.portable.Streamable{
	 public TransactionalLockSet value;
	public TransactionalLockSetHolder()
	{
	}
	public TransactionalLockSetHolder (final TransactionalLockSet initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TransactionalLockSetHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TransactionalLockSetHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TransactionalLockSetHelper.write (_out,value);
	}
}
