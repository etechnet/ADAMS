package org.omg.CosEventComm;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "PullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class PullSupplierPOATie
	extends PullSupplierPOA
{
	private PullSupplierOperations _delegate;

	private POA _poa;
	public PullSupplierPOATie(PullSupplierOperations delegate)
	{
		_delegate = delegate;
	}
	public PullSupplierPOATie(PullSupplierOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosEventComm.PullSupplier _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosEventComm.PullSupplier __r = org.omg.CosEventComm.PullSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosEventComm.PullSupplier _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosEventComm.PullSupplier __r = org.omg.CosEventComm.PullSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public PullSupplierOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PullSupplierOperations delegate)
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
	public void disconnect_pull_supplier()
	{
_delegate.disconnect_pull_supplier();
	}

	public org.omg.CORBA.Any pull() throws org.omg.CosEventComm.Disconnected
	{
		return _delegate.pull();
	}

	public org.omg.CORBA.Any try_pull(org.omg.CORBA.BooleanHolder has_event) throws org.omg.CosEventComm.Disconnected
	{
		return _delegate.try_pull(has_event);
	}

}
