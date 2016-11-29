package org.jacorb.imr.RegistrationPackage;

/**
 * Generated from IDL exception "DuplicatePOAName".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class DuplicatePOANameHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.RegistrationPackage.DuplicatePOAName value;

	public DuplicatePOANameHolder ()
	{
	}
	public DuplicatePOANameHolder(final org.jacorb.imr.RegistrationPackage.DuplicatePOAName initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.RegistrationPackage.DuplicatePOANameHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.RegistrationPackage.DuplicatePOANameHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.RegistrationPackage.DuplicatePOANameHelper.write(_out, value);
	}
}
