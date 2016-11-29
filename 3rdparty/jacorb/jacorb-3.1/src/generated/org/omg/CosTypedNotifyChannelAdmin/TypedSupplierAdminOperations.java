package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedSupplierAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public interface TypedSupplierAdminOperations
	extends org.omg.CosNotifyChannelAdmin.SupplierAdminOperations , org.omg.CosTypedEventChannelAdmin.TypedSupplierAdminOperations
{
	/* constants */
	/* operations  */
	org.omg.CosTypedNotifyChannelAdmin.TypedProxyPushConsumer obtain_typed_notification_push_consumer(java.lang.String supported_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded;
	org.omg.CosTypedNotifyChannelAdmin.TypedProxyPullConsumer obtain_typed_notification_pull_consumer(java.lang.String uses_interface, org.omg.CORBA.IntHolder proxy_id) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation,org.omg.CosNotifyChannelAdmin.AdminLimitExceeded;
}
