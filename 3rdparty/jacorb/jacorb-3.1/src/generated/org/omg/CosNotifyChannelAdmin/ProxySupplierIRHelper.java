package org.omg.CosNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ProxySupplierIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("obtain_offered_types", "org.omg.CosNotification.EventTypeSeq(in:mode )");
		irInfo.put("priority_filter", "attribute-w;org.omg.CosNotifyFilter.MappingFilter");
		irInfo.put("validate_event_qos", "(in:required_qos org.omg.CosNotification.QoSProperties,out:available_qos org.omg.CosNotification.NamedPropertyRangeSeq)");
		irInfo.put("MyType", "attribute;org.omg.CosNotifyChannelAdmin.ProxyType");
		irInfo.put("MyAdmin", "attribute;org.omg.CosNotifyChannelAdmin.ConsumerAdmin");
		irInfo.put("lifetime_filter", "attribute-w;org.omg.CosNotifyFilter.MappingFilter");
	}
}
