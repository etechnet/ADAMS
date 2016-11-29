package org.omg.CosEventComm;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "PullConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class PullConsumerPOATie
	extends PullConsumerPOA
{
	private PullConsumerOperations _delegate;

	private POA _poa;
	public PullConsumerPOATie(PullConsumerOperations delegate)
	{
		_delegate = delegate;
	}
	public PullConsumerPOATie(PullConsumerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosEventComm.PullConsumer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosEventComm.PullConsumer __r = org.omg.CosEventComm.PullConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosEventComm.PullConsumer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosEventComm.PullConsumer __r = org.omg.CosEventComm.PullConsumerHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public PullConsumerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PullConsumerOperations delegate)
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
	public void disconnect_pull_consumer()
	{
_delegate.disconnect_pull_consumer();
	}

}
