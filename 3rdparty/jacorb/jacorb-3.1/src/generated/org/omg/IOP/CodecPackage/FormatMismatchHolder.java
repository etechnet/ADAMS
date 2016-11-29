package org.omg.IOP.CodecPackage;

/**
 * Generated from IDL exception "FormatMismatch".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class FormatMismatchHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.IOP.CodecPackage.FormatMismatch value;

	public FormatMismatchHolder ()
	{
	}
	public FormatMismatchHolder(final org.omg.IOP.CodecPackage.FormatMismatch initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.IOP.CodecPackage.FormatMismatchHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.IOP.CodecPackage.FormatMismatchHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.IOP.CodecPackage.FormatMismatchHelper.write(_out, value);
	}
}
