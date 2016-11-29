package org.omg.CosNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ProxyConsumerIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("validate_event_qos", "(in:required_qos org.omg.CosNotification.QoSProperties,out:available_qos org.omg.CosNotification.NamedPropertyRangeSeq)");
		irInfo.put("obtain_subscription_types", "org.omg.CosNotification.EventTypeSeq(in:mode )");
		irInfo.put("MyType", "attribute;org.omg.CosNotifyChannelAdmin.ProxyType");
		irInfo.put("MyAdmin", "attribute;org.omg.CosNotifyChannelAdmin.SupplierAdmin");
	}
}
