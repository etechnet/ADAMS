package org.omg.CosEventComm;


/**
 * Generated from IDL interface "PushConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface PushConsumerOperations
{
	/* constants */
	/* operations  */
	void push(org.omg.CORBA.Any data) throws org.omg.CosEventComm.Disconnected;
	void disconnect_push_consumer();
}
