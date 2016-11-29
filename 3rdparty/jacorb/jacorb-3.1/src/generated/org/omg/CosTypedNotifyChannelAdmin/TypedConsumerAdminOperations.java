package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedConsumerAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public interface TypedConsumerAdminOperations
	extends org.omg.CosNotifyChannelAdmin.ConsumerAdminOperations , org.omg.CosTypedEventChannelAdmin.TypedConsumerAdminOperations
{
	/* constants */
	/* operations  */
	org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullSupplier obtain_typed_notification_pull_supplier(java.lang.String supported_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded;
	org.omg.CosTypedNotifyChannelAdmin.TypedProxyPushSupplier obtain_typed_notification_push_supplier(java.lang.String uses_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded;
}
