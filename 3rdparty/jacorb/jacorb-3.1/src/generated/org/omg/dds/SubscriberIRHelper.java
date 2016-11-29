package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class SubscriberIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("begin_access", "org.omg.dds.ReturnCode_t()");
		irInfo.put("get_datareaders", "org.omg.dds.ReturnCode_t(out:readers org.omg.dds.DataReaderSeq,in:sample_states org.omg.dds.SampleStateMask,in:view_states org.omg.dds.ViewStateMask,in:instance_states org.omg.dds.InstanceStateMask)");
		irInfo.put("create_datareader", "(in:a_topic ,in:qos ,in:a_listener )");
		irInfo.put("set_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("copy_from_topic_qos", "org.omg.dds.ReturnCode_t(inout:a_datareader_qos ,in:a_topic_qos )");
		irInfo.put("set_listener", "org.omg.dds.ReturnCode_t(in:a_listener ,in:mask org.omg.dds.StatusKindMask)");
		irInfo.put("notify_datareaders", "()");
		irInfo.put("delete_contained_entities", "org.omg.dds.ReturnCode_t()");
		irInfo.put("delete_datareader", "org.omg.dds.ReturnCode_t(in:a_datareader )");
		irInfo.put("get_listener", "()");
		irInfo.put("get_participant", "()");
		irInfo.put("set_default_datareader_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("end_access", "org.omg.dds.ReturnCode_t()");
		irInfo.put("get_qos", "(inout:qos )");
		irInfo.put("lookup_datareader", "(in:topic_name )");
		irInfo.put("get_default_datareader_qos", "(out:qos )");
	}
}
