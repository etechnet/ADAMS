package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class DomainParticipantIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("lookup_topicdescription", "(in:name )");
		irInfo.put("set_listener", "org.omg.dds.ReturnCode_t(in:a_listener ,in:mask org.omg.dds.StatusKindMask)");
		irInfo.put("set_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("create_topic", "(in:topic_name ,in:type_name ,in:qos ,in:a_listener )");
		irInfo.put("get_default_topic_qos", "(out:qos )");
		irInfo.put("delete_multitopic", "org.omg.dds.ReturnCode_t(in:a_multitopic )");
		irInfo.put("get_qos", "(inout:qos )");
		irInfo.put("get_default_publisher_qos", "(out:qos )");
		irInfo.put("ignore_publication", "org.omg.dds.ReturnCode_t(in:handle org.omg.dds.InstanceHandle_t)");
		irInfo.put("delete_contentfilteredtopic", "org.omg.dds.ReturnCode_t(in:a_contentfilteredtopic )");
		irInfo.put("create_subscriber", "(in:qos ,in:a_listener )");
		irInfo.put("assert_liveliness", "()");
		irInfo.put("delete_publisher", "org.omg.dds.ReturnCode_t(in:p )");
		irInfo.put("get_default_subscriber_qos", "(out:qos )");
		irInfo.put("get_listener", "()");
		irInfo.put("set_default_publisher_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("delete_topic", "org.omg.dds.ReturnCode_t(in:a_topic )");
		irInfo.put("create_publisher", "(in:qos ,in:a_listener )");
		irInfo.put("create_multitopic", "(in:name ,in:type_name ,in:subscription_expression ,in:expression_parameters org.omg.dds.StringSeq)");
		irInfo.put("delete_contained_entities", "org.omg.dds.ReturnCode_t()");
		irInfo.put("set_default_topic_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("ignore_participant", "org.omg.dds.ReturnCode_t(in:handle org.omg.dds.InstanceHandle_t)");
		irInfo.put("set_default_subscriber_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("ignore_subscription", "org.omg.dds.ReturnCode_t(in:handle org.omg.dds.InstanceHandle_t)");
		irInfo.put("delete_subscriber", "org.omg.dds.ReturnCode_t(in:s )");
		irInfo.put("get_domain_id", "org.omg.dds.DomainId_t()");
		irInfo.put("create_contentfilteredtopic", "(in:name ,in:related_topic ,in:filter_expression ,in:filter_parameters org.omg.dds.StringSeq)");
		irInfo.put("ignore_topic", "org.omg.dds.ReturnCode_t(in:handle org.omg.dds.InstanceHandle_t)");
		irInfo.put("get_builtin_subscriber", "()");
		irInfo.put("find_topic", "(in:topic_name ,in:timeout )");
	}
}
