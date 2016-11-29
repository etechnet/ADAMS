package org.omg.Security;

/**
 * Generated from IDL struct "SelectorValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class SelectorValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Security.SelectorValue value;

	public SelectorValueHolder ()
	{
	}
	public SelectorValueHolder(final org.omg.Security.SelectorValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.Security.SelectorValueHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.Security.SelectorValueHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.Security.SelectorValueHelper.write(_out, value);
	}
}
