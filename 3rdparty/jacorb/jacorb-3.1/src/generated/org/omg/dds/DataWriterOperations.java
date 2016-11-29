package org.omg.dds;


/**
 * Generated from IDL interface "DataWriter".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface DataWriterOperations
	extends org.omg.dds.EntityOperations
{
	/* constants */
	/* operations  */
	int set_qos(org.omg.dds.DataWriterQos qos);
	void get_qos(org.omg.dds.DataWriterQosHolder qos);
	int set_listener(org.omg.dds.DataWriterListener a_listener, int mask);
	org.omg.dds.DataWriterListener get_listener();
	org.omg.dds.Topic get_topic();
	org.omg.dds.Publisher get_publisher();
	org.omg.dds.LivelinessLostStatus get_liveliness_lost_status();
	org.omg.dds.OfferedDeadlineMissedStatus get_offered_deadline_missed_status();
	org.omg.dds.OfferedIncompatibleQosStatus get_offered_incompatible_qos_status();
	org.omg.dds.PublicationMatchStatus get_publication_match_status();
	void assert_liveliness();
	int get_matched_subscriptions(org.omg.dds.InstanceHandleSeqHolder subscription_handles);
	int get_matched_subscription_data(org.omg.dds.SubscriptionBuiltinTopicDataHolder subscription_data, int subscription_handle);
}
