package org.omg.PortableInterceptor;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ORBInitInfoIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("resolve_initial_references", "(in:id org.omg.PortableInterceptor.ORBInitInfoPackage.ObjectId)");
		irInfo.put("arguments", "attribute;org.omg.CORBA.StringSeq");
		irInfo.put("allocate_slot_id", "org.omg.PortableInterceptor.SlotId()");
		irInfo.put("add_server_request_interceptor", "(in:interceptor )");
		irInfo.put("orb_id", "attribute;java.lang.String");
		irInfo.put("register_policy_factory", "(in:type org.omg.CORBA.PolicyType,in:policy_factory )");
		irInfo.put("codec_factory", "attribute;org.omg.IOP.CodecFactory");
		irInfo.put("add_ior_interceptor", "(in:interceptor )");
		irInfo.put("add_client_request_interceptor", "(in:interceptor )");
		irInfo.put("register_initial_reference", "(in:id org.omg.PortableInterceptor.ORBInitInfoPackage.ObjectId,in:obj )");
	}
}
