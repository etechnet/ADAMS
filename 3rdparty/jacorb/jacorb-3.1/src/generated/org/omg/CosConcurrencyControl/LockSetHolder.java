package org.omg.CosConcurrencyControl;

/**
 * Generated from IDL interface "LockSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LockSetHolder	implements org.omg.CORBA.portable.Streamable{
	 public LockSet value;
	public LockSetHolder()
	{
	}
	public LockSetHolder (final LockSet initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return LockSetHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LockSetHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LockSetHelper.write (_out,value);
	}
}
