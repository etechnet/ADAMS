package org.omg.dds;


/**
 * Generated from IDL struct "TopicBuiltinTopicData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TopicBuiltinTopicDataHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TopicBuiltinTopicDataHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.TopicBuiltinTopicDataHelper.id(),"TopicBuiltinTopicData",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("key", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.dds.BuiltinTopicKey_tHelper.id(), "BuiltinTopicKey_t",org.omg.CORBA.ORB.init().create_array_tc(3,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)))), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("durability", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DurabilityQosPolicyHelper.id(),"DurabilityQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.DurabilityQosPolicyKindHelper.id(),"DurabilityQosPolicyKind",new String[]{"VOLATILE_DURABILITY_QOS","TRANSIENT_LOCAL_DURABILITY_QOS","TRANSIENT_DURABILITY_QOS","PERSISTENT_DURABILITY_QOS"}), null),new org.omg.CORBA.StructMember("service_cleanup_delay", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("deadline", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DeadlineQosPolicyHelper.id(),"DeadlineQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("period", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("latency_budget", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.LatencyBudgetQosPolicyHelper.id(),"LatencyBudgetQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("duration", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("liveliness", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.LivelinessQosPolicyHelper.id(),"LivelinessQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.LivelinessQosPolicyKindHelper.id(),"LivelinessQosPolicyKind",new String[]{"AUTOMATIC_LIVELINESS_QOS","MANUAL_BY_PARTICIPANT_LIVELINESS_QOS","MANUAL_BY_TOPIC_LIVELINESS_QOS"}), null),new org.omg.CORBA.StructMember("lease_duration", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("reliability", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.ReliabilityQosPolicyHelper.id(),"ReliabilityQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.ReliabilityQosPolicyKindHelper.id(),"ReliabilityQosPolicyKind",new String[]{"BEST_EFFORT_RELIABILITY_QOS","RELIABLE_RELIABILITY_QOS"}), null),new org.omg.CORBA.StructMember("max_blocking_time", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("transport_priority", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.TransportPriorityQosPolicyHelper.id(),"TransportPriorityQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)}), null),new org.omg.CORBA.StructMember("lifespan", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.LifespanQosPolicyHelper.id(),"LifespanQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("duration", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.Duration_tHelper.id(),"Duration_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("sec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("nanosec", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}), null)}), null),new org.omg.CORBA.StructMember("destination_order", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.DestinationOrderQosPolicyHelper.id(),"DestinationOrderQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.DestinationOrderQosPolicyKindHelper.id(),"DestinationOrderQosPolicyKind",new String[]{"BY_RECEPTION_TIMESTAMP_DESTINATIONORDER_QOS","BY_SOURCE_TIMESTAMP_DESTINATIONORDER_QOS"}), null)}), null),new org.omg.CORBA.StructMember("history", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.HistoryQosPolicyHelper.id(),"HistoryQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.HistoryQosPolicyKindHelper.id(),"HistoryQosPolicyKind",new String[]{"KEEP_LAST_HISTORY_QOS","KEEP_ALL_HISTORY_QOS"}), null),new org.omg.CORBA.StructMember("depth", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)}), null),new org.omg.CORBA.StructMember("resource_limits", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.ResourceLimitsQosPolicyHelper.id(),"ResourceLimitsQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("max_samples", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("max_instances", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("max_samples_per_instance", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)}), null),new org.omg.CORBA.StructMember("ownership", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.OwnershipQosPolicyHelper.id(),"OwnershipQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("kind", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.dds.OwnershipQosPolicyKindHelper.id(),"OwnershipQosPolicyKind",new String[]{"SHARED_OWNERSHIP_QOS","EXCLUSIVE_OWNERSHIP_QOS"}), null)}), null),new org.omg.CORBA.StructMember("topic_data", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.dds.TopicDataQosPolicyHelper.id(),"TopicDataQosPolicy",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.dds.TopicBuiltinTopicData s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.dds.TopicBuiltinTopicData extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/dds/TopicBuiltinTopicData:1.0";
	}
	public static org.omg.dds.TopicBuiltinTopicData read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.dds.TopicBuiltinTopicData result = new org.omg.dds.TopicBuiltinTopicData();
		result.key = org.omg.dds.BuiltinTopicKey_tHelper.read(in);
		result.name=in.read_string();
		result.type_name=in.read_string();
		result.durability=org.omg.dds.DurabilityQosPolicyHelper.read(in);
		result.deadline=org.omg.dds.DeadlineQosPolicyHelper.read(in);
		result.latency_budget=org.omg.dds.LatencyBudgetQosPolicyHelper.read(in);
		result.liveliness=org.omg.dds.LivelinessQosPolicyHelper.read(in);
		result.reliability=org.omg.dds.ReliabilityQosPolicyHelper.read(in);
		result.transport_priority=org.omg.dds.TransportPriorityQosPolicyHelper.read(in);
		result.lifespan=org.omg.dds.LifespanQosPolicyHelper.read(in);
		result.destination_order=org.omg.dds.DestinationOrderQosPolicyHelper.read(in);
		result.history=org.omg.dds.HistoryQosPolicyHelper.read(in);
		result.resource_limits=org.omg.dds.ResourceLimitsQosPolicyHelper.read(in);
		result.ownership=org.omg.dds.OwnershipQosPolicyHelper.read(in);
		result.topic_data=org.omg.dds.TopicDataQosPolicyHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.dds.TopicBuiltinTopicData s)
	{
		org.omg.dds.BuiltinTopicKey_tHelper.write(out,s.key);
		java.lang.String tmpResult1112 = s.name;
out.write_string( tmpResult1112 );
		java.lang.String tmpResult1113 = s.type_name;
out.write_string( tmpResult1113 );
		org.omg.dds.DurabilityQosPolicyHelper.write(out,s.durability);
		org.omg.dds.DeadlineQosPolicyHelper.write(out,s.deadline);
		org.omg.dds.LatencyBudgetQosPolicyHelper.write(out,s.latency_budget);
		org.omg.dds.LivelinessQosPolicyHelper.write(out,s.liveliness);
		org.omg.dds.ReliabilityQosPolicyHelper.write(out,s.reliability);
		org.omg.dds.TransportPriorityQosPolicyHelper.write(out,s.transport_priority);
		org.omg.dds.LifespanQosPolicyHelper.write(out,s.lifespan);
		org.omg.dds.DestinationOrderQosPolicyHelper.write(out,s.destination_order);
		org.omg.dds.HistoryQosPolicyHelper.write(out,s.history);
		org.omg.dds.ResourceLimitsQosPolicyHelper.write(out,s.resource_limits);
		org.omg.dds.OwnershipQosPolicyHelper.write(out,s.ownership);
		org.omg.dds.TopicDataQosPolicyHelper.write(out,s.topic_data);
	}
}
