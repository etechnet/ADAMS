package org.omg.Security;


/**
 * Generated from IDL struct "OpaqueBuffer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class OpaqueBufferHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(OpaqueBufferHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.Security.OpaqueBufferHelper.id(),"OpaqueBuffer",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("buffer", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.Security.OpaqueHelper.id(), "Opaque",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null),new org.omg.CORBA.StructMember("startpos", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("endpos", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.Security.OpaqueBuffer s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.Security.OpaqueBuffer extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/Security/OpaqueBuffer:1.0";
	}
	public static org.omg.Security.OpaqueBuffer read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.Security.OpaqueBuffer result = new org.omg.Security.OpaqueBuffer();
		result.buffer = org.omg.Security.OpaqueHelper.read(in);
		result.startpos=in.read_ulong();
		result.endpos=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.Security.OpaqueBuffer s)
	{
		org.omg.Security.OpaqueHelper.write(out,s.buffer);
		out.write_ulong(s.startpos);
		out.write_ulong(s.endpos);
	}
}
