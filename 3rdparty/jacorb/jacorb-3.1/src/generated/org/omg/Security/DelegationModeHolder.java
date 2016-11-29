package org.omg.Security;
/**
 * Generated from IDL enum "DelegationMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class DelegationModeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public DelegationMode value;

	public DelegationModeHolder ()
	{
	}
	public DelegationModeHolder (final DelegationMode initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DelegationModeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DelegationModeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DelegationModeHelper.write (out,value);
	}
}
