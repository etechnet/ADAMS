package org.omg.dds;


/**
 * Generated from IDL struct "ResourceLimitsQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ResourceLimitsQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ResourceLimitsQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.ResourceLimitsQosPolicyHelper.id(),"ResourceLimitsQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("max_samples", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("max_instances", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("max_samples_per_instance", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.ResourceLimitsQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.ResourceLimitsQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/ResourceLimitsQosPolicy:1.0";
	}
	public static org.omg.dds.ResourceLimitsQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.ResourceLimitsQosPolicy result = new org.omg.dds.ResourceLimitsQosPolicy();
		result.max_samples=in.read_long();
		result.max_instances=in.read_long();
		result.max_samples_per_instance=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.ResourceLimitsQosPolicy s)
	{
		out.write_long(s.max_samples);
		out.write_long(s.max_instances);
		out.write_long(s.max_samples_per_instance);
	}
}
