package org.omg.GIOP;

/**
 * Generated from IDL struct "Version".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class VersionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.GIOP.Version value;

	public VersionHolder ()
	{
	}
	public VersionHolder(final org.omg.GIOP.Version initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.GIOP.VersionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.GIOP.VersionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.GIOP.VersionHelper.write(_out, value);
	}
}
