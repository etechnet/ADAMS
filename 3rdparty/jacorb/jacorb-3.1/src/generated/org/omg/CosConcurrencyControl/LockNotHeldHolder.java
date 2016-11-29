package org.omg.CosConcurrencyControl;

/**
 * Generated from IDL exception "LockNotHeld".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LockNotHeldHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosConcurrencyControl.LockNotHeld value;

	public LockNotHeldHolder ()
	{
	}
	public LockNotHeldHolder(final org.omg.CosConcurrencyControl.LockNotHeld initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosConcurrencyControl.LockNotHeldHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosConcurrencyControl.LockNotHeldHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosConcurrencyControl.LockNotHeldHelper.write(_out, value);
	}
}
