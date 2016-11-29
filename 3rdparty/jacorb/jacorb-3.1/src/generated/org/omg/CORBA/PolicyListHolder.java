package org.omg.CORBA;

/**
 * Generated from IDL alias "PolicyList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PolicyListHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.Policy[] value;

	public PolicyListHolder ()
	{
	}
	public PolicyListHolder (final org.omg.CORBA.Policy[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PolicyListHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PolicyListHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PolicyListHelper.write (out,value);
	}
}
