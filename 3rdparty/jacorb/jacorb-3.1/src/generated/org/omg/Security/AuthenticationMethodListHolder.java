package org.omg.Security;

/**
 * Generated from IDL alias "AuthenticationMethodList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class AuthenticationMethodListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public AuthenticationMethodListHolder ()
	{
	}
	public AuthenticationMethodListHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return AuthenticationMethodListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AuthenticationMethodListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AuthenticationMethodListHelper.write (out,value);
	}
}
