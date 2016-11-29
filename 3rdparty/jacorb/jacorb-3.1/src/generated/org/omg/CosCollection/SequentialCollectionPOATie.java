package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "SequentialCollection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class SequentialCollectionPOATie
	extends SequentialCollectionPOA
{
	private SequentialCollectionOperations _delegate;

	private POA _poa;
	public SequentialCollectionPOATie(SequentialCollectionOperations delegate)
	{
		_delegate = delegate;
	}
	public SequentialCollectionPOATie(SequentialCollectionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.SequentialCollection _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.SequentialCollection __r = org.omg.CosCollection.SequentialCollectionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.SequentialCollection _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.SequentialCollection __r = org.omg.CosCollection.SequentialCollectionHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public SequentialCollectionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SequentialCollectionOperations delegate)
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
	public void add_element_as_last(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_element_as_last(element);
	}

	public void reverse()
	{
_delegate.reverse();
	}

	public int number_of_elements()
	{
		return _delegate.number_of_elements();
	}

	public boolean retrieve_element_at(org.omg.CosCollection.Iterator where, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
		return _delegate.retrieve_element_at(where,element);
	}

	public boolean retrieve_element_at_position(int position, org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.PositionInvalid
	{
		return _delegate.retrieve_element_at_position(position,element);
	}

	public void add_element_as_first(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.add_element_as_first(element);
	}

	public void add_element_at_position(int position, org.omg.CORBA.Any element) throws org.omg.CosCollection.PositionInvalid,org.omg.CosCollection.ElementInvalid
	{
_delegate.add_element_at_position(position,element);
	}

	public void add_element_as_next(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
_delegate.add_element_as_next(element,where);
	}

	public void replace_element_at_position(int position, org.omg.CORBA.Any element) throws org.omg.CosCollection.PositionInvalid,org.omg.CosCollection.ElementInvalid
	{
_delegate.replace_element_at_position(position,element);
	}

	public org.omg.CORBA.TypeCode element_type()
	{
		return _delegate.element_type();
	}

	public void replace_last_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.EmptyCollection,org.omg.CosCollection.ElementInvalid
	{
_delegate.replace_last_element(element);
	}

	public org.omg.CosCollection.OrderedIterator create_ordered_iterator(boolean read_only, boolean reverse_iteration)
	{
		return _delegate.create_ordered_iterator(read_only,reverse_iteration);
	}

	public boolean add_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
		return _delegate.add_element(element);
	}

	public void remove_element_at(org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.IteratorInvalid,org.omg.CosCollection.IteratorInBetween
	{
_delegate.remove_element_at(where);
	}

	public void sort(org.omg.CosCollection.Comparator comparison)
	{
_delegate.sort(comparison);
	}

	public void add_element_at_position_set_iterator(int position, org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.PositionInvalid,org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
_delegate.add_element_at_position_set_iterator(position,element,where);
	}

	public boolean is_empty()
	{
		return _delegate.is_empty();
	}

	public boolean retrieve_first_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.retrieve_first_element(element);
	}

	public org.omg.CosCollection.Iterator create_iterator(boolean read_only)
	{
		return _delegate.create_iterator(read_only);
	}

	public void replace_first_element(org.omg.CORBA.Any element) throws org.omg.CosCollection.EmptyCollection,org.omg.CosCollection.ElementInvalid
	{
_delegate.replace_first_element(element);
	}

	public void add_element_as_last_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
_delegate.add_element_as_last_set_iterator(element,where);
	}

	public boolean add_element_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
		return _delegate.add_element_set_iterator(element,where);
	}

	public void remove_last_element() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.remove_last_element();
	}

	public void add_element_as_first_set_iterator(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
_delegate.add_element_as_first_set_iterator(element,where);
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

	public void add_element_as_previous(org.omg.CORBA.Any element, org.omg.CosCollection.Iterator where) throws org.omg.CosCollection.ElementInvalid,org.omg.CosCollection.IteratorInvalid
	{
_delegate.add_element_as_previous(element,where);
	}

	public boolean retrieve_last_element(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.retrieve_last_element(element);
	}

	public void remove_element_at_position(int position) throws org.omg.CosCollection.PositionInvalid
	{
_delegate.remove_element_at_position(position);
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
