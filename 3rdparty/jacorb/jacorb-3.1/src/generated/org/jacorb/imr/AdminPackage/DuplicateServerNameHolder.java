package org.jacorb.imr.AdminPackage;

/**
 * Generated from IDL exception "DuplicateServerName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class DuplicateServerNameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.AdminPackage.DuplicateServerName value;

	public DuplicateServerNameHolder ()
	{
	}
	public DuplicateServerNameHolder(final org.jacorb.imr.AdminPackage.DuplicateServerName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.AdminPackage.DuplicateServerNameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.AdminPackage.DuplicateServerNameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.AdminPackage.DuplicateServerNameHelper.write(_out, value);
	}
}
