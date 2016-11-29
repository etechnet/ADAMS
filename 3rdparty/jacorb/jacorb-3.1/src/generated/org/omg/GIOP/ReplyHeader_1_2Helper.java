package org.omg.GIOP;


/**
 * Generated from IDL struct "ReplyHeader_1_2".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ReplyHeader_1_2Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ReplyHeader_1_2Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.ReplyHeader_1_2Helper.id(),"ReplyHeader_1_2",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("request_id", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("reply_status", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.GIOP.ReplyStatusType_1_2Helper.id(),"ReplyStatusType_1_2",new String[]{"NO_EXCEPTION","USER_EXCEPTION","SYSTEM_EXCEPTION","LOCATION_FORWARD","LOCATION_FORWARD_PERM","NEEDS_ADDRESSING_MODE"}), null),new org.omg.CORBA.StructMember("service_context", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ServiceContextListHelper.id(), "ServiceContextList",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(org.omg.IOP.ServiceContextHelper.id(),"ServiceContext",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("context_id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.IOP.ServiceIdHelper.id(), "ServiceId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5))), null),new org.omg.CORBA.StructMember("context_data", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.ReplyHeader_1_2 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.ReplyHeader_1_2 extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/ReplyHeader_1_2:1.0";
	}
	public static org.omg.GIOP.ReplyHeader_1_2 read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.GIOP.ReplyHeader_1_2 result = new org.omg.GIOP.ReplyHeader_1_2();
		result.request_id=in.read_ulong();
		result.reply_status=org.omg.GIOP.ReplyStatusType_1_2Helper.read(in);
		result.service_context = org.omg.IOP.ServiceContextListHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.GIOP.ReplyHeader_1_2 s)
	{
		out.write_ulong(s.request_id);
		org.omg.GIOP.ReplyStatusType_1_2Helper.write(out,s.reply_status);
		org.omg.IOP.ServiceContextListHelper.write(out,s.service_context);
	}
}
