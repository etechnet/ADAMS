package org.omg.PortableInterceptor;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ClientRequestInfoIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("effective_target", "attribute;org.omg.CORBA.Object");
		irInfo.put("received_exception_id", "attribute;org.omg.CORBA.RepositoryId");
		irInfo.put("get_request_policy", "(in:type org.omg.CORBA.PolicyType)");
		irInfo.put("get_effective_components", "org.omg.IOP.TaggedComponentSeq(in:id org.omg.IOP.ComponentId)");
		irInfo.put("target", "attribute;org.omg.CORBA.Object");
		irInfo.put("add_request_service_context", "(in:service_context ,in:replace )");
		irInfo.put("get_effective_component", "(in:id org.omg.IOP.ComponentId)");
		irInfo.put("effective_profile", "attribute;org.omg.IOP.TaggedProfile");
		irInfo.put("received_exception", "attribute;org.omg.CORBA.Any");
	}
}
