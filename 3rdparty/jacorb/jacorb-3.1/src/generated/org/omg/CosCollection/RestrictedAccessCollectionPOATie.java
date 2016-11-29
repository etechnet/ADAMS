package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RestrictedAccessCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class RestrictedAccessCollectionPOATie
	extends RestrictedAccessCollectionPOA
{
	private RestrictedAccessCollectionOperations _delegate;

	private POA _poa;
	public RestrictedAccessCollectionPOATie(RestrictedAccessCollectionOperations delegate)
	{
		_delegate = delegate;
	}
	public RestrictedAccessCollectionPOATie(RestrictedAccessCollectionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.RestrictedAccessCollection _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.RestrictedAccessCollection __r = org.omg.CosCollection.RestrictedAccessCollectionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.RestrictedAccessCollection _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.RestrictedAccessCollection __r = org.omg.CosCollection.RestrictedAccessCollectionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public RestrictedAccessCollectionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RestrictedAccessCollectionOperations delegate)
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
	public void purge()
	{
_delegate.purge();
	}

	public boolean unfilled()
	{
		return _delegate.unfilled();
	}

	public int size()
	{
		return _delegate.size();
	}

}
