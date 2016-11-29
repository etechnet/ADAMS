package org.omg.IOP.CodecPackage;

/**
 * Generated from IDL exception "InvalidTypeForEncoding".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class InvalidTypeForEncodingHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.IOP.CodecPackage.InvalidTypeForEncoding value;

	public InvalidTypeForEncodingHolder ()
	{
	}
	public InvalidTypeForEncodingHolder(final org.omg.IOP.CodecPackage.InvalidTypeForEncoding initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.IOP.CodecPackage.InvalidTypeForEncodingHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.IOP.CodecPackage.InvalidTypeForEncodingHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.IOP.CodecPackage.InvalidTypeForEncodingHelper.write(_out, value);
	}
}
