package org.omg.CosNotifyComm;


/**
 * Generated from IDL interface "StructuredPushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface StructuredPushConsumerOperations
	extends org.omg.CosNotifyComm.NotifyPublishOperations
{
	/* constants */
	/* operations  */
	void push_structured_event(org.omg.CosNotification.StructuredEvent notification) throws org.omg.CosEventComm.Disconnected;
	void disconnect_structured_push_consumer();
}
