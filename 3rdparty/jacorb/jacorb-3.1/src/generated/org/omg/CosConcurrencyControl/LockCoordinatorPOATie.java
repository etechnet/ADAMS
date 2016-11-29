package org.omg.CosConcurrencyControl;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "LockCoordinator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class LockCoordinatorPOATie
	extends LockCoordinatorPOA
{
	private LockCoordinatorOperations _delegate;

	private POA _poa;
	public LockCoordinatorPOATie(LockCoordinatorOperations delegate)
	{
		_delegate = delegate;
	}
	public LockCoordinatorPOATie(LockCoordinatorOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosConcurrencyControl.LockCoordinator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosConcurrencyControl.LockCoordinator __r = org.omg.CosConcurrencyControl.LockCoordinatorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosConcurrencyControl.LockCoordinator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosConcurrencyControl.LockCoordinator __r = org.omg.CosConcurrencyControl.LockCoordinatorHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public LockCoordinatorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(LockCoordinatorOperations delegate)
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
	public void drop_locks()
	{
_delegate.drop_locks();
	}

}
