package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ORBInitInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class ORBInitInfoLocalTie
	extends _ORBInitInfoLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ORBInitInfoOperations _delegate;

	public ORBInitInfoLocalTie(ORBInitInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public ORBInitInfoOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ORBInitInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.CORBA.Object resolve_initial_references(java.lang.String id) throws org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName
	{
		return _delegate.resolve_initial_references(id);
	}

	public void add_client_request_interceptor(org.omg.PortableInterceptor.ClientRequestInterceptor interceptor) throws org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName
	{
_delegate.add_client_request_interceptor(interceptor);
	}

	public void add_ior_interceptor(org.omg.PortableInterceptor.IORInterceptor interceptor) throws org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName
	{
_delegate.add_ior_interceptor(interceptor);
	}

	public java.lang.String orb_id()
	{
		return _delegate.orb_id();
	}

	public org.omg.IOP.CodecFactory codec_factory()
	{
		return _delegate.codec_factory();
	}

	public java.lang.String[] arguments()
	{
		return _delegate.arguments();
	}

	public int allocate_slot_id()
	{
		return _delegate.allocate_slot_id();
	}

	public void add_server_request_interceptor(org.omg.PortableInterceptor.ServerRequestInterceptor interceptor) throws org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName
	{
_delegate.add_server_request_interceptor(interceptor);
	}

	public void register_policy_factory(int type, org.omg.PortableInterceptor.PolicyFactory policy_factory)
	{
_delegate.register_policy_factory(type,policy_factory);
	}

	public void register_initial_reference(java.lang.String id, org.omg.CORBA.Object obj) throws org.omg.PortableInterceptor.ORBInitInfoPackage.InvalidName
	{
_delegate.register_initial_reference(id,obj);
	}

}
