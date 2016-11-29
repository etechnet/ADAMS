package org.omg.CSI;

/**
 * Generated from IDL alias "AuthorizationToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthorizationTokenHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CSI.AuthorizationElement[] value;

	public AuthorizationTokenHolder ()
	{
	}
	public AuthorizationTokenHolder (final org.omg.CSI.AuthorizationElement[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AuthorizationTokenHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AuthorizationTokenHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AuthorizationTokenHelper.write (out,value);
	}
}
