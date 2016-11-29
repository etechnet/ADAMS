package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Iterator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class IteratorPOATie
	extends IteratorPOA
{
	private IteratorOperations _delegate;

	private POA _poa;
	public IteratorPOATie(IteratorOperations delegate)
	{
		_delegate = delegate;
	}
	public IteratorPOATie(IteratorOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.Iterator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Iterator __r = org.omg.CosCollection.IteratorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Iterator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Iterator __r = org.omg.CosCollection.IteratorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public IteratorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IteratorOperations delegate)
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
	public void replace_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.replace_element(element);
	}

	public void assign(org.omg.CosCollection.Iterator from_where) throws org.omg.CosCollection.IteratorInvalid
	{
_delegate.assign(from_where);
	}

	public boolean remove_next_n_elements(int n, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.remove_next_n_elements(n,actual_number);
	}

	public boolean remove_element_set_to_next() throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.remove_element_set_to_next();
	}

	public boolean retrieve_next_n_elements(int n, org.omg.CosCollection.AnySequenceHolder result, org.omg.CORBA.BooleanHolder more) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_next_n_elements(n,result,more);
	}

	public boolean replace_next_n_elements(org.omg.CORBA.Any[] elements, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.replace_next_n_elements(elements,actual_number);
	}

	public boolean is_equal(org.omg.CosCollection.Iterator test) throws org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.is_equal(test);
	}

	public org.omg.CosCollection.Iterator _clone()
	{
		return _delegate._clone();
	}

	public boolean retrieve_element_set_to_next(org.omg.CORBA.AnyHolder element, org.omg.CORBA.BooleanHolder more) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element_set_to_next(element,more);
	}

	public boolean set_to_first_element()
	{
		return _delegate.set_to_first_element();
	}

	public boolean is_in_between()
	{
		return _delegate.is_in_between();
	}

	public boolean set_to_next_nth_element(int n) throws org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.set_to_next_nth_element(n);
	}

	public boolean not_equal_remove_element_set_to_next(org.omg.CosCollection.Iterator test) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.not_equal_remove_element_set_to_next(test);
	}

	public boolean replace_element_set_to_next(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.replace_element_set_to_next(element);
	}

	public void invalidate()
	{
_delegate.invalidate();
	}

	public boolean not_equal_replace_element_set_to_next(org.omg.CosCollection.Iterator test, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.not_equal_replace_element_set_to_next(test,element);
	}

	public boolean set_to_next_element() throws org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.set_to_next_element();
	}

	public boolean is_for(org.omg.CosCollection.Collection collector)
	{
		return _delegate.is_for(collector);
	}

	public void remove_element() throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.remove_element();
	}

	public boolean add_element_set_iterator(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_element_set_iterator(element);
	}

	public boolean is_valid()
	{
		return _delegate.is_valid();
	}

	public boolean is_const()
	{
		return _delegate.is_const();
	}

	public boolean retrieve_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element(element);
	}

	public boolean add_n_elements_set_iterator(org.omg.CORBA.Any[] elements, org.omg.CORBA.IntHolder actual_number) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_n_elements_set_iterator(elements,actual_number);
	}

	public boolean not_equal_retrieve_element_set_to_next(org.omg.CosCollection.Iterator test, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.not_equal_retrieve_element_set_to_next(test,element);
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
