package org.omg.RTCORBA;


/**
 * Generated from IDL struct "ThreadpoolLane".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ThreadpoolLaneHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ThreadpoolLaneHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.RTCORBA.ThreadpoolLaneHelper.id(),"ThreadpoolLane",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("lane_priority", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.RTCORBA.PriorityHelper.id(), "Priority",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2))), null),new org.omg.CORBA.StructMember("static_threads", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("dynamic_threads", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.RTCORBA.ThreadpoolLane s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.RTCORBA.ThreadpoolLane extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/RTCORBA/ThreadpoolLane:1.0";
	}
	public static org.omg.RTCORBA.ThreadpoolLane read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.RTCORBA.ThreadpoolLane result = new org.omg.RTCORBA.ThreadpoolLane();
		result.lane_priority=in.read_short();
		result.static_threads=in.read_ulong();
		result.dynamic_threads=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.RTCORBA.ThreadpoolLane s)
	{
		out.write_short(s.lane_priority);
		out.write_ulong(s.static_threads);
		out.write_ulong(s.dynamic_threads);
	}
}
