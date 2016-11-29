package org.omg.CosTrading;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "OfferIdIterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class OfferIdIteratorPOATie
	extends OfferIdIteratorPOA
{
	private OfferIdIteratorOperations _delegate;

	private POA _poa;
	public OfferIdIteratorPOATie(OfferIdIteratorOperations delegate)
	{
		_delegate = delegate;
	}
	public OfferIdIteratorPOATie(OfferIdIteratorOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTrading.OfferIdIterator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTrading.OfferIdIterator __r = org.omg.CosTrading.OfferIdIteratorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTrading.OfferIdIterator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTrading.OfferIdIterator __r = org.omg.CosTrading.OfferIdIteratorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public OfferIdIteratorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(OfferIdIteratorOperations delegate)
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
	public boolean next_n(int n, org.omg.CosTrading.OfferIdSeqHolder ids)
	{
		return _delegate.next_n(n,ids);
	}

	public int max_left() throws org.omg.CosTrading.UnknownMaxLeft
	{
		return _delegate.max_left();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
