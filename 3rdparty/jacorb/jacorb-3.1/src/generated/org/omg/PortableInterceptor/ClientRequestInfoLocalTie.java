package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ClientRequestInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class ClientRequestInfoLocalTie
	extends _ClientRequestInfoLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ClientRequestInfoOperations _delegate;

	public ClientRequestInfoLocalTie(ClientRequestInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public ClientRequestInfoOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ClientRequestInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public java.lang.String received_exception_id()
	{
		return _delegate.received_exception_id();
	}

	public org.omg.IOP.TaggedComponent get_effective_component(int id)
	{
		return _delegate.get_effective_component(id);
	}

	public org.omg.CORBA.Object effective_target()
	{
		return _delegate.effective_target();
	}

	public org.omg.CORBA.Object target()
	{
		return _delegate.target();
	}

	public org.omg.IOP.ServiceContext get_request_service_context(int id)
	{
		return _delegate.get_request_service_context(id);
	}

	public org.omg.CORBA.Any get_slot(int id) throws org.omg.PortableInterceptor.InvalidSlot
	{
		return _delegate.get_slot(id);
	}

	public java.lang.String[] contexts()
	{
		return _delegate.contexts();
	}

	public org.omg.CORBA.Any received_exception()
	{
		return _delegate.received_exception();
	}

	public void add_request_service_context(org.omg.IOP.ServiceContext service_context, boolean replace)
	{
_delegate.add_request_service_context(service_context,replace);
	}

	public org.omg.CORBA.Policy get_request_policy(int type)
	{
		return _delegate.get_request_policy(type);
	}

	public org.omg.IOP.ServiceContext get_reply_service_context(int id)
	{
		return _delegate.get_reply_service_context(id);
	}

	public int request_id()
	{
		return _delegate.request_id();
	}

	public org.omg.Dynamic.Parameter[] arguments()
	{
		return _delegate.arguments();
	}

	public org.omg.CORBA.Any result()
	{
		return _delegate.result();
	}

	public boolean response_expected()
	{
		return _delegate.response_expected();
	}

	public short sync_scope()
	{
		return _delegate.sync_scope();
	}

	public short reply_status()
	{
		return _delegate.reply_status();
	}

	public java.lang.String[] operation_context()
	{
		return _delegate.operation_context();
	}

	public org.omg.CORBA.TypeCode[] exceptions()
	{
		return _delegate.exceptions();
	}

	public org.omg.IOP.TaggedComponent[] get_effective_components(int id)
	{
		return _delegate.get_effective_components(id);
	}

	public java.lang.String operation()
	{
		return _delegate.operation();
	}

	public org.omg.IOP.TaggedProfile effective_profile()
	{
		return _delegate.effective_profile();
	}

	public org.omg.CORBA.Object forward_reference()
	{
		return _delegate.forward_reference();
	}

}
