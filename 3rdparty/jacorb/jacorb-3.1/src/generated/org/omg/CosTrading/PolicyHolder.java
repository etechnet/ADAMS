package org.omg.CosTrading;

/**
 * Generated from IDL struct "Policy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PolicyHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.Policy value;

	public PolicyHolder ()
	{
	}
	public PolicyHolder(final org.omg.CosTrading.Policy initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.CosTrading.PolicyHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.CosTrading.PolicyHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.CosTrading.PolicyHelper.write(_out, value);
	}
}
