package org.omg.GSSUP;

/**
 * Generated from IDL struct "ErrorToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ErrorTokenHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.GSSUP.ErrorToken value;

	public ErrorTokenHolder ()
	{
	}
	public ErrorTokenHolder(final org.omg.GSSUP.ErrorToken initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.GSSUP.ErrorTokenHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.GSSUP.ErrorTokenHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.GSSUP.ErrorTokenHelper.write(_out, value);
	}
}
