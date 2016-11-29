package org.omg.dds;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class DomainParticipantFactoryIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_default_participant_qos", "(out:qos )");
		irInfo.put("set_default_participant_qos", "org.omg.dds.ReturnCode_t(in:qos )");
		irInfo.put("lookup_participant", "(in:domainId org.omg.dds.DomainId_t)");
		irInfo.put("delete_participant", "org.omg.dds.ReturnCode_t(in:a_participant )");
		irInfo.put("create_participant", "(in:domainId org.omg.dds.DomainId_t,in:qos ,in:a_listener )");
	}
}
