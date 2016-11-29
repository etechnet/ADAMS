package org.omg.CosCollection;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Stack".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class StackPOATie
	extends StackPOA
{
	private StackOperations _delegate;

	private POA _poa;
	public StackPOATie(StackOperations delegate)
	{
		_delegate = delegate;
	}
	public StackPOATie(StackOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosCollection.Stack _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosCollection.Stack __r = org.omg.CosCollection.StackHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosCollection.Stack _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosCollection.Stack __r = org.omg.CosCollection.StackHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public StackOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(StackOperations delegate)
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

	public void pop() throws org.omg.CosCollection.EmptyCollection
	{
_delegate.pop();
	}

	public boolean top(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.top(element);
	}

	public boolean element_pop(org.omg.CORBA.AnyHolder element) throws org.omg.CosCollection.EmptyCollection
	{
		return _delegate.element_pop(element);
	}

	public void push(org.omg.CORBA.Any element) throws org.omg.CosCollection.ElementInvalid
	{
_delegate.push(element);
	}

}
