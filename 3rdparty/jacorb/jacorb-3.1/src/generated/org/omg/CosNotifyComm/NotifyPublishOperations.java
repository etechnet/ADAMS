package org.omg.CosNotifyComm;


/**
 * Generated from IDL interface "NotifyPublish".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface NotifyPublishOperations
{
	/* constants */
	/* operations  */
	void offer_change(org.omg.CosNotification.EventType[] added, org.omg.CosNotification.EventType[] removed) throws org.omg.CosNotifyComm.InvalidEventType;
}
