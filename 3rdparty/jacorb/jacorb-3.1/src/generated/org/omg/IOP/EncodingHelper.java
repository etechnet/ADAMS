package org.omg.IOP;


/**
 * Generated from IDL struct "Encoding".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class EncodingHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EncodingHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.EncodingHelper.id(),"Encoding",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("format", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.EncodingFormatHelper.id(), "EncodingFormat",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null),new org.omg.CORBA.StructMember("major_version", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("minor_version", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.IOP.Encoding s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.IOP.Encoding extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/IOP/Encoding:1.0";
	}
	public static org.omg.IOP.Encoding read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.IOP.Encoding result = new org.omg.IOP.Encoding();
		result.format=in.read_short();
		result.major_version=in.read_octet();
		result.minor_version=in.read_octet();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.IOP.Encoding s)
	{
		out.write_short(s.format);
		out.write_octet(s.major_version);
		out.write_octet(s.minor_version);
	}
}
