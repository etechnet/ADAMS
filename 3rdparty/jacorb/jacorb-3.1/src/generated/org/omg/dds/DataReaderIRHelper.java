package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class DataReaderIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("set_listener", "org.omg.dds.ReturnCode_t(in:a_listener ,in:mask org.omg.dds.StatusKindMask)");
		irInfo.put("set_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("wait_for_historical_data", "org.omg.dds.ReturnCode_t(in:max_wait )");
		irInfo.put("get_sample_rejected_status", "()");
		irInfo.put("get_sample_lost_status", "()");
		irInfo.put("get_subscription_match_status", "()");
		irInfo.put("get_requested_deadline_missed_status", "()");
		irInfo.put("get_topicdescription", "()");
		irInfo.put("get_qos", "(inout:qos )");
		irInfo.put("take_instance_from_subscriber", "()");
		irInfo.put("delete_readcondition", "org.omg.dds.ReturnCode_t(in:a_condition )");
		irInfo.put("get_liveliness_changed_status", "()");
		irInfo.put("get_listener", "()");
		irInfo.put("create_readcondition", "(in:sample_states org.omg.dds.SampleStateMask,in:view_states org.omg.dds.ViewStateMask,in:instance_states org.omg.dds.InstanceStateMask)");
		irInfo.put("get_matched_publications", "org.omg.dds.ReturnCode_t(inout:publication_handles org.omg.dds.InstanceHandleSeq)");
		irInfo.put("delete_contained_entities", "org.omg.dds.ReturnCode_t()");
		irInfo.put("create_querycondition", "(in:sample_states org.omg.dds.SampleStateMask,in:view_states org.omg.dds.ViewStateMask,in:instance_states org.omg.dds.InstanceStateMask,in:query_expression ,in:query_parameters org.omg.dds.StringSeq)");
		irInfo.put("get_matched_publication_data", "org.omg.dds.ReturnCode_t(inout:publication_data ,in:publication_handle org.omg.dds.InstanceHandle_t)");
		irInfo.put("get_requested_incompatible_qos_status", "()");
		irInfo.put("get_subscriber", "()");
	}
}
