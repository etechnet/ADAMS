package org.omg.CORBA;
/**
 * Generated from IDL enum "PrimitiveKind".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public final class PrimitiveKindHolder
	implements org.omg.CORBA.portable.Streamable
{
	public PrimitiveKind value;

	public PrimitiveKindHolder ()
	{
	}
	public PrimitiveKindHolder (final PrimitiveKind initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return PrimitiveKindHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PrimitiveKindHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PrimitiveKindHelper.write (out,value);
	}
}
