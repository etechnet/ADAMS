package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Queue".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class QueuePOATie
	extends QueuePOA
{
	private QueueOperations _delegate;

	private POA _poa;
	public QueuePOATie(QueueOperations delegate)
	{
		_delegate = delegate;
	}
	public QueuePOATie(QueueOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.Queue _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Queue __r = org.omg.CosCollection.QueueHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Queue _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Queue __r = org.omg.CosCollection.QueueHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public QueueOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(QueueOperations delegate)
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

	public boolean element_dequeue(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.element_dequeue(element);
	}

	public void dequeue() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.dequeue();
	}

	public void enqueue(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.enqueue(element);
	}

}
