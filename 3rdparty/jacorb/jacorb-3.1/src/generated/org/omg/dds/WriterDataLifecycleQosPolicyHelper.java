package org.omg.dds;


/**
 * Generated from IDL struct "WriterDataLifecycleQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class WriterDataLifecycleQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(WriterDataLifecycleQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.WriterDataLifecycleQosPolicyHelper.id(),"WriterDataLifecycleQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("autodispose_unregistered_instances", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.WriterDataLifecycleQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.WriterDataLifecycleQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/WriterDataLifecycleQosPolicy:1.0";
	}
	public static org.omg.dds.WriterDataLifecycleQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.WriterDataLifecycleQosPolicy result = new org.omg.dds.WriterDataLifecycleQosPolicy();
		result.autodispose_unregistered_instances=in.read_boolean();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.WriterDataLifecycleQosPolicy s)
	{
		out.write_boolean(s.autodispose_unregistered_instances);
	}
}
