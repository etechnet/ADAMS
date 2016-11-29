package org.omg.dds;


/**
 * Generated from IDL struct "PublisherQos".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class PublisherQosHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PublisherQosHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.PublisherQosHelper.id(),"PublisherQos",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("presentation", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.PresentationQosPolicyHelper.id(),"PresentationQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("access_scope", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.PresentationQosPolicyAccessScopeKindHelper.id(),"PresentationQosPolicyAccessScopeKind",new String[]{"INSTANCE_PRESENTATION_QOS","TOPIC_PRESENTATION_QOS","GROUP_PRESENTATION_QOS"}), null),new org.omg.CORBA.StructMember("coherent_access", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("ordered_access", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)}), null),new org.omg.CORBA.StructMember("partition", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.PartitionQosPolicyHelper.id(),"PartitionQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.StringSeqHelper.id(), "StringSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_string_tc(0))), null)}), null),new org.omg.CORBA.StructMember("group_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.GroupDataQosPolicyHelper.id(),"GroupDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null),new org.omg.CORBA.StructMember("entity_factory", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.EntityFactoryQosPolicyHelper.id(),"EntityFactoryQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("autoenable_created_entities", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.PublisherQos s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.PublisherQos extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/PublisherQos:1.0";
	}
	public static org.omg.dds.PublisherQos read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.PublisherQos result = new org.omg.dds.PublisherQos();
		result.presentation=org.omg.dds.PresentationQosPolicyHelper.read(in);
		result.partition=org.omg.dds.PartitionQosPolicyHelper.read(in);
		result.group_data=org.omg.dds.GroupDataQosPolicyHelper.read(in);
		result.entity_factory=org.omg.dds.EntityFactoryQosPolicyHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.PublisherQos s)
	{
		org.omg.dds.PresentationQosPolicyHelper.write(out,s.presentation);
		org.omg.dds.PartitionQosPolicyHelper.write(out,s.partition);
		org.omg.dds.GroupDataQosPolicyHelper.write(out,s.group_data);
		org.omg.dds.EntityFactoryQosPolicyHelper.write(out,s.entity_factory);
	}
}
