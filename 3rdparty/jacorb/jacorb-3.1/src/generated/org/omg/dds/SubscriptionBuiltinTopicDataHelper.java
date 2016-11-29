package org.omg.dds;


/**
 * Generated from IDL struct "SubscriptionBuiltinTopicData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SubscriptionBuiltinTopicDataHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(SubscriptionBuiltinTopicDataHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.SubscriptionBuiltinTopicDataHelper.id(),"SubscriptionBuiltinTopicData",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("key", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.BuiltinTopicKey_tHelper.id(), "BuiltinTopicKey_t",org.omg.CORBA.ORB.init().create_array_tc(3,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)))), null),new org.omg.CORBA.StructMember("participant_key", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.BuiltinTopicKey_tHelper.id(), "BuiltinTopicKey_t",org.omg.CORBA.ORB.init().create_array_tc(3,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)))), null),new org.omg.CORBA.StructMember("topic_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("durability", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DurabilityQosPolicyHelper.id(),"DurabilityQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.DurabilityQosPolicyKindHelper.id(),"DurabilityQosPolicyKind",new String[]{"VOLATILE_DURABILITY_QOS","TRANSIENT_LOCAL_DURABILITY_QOS","TRANSIENT_DURABILITY_QOS","PERSISTENT_DURABILITY_QOS"}), null),new org.omg.CORBA.StructMember("service_cleanup_delay", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("deadline", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DeadlineQosPolicyHelper.id(),"DeadlineQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("period", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("latency_budget", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.LatencyBudgetQosPolicyHelper.id(),"LatencyBudgetQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("duration", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("liveliness", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.LivelinessQosPolicyHelper.id(),"LivelinessQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.LivelinessQosPolicyKindHelper.id(),"LivelinessQosPolicyKind",new String[]{"AUTOMATIC_LIVELINESS_QOS","MANUAL_BY_PARTICIPANT_LIVELINESS_QOS","MANUAL_BY_TOPIC_LIVELINESS_QOS"}), null),new org.omg.CORBA.StructMember("lease_duration", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("reliability", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.ReliabilityQosPolicyHelper.id(),"ReliabilityQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.ReliabilityQosPolicyKindHelper.id(),"ReliabilityQosPolicyKind",new String[]{"BEST_EFFORT_RELIABILITY_QOS","RELIABLE_RELIABILITY_QOS"}), null),new org.omg.CORBA.StructMember("max_blocking_time", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("destination_order", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DestinationOrderQosPolicyHelper.id(),"DestinationOrderQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.DestinationOrderQosPolicyKindHelper.id(),"DestinationOrderQosPolicyKind",new String[]{"BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS","BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS"}), null)}), null),new org.omg.CORBA.StructMember("user_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.UserDataQosPolicyHelper.id(),"UserDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null),new org.omg.CORBA.StructMember("time_based_filter", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.TimeBasedFilterQosPolicyHelper.id(),"TimeBasedFilterQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("minimum_separation", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("presentation", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.PresentationQosPolicyHelper.id(),"PresentationQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("access_scope", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.PresentationQosPolicyAccessScopeKindHelper.id(),"PresentationQosPolicyAccessScopeKind",new String[]{"INSTANCE_PRESENTATION_QOS","TOPIC_PRESENTATION_QOS","GROUP_PRESENTATION_QOS"}), null),new org.omg.CORBA.StructMember("coherent_access", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("ordered_access", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null)}), null),new org.omg.CORBA.StructMember("partition", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.PartitionQosPolicyHelper.id(),"PartitionQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.StringSeqHelper.id(), "StringSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_string_tc(0))), null)}), null),new org.omg.CORBA.StructMember("topic_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.TopicDataQosPolicyHelper.id(),"TopicDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null),new org.omg.CORBA.StructMember("group_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.GroupDataQosPolicyHelper.id(),"GroupDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.SubscriptionBuiltinTopicData s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.SubscriptionBuiltinTopicData extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/SubscriptionBuiltinTopicData:1.0";
	}
	public static org.omg.dds.SubscriptionBuiltinTopicData read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.SubscriptionBuiltinTopicData result = new org.omg.dds.SubscriptionBuiltinTopicData();
		result.key = org.omg.dds.BuiltinTopicKey_tHelper.read(in);
		result.participant_key = org.omg.dds.BuiltinTopicKey_tHelper.read(in);
		result.topic_name=in.read_string();
		result.type_name=in.read_string();
		result.durability=org.omg.dds.DurabilityQosPolicyHelper.read(in);
		result.deadline=org.omg.dds.DeadlineQosPolicyHelper.read(in);
		result.latency_budget=org.omg.dds.LatencyBudgetQosPolicyHelper.read(in);
		result.liveliness=org.omg.dds.LivelinessQosPolicyHelper.read(in);
		result.reliability=org.omg.dds.ReliabilityQosPolicyHelper.read(in);
		result.destination_order=org.omg.dds.DestinationOrderQosPolicyHelper.read(in);
		result.user_data=org.omg.dds.UserDataQosPolicyHelper.read(in);
		result.time_based_filter=org.omg.dds.TimeBasedFilterQosPolicyHelper.read(in);
		result.presentation=org.omg.dds.PresentationQosPolicyHelper.read(in);
		result.partition=org.omg.dds.PartitionQosPolicyHelper.read(in);
		result.topic_data=org.omg.dds.TopicDataQosPolicyHelper.read(in);
		result.group_data=org.omg.dds.GroupDataQosPolicyHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.SubscriptionBuiltinTopicData s)
	{
		org.omg.dds.BuiltinTopicKey_tHelper.write(out,s.key);
		org.omg.dds.BuiltinTopicKey_tHelper.write(out,s.participant_key);
		java.lang.String tmpResult1116 = s.topic_name;
out.write_string( tmpResult1116 );
		java.lang.String tmpResult1117 = s.type_name;
out.write_string( tmpResult1117 );
		org.omg.dds.DurabilityQosPolicyHelper.write(out,s.durability);
		org.omg.dds.DeadlineQosPolicyHelper.write(out,s.deadline);
		org.omg.dds.LatencyBudgetQosPolicyHelper.write(out,s.latency_budget);
		org.omg.dds.LivelinessQosPolicyHelper.write(out,s.liveliness);
		org.omg.dds.ReliabilityQosPolicyHelper.write(out,s.reliability);
		org.omg.dds.DestinationOrderQosPolicyHelper.write(out,s.destination_order);
		org.omg.dds.UserDataQosPolicyHelper.write(out,s.user_data);
		org.omg.dds.TimeBasedFilterQosPolicyHelper.write(out,s.time_based_filter);
		org.omg.dds.PresentationQosPolicyHelper.write(out,s.presentation);
		org.omg.dds.PartitionQosPolicyHelper.write(out,s.partition);
		org.omg.dds.TopicDataQosPolicyHelper.write(out,s.topic_data);
		org.omg.dds.GroupDataQosPolicyHelper.write(out,s.group_data);
	}
}
