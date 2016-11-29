package org.omg.CORBA;
/**
 * Generated from IDL enum "ParameterMode".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class ParameterModeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ParameterMode value;

	public ParameterModeHolder ()
	{
	}
	public ParameterModeHolder (final ParameterMode initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ParameterModeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ParameterModeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ParameterModeHelper.write (out,value);
	}
}
