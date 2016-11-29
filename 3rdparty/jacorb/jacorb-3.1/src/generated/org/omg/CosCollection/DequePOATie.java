package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Deque".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class DequePOATie
	extends DequePOA
{
	private DequeOperations _delegate;

	private POA _poa;
	public DequePOATie(DequeOperations delegate)
	{
		_delegate = delegate;
	}
	public DequePOATie(DequeOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.Deque _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Deque __r = org.omg.CosCollection.DequeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Deque _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Deque __r = org.omg.CosCollection.DequeHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public DequeOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(DequeOperations delegate)
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

	public void enqueue_as_last(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.enqueue_as_last(element);
	}

	public boolean element_dequeue_first(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.element_dequeue_first(element);
	}

	public int size()
	{
		return _delegate.size();
	}

	public void dequeue_first() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.dequeue_first();
	}

	public boolean element_dequeue_last(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.element_dequeue_last(element);
	}

	public boolean unfilled()
	{
		return _delegate.unfilled();
	}

	public void enqueue_as_first(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.enqueue_as_first(element);
	}

	public void dequeue_last() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.dequeue_last();
	}

}
