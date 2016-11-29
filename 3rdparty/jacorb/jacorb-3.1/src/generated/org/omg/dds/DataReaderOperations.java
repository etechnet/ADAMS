package org.omg.dds;


/**
 * Generated from IDL interface "DataReader".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface DataReaderOperations
	extends org.omg.dds.EntityOperations
{
	/* constants */
	/* operations  */
	org.omg.dds.ReadCondition create_readcondition(int sample_states, int view_states, int instance_states);
	org.omg.dds.QueryCondition create_querycondition(int sample_states, int view_states, int instance_states, java.lang.String query_expression, java.lang.String[] query_parameters);
	int delete_readcondition(org.omg.dds.ReadCondition a_condition);
	int delete_contained_entities();
	int set_qos(org.omg.dds.DataReaderQos qos);
	void get_qos(org.omg.dds.DataReaderQosHolder qos);
	int set_listener(org.omg.dds.DataReaderListener a_listener, int mask);
	org.omg.dds.DataReaderListener get_listener();
	org.omg.dds.TopicDescription get_topicdescription();
	org.omg.dds.Subscriber get_subscriber();
	org.omg.dds.SampleRejectedStatus get_sample_rejected_status();
	org.omg.dds.LivelinessChangedStatus get_liveliness_changed_status();
	org.omg.dds.RequestedDeadlineMissedStatus get_requested_deadline_missed_status();
	org.omg.dds.RequestedIncompatibleQosStatus get_requested_incompatible_qos_status();
	org.omg.dds.SubscriptionMatchStatus get_subscription_match_status();
	org.omg.dds.SampleLostStatus get_sample_lost_status();
	int wait_for_historical_data(org.omg.dds.Duration_t max_wait);
	int get_matched_publications(org.omg.dds.InstanceHandleSeqHolder publication_handles);
	int get_matched_publication_data(org.omg.dds.PublicationBuiltinTopicDataHolder publication_data, int publication_handle);
	void take_instance_from_subscriber();
}
