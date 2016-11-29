package org.omg.CosNotifyComm;


/**
 * Generated from IDL interface "SequencePushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface SequencePushConsumerOperations
	extends org.omg.CosNotifyComm.NotifyPublishOperations
{
	/* constants */
	/* operations  */
	void push_structured_events(org.omg.CosNotification.StructuredEvent[] notifications) throws org.omg.CosEventComm.Disconnected;
	void disconnect_sequence_push_consumer();
}
