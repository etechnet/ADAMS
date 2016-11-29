package org.omg.dds;
/**
 * Generated from IDL enum "LivelinessQosPolicyKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LivelinessQosPolicyKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public LivelinessQosPolicyKind value;

	public LivelinessQosPolicyKindHolder ()
	{
	}
	public LivelinessQosPolicyKindHolder (final LivelinessQosPolicyKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return LivelinessQosPolicyKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LivelinessQosPolicyKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		LivelinessQosPolicyKindHelper.write (out,value);
	}
}
