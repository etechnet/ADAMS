package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ServerRequestInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class ServerRequestInfoLocalTie
	extends _ServerRequestInfoLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ServerRequestInfoOperations _delegate;

	public ServerRequestInfoLocalTie(ServerRequestInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public ServerRequestInfoOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ServerRequestInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public void add_reply_service_context(org.omg.IOP.ServiceContext service_context, boolean replace)
	{
_delegate.add_reply_service_context(service_context,replace);
	}

	public boolean target_is_a(java.lang.String id)
	{
		return _delegate.target_is_a(id);
	}

	public org.omg.IOP.ServiceContext get_request_service_context(int id)
	{
		return _delegate.get_request_service_context(id);
	}

	public byte[] adapter_id()
	{
		return _delegate.adapter_id();
	}

	public org.omg.CORBA.Any get_slot(int id) throws org.omg.PortableInterceptor.InvalidSlot
	{
		return _delegate.get_slot(id);
	}

	public java.lang.String[] contexts()
	{
		return _delegate.contexts();
	}

	public java.lang.String orb_id()
	{
		return _delegate.orb_id();
	}

	public org.omg.CORBA.Policy get_server_policy(int type)
	{
		return _delegate.get_server_policy(type);
	}

	public java.lang.String server_id()
	{
		return _delegate.server_id();
	}

	public org.omg.CORBA.Any sending_exception()
	{
		return _delegate.sending_exception();
	}

	public java.lang.String target_most_derived_interface()
	{
		return _delegate.target_most_derived_interface();
	}

	public org.omg.IOP.ServiceContext get_reply_service_context(int id)
	{
		return _delegate.get_reply_service_context(id);
	}

	public int request_id()
	{
		return _delegate.request_id();
	}

	public void set_slot(int id, org.omg.CORBA.Any data) throws org.omg.PortableInterceptor.InvalidSlot
	{
_delegate.set_slot(id,data);
	}

	public java.lang.String[] adapter_name()
	{
		return _delegate.adapter_name();
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

	public byte[] object_id()
	{
		return _delegate.object_id();
	}

	public java.lang.String operation()
	{
		return _delegate.operation();
	}

	public org.omg.CORBA.Object forward_reference()
	{
		return _delegate.forward_reference();
	}

}
