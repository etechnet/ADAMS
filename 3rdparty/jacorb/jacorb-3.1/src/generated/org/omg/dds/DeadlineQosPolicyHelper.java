package org.omg.dds;


/**
 * Generated from IDL struct "DeadlineQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DeadlineQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DeadlineQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DeadlineQosPolicyHelper.id(),"DeadlineQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("period", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.DeadlineQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.DeadlineQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/DeadlineQosPolicy:1.0";
	}
	public static org.omg.dds.DeadlineQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.DeadlineQosPolicy result = new org.omg.dds.DeadlineQosPolicy();
		result.period=org.omg.dds.Duration_tHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.DeadlineQosPolicy s)
	{
		org.omg.dds.Duration_tHelper.write(out,s.period);
	}
}
