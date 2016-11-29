package org.jacorb.util.tracing;


/**
 * Generated from IDL struct "Request".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class RequestHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RequestHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.jacorb.util.tracing.RequestHelper.id(),"Request",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("originator", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.util.tracing.PointIdHelper.id(), "PointId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3))), null),new org.omg.CORBA.StructMember("rid", org.omg.CORBA.ORB.init().create_alias_tc(org.jacorb.util.tracing.RequestIdHelper.id(), "RequestId",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(23))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.jacorb.util.tracing.Request s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.jacorb.util.tracing.Request extract (final org.omg.CORBA.Any any)
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
		return "IDL:org/jacorb/util/tracing/Request:1.0";
	}
	public static org.jacorb.util.tracing.Request read (final org.omg.CORBA.portable.InputStream in)
	{
		org.jacorb.util.tracing.Request result = new org.jacorb.util.tracing.Request();
		result.originator=in.read_long();
		result.rid=in.read_longlong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.jacorb.util.tracing.Request s)
	{
		out.write_long(s.originator);
		out.write_longlong(s.rid);
	}
}
