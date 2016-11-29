package org.omg.CosNotifyCommAck;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "SequencePushSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public class SequencePushSupplierAckPOATie
	extends SequencePushSupplierAckPOA
{
	private SequencePushSupplierAckOperations _delegate;

	private POA _poa;
	public SequencePushSupplierAckPOATie(SequencePushSupplierAckOperations delegate)
	{
		_delegate = delegate;
	}
	public SequencePushSupplierAckPOATie(SequencePushSupplierAckOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyCommAck.SequencePushSupplierAck _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyCommAck.SequencePushSupplierAck __r = org.omg.CosNotifyCommAck.SequencePushSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyCommAck.SequencePushSupplierAck _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyCommAck.SequencePushSupplierAck __r = org.omg.CosNotifyCommAck.SequencePushSupplierAckHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public SequencePushSupplierAckOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SequencePushSupplierAckOperations delegate)
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

	public void acknowledge(int[] sequence_numbers)
	{
_delegate.acknowledge(sequence_numbers);
	}

	public void disconnect_sequence_push_supplier()
	{
_delegate.disconnect_sequence_push_supplier();
	}

}
