package org.omg.CosTrading;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Proxy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class ProxyPOATie
	extends ProxyPOA
{
	private ProxyOperations _delegate;

	private POA _poa;
	public ProxyPOATie(ProxyOperations delegate)
	{
		_delegate = delegate;
	}
	public ProxyPOATie(ProxyOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTrading.Proxy _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTrading.Proxy __r = org.omg.CosTrading.ProxyHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTrading.Proxy _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTrading.Proxy __r = org.omg.CosTrading.ProxyHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public ProxyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ProxyOperations delegate)
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
	public org.omg.CosTrading.Admin admin_if()
	{
		return _delegate.admin_if();
	}

	public org.omg.CORBA.Object type_repos()
	{
		return _delegate.type_repos();
	}

	public boolean supports_proxy_offers()
	{
		return _delegate.supports_proxy_offers();
	}

	public org.omg.CosTrading.Link link_if()
	{
		return _delegate.link_if();
	}

	public boolean supports_modifiable_properties()
	{
		return _delegate.supports_modifiable_properties();
	}

	public org.omg.CosTrading.Proxy proxy_if()
	{
		return _delegate.proxy_if();
	}

	public void withdraw_proxy(java.lang.String id) throws org.omg.CosTrading.ProxyPackage.NotProxyOfferId,org.omg.CosTrading.IllegalOfferId,org.omg.CosTrading.UnknownOfferId
	{
_delegate.withdraw_proxy(id);
	}

	public org.omg.CosTrading.Register register_if()
	{
		return _delegate.register_if();
	}

	public org.omg.CosTrading.Lookup lookup_if()
	{
		return _delegate.lookup_if();
	}

	public org.omg.CosTrading.ProxyPackage.ProxyInfo describe_proxy(java.lang.String id) throws org.omg.CosTrading.ProxyPackage.NotProxyOfferId,org.omg.CosTrading.IllegalOfferId,org.omg.CosTrading.UnknownOfferId
	{
		return _delegate.describe_proxy(id);
	}

	public boolean supports_dynamic_properties()
	{
		return _delegate.supports_dynamic_properties();
	}

	public java.lang.String export_proxy(org.omg.CosTrading.Lookup target, java.lang.String type, org.omg.CosTrading.Property[] properties, boolean if_match_all, java.lang.String recipe, org.omg.CosTrading.Policy[] policies_to_pass_on) throws org.omg.CosTrading.DuplicatePropertyName,org.omg.CosTrading.MissingMandatoryProperty,org.omg.CosTrading.IllegalServiceType,org.omg.CosTrading.ProxyPackage.IllegalRecipe,org.omg.CosTrading.ReadonlyDynamicProperty,org.omg.CosTrading.DuplicatePolicyName,org.omg.CosTrading.PropertyTypeMismatch,org.omg.CosTrading.IllegalPropertyName,org.omg.CosTrading.UnknownServiceType,org.omg.CosTrading.InvalidLookupRef
	{
		return _delegate.export_proxy(target,type,properties,if_match_all,recipe,policies_to_pass_on);
	}

}
