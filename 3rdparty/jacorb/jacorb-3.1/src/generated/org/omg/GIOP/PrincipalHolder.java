package org.omg.GIOP;

/**
 * Generated from IDL alias "Principal".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PrincipalHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public PrincipalHolder ()
	{
	}
	public PrincipalHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PrincipalHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrincipalHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PrincipalHelper.write (out,value);
	}
}
