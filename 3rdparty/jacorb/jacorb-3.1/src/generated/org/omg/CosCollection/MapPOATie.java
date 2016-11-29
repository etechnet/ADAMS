package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Map".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class MapPOATie
	extends MapPOA
{
	private MapOperations _delegate;

	private POA _poa;
	public MapPOATie(MapOperations delegate)
	{
		_delegate = delegate;
	}
	public MapPOATie(MapOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.Map _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Map __r = org.omg.CosCollection.MapHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Map _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Map __r = org.omg.CosCollection.MapHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public MapOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(MapOperations delegate)
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
	public int remove_all_elements_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.remove_all_elements_with_key(key);
	}

	public boolean locate_next_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_next_element(element,where);
	}

	public boolean contains_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.contains_element(element);
	}

	public boolean add_or_replace_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_or_replace_element_with_key_set_iterator(element,where);
	}

	public boolean contains_all_keys_from(org.omg.CosCollection.KeyCollection collector) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.contains_all_keys_from(collector);
	}

	public boolean locate_or_add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_or_add_element_set_iterator(element,where);
	}

	public int number_of_different_elements()
	{
		return _delegate.number_of_different_elements();
	}

	public boolean locate_next_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_next_element_with_key(key,where);
	}

	public org.omg.CORBA.TypeCode element_type()
	{
		return _delegate.element_type();
	}

	public org.omg.CosCollection.Iterator create_iterator(boolean read_only)
	{
		return _delegate.create_iterator(read_only);
	}

	public void add_intersection(org.omg.CosCollection.Map collector1, org.omg.CosCollection.Map collector2) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_intersection(collector1,collector2);
	}

	public boolean locate_element_with_key(org.omg.CORBA.Any key, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.KeyInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_element_with_key(key,where);
	}

	public int number_of_elements_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.number_of_elements_with_key(key);
	}

	public boolean is_empty()
	{
		return _delegate.is_empty();
	}

	public boolean locate_or_add_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_or_add_element_with_key_set_iterator(element,where);
	}

	public boolean locate_or_add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.locate_or_add_element(element);
	}

	public int remove_all_occurrences(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.remove_all_occurrences(element);
	}

	public boolean locate_or_add_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.locate_or_add_element_with_key(element);
	}

	public boolean remove_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.remove_element(element);
	}

	public boolean locate_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_element(element,where);
	}

	public boolean add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_element(element);
	}

	public void replace_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.replace_element_at(where,element);
	}

	public boolean add_or_replace_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_or_replace_element_with_key(element);
	}

	public boolean remove_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.remove_element_with_key(key);
	}

	public boolean retrieve_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element_at(where,element);
	}

	public org.omg.CORBA.TypeCode key_type()
	{
		return _delegate.key_type();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public void difference_with(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.difference_with(collector);
	}

	public void keys(org.omg.CORBA.Any[] elements, org.omg.CosCollection.AnySequenceHolder keys) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.keys(elements,keys);
	}

	public boolean replace_element_with_key_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.replace_element_with_key_set_iterator(element,where);
	}

	public boolean contains_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.contains_all_from(collector);
	}

	public int number_of_elements()
	{
		return _delegate.number_of_elements();
	}

	public void add_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_all_from(collector);
	}

	public void intersection_with(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.intersection_with(collector);
	}

	public boolean locate_next_element_with_different_key(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.locate_next_element_with_different_key(where);
	}

	public void union_with(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.union_with(collector);
	}

	public void remove_element_at(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.remove_element_at(where);
	}

	public int number_of_occurrences(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.number_of_occurrences(element);
	}

	public int number_of_different_keys()
	{
		return _delegate.number_of_different_keys();
	}

	public boolean equal(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.equal(collector);
	}

	public int remove_all()
	{
		return _delegate.remove_all();
	}

	public void add_union(org.omg.CosCollection.Map collector1, org.omg.CosCollection.Map collector2) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_union(collector1,collector2);
	}

	public boolean replace_element_with_key(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.replace_element_with_key(element);
	}

	public boolean add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_element_set_iterator(element,where);
	}

	public boolean contains_element_with_key(org.omg.CORBA.Any key) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.contains_element_with_key(key);
	}

	public void add_difference(org.omg.CosCollection.Map collector1, org.omg.CosCollection.Map collector2) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_difference(collector1,collector2);
	}

	public boolean not_equal(org.omg.CosCollection.Map collector) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.not_equal(collector);
	}

	public boolean all_elements_do(org.omg.CosCollection.Command what)
	{
		return _delegate.all_elements_do(what);
	}

	public boolean retrieve_element_with_key(org.omg.CORBA.Any key, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.KeyInvalid
	{
		return _delegate.retrieve_element_with_key(key,element);
	}

	public boolean locate_next_different_element(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.locate_next_different_element(where);
	}

	public void key(org.omg.CORBA.Any element, org.omg.CORBA.AnyHolder key) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.key(element,key);
	}

}
