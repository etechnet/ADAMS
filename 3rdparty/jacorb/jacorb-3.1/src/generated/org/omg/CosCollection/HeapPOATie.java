package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Heap".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class HeapPOATie
	extends HeapPOA
{
	private HeapOperations _delegate;

	private POA _poa;
	public HeapPOATie(HeapOperations delegate)
	{
		_delegate = delegate;
	}
	public HeapPOATie(HeapOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.Heap _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Heap __r = org.omg.CosCollection.HeapHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Heap _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Heap __r = org.omg.CosCollection.HeapHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public HeapOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(HeapOperations delegate)
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
	public int remove_all()
	{
		return _delegate.remove_all();
	}

	public boolean add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_element(element);
	}

	public void remove_element_at(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.remove_element_at(where);
	}

	public void replace_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.replace_element_at(where,element);
	}

	public int number_of_elements()
	{
		return _delegate.number_of_elements();
	}

	public org.omg.CosCollection.Iterator create_iterator(boolean read_only)
	{
		return _delegate.create_iterator(read_only);
	}

	public org.omg.CORBA.TypeCode element_type()
	{
		return _delegate.element_type();
	}

	public boolean retrieve_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element_at(where,element);
	}

	public boolean add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_element_set_iterator(element,where);
	}

	public boolean is_empty()
	{
		return _delegate.is_empty();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public void add_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_all_from(collector);
	}

	public boolean all_elements_do(org.omg.CosCollection.Command what)
	{
		return _delegate.all_elements_do(what);
	}

}
