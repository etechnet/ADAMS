package org.omg.CosTypedNotifyChannelAdmin;

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
	public org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin __r = org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin __r = org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdminHelper.narrow(__o);
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
	public int[] get_all_filters()
	{
		return _delegate.get_all_filters();
	}

	public org.omg.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier()
	{
		return _delegate.obtain_pull_supplier();
	}

	public org.omg.CosNotifyChannelAdmin.ProxySupplier get_proxy_supplier(int proxy_id) throws org.omg.CosNotifyChannelAdmin.ProxyNotFound
	{
		return _delegate.get_proxy_supplier(proxy_id);
	}

	public int MyID()
	{
		return _delegate.MyID();
	}

	public org.omg.CosNotification.Property[] get_qos()
	{
		return _delegate.get_qos();
	}

	public org.omg.CosNotifyFilter.MappingFilter lifetime_filter()
	{
		return _delegate.lifetime_filter();
	}

	public org.omg.CosTypedEventChannelAdmin.TypedProxyPullSupplier obtain_typed_pull_supplier(java.lang.String supported_interface) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported
	{
		return _delegate.obtain_typed_pull_supplier(supported_interface);
	}

	public org.omg.CosNotifyFilter.MappingFilter priority_filter()
	{
		return _delegate.priority_filter();
	}

	public org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_typed_push_supplier(java.lang.String uses_interface) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation
	{
		return _delegate.obtain_typed_push_supplier(uses_interface);
	}

	public org.omg.CosNotifyFilter.Filter get_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
		return _delegate.get_filter(filter);
	}

	public org.omg.CosNotifyChannelAdmin.ProxySupplier obtain_notification_pull_supplier(org.omg.CosNotifyChannelAdmin.ClientType ctype, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_notification_pull_supplier(ctype,proxy_id);
	}

	public org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator MyOperator()
	{
		return _delegate.MyOperator();
	}

	public void set_qos(org.omg.CosNotification.Property[] qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.set_qos(qos);
	}

	public int[] pull_suppliers()
	{
		return _delegate.pull_suppliers();
	}

	public org.omg.CosNotifyChannelAdmin.ProxySupplier obtain_notification_push_supplier(org.omg.CosNotifyChannelAdmin.ClientType ctype, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_notification_push_supplier(ctype,proxy_id);
	}

	public int add_filter(org.omg.CosNotifyFilter.Filter new_filter)
	{
		return _delegate.add_filter(new_filter);
	}

	public void subscription_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.subscription_change(added,removed);
	}

	public void priority_filter(org.omg.CosNotifyFilter.MappingFilter a)
	{
		_delegate.priority_filter(a);
	}

	public org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier()
	{
		return _delegate.obtain_push_supplier();
	}

	public void remove_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
_delegate.remove_filter(filter);
	}

	public void remove_all_filters()
	{
_delegate.remove_all_filters();
	}

	public org.omg.CosTypedNotifyChannelAdmin.TypedProxyPushSupplier obtain_typed_notification_push_supplier(java.lang.String uses_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_typed_notification_push_supplier(uses_interface,proxy_id);
	}

	public org.omg.CosNotifyChannelAdmin.EventChannel MyChannel()
	{
		return _delegate.MyChannel();
	}

	public void lifetime_filter(org.omg.CosNotifyFilter.MappingFilter a)
	{
		_delegate.lifetime_filter(a);
	}

	public void validate_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_qos(required_qos,available_qos);
	}

	public int[] push_suppliers()
	{
		return _delegate.push_suppliers();
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullSupplier obtain_typed_notification_pull_supplier(java.lang.String supported_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_typed_notification_pull_supplier(supported_interface,proxy_id);
	}

}
