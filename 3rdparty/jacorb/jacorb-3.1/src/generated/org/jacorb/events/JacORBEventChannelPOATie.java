package org.jacorb.events;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "JacORBEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.03.02
 */

public class JacORBEventChannelPOATie
	extends JacORBEventChannelPOA
{
	private JacORBEventChannelOperations _delegate;

	private POA _poa;
	public JacORBEventChannelPOATie(JacORBEventChannelOperations delegate)
	{
		_delegate = delegate;
	}
	public JacORBEventChannelPOATie(JacORBEventChannelOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.jacorb.events.JacORBEventChannel _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.jacorb.events.JacORBEventChannel __r = org.jacorb.events.JacORBEventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.jacorb.events.JacORBEventChannel _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.jacorb.events.JacORBEventChannel __r = org.jacorb.events.JacORBEventChannelHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public JacORBEventChannelOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(JacORBEventChannelOperations delegate)
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
	public org.omg.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier()
	{
		return _delegate.obtain_pull_supplier();
	}

	public org.omg.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer()
	{
		return _delegate.obtain_push_consumer();
	}

	public org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer()
	{
		return _delegate.obtain_pull_consumer();
	}

	public org.omg.CosEventChannelAdmin.ConsumerAdmin for_consumers()
	{
		return _delegate.for_consumers();
	}

	public org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier()
	{
		return _delegate.obtain_push_supplier();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public org.omg.CosEventChannelAdmin.SupplierAdmin for_suppliers()
	{
		return _delegate.for_suppliers();
	}

}
