package org.jacorb.imr;

/**
 * Generated from IDL interface "Registration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public final class RegistrationHolder	implements org.omg.CORBA.portable.Streamable{
	 public Registration value;
	public RegistrationHolder()
	{
	}
	public RegistrationHolder (final Registration initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RegistrationHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RegistrationHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RegistrationHelper.write (_out,value);
	}
}
