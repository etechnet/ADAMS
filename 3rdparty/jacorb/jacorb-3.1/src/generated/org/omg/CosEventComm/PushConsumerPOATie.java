package org.omg.CosEventComm;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "PushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class PushConsumerPOATie
	extends PushConsumerPOA
{
	private PushConsumerOperations _delegate;

	private POA _poa;
	public PushConsumerPOATie(PushConsumerOperations delegate)
	{
		_delegate = delegate;
	}
	public PushConsumerPOATie(PushConsumerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosEventComm.PushConsumer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosEventComm.PushConsumer __r = org.omg.CosEventComm.PushConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosEventComm.PushConsumer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosEventComm.PushConsumer __r = org.omg.CosEventComm.PushConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public PushConsumerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PushConsumerOperations delegate)
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
	public void disconnect_push_consumer()
	{
_delegate.disconnect_push_consumer();
	}

	public void push(org.omg.CORBA.Any data) throws org.omg.CosEventComm.Disconnected
	{
_delegate.push(data);
	}

}
