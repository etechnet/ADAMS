package org.omg.CORBA;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ConstructionPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class ConstructionPolicyPOATie
	extends ConstructionPolicyPOA
{
	private ConstructionPolicyOperations _delegate;

	private POA _poa;
	public ConstructionPolicyPOATie(ConstructionPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public ConstructionPolicyPOATie(ConstructionPolicyOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CORBA.ConstructionPolicy _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CORBA.ConstructionPolicy __r = org.omg.CORBA.ConstructionPolicyHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CORBA.ConstructionPolicy _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CORBA.ConstructionPolicy __r = org.omg.CORBA.ConstructionPolicyHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ConstructionPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ConstructionPolicyOperations delegate)
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
	public int policy_type()
	{
		return _delegate.policy_type();
	}

	public org.omg.CORBA.Policy copy()
	{
		return _delegate.copy();
	}

	public void make_domain_manager(org.omg.CORBA.InterfaceDef object_type, boolean constr_policy)
	{
_delegate.make_domain_manager(object_type,constr_policy);
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
