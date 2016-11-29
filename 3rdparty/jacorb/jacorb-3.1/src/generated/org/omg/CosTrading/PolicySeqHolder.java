package org.omg.CosTrading;

/**
 * Generated from IDL alias "PolicySeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class PolicySeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CosTrading.Policy[] value;

	public PolicySeqHolder ()
	{
	}
	public PolicySeqHolder (final org.omg.CosTrading.Policy[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PolicySeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PolicySeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PolicySeqHelper.write (out,value);
	}
}
