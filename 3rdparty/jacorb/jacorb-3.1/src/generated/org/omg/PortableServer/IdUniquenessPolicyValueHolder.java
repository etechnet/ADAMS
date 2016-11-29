package org.omg.PortableServer;
/**
 * Generated from IDL enum "IdUniquenessPolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class IdUniquenessPolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public IdUniquenessPolicyValue value;

	public IdUniquenessPolicyValueHolder ()
	{
	}
	public IdUniquenessPolicyValueHolder (final IdUniquenessPolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return IdUniquenessPolicyValueHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IdUniquenessPolicyValueHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		IdUniquenessPolicyValueHelper.write (out,value);
	}
}
