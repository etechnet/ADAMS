package org.omg.CosConcurrencyControl;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "LockSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class LockSetPOATie
	extends LockSetPOA
{
	private LockSetOperations _delegate;

	private POA _poa;
	public LockSetPOATie(LockSetOperations delegate)
	{
		_delegate = delegate;
	}
	public LockSetPOATie(LockSetOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosConcurrencyControl.LockSet _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosConcurrencyControl.LockSet __r = org.omg.CosConcurrencyControl.LockSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosConcurrencyControl.LockSet _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosConcurrencyControl.LockSet __r = org.omg.CosConcurrencyControl.LockSetHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public LockSetOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(LockSetOperations delegate)
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
	public void lock(org.omg.CosConcurrencyControl.lock_mode mode)
	{
_delegate.lock(mode);
	}

	public boolean try_lock(org.omg.CosConcurrencyControl.lock_mode mode)
	{
		return _delegate.try_lock(mode);
	}

	public org.omg.CosConcurrencyControl.LockCoordinator get_coordinator(org.omg.CosTransactions.Coordinator which)
	{
		return _delegate.get_coordinator(which);
	}

	public void change_mode(org.omg.CosConcurrencyControl.lock_mode held_mode, org.omg.CosConcurrencyControl.lock_mode new_mode) throws org.omg.CosConcurrencyControl.LockNotHeld
	{
_delegate.change_mode(held_mode,new_mode);
	}

	public void unlock(org.omg.CosConcurrencyControl.lock_mode mode) throws org.omg.CosConcurrencyControl.LockNotHeld
	{
_delegate.unlock(mode);
	}

}
