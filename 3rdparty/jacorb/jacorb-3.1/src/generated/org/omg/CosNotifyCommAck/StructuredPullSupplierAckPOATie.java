package org.omg.CosNotifyCommAck;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "StructuredPullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class StructuredPullSupplierAckPOATie
	extends StructuredPullSupplierAckPOA
{
	private StructuredPullSupplierAckOperations _delegate;

	private POA _poa;
	public StructuredPullSupplierAckPOATie(StructuredPullSupplierAckOperations delegate)
	{
		_delegate = delegate;
	}
	public StructuredPullSupplierAckPOATie(StructuredPullSupplierAckOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyCommAck.StructuredPullSupplierAck _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyCommAck.StructuredPullSupplierAck __r = org.omg.CosNotifyCommAck.StructuredPullSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyCommAck.StructuredPullSupplierAck _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyCommAck.StructuredPullSupplierAck __r = org.omg.CosNotifyCommAck.StructuredPullSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public StructuredPullSupplierAckOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(StructuredPullSupplierAckOperations delegate)
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
	public void disconnect_structured_pull_supplier()
	{
_delegate.disconnect_structured_pull_supplier();
	}

	public void subscription_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.subscription_change(added,removed);
	}

	public void acknowledge(int[] sequence_numbers)
	{
_delegate.acknowledge(sequence_numbers);
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
