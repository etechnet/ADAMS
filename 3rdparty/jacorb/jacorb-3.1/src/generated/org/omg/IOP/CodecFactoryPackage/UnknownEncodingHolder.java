package org.omg.IOP.CodecFactoryPackage;

/**
 * Generated from IDL exception "UnknownEncoding".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class UnknownEncodingHolder
	implements org.omg.CORBA.portable.Streamable
{
	public org.omg.IOP.CodecFactoryPackage.UnknownEncoding value;

	public UnknownEncodingHolder ()
	{
	}
	public UnknownEncodingHolder(final org.omg.IOP.CodecFactoryPackage.UnknownEncoding initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return org.omg.IOP.CodecFactoryPackage.UnknownEncodingHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = org.omg.IOP.CodecFactoryPackage.UnknownEncodingHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		org.omg.IOP.CodecFactoryPackage.UnknownEncodingHelper.write(_out, value);
	}
}
