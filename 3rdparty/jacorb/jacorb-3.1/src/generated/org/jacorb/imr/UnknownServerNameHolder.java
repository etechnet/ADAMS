package org.jacorb.imr;

/**
 * Generated from IDL exception "UnknownServerName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class UnknownServerNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.UnknownServerName value;

	public UnknownServerNameHolder ()
	{
	}
	public UnknownServerNameHolder(final org.jacorb.imr.UnknownServerName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.UnknownServerNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.UnknownServerNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.UnknownServerNameHelper.write(_out, value);
	}
}
