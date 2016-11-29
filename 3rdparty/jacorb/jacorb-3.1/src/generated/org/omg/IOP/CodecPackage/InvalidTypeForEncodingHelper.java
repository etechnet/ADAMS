package org.omg.IOP.CodecPackage;


/**
 * Generated from IDL exception "InvalidTypeForEncoding".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class InvalidTypeForEncodingHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(InvalidTypeForEncodingHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(org.omg.IOP.CodecPackage.InvalidTypeForEncodingHelper.id(),"InvalidTypeForEncoding",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IOP.CodecPackage.InvalidTypeForEncoding s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IOP.CodecPackage.InvalidTypeForEncoding extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/IOP/Codec/InvalidTypeForEncoding:1.0";
	}
	public static org.omg.IOP.CodecPackage.InvalidTypeForEncoding read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final org.omg.IOP.CodecPackage.InvalidTypeForEncoding result = new org.omg.IOP.CodecPackage.InvalidTypeForEncoding(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IOP.CodecPackage.InvalidTypeForEncoding s)
	{
		out.write_string(id());
	}
}
