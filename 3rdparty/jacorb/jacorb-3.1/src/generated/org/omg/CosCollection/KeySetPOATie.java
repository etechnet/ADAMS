package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "KeySet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class KeySetPOATie
	extends KeySetPOA
{
	private KeySetOperations _delegate;

	private POA _poa;
	public KeySetPOATie(KeySetOperations delegate)
	{
		_delegate = delegate;
	}
	public KeySetPOATie(KeySetOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.KeySet _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.KeySet __r = org.omg.CosCollection.KeySetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.KeySet _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.KeySet __r = org.omg.CosCollection.KeySetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public KeySetOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(KeySetOperations delegate)
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
	public boolean add_or_replace_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_or_replace_element_with_key_set_iterator(element,where);
	}

	public int number_of_elements_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.number_of_elements_with_key(key);
	}

	public boolean locate_next_element_with_different_key(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.locate_next_element_with_different_key(where);
	}

	public int number_of_different_keys()
	{
		return _delegate.number_of_different_keys();
	}

	public int number_of_elements()
	{
		return _delegate.number_of_elements();
	}

	public boolean retrieve_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element_at(where,element);
	}

	public boolean replace_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.replace_element_with_key(element);
	}

	public boolean add_or_replace_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_or_replace_element_with_key(element);
	}

	public org.omg.CORBA.TypeCode key_type()
	{
		return _delegate.key_type();
	}

	public boolean retrieve_element_with_key(org.omg.CORBA.Any key, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.retrieve_element_with_key(key,element);
	}

	public void keys(org.omg.CORBA.Any[] elements, org.omg.CosCollection.AnySequenceHolder keys) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.keys(elements,keys);
	}

	public void key(org.omg.CORBA.Any element, org.omg.CORBA.AnyHolder key) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.key(element,key);
	}

	public org.omg.CORBA.TypeCode element_type()
	{
		return _delegate.element_type();
	}

	public boolean remove_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.remove_element_with_key(key);
	}

	public void remove_element_at(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.remove_element_at(where);
	}

	public boolean add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_element(element);
	}

	public boolean replace_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.replace_element_with_key_set_iterator(element,where);
	}

	public boolean locate_or_add_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.locate_or_add_element_with_key(element);
	}

	public boolean is_empty()
	{
		return _delegate.is_empty();
	}

	public boolean locate_next_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_next_element_with_key(key,where);
	}

	public org.omg.CosCollection.Iterator create_iterator(boolean read_only)
	{
		return _delegate.create_iterator(read_only);
	}

	public boolean locate_or_add_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_or_add_element_with_key_set_iterator(element,where);
	}

	public boolean locate_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_element_with_key(key,where);
	}

	public boolean add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_element_set_iterator(element,where);
	}

	public boolean contains_all_keys_from(org.omg.CosCollection.KeyCollection collector) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.contains_all_keys_from(collector);
	}

	public int remove_all_elements_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.remove_all_elements_with_key(key);
	}

	public int remove_all()
	{
		return _delegate.remove_all();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public boolean contains_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.contains_element_with_key(key);
	}

	public void add_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_all_from(collector);
	}

	public void replace_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.replace_element_at(where,element);
	}

	public boolean all_elements_do(org.omg.CosCollection.Command what)
	{
		return _delegate.all_elements_do(what);
	}

}
