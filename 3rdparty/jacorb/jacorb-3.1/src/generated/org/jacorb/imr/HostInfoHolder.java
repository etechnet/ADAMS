package org.jacorb.imr;

/**
 * Generated from IDL struct "HostInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class HostInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.HostInfo value;

	public HostInfoHolder ()
	{
	}
	public HostInfoHolder(final org.jacorb.imr.HostInfo initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.HostInfoHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.HostInfoHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.HostInfoHelper.write(_out, value);
	}
}
