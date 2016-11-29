package org.omg.dds;
/**
 * Generated from IDL enum "DestinationOrderQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DestinationOrderQosPolicyKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public DestinationOrderQosPolicyKind value;

	public DestinationOrderQosPolicyKindHolder ()
	{
	}
	public DestinationOrderQosPolicyKindHolder (final DestinationOrderQosPolicyKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return DestinationOrderQosPolicyKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DestinationOrderQosPolicyKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DestinationOrderQosPolicyKindHelper.write (out,value);
	}
}
