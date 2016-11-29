package org.omg.GIOP;

/**
 * Generated from IDL struct "SystemExceptionReplyBody".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class SystemExceptionReplyBodyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.GIOP.SystemExceptionReplyBody value;

	public SystemExceptionReplyBodyHolder ()
	{
	}
	public SystemExceptionReplyBodyHolder(final org.omg.GIOP.SystemExceptionReplyBody initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.GIOP.SystemExceptionReplyBodyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.GIOP.SystemExceptionReplyBodyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.GIOP.SystemExceptionReplyBodyHelper.write(_out, value);
	}
}
