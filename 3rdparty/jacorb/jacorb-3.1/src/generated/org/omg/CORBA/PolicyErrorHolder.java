package org.omg.CORBA;

/**
 * Generated from IDL exception "PolicyError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PolicyErrorHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.PolicyError value;

	public PolicyErrorHolder ()
	{
	}
	public PolicyErrorHolder(final org.omg.CORBA.PolicyError initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CORBA.PolicyErrorHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CORBA.PolicyErrorHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CORBA.PolicyErrorHelper.write(_out, value);
	}
}
