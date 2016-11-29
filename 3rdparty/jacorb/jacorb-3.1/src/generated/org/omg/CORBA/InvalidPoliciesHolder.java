package org.omg.CORBA;

/**
 * Generated from IDL exception "InvalidPolicies".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class InvalidPoliciesHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.InvalidPolicies value;

	public InvalidPoliciesHolder ()
	{
	}
	public InvalidPoliciesHolder(final org.omg.CORBA.InvalidPolicies initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.InvalidPoliciesHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.InvalidPoliciesHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.InvalidPoliciesHelper.write(_out, value);
	}
}
