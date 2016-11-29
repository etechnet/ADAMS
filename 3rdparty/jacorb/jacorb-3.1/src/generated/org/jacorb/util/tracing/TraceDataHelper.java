package org.jacorb.util.tracing;


/**
 * Generated from IDL struct "TraceData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class TraceDataHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TraceDataHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.util.tracing.TraceDataHelper.id(),"TraceData",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("subtrace", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_recursive_tc("IDL:org/jacorb/util/tracing/TraceData:1.0")), null),new org.omg.CORBA.StructMember("tracer_id", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.util.tracing.PointIdHelper.id(), "PointId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null),new org.omg.CORBA.StructMember("operation", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("client_time", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.util.tracing.MSecHelper.id(), "MSec",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(23))), null),new org.omg.CORBA.StructMember("trace_system_time", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.util.tracing.MSecHelper.id(), "MSec",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(23))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.util.tracing.TraceData s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.util.tracing.TraceData extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/util/tracing/TraceData:1.0";
	}
	public static org.jacorb.util.tracing.TraceData read (final org.omg.CORBA.portable.InputStream in)
	{
		org.jacorb.util.tracing.TraceData result = new org.jacorb.util.tracing.TraceData();
		int _lresult_subtrace0 = in.read_long();
		try
		{
			 int x = in.available();
			 if ( x > 0 && _lresult_subtrace0 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _lresult_subtrace0);
				}
		}
		catch (java.io.IOException e)
		{
		}
		result.subtrace = new org.jacorb.util.tracing.TraceData[_lresult_subtrace0];
		for (int i=0;i<result.subtrace.length;i++)
		{
			result.subtrace[i]=org.jacorb.util.tracing.TraceDataHelper.read(in);
		}

		result.tracer_id=in.read_long();
		result.operation=in.read_string();
		result.client_time=in.read_longlong();
		result.trace_system_time=in.read_longlong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.util.tracing.TraceData s)
	{
		
		out.write_long(s.subtrace.length);
		for (int i=0; i<s.subtrace.length;i++)
		{
			org.jacorb.util.tracing.TraceDataHelper.write(out,s.subtrace[i]);
		}

		out.write_long(s.tracer_id);
		java.lang.String tmpResult0 = s.operation;
out.write_string( tmpResult0 );
		out.write_longlong(s.client_time);
		out.write_longlong(s.trace_system_time);
	}
}
