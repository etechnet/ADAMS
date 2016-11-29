package org.omg.CSIIOP;

/**
 * Generated from IDL struct "AS_ContextSec".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AS_ContextSecHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSIIOP.AS_ContextSec value;

	public AS_ContextSecHolder ()
	{
	}
	public AS_ContextSecHolder(final org.omg.CSIIOP.AS_ContextSec initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CSIIOP.AS_ContextSecHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CSIIOP.AS_ContextSecHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CSIIOP.AS_ContextSecHelper.write(_out, value);
	}
}
