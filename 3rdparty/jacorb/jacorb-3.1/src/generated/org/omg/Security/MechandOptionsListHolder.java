package org.omg.Security;

/**
 * Generated from IDL alias "MechandOptionsList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class MechandOptionsListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Security.MechandOptions[] value;

	public MechandOptionsListHolder ()
	{
	}
	public MechandOptionsListHolder (final org.omg.Security.MechandOptions[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return MechandOptionsListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MechandOptionsListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		MechandOptionsListHelper.write (out,value);
	}
}
