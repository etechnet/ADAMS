package org.omg.Dynamic;

/**
 * Generated from IDL alias "ExceptionList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ExceptionListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.TypeCode[] value;

	public ExceptionListHolder ()
	{
	}
	public ExceptionListHolder (final org.omg.CORBA.TypeCode[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExceptionListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExceptionListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExceptionListHelper.write (out,value);
	}
}
