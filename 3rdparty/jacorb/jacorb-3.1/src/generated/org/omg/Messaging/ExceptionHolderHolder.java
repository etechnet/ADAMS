package org.omg.Messaging;

/**
 * Generated from IDL valuetype "ExceptionHolder".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ExceptionHolderHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Messaging.ExceptionHolder value;
	public ExceptionHolderHolder () {}
	public ExceptionHolderHolder (final org.omg.Messaging.ExceptionHolder initial)
	{
		value = initial;
	}
	public void _read (final org.omg.CORBA.portable.InputStream is)
	{
		value = org.omg.Messaging.ExceptionHolderHelper.read (is);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream os)
	{
		org.omg.Messaging.ExceptionHolderHelper.write (os, value);
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return value._type ();
	}
}
