package org.omg.CosNotifyChannelAdmin;


/**
 * Generated from IDL interface "ProxySupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface ProxySupplierOperations
	extends org.omg.CosNotification.QoSAdminOperations , org.omg.CosNotifyFilter.FilterAdminOperations
{
	/* constants */
	/* operations  */
	org.omg.CosNotifyChannelAdmin.ProxyType MyType();
	org.omg.CosNotifyChannelAdmin.ConsumerAdmin MyAdmin();
	org.omg.CosNotifyFilter.MappingFilter priority_filter();
	void priority_filter(org.omg.CosNotifyFilter.MappingFilter arg);
	org.omg.CosNotifyFilter.MappingFilter lifetime_filter();
	void lifetime_filter(org.omg.CosNotifyFilter.MappingFilter arg);
	org.omg.CosNotification.EventType[] obtain_offered_types(org.omg.CosNotifyChannelAdmin.ObtainInfoMode mode);
	void validate_event_qos(org.omg.CosNotification.Property[] required_qos, org.omg.CosNotification.NamedPropertyRangeSeqHolder available_qos) throws org.omg.CosNotification.UnsupportedQoS;
}
