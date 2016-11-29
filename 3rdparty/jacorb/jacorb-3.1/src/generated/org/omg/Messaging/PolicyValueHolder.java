package org.omg.Messaging;

/**
 * Generated from IDL struct "PolicyValue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class PolicyValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.Messaging.PolicyValue value;

	public PolicyValueHolder ()
	{
	}
	public PolicyValueHolder(final org.omg.Messaging.PolicyValue initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.Messaging.PolicyValueHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.Messaging.PolicyValueHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.Messaging.PolicyValueHelper.write(_out, value);
	}
}
