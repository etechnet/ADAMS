package org.omg.CSI;
/**
 * Generated from IDL union "IdentityToken".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class IdentityTokenHolder
	implements org.omg.CORBA.portable.Streamable
{
	public IdentityToken value;

	public IdentityTokenHolder ()
	{
	}
	public IdentityTokenHolder (final IdentityToken initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return IdentityTokenHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IdentityTokenHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		IdentityTokenHelper.write (out, value);
	}
}
