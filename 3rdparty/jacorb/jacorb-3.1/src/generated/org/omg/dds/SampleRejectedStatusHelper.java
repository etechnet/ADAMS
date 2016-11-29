package org.omg.dds;


/**
 * Generated from IDL struct "SampleRejectedStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SampleRejectedStatusHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SampleRejectedStatusHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.SampleRejectedStatusHelper.id(),"SampleRejectedStatus",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("total_count", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("total_count_change", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("last_reason", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.SampleRejectedStatusKindHelper.id(),"SampleRejectedStatusKind",new String[]{"REJECTED_BY_INSTANCE_LIMIT","REJECTED_BY_TOPIC_LIMIT"}), null),new org.omg.CORBA.StructMember("last_instance_handle", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.InstanceHandle_tHelper.id(), "InstanceHandle_t",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.SampleRejectedStatus s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.SampleRejectedStatus extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/SampleRejectedStatus:1.0";
	}
	public static org.omg.dds.SampleRejectedStatus read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.SampleRejectedStatus result = new org.omg.dds.SampleRejectedStatus();
		result.total_count=in.read_long();
		result.total_count_change=in.read_long();
		result.last_reason=org.omg.dds.SampleRejectedStatusKindHelper.read(in);
		result.last_instance_handle=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.SampleRejectedStatus s)
	{
		out.write_long(s.total_count);
		out.write_long(s.total_count_change);
		org.omg.dds.SampleRejectedStatusKindHelper.write(out,s.last_reason);
		out.write_long(s.last_instance_handle);
	}
}
