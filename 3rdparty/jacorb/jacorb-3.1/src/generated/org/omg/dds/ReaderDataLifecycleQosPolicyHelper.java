package org.omg.dds;


/**
 * Generated from IDL struct "ReaderDataLifecycleQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ReaderDataLifecycleQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ReaderDataLifecycleQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.ReaderDataLifecycleQosPolicyHelper.id(),"ReaderDataLifecycleQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("autopurge_nowriter_samples_delay", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.ReaderDataLifecycleQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.ReaderDataLifecycleQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/ReaderDataLifecycleQosPolicy:1.0";
	}
	public static org.omg.dds.ReaderDataLifecycleQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.ReaderDataLifecycleQosPolicy result = new org.omg.dds.ReaderDataLifecycleQosPolicy();
		result.autopurge_nowriter_samples_delay=org.omg.dds.Duration_tHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.ReaderDataLifecycleQosPolicy s)
	{
		org.omg.dds.Duration_tHelper.write(out,s.autopurge_nowriter_samples_delay);
	}
}
