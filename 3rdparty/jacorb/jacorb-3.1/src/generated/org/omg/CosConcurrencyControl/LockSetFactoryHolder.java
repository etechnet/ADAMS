package org.omg.CosConcurrencyControl;

/**
 * Generated from IDL interface "LockSetFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LockSetFactoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public LockSetFactory value;
	public LockSetFactoryHolder()
	{
	}
	public LockSetFactoryHolder (final LockSetFactory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return LockSetFactoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LockSetFactoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LockSetFactoryHelper.write (_out,value);
	}
}
