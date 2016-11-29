package org.omg.CosNotifyComm;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "StructuredPullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public class StructuredPullConsumerPOATie
	extends StructuredPullConsumerPOA
{
	private StructuredPullConsumerOperations _delegate;

	private POA _poa;
	public StructuredPullConsumerPOATie(StructuredPullConsumerOperations delegate)
	{
		_delegate = delegate;
	}
	public StructuredPullConsumerPOATie(StructuredPullConsumerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosNotifyComm.StructuredPullConsumer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosNotifyComm.StructuredPullConsumer __r = org.omg.CosNotifyComm.StructuredPullConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosNotifyComm.StructuredPullConsumer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosNotifyComm.StructuredPullConsumer __r = org.omg.CosNotifyComm.StructuredPullConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public StructuredPullConsumerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(StructuredPullConsumerOperations delegate)
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
	public void disconnect_structured_pull_consumer()
	{
_delegate.disconnect_structured_pull_consumer();
	}

	public void offer_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.offer_change(added,removed);
	}

}
