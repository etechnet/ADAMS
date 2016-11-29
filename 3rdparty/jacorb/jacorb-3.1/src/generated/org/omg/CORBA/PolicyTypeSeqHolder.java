package org.omg.CORBA;

/**
 * Generated from IDL alias "PolicyTypeSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PolicyTypeSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public PolicyTypeSeqHolder ()
	{
	}
	public PolicyTypeSeqHolder (final int[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PolicyTypeSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PolicyTypeSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PolicyTypeSeqHelper.write (out,value);
	}
}
