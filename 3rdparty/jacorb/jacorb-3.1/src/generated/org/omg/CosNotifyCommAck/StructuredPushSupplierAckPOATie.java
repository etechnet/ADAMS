package org.omg.CosNotifyCommAck;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "StructuredPushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class StructuredPushSupplierAckPOATie
	extends StructuredPushSupplierAckPOA
{
	private StructuredPushSupplierAckOperations _delegate;

	private POA _poa;
	public StructuredPushSupplierAckPOATie(StructuredPushSupplierAckOperations delegate)
	{
		_delegate = delegate;
	}
	public StructuredPushSupplierAckPOATie(StructuredPushSupplierAckOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyCommAck.StructuredPushSupplierAck _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyCommAck.StructuredPushSupplierAck __r = org.omg.CosNotifyCommAck.StructuredPushSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyCommAck.StructuredPushSupplierAck _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyCommAck.StructuredPushSupplierAck __r = org.omg.CosNotifyCommAck.StructuredPushSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public StructuredPushSupplierAckOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(StructuredPushSupplierAckOperations delegate)
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
	public void disconnect_structured_push_supplier()
	{
_delegate.disconnect_structured_push_supplier();
	}

	public void subscription_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.subscription_change(added,removed);
	}

	public void acknowledge(int[] sequence_numbers)
	{
_delegate.acknowledge(sequence_numbers);
	}

}
