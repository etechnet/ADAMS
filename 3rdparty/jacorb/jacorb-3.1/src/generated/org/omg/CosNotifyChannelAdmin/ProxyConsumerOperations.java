package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "ProxyConsumer".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface ProxyConsumerOperations
	extends org.omg.CosNotification.QoSAdminOperations , org.omg.CosNotifyFilter.FilterAdminOperations
{
	/* constants */
	/* operations  */
	org.omg.CosNotifyChannelAdmin.ProxyType MyType();
	org.omg.CosNotifyChannelAdmin.SupplierAdmin MyAdmin();
	org.omg.CosNotification.EventType[] obtain_subscription_types(org.omg.CosNotifyChannelAdmin.ObtainInfoMode mode);
	void validate_event_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS;
}
