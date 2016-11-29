package org.omg.GIOP;
/**
 * Generated from IDL union "TargetAddress".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class TargetAddressHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TargetAddress value;

	public TargetAddressHolder ()
	{
	}
	public TargetAddressHolder (final TargetAddress initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return TargetAddressHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TargetAddressHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		TargetAddressHelper.write (out, value);
	}
}
