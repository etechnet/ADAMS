package org.omg.CosNotifyComm;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "SequencePushSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class SequencePushSupplierPOATie
	extends SequencePushSupplierPOA
{
	private SequencePushSupplierOperations _delegate;

	private POA _poa;
	public SequencePushSupplierPOATie(SequencePushSupplierOperations delegate)
	{
		_delegate = delegate;
	}
	public SequencePushSupplierPOATie(SequencePushSupplierOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyComm.SequencePushSupplier _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyComm.SequencePushSupplier __r = org.omg.CosNotifyComm.SequencePushSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyComm.SequencePushSupplier _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyComm.SequencePushSupplier __r = org.omg.CosNotifyComm.SequencePushSupplierHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public SequencePushSupplierOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SequencePushSupplierOperations delegate)
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

	public void disconnect_sequence_push_supplier()
	{
_delegate.disconnect_sequence_push_supplier();
	}

}
