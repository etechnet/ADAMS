package org.omg.Security;
/**
 * Generated from IDL enum "CredentialType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CredentialTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public CredentialType value;

	public CredentialTypeHolder ()
	{
	}
	public CredentialTypeHolder (final CredentialType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return CredentialTypeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CredentialTypeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CredentialTypeHelper.write (out,value);
	}
}
