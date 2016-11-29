package org.omg.CosConcurrencyControl;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "LockSetFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class LockSetFactoryPOATie
	extends LockSetFactoryPOA
{
	private LockSetFactoryOperations _delegate;

	private POA _poa;
	public LockSetFactoryPOATie(LockSetFactoryOperations delegate)
	{
		_delegate = delegate;
	}
	public LockSetFactoryPOATie(LockSetFactoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosConcurrencyControl.LockSetFactory _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosConcurrencyControl.LockSetFactory __r = org.omg.CosConcurrencyControl.LockSetFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosConcurrencyControl.LockSetFactory _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosConcurrencyControl.LockSetFactory __r = org.omg.CosConcurrencyControl.LockSetFactoryHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public LockSetFactoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(LockSetFactoryOperations delegate)
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
	public org.omg.CosConcurrencyControl.TransactionalLockSet create_transactional()
	{
		return _delegate.create_transactional();
	}

	public org.omg.CosConcurrencyControl.TransactionalLockSet create_transactional_related(org.omg.CosConcurrencyControl.TransactionalLockSet which)
	{
		return _delegate.create_transactional_related(which);
	}

	public org.omg.CosConcurrencyControl.LockSet create_related(org.omg.CosConcurrencyControl.LockSet which)
	{
		return _delegate.create_related(which);
	}

	public org.omg.CosConcurrencyControl.LockSet create()
	{
		return _delegate.create();
	}

}
