package org.omg.dds;

/**
 * Generated from IDL struct "SubscriptionBuiltinTopicData".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SubscriptionBuiltinTopicData
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SubscriptionBuiltinTopicData(){}
	public int[] key;
	public int[] participant_key;
	public java.lang.String topic_name = "";
	public java.lang.String type_name = "";
	public org.omg.dds.DurabilityQosPolicy durability;
	public org.omg.dds.DeadlineQosPolicy deadline;
	public org.omg.dds.LatencyBudgetQosPolicy latency_budget;
	public org.omg.dds.LivelinessQosPolicy liveliness;
	public org.omg.dds.ReliabilityQosPolicy reliability;
	public org.omg.dds.DestinationOrderQosPolicy destination_order;
	public org.omg.dds.UserDataQosPolicy user_data;
	public org.omg.dds.TimeBasedFilterQosPolicy time_based_filter;
	public org.omg.dds.PresentationQosPolicy presentation;
	public org.omg.dds.PartitionQosPolicy partition;
	public org.omg.dds.TopicDataQosPolicy topic_data;
	public org.omg.dds.GroupDataQosPolicy group_data;
	public SubscriptionBuiltinTopicData(int[] key, int[] participant_key, java.lang.String topic_name, java.lang.String type_name, org.omg.dds.DurabilityQosPolicy durability, org.omg.dds.DeadlineQosPolicy deadline, org.omg.dds.LatencyBudgetQosPolicy latency_budget, org.omg.dds.LivelinessQosPolicy liveliness, org.omg.dds.ReliabilityQosPolicy reliability, org.omg.dds.DestinationOrderQosPolicy destination_order, org.omg.dds.UserDataQosPolicy user_data, org.omg.dds.TimeBasedFilterQosPolicy time_based_filter, org.omg.dds.PresentationQosPolicy presentation, org.omg.dds.PartitionQosPolicy partition, org.omg.dds.TopicDataQosPolicy topic_data, org.omg.dds.GroupDataQosPolicy group_data)
	{
		this.key = key;
		this.participant_key = participant_key;
		this.topic_name = topic_name;
		this.type_name = type_name;
		this.durability = durability;
		this.deadline = deadline;
		this.latency_budget = latency_budget;
		this.liveliness = liveliness;
		this.reliability = reliability;
		this.destination_order = destination_order;
		this.user_data = user_data;
		this.time_based_filter = time_based_filter;
		this.presentation = presentation;
		this.partition = partition;
		this.topic_data = topic_data;
		this.group_data = group_data;
	}
}
