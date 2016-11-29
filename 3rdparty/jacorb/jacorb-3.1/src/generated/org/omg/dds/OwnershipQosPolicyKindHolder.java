package org.omg.dds;
/**
 * Generated from IDL enum "OwnershipQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OwnershipQosPolicyKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public OwnershipQosPolicyKind value;

	public OwnershipQosPolicyKindHolder ()
	{
	}
	public OwnershipQosPolicyKindHolder (final OwnershipQosPolicyKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return OwnershipQosPolicyKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = OwnershipQosPolicyKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		OwnershipQosPolicyKindHelper.write (out,value);
	}
}
