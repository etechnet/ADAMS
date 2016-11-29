package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class DataWriterIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_offered_incompatible_qos_status", "()");
		irInfo.put("get_publication_match_status", "()");
		irInfo.put("set_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("get_liveliness_lost_status", "()");
		irInfo.put("set_listener", "org.omg.dds.ReturnCode_t(in:a_listener ,in:mask org.omg.dds.StatusKindMask)");
		irInfo.put("assert_liveliness", "()");
		irInfo.put("get_matched_subscriptions", "org.omg.dds.ReturnCode_t(inout:subscription_handles org.omg.dds.InstanceHandleSeq)");
		irInfo.put("get_listener", "()");
		irInfo.put("get_publisher", "()");
		irInfo.put("get_matched_subscription_data", "org.omg.dds.ReturnCode_t(inout:subscription_data ,in:subscription_handle org.omg.dds.InstanceHandle_t)");
		irInfo.put("get_qos", "(inout:qos )");
		irInfo.put("get_offered_deadline_missed_status", "()");
		irInfo.put("get_topic", "()");
	}
}
