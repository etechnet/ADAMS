package org.omg.SSLIOP;

/**
 * Generated from IDL struct "SSL".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class SSLHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.SSLIOP.SSL value;

	public SSLHolder ()
	{
	}
	public SSLHolder(final org.omg.SSLIOP.SSL initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.SSLIOP.SSLHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.SSLIOP.SSLHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.SSLIOP.SSLHelper.write(_out, value);
	}
}
