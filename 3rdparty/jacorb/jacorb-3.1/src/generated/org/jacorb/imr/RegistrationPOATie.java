package org.jacorb.imr;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Registration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public class RegistrationPOATie
	extends RegistrationPOA
{
	private RegistrationOperations _delegate;

	private POA _poa;
	public RegistrationPOATie(RegistrationOperations delegate)
	{
		_delegate = delegate;
	}
	public RegistrationPOATie(RegistrationOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.jacorb.imr.Registration _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.imr.Registration __r = org.jacorb.imr.RegistrationHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.imr.Registration _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.imr.Registration __r = org.jacorb.imr.RegistrationHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public RegistrationOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RegistrationOperations delegate)
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
	public org.jacorb.imr.ImRInfo get_imr_info()
	{
		return _delegate.get_imr_info();
	}

	public void register_poa(java.lang.String name, java.lang.String server, java.lang.String host, int port) throws org.jacorb.imr.RegistrationPackage.IllegalPOAName,org.jacorb.imr.RegistrationPackage.DuplicatePOAName,org.jacorb.imr.UnknownServerName
	{
_delegate.register_poa(name,server,host,port);
	}

	public void set_server_down(java.lang.String name) throws org.jacorb.imr.UnknownServerName
	{
_delegate.set_server_down(name);
	}

	public void register_host(org.jacorb.imr.HostInfo info) throws org.jacorb.imr.RegistrationPackage.InvalidSSDRef,org.jacorb.imr.RegistrationPackage.IllegalHostName
	{
_delegate.register_host(info);
	}

}
