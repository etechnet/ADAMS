package org.omg.Dynamic;

/**
 * Generated from IDL struct "Parameter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ParameterHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Dynamic.Parameter value;

	public ParameterHolder ()
	{
	}
	public ParameterHolder(final org.omg.Dynamic.Parameter initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.Dynamic.ParameterHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.Dynamic.ParameterHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.Dynamic.ParameterHelper.write(_out, value);
	}
}
