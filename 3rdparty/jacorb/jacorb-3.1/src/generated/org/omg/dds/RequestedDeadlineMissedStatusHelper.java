package org.omg.dds;


/**
 * Generated from IDL struct "RequestedDeadlineMissedStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class RequestedDeadlineMissedStatusHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RequestedDeadlineMissedStatusHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.RequestedDeadlineMissedStatusHelper.id(),"RequestedDeadlineMissedStatus",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("total_count", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("total_count_change", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("last_instance_handle", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.InstanceHandle_tHelper.id(), "InstanceHandle_t",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.RequestedDeadlineMissedStatus s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.RequestedDeadlineMissedStatus extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/RequestedDeadlineMissedStatus:1.0";
	}
	public static org.omg.dds.RequestedDeadlineMissedStatus read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.RequestedDeadlineMissedStatus result = new org.omg.dds.RequestedDeadlineMissedStatus();
		result.total_count=in.read_long();
		result.total_count_change=in.read_long();
		result.last_instance_handle=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.RequestedDeadlineMissedStatus s)
	{
		out.write_long(s.total_count);
		out.write_long(s.total_count_change);
		out.write_long(s.last_instance_handle);
	}
}
