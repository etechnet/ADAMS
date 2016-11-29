package org.omg.dds;
/**
 * Generated from IDL enum "HistoryQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class HistoryQosPolicyKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public HistoryQosPolicyKind value;

	public HistoryQosPolicyKindHolder ()
	{
	}
	public HistoryQosPolicyKindHolder (final HistoryQosPolicyKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return HistoryQosPolicyKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = HistoryQosPolicyKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		HistoryQosPolicyKindHelper.write (out,value);
	}
}
