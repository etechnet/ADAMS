package org.omg.RTCORBA;

/**
 * Generated from IDL interface "Mutex".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class MutexHolder	implements org.omg.CORBA.portable.Streamable{
	 public Mutex value;
	public MutexHolder()
	{
	}
	public MutexHolder (final Mutex initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return MutexHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MutexHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		MutexHelper.write (_out,value);
	}
}
