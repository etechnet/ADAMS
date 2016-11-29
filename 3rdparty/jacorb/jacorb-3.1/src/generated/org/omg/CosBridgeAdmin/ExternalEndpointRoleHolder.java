package org.omg.CosBridgeAdmin;
/**
 * Generated from IDL enum "ExternalEndpointRole".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ExternalEndpointRoleHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ExternalEndpointRole value;

	public ExternalEndpointRoleHolder ()
	{
	}
	public ExternalEndpointRoleHolder (final ExternalEndpointRole initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ExternalEndpointRoleHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ExternalEndpointRoleHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ExternalEndpointRoleHelper.write (out,value);
	}
}
