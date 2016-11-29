package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "CollectionFactories".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class CollectionFactoriesPOATie
	extends CollectionFactoriesPOA
{
	private CollectionFactoriesOperations _delegate;

	private POA _poa;
	public CollectionFactoriesPOATie(CollectionFactoriesOperations delegate)
	{
		_delegate = delegate;
	}
	public CollectionFactoriesPOATie(CollectionFactoriesOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.CollectionFactories _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.CollectionFactories __r = org.omg.CosCollection.CollectionFactoriesHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.CollectionFactories _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.CollectionFactories __r = org.omg.CosCollection.CollectionFactoriesHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public CollectionFactoriesOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(CollectionFactoriesOperations delegate)
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
	public boolean add_factory(java.lang.String collection_interface, java.lang.String impl_category, java.lang.String impl_interface, org.omg.CosCollection.CollectionFactory factory)
	{
		return _delegate.add_factory(collection_interface,impl_category,impl_interface,factory);
	}

	public org.omg.CosCollection.Collection generic_create(org.omg.CosCollection.NVPair[] parameters) throws org.omg.CosCollection.ParameterInvalid
	{
		return _delegate.generic_create(parameters);
	}

	public org.omg.CosCollection.Collection create(org.omg.CosCollection.NVPair[] parameters) throws org.omg.CosCollection.ParameterInvalid
	{
		return _delegate.create(parameters);
	}

	public boolean remove_factory(java.lang.String collection_interface, java.lang.String impl_category, java.lang.String impl_interface)
	{
		return _delegate.remove_factory(collection_interface,impl_category,impl_interface);
	}

}
