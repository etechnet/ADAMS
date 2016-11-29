package org.omg.dds;
/**
 * Generated from IDL enum "PresentationQosPolicyAccessScopeKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PresentationQosPolicyAccessScopeKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public PresentationQosPolicyAccessScopeKind value;

	public PresentationQosPolicyAccessScopeKindHolder ()
	{
	}
	public PresentationQosPolicyAccessScopeKindHolder (final PresentationQosPolicyAccessScopeKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PresentationQosPolicyAccessScopeKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PresentationQosPolicyAccessScopeKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PresentationQosPolicyAccessScopeKindHelper.write (out,value);
	}
}
