package org.omg.RTPortableServer;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class POAIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("activate_object_with_id_and_priority", "(in:oid org.omg.PortableServer.ObjectId,in:p_servant ,in:priority_ org.omg.RTCORBA.Priority)");
		irInfo.put("create_reference_with_id_and_priority", "(in:oid org.omg.PortableServer.ObjectId,in:intf org.omg.CORBA.RepositoryId,in:priority_ org.omg.RTCORBA.Priority)");
		irInfo.put("activate_object_with_priority", "org.omg.PortableServer.ObjectId(in:p_servant ,in:priority_ org.omg.RTCORBA.Priority)");
		irInfo.put("create_reference_with_priority", "(in:intf org.omg.CORBA.RepositoryId,in:priority_ org.omg.RTCORBA.Priority)");
	}
}
