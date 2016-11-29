package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "EqualitySortedCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class EqualitySortedCollectionPOATie
	extends EqualitySortedCollectionPOA
{
	private EqualitySortedCollectionOperations _delegate;

	private POA _poa;
	public EqualitySortedCollectionPOATie(EqualitySortedCollectionOperations delegate)
	{
		_delegate = delegate;
	}
	public EqualitySortedCollectionPOATie(EqualitySortedCollectionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.EqualitySortedCollection _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.EqualitySortedCollection __r = org.omg.CosCollection.EqualitySortedCollectionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.EqualitySortedCollection _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.EqualitySortedCollection __r = org.omg.CosCollection.EqualitySortedCollectionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public EqualitySortedCollectionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(EqualitySortedCollectionOperations delegate)
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
	public boolean locate_or_add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_or_add_element_set_iterator(element,where);
	}

	public int number_of_elements()
	{
		return _delegate.number_of_elements();
	}

	public boolean locate_last_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_last_element(element,where);
	}

	public boolean locate_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_element(element,where);
	}

	public boolean retrieve_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element_at(where,element);
	}

	public boolean retrieve_element_at_position(int position, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.PositionInvalid
	{
		return _delegate.retrieve_element_at_position(position,element);
	}

	public boolean locate_previous_different_element(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_previous_different_element(where);
	}

	public org.omg.CORBA.TypeCode element_type()
	{
		return _delegate.element_type();
	}

	public boolean locate_previous_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_previous_element(element,where);
	}

	public org.omg.CosCollection.OrderedIterator create_ordered_iterator(boolean read_only, boolean reverse_iteration)
	{
		return _delegate.create_ordered_iterator(read_only,reverse_iteration);
	}

	public boolean remove_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.remove_element(element);
	}

	public int number_of_different_elements()
	{
		return _delegate.number_of_different_elements();
	}

	public boolean contains_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.contains_element(element);
	}

	public void remove_element_at(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.remove_element_at(where);
	}

	public boolean add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_element(element);
	}

	public boolean locate_next_different_element(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.locate_next_different_element(where);
	}

	public boolean is_empty()
	{
		return _delegate.is_empty();
	}

	public boolean retrieve_first_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.retrieve_first_element(element);
	}

	public boolean locate_next_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_next_element(element,where);
	}

	public org.omg.CosCollection.Iterator create_iterator(boolean read_only)
	{
		return _delegate.create_iterator(read_only);
	}

	public boolean locate_or_add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.locate_or_add_element(element);
	}

	public boolean contains_all_from(org.omg.CosCollection.Collection collector) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.contains_all_from(collector);
	}

	public boolean locate_first_element(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.locate_first_element(element,where);
	}

	public int number_of_occurrences(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.number_of_occurrences(element);
	}

	public void remove_last_element() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.remove_last_element();
	}

	public boolean add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_element_set_iterator(element,where);
	}

	public void remove_first_element() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.remove_first_element();
	}

	public int remove_all()
	{
		return _delegate.remove_all();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public void remove_element_at_position(int position) throws org.omg.CosCollection.PositionInvalid
	{
_delegate.remove_element_at_position(position);
	}

	public boolean retrieve_last_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.retrieve_last_element(element);
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

	public int remove_all_occurrences(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.remove_all_occurrences(element);
	}

}
