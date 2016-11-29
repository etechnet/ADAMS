package org.omg.CosTrading;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Lookup".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class LookupPOATie
	extends LookupPOA
{
	private LookupOperations _delegate;

	private POA _poa;
	public LookupPOATie(LookupOperations delegate)
	{
		_delegate = delegate;
	}
	public LookupPOATie(LookupOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTrading.Lookup _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTrading.Lookup __r = org.omg.CosTrading.LookupHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTrading.Lookup _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTrading.Lookup __r = org.omg.CosTrading.LookupHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public LookupOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(LookupOperations delegate)
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
	public org.omg.CosTrading.Register register_if()
	{
		return _delegate.register_if();
	}

	public org.omg.CosTrading.FollowOption def_follow_policy()
	{
		return _delegate.def_follow_policy();
	}

	public org.omg.CosTrading.Lookup lookup_if()
	{
		return _delegate.lookup_if();
	}

	public int max_list()
	{
		return _delegate.max_list();
	}

	public int max_search_card()
	{
		return _delegate.max_search_card();
	}

	public org.omg.CosTrading.Proxy proxy_if()
	{
		return _delegate.proxy_if();
	}

	public void query(java.lang.String type, java.lang.String constr, java.lang.String pref, org.omg.CosTrading.Policy[] policies, org.omg.CosTrading.LookupPackage.SpecifiedProps desired_props, int how_many, org.omg.CosTrading.OfferSeqHolder offers, org.omg.CosTrading.OfferIteratorHolder offer_itr, org.omg.CosTrading.PolicyNameSeqHolder limits_applied) throws org.omg.CosTrading.LookupPackage.IllegalPreference,org.omg.CosTrading.DuplicatePropertyName,org.omg.CosTrading.LookupPackage.InvalidPolicyValue,org.omg.CosTrading.IllegalServiceType,org.omg.CosTrading.LookupPackage.PolicyTypeMismatch,org.omg.CosTrading.IllegalConstraint,org.omg.CosTrading.LookupPackage.IllegalPolicyName,org.omg.CosTrading.DuplicatePolicyName,org.omg.CosTrading.IllegalPropertyName,org.omg.CosTrading.UnknownServiceType
	{
_delegate.query(type,constr,pref,policies,desired_props,how_many,offers,offer_itr,limits_applied);
	}

	public int def_search_card()
	{
		return _delegate.def_search_card();
	}

	public org.omg.CosTrading.Link link_if()
	{
		return _delegate.link_if();
	}

	public org.omg.CORBA.Object type_repos()
	{
		return _delegate.type_repos();
	}

	public int max_return_card()
	{
		return _delegate.max_return_card();
	}

	public org.omg.CosTrading.Admin admin_if()
	{
		return _delegate.admin_if();
	}

	public boolean supports_dynamic_properties()
	{
		return _delegate.supports_dynamic_properties();
	}

	public int max_hop_count()
	{
		return _delegate.max_hop_count();
	}

	public int def_match_card()
	{
		return _delegate.def_match_card();
	}

	public boolean supports_proxy_offers()
	{
		return _delegate.supports_proxy_offers();
	}

	public int max_match_card()
	{
		return _delegate.max_match_card();
	}

	public int def_return_card()
	{
		return _delegate.def_return_card();
	}

	public boolean supports_modifiable_properties()
	{
		return _delegate.supports_modifiable_properties();
	}

	public int def_hop_count()
	{
		return _delegate.def_hop_count();
	}

	public org.omg.CosTrading.FollowOption max_follow_policy()
	{
		return _delegate.max_follow_policy();
	}

}
