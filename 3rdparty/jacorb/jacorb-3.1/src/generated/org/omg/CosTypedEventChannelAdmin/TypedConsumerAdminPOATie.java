package org.omg.CosTypedEventChannelAdmin;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TypedConsumerAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public class TypedConsumerAdminPOATie
	extends TypedConsumerAdminPOA
{
	private TypedConsumerAdminOperations _delegate;

	private POA _poa;
	public TypedConsumerAdminPOATie(TypedConsumerAdminOperations delegate)
	{
		_delegate = delegate;
	}
	public TypedConsumerAdminPOATie(TypedConsumerAdminOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin __r = org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin __r = org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public TypedConsumerAdminOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TypedConsumerAdminOperations delegate)
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

	public org.omg.CosTypedEventChannelAdmin.TypedProxyPullSupplier obtain_typed_pull_supplier(java.lang.String supported_interface) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported
	{
		return _delegate.obtain_typed_pull_supplier(supported_interface);
	}

	public org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_typed_push_supplier(java.lang.String uses_interface) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation
	{
		return _delegate.obtain_typed_push_supplier(uses_interface);
	}

	public org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier()
	{
		return _delegate.obtain_push_supplier();
	}

}
