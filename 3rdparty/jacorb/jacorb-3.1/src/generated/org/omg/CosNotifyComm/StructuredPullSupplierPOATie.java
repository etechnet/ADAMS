package org.omg.CosNotifyComm;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "StructuredPullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class StructuredPullSupplierPOATie
	extends StructuredPullSupplierPOA
{
	private StructuredPullSupplierOperations _delegate;

	private POA _poa;
	public StructuredPullSupplierPOATie(StructuredPullSupplierOperations delegate)
	{
		_delegate = delegate;
	}
	public StructuredPullSupplierPOATie(StructuredPullSupplierOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyComm.StructuredPullSupplier _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyComm.StructuredPullSupplier __r = org.omg.CosNotifyComm.StructuredPullSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyComm.StructuredPullSupplier _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyComm.StructuredPullSupplier __r = org.omg.CosNotifyComm.StructuredPullSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public StructuredPullSupplierOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(StructuredPullSupplierOperations delegate)
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
	public void subscription_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.subscription_change(added,removed);
	}

	public void disconnect_structured_pull_supplier()
	{
_delegate.disconnect_structured_pull_supplier();
	}

	public org.omg.CosNotification.StructuredEvent pull_structured_event() throws org.omg.CosEventComm.Disconnected
	{
		return _delegate.pull_structured_event();
	}

	public org.omg.CosNotification.StructuredEvent try_pull_structured_event(org.omg.CORBA.BooleanHolder has_event) throws org.omg.CosEventComm.Disconnected
	{
		return _delegate.try_pull_structured_event(has_event);
	}

}
