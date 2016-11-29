package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ORBInitInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface ORBInitInfoOperations
{
	/* constants */
	/* operations  */
	java.lang.String[] arguments();
	java.lang.String orb_id();
	org.omg.IOP.CodecFactory codec_factory();
	void register_initial_reference(java.lang.String id, org.omg.CORBA.Object obj) throws org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;
	org.omg.CORBA.Object resolve_initial_references(java.lang.String id) throws org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName;
	void add_client_request_interceptor(org.omg.PortableInterceptor.ClientRequestInterceptor interceptor) throws org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
	void add_server_request_interceptor(org.omg.PortableInterceptor.ServerRequestInterceptor interceptor) throws org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
	void add_ior_interceptor(org.omg.PortableInterceptor.IORInterceptor interceptor) throws org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
	int allocate_slot_id();
	void register_policy_factory(int type, org.omg.PortableInterceptor.PolicyFactory policy_factory);
}
