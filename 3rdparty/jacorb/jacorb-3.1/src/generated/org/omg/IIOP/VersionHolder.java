package org.omg.IIOP;

/**
 * Generated from IDL struct "Version".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class VersionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.IIOP.Version value;

	public VersionHolder ()
	{
	}
	public VersionHolder(final org.omg.IIOP.Version initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.IIOP.VersionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.IIOP.VersionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.IIOP.VersionHelper.write(_out, value);
	}
}
