package org.jacorb.imr;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ImplementationRepository".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public class ImplementationRepositoryPOATie
	extends ImplementationRepositoryPOA
{
	private ImplementationRepositoryOperations _delegate;

	private POA _poa;
	public ImplementationRepositoryPOATie(ImplementationRepositoryOperations delegate)
	{
		_delegate = delegate;
	}
	public ImplementationRepositoryPOATie(ImplementationRepositoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.jacorb.imr.ImplementationRepository _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.imr.ImplementationRepository __r = org.jacorb.imr.ImplementationRepositoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.imr.ImplementationRepository _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.imr.ImplementationRepository __r = org.jacorb.imr.ImplementationRepositoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ImplementationRepositoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ImplementationRepositoryOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void register_poa(java.lang.String name, java.lang.String server, java.lang.String host, int port) throws org.jacorb.imr.RegistrationPackage.IllegalPOAName,org.jacorb.imr.RegistrationPackage.DuplicatePOAName,org.jacorb.imr.UnknownServerName
	{
_delegate.register_poa(name,server,host,port);
	}

	public void release_server(java.lang.String name) throws org.jacorb.imr.UnknownServerName
	{
_delegate.release_server(name);
	}

	public org.jacorb.imr.HostInfo[] list_hosts()
	{
		return _delegate.list_hosts();
	}

	public void unregister_host(java.lang.String name) throws org.jacorb.imr.AdminPackage.UnknownHostName
	{
_delegate.unregister_host(name);
	}

	public void start_server(java.lang.String name) throws org.jacorb.imr.ServerStartupFailed,org.jacorb.imr.UnknownServerName
	{
_delegate.start_server(name);
	}

	public org.jacorb.imr.ServerInfo[] list_servers()
	{
		return _delegate.list_servers();
	}

	public void shutdown(boolean _wait)
	{
_delegate.shutdown(_wait);
	}

	public void save_server_table() throws org.jacorb.imr.AdminPackage.FileOpFailed
	{
_delegate.save_server_table();
	}

	public void hold_server(java.lang.String name) throws org.jacorb.imr.UnknownServerName
	{
_delegate.hold_server(name);
	}

	public org.jacorb.imr.ImRInfo get_imr_info()
	{
		return _delegate.get_imr_info();
	}

	public org.jacorb.imr.ServerInfo get_server_info(java.lang.String name) throws org.jacorb.imr.UnknownServerName
	{
		return _delegate.get_server_info(name);
	}

	public void register_server(java.lang.String name, java.lang.String command, java.lang.String host) throws org.jacorb.imr.AdminPackage.IllegalServerName,org.jacorb.imr.AdminPackage.DuplicateServerName
	{
_delegate.register_server(name,command,host);
	}

	public void unregister_server(java.lang.String name) throws org.jacorb.imr.UnknownServerName
	{
_delegate.unregister_server(name);
	}

	public void edit_server(java.lang.String name, java.lang.String command, java.lang.String host) throws org.jacorb.imr.UnknownServerName
	{
_delegate.edit_server(name,command,host);
	}

	public void register_host(org.jacorb.imr.HostInfo info) throws org.jacorb.imr.RegistrationPackage.InvalidSSDRef,org.jacorb.imr.RegistrationPackage.IllegalHostName
	{
_delegate.register_host(info);
	}

	public void set_server_down(java.lang.String name) throws org.jacorb.imr.UnknownServerName
	{
_delegate.set_server_down(name);
	}

}
