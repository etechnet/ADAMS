package org.omg.CORBA;
/**
 * Generated from IDL enum "CompletionStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class CompletionStatusHolder
	implements org.omg.CORBA.portable.Streamable
{
	public CompletionStatus value;

	public CompletionStatusHolder ()
	{
	}
	public CompletionStatusHolder (final CompletionStatus initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return CompletionStatusHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CompletionStatusHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CompletionStatusHelper.write (out,value);
	}
}
