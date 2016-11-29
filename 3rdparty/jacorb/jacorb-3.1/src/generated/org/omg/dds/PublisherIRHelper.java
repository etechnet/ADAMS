package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class PublisherIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("begin_coherent_changes", "org.omg.dds.ReturnCode_t()");
		irInfo.put("set_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("copy_from_topic_qos", "org.omg.dds.ReturnCode_t(inout:a_datawriter_qos ,in:a_topic_qos )");
		irInfo.put("suspend_publications", "org.omg.dds.ReturnCode_t()");
		irInfo.put("delete_datawriter", "org.omg.dds.ReturnCode_t(in:a_datawriter )");
		irInfo.put("set_listener", "org.omg.dds.ReturnCode_t(in:a_listener ,in:mask org.omg.dds.StatusKindMask)");
		irInfo.put("delete_contained_entities", "org.omg.dds.ReturnCode_t()");
		irInfo.put("lookup_datawriter", "(in:topic_name )");
		irInfo.put("get_listener", "()");
		irInfo.put("get_participant", "()");
		irInfo.put("set_default_datawriter_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("get_qos", "(inout:qos )");
		irInfo.put("resume_publications", "org.omg.dds.ReturnCode_t()");
		irInfo.put("get_default_datawriter_qos", "(out:qos )");
		irInfo.put("create_datawriter", "(in:a_topic ,in:qos ,in:a_listener )");
		irInfo.put("end_coherent_changes", "org.omg.dds.ReturnCode_t()");
	}
}
