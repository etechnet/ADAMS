package org.omg.CosTypedEventChannelAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TypedSupplierAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public class TypedSupplierAdminPOATie
	extends TypedSupplierAdminPOA
{
	private TypedSupplierAdminOperations _delegate;

	private POA _poa;
	public TypedSupplierAdminPOATie(TypedSupplierAdminOperations delegate)
	{
		_delegate = delegate;
	}
	public TypedSupplierAdminPOATie(TypedSupplierAdminOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedSupplierAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedEventChannelAdmin.TypedSupplierAdmin __r = org.omg.CosTypedEventChannelAdmin.TypedSupplierAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedSupplierAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedEventChannelAdmin.TypedSupplierAdmin __r = org.omg.CosTypedEventChannelAdmin.TypedSupplierAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TypedSupplierAdminOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TypedSupplierAdminOperations delegate)
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
	public org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer obtain_typed_push_consumer(java.lang.String supported_interface) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported
	{
		return _delegate.obtain_typed_push_consumer(supported_interface);
	}

	public org.omg.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer()
	{
		return _delegate.obtain_push_consumer();
	}

	public org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer()
	{
		return _delegate.obtain_pull_consumer();
	}

	public org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_typed_pull_consumer(java.lang.String uses_interface) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation
	{
		return _delegate.obtain_typed_pull_consumer(uses_interface);
	}

}
