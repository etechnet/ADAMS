package org.omg.GIOP;


/**
 * Generated from IDL struct "SystemExceptionReplyBody".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class SystemExceptionReplyBodyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SystemExceptionReplyBodyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.SystemExceptionReplyBodyHelper.id(),"SystemExceptionReplyBody",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("exception_id", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("minor_code_value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("completion_status", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.SystemExceptionReplyBody s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.SystemExceptionReplyBody extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/SystemExceptionReplyBody:1.0";
	}
	public static org.omg.GIOP.SystemExceptionReplyBody read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.GIOP.SystemExceptionReplyBody result = new org.omg.GIOP.SystemExceptionReplyBody();
		result.exception_id=in.read_string();
		result.minor_code_value=in.read_ulong();
		result.completion_status=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.GIOP.SystemExceptionReplyBody s)
	{
		java.lang.String tmpResult984 = s.exception_id;
out.write_string( tmpResult984 );
		out.write_ulong(s.minor_code_value);
		out.write_ulong(s.completion_status);
	}
}
