package org.omg.CosTypedEventChannelAdmin;


/**
 * Generated from IDL interface "TypedSupplierAdmin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public interface TypedSupplierAdminOperations
	extends org.omg.CosEventChannelAdmin.SupplierAdminOperations
{
	/* constants */
	/* operations  */
	org.omg.CosTypedEventChannelAdmin.TypedProxyPushConsumer obtain_typed_push_consumer(java.lang.String supported_interface) throws org.omg.CosTypedEventChannelAdmin.InterfaceNotSupported;
	org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_typed_pull_consumer(java.lang.String uses_interface) throws org.omg.CosTypedEventChannelAdmin.NoSuchImplementation;
}
