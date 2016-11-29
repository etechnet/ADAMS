package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "SequenceProxyPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface SequenceProxyPushConsumerOperations
	extends org.omg.CosNotifyChannelAdmin.ProxyConsumerOperations , org.omg.CosNotifyComm.SequencePushConsumerOperations
{
	/* constants */
	/* operations  */
	void connect_sequence_push_supplier(org.omg.CosNotifyComm.SequencePushSupplier push_supplier) throws org.omg.CosEventChannelAdmin.AlreadyConnected;
}
