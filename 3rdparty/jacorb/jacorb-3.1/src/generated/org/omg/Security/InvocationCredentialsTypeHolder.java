package org.omg.Security;
/**
 * Generated from IDL enum "InvocationCredentialsType".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class InvocationCredentialsTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public InvocationCredentialsType value;

	public InvocationCredentialsTypeHolder ()
	{
	}
	public InvocationCredentialsTypeHolder (final InvocationCredentialsType initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return InvocationCredentialsTypeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = InvocationCredentialsTypeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		InvocationCredentialsTypeHelper.write (out,value);
	}
}
