package org.omg.CosConcurrencyControl;
/**
 * Generated from IDL enum "lock_mode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class lock_modeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public lock_mode value;

	public lock_modeHolder ()
	{
	}
	public lock_modeHolder (final lock_mode initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return lock_modeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = lock_modeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		lock_modeHelper.write (out,value);
	}
}
