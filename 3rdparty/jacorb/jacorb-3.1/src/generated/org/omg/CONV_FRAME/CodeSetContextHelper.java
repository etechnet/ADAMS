package org.omg.CONV_FRAME;


/**
 * Generated from IDL struct "CodeSetContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public final class CodeSetContextHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(CodeSetContextHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.CONV_FRAME.CodeSetContextHelper.id(),"CodeSetContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("char_data", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CONV_FRAME.CodeSetIdHelper.id(), "CodeSetId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("wchar_data", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.CONV_FRAME.CodeSetIdHelper.id(), "CodeSetId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.CONV_FRAME.CodeSetContext s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.CONV_FRAME.CodeSetContext extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/CONV_FRAME/CodeSetContext:1.0";
	}
	public static org.omg.CONV_FRAME.CodeSetContext read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.CONV_FRAME.CodeSetContext result = new org.omg.CONV_FRAME.CodeSetContext();
		result.char_data=in.read_ulong();
		result.wchar_data=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.CONV_FRAME.CodeSetContext s)
	{
		out.write_ulong(s.char_data);
		out.write_ulong(s.wchar_data);
	}
}
