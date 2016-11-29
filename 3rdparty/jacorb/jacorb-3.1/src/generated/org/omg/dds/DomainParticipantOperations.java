package org.omg.dds;


/**
 * Generated from IDL interface "DomainParticipant".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface DomainParticipantOperations
	extends org.omg.dds.EntityOperations
{
	/* constants */
	/* operations  */
	org.omg.dds.Publisher create_publisher(org.omg.dds.PublisherQos qos, org.omg.dds.PublisherListener a_listener);
	int delete_publisher(org.omg.dds.Publisher p);
	org.omg.dds.Subscriber create_subscriber(org.omg.dds.SubscriberQos qos, org.omg.dds.SubscriberListener a_listener);
	int delete_subscriber(org.omg.dds.Subscriber s);
	org.omg.dds.Subscriber get_builtin_subscriber();
	org.omg.dds.Topic create_topic(java.lang.String topic_name, java.lang.String type_name, org.omg.dds.TopicQos qos, org.omg.dds.TopicListener a_listener);
	int delete_topic(org.omg.dds.Topic a_topic);
	org.omg.dds.Topic find_topic(java.lang.String topic_name, org.omg.dds.Duration_t timeout);
	org.omg.dds.TopicDescription lookup_topicdescription(java.lang.String name);
	org.omg.dds.ContentFilteredTopic create_contentfilteredtopic(java.lang.String name, org.omg.dds.Topic related_topic, java.lang.String filter_expression, java.lang.String[] filter_parameters);
	int delete_contentfilteredtopic(org.omg.dds.ContentFilteredTopic a_contentfilteredtopic);
	org.omg.dds.MultiTopic create_multitopic(java.lang.String name, java.lang.String type_name, java.lang.String subscription_expression, java.lang.String[] expression_parameters);
	int delete_multitopic(org.omg.dds.MultiTopic a_multitopic);
	int delete_contained_entities();
	int set_qos(org.omg.dds.DomainParticipantQos qos);
	void get_qos(org.omg.dds.DomainParticipantQosHolder qos);
	int set_listener(org.omg.dds.DomainParticipantListener a_listener, int mask);
	org.omg.dds.DomainParticipantListener get_listener();
	int ignore_participant(int handle);
	int ignore_topic(int handle);
	int ignore_publication(int handle);
	int ignore_subscription(int handle);
	int get_domain_id();
	void assert_liveliness();
	int set_default_publisher_qos(org.omg.dds.PublisherQos qos);
	void get_default_publisher_qos(org.omg.dds.PublisherQosHolder qos);
	int set_default_subscriber_qos(org.omg.dds.SubscriberQos qos);
	void get_default_subscriber_qos(org.omg.dds.SubscriberQosHolder qos);
	int set_default_topic_qos(org.omg.dds.TopicQos qos);
	void get_default_topic_qos(org.omg.dds.TopicQosHolder qos);
}
