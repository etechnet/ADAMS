package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "StructuredProxyPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface StructuredProxyPushConsumerOperations
	extends org.omg.CosNotifyChannelAdmin.ProxyConsumerOperations , org.omg.CosNotifyComm.StructuredPushConsumerOperations
{
	/* constants */
	/* operations  */
	void connect_structured_push_supplier(org.omg.CosNotifyComm.StructuredPushSupplier push_supplier) throws org.omg.CosEventChannelAdmin.AlreadyConnected;
}
