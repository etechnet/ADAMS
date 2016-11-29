package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "DomainManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class DomainManagerPOATie
	extends DomainManagerPOA
{
	private DomainManagerOperations _delegate;

	private POA _poa;
	public DomainManagerPOATie(DomainManagerOperations delegate)
	{
		_delegate = delegate;
	}
	public DomainManagerPOATie(DomainManagerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.DomainManager _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.DomainManager __r = org.omg.CORBA.DomainManagerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.DomainManager _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.DomainManager __r = org.omg.CORBA.DomainManagerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public DomainManagerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DomainManagerOperations delegate)
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
	public org.omg.CORBA.Policy get_domain_policy(int policy_type)
	{
		return _delegate.get_domain_policy(policy_type);
	}

}
