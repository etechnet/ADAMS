package org.omg.Dynamic;

/**
 * Generated from IDL alias "ParameterList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ParameterListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Dynamic.Parameter[] value;

	public ParameterListHolder ()
	{
	}
	public ParameterListHolder (final org.omg.Dynamic.Parameter[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ParameterListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ParameterListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ParameterListHelper.write (out,value);
	}
}
