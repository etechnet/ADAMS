package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "StructuredProxyPullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface StructuredProxyPullSupplierOperations
	extends org.omg.CosNotifyChannelAdmin.ProxySupplierOperations , org.omg.CosNotifyComm.StructuredPullSupplierOperations
{
	/* constants */
	/* operations  */
	void connect_structured_pull_consumer(org.omg.CosNotifyComm.StructuredPullConsumer pull_consumer) throws org.omg.CosEventChannelAdmin.AlreadyConnected;
}
