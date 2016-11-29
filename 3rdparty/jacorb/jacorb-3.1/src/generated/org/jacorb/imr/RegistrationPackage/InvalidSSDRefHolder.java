package org.jacorb.imr.RegistrationPackage;

/**
 * Generated from IDL exception "InvalidSSDRef".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class InvalidSSDRefHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.jacorb.imr.RegistrationPackage.InvalidSSDRef value;

	public InvalidSSDRefHolder ()
	{
	}
	public InvalidSSDRefHolder(final org.jacorb.imr.RegistrationPackage.InvalidSSDRef initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.jacorb.imr.RegistrationPackage.InvalidSSDRefHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.jacorb.imr.RegistrationPackage.InvalidSSDRefHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.jacorb.imr.RegistrationPackage.InvalidSSDRefHelper.write(_out, value);
	}
}
