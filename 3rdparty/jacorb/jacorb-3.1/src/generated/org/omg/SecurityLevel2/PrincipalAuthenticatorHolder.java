package org.omg.SecurityLevel2;

/**
 * Generated from IDL interface "PrincipalAuthenticator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.37
 */

public final class PrincipalAuthenticatorHolder	implements org.omg.CORBA.portable.Streamable{
	 public PrincipalAuthenticator value;
	public PrincipalAuthenticatorHolder()
	{
	}
	public PrincipalAuthenticatorHolder (final PrincipalAuthenticator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return PrincipalAuthenticatorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrincipalAuthenticatorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		PrincipalAuthenticatorHelper.write (_out,value);
	}
}
