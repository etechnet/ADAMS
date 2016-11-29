package org.omg.CORBA;
/**
 * Generated from IDL enum "TCKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class TCKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TCKind value;

	public TCKindHolder ()
	{
	}
	public TCKindHolder (final TCKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return TCKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TCKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		TCKindHelper.write (out,value);
	}
}
