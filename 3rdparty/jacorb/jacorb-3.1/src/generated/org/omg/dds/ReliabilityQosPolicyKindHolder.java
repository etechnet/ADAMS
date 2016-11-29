package org.omg.dds;
/**
 * Generated from IDL enum "ReliabilityQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ReliabilityQosPolicyKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ReliabilityQosPolicyKind value;

	public ReliabilityQosPolicyKindHolder ()
	{
	}
	public ReliabilityQosPolicyKindHolder (final ReliabilityQosPolicyKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ReliabilityQosPolicyKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ReliabilityQosPolicyKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ReliabilityQosPolicyKindHelper.write (out,value);
	}
}
