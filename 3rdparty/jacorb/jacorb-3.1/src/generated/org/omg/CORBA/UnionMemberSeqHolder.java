package org.omg.CORBA;

/**
 * Generated from IDL alias "UnionMemberSeq".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class UnionMemberSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.CORBA.UnionMember[] value;

	public UnionMemberSeqHolder ()
	{
	}
	public UnionMemberSeqHolder (final org.omg.CORBA.UnionMember[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return UnionMemberSeqHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = UnionMemberSeqHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		UnionMemberSeqHelper.write (out,value);
	}
}
