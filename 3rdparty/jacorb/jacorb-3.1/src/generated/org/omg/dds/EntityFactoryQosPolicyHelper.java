package org.omg.dds;


/**
 * Generated from IDL struct "EntityFactoryQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class EntityFactoryQosPolicyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(EntityFactoryQosPolicyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.EntityFactoryQosPolicyHelper.id(),"EntityFactoryQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("autoenable_created_entities", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.EntityFactoryQosPolicy s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.EntityFactoryQosPolicy extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/EntityFactoryQosPolicy:1.0";
	}
	public static org.omg.dds.EntityFactoryQosPolicy read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.EntityFactoryQosPolicy result = new org.omg.dds.EntityFactoryQosPolicy();
		result.autoenable_created_entities=in.read_boolean();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.EntityFactoryQosPolicy s)
	{
		out.write_boolean(s.autoenable_created_entities);
	}
}
