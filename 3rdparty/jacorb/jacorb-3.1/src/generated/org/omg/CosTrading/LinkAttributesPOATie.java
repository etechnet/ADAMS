package org.omg.CosTrading;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "LinkAttributes".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class LinkAttributesPOATie
	extends LinkAttributesPOA
{
	private LinkAttributesOperations _delegate;

	private POA _poa;
	public LinkAttributesPOATie(LinkAttributesOperations delegate)
	{
		_delegate = delegate;
	}
	public LinkAttributesPOATie(LinkAttributesOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTrading.LinkAttributes _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTrading.LinkAttributes __r = org.omg.CosTrading.LinkAttributesHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTrading.LinkAttributes _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTrading.LinkAttributes __r = org.omg.CosTrading.LinkAttributesHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public LinkAttributesOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(LinkAttributesOperations delegate)
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
	public org.omg.CosTrading.FollowOption max_link_follow_policy()
	{
		return _delegate.max_link_follow_policy();
	}

}
