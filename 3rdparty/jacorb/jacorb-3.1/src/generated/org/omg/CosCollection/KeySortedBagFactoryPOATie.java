package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "KeySortedBagFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class KeySortedBagFactoryPOATie
	extends KeySortedBagFactoryPOA
{
	private KeySortedBagFactoryOperations _delegate;

	private POA _poa;
	public KeySortedBagFactoryPOATie(KeySortedBagFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public KeySortedBagFactoryPOATie(KeySortedBagFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.KeySortedBagFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.KeySortedBagFactory __r = org.omg.CosCollection.KeySortedBagFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.KeySortedBagFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.KeySortedBagFactory __r = org.omg.CosCollection.KeySortedBagFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public KeySortedBagFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(KeySortedBagFactoryOperations delegate)
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
	public org.omg.CosCollection.KeySortedBag create(org.omg.CosCollection.Operations ops, int expected_size)
	{
		return _delegate.create(ops,expected_size);
	}

	public org.omg.CosCollection.Collection generic_create(org.omg.CosCollection.NVPair[] parameters) throws org.omg.CosCollection.ParameterInvalid
	{
		return _delegate.generic_create(parameters);
	}

}
