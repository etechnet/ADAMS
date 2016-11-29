package org.omg.CosTypedNotifyChannelAdmin;

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
	public org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin __r = org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.narrow(__o);
		if (__o != null && __o != __r)
		{
			((org.omg.CORBA.portable.ObjectImpl)__o)._set_delegate(null);

		}
		return __r;
	}
	public org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin __r = org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdminHelper.narrow(__o);
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
	public org.omg.CosTypedNotifyChannelAdmin.TypedProxyPushConsumer obtain_typed_notification_push_consumer(java.lang.String supported_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_typed_notification_push_consumer(supported_interface,proxy_id);
	}

	public int[] get_all_filters()
	{
		return _delegate.get_all_filters();
	}

	public org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer()
	{
		return _delegate.obtain_pull_consumer();
	}

	public int[] pull_consumers()
	{
		return _delegate.pull_consumers();
	}

	public int MyID()
	{
		return _delegate.MyID();
	}

	public org.omg.CosNotification.Property[] get_qos()
	{
		return _delegate.get_qos();
	}

	public org.omg.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer()
	{
		return _delegate.obtain_push_consumer();
	}

	public org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer obtain_typed_notification_pull_consumer(java.lang.String uses_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_typed_notification_pull_consumer(uses_interface,proxy_id);
	}

	public org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_typed_pull_consumer(java.lang.String uses_interface) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation
	{
		return _delegate.obtain_typed_pull_consumer(uses_interface);
	}

	public org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer obtain_typed_push_consumer(java.lang.String supported_interface) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported
	{
		return _delegate.obtain_typed_push_consumer(supported_interface);
	}

	public org.omg.CosNotifyFilter.Filter get_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
		return _delegate.get_filter(filter);
	}

	public int[] push_consumers()
	{
		return _delegate.push_consumers();
	}

	public org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator MyOperator()
	{
		return _delegate.MyOperator();
	}

	public org.omg.CosNotifyChannelAdmin.ProxyConsumer obtain_notification_pull_consumer(org.omg.CosNotifyChannelAdmin.ClientType ctype, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_notification_pull_consumer(ctype,proxy_id);
	}

	public void set_qos(org.omg.CosNotification.Property[] qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.set_qos(qos);
	}

	public org.omg.CosNotifyChannelAdmin.ProxyConsumer get_proxy_consumer(int proxy_id) throws org.omg.CosNotifyChannelAdmin.ProxyNotFound
	{
		return _delegate.get_proxy_consumer(proxy_id);
	}

	public int add_filter(org.omg.CosNotifyFilter.Filter new_filter)
	{
		return _delegate.add_filter(new_filter);
	}

	public org.omg.CosNotifyChannelAdmin.ProxyConsumer obtain_notification_push_consumer(org.omg.CosNotifyChannelAdmin.ClientType ctype, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosNotifyChannelAdmin.AdminLimitExceeded
	{
		return _delegate.obtain_notification_push_consumer(ctype,proxy_id);
	}

	public void remove_filter(int filter) throws org.omg.CosNotifyFilter.FilterNotFound
	{
_delegate.remove_filter(filter);
	}

	public void offer_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType
	{
_delegate.offer_change(added,removed);
	}

	public void remove_all_filters()
	{
_delegate.remove_all_filters();
	}

	public org.omg.CosNotifyChannelAdmin.EventChannel MyChannel()
	{
		return _delegate.MyChannel();
	}

	public void validate_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS
	{
_delegate.validate_qos(required_qos,available_qos);
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
