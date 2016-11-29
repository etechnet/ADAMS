package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL interface "ProxyPushSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface ProxyPushSupplierOperations
	extends org.omg.CosEventComm.PushSupplierOperations
{
	/* constants */
	/* operations  */
	void connect_push_consumer(org.omg.CosEventComm.PushConsumer push_consumer) throws org.omg.CosEventChannelAdmin.AlreadyConnected,org.omg.CosEventChannelAdmin.TypeError;
}
