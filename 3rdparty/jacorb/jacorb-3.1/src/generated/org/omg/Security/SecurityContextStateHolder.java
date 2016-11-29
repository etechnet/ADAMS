package org.omg.Security;
/**
 * Generated from IDL enum "SecurityContextState".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SecurityContextStateHolder
	implements org.omg.CORBA.portable.Streamable
{
	public SecurityContextState value;

	public SecurityContextStateHolder ()
	{
	}
	public SecurityContextStateHolder (final SecurityContextState initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return SecurityContextStateHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SecurityContextStateHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		SecurityContextStateHelper.write (out,value);
	}
}
