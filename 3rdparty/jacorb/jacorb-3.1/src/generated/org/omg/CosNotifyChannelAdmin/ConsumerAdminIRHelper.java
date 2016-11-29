package org.omg.CosNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class ConsumerAdminIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_proxy_supplier", "(in:proxy_id org.omg.CosNotifyChannelAdmin.ProxyID)");
		irInfo.put("lifetime_filter", "attribute-w;org.omg.CosNotifyFilter.MappingFilter");
		irInfo.put("priority_filter", "attribute-w;org.omg.CosNotifyFilter.MappingFilter");
		irInfo.put("pull_suppliers", "attribute;org.omg.CosNotifyChannelAdmin.ProxyIDSeq");
		irInfo.put("push_suppliers", "attribute;org.omg.CosNotifyChannelAdmin.ProxyIDSeq");
		irInfo.put("obtain_notification_push_supplier", "(in:ctype ,out:proxy_id org.omg.CosNotifyChannelAdmin.ProxyID)");
		irInfo.put("MyChannel", "attribute;org.omg.CosNotifyChannelAdmin.EventChannel");
		irInfo.put("destroy", "()");
		irInfo.put("MyOperator", "attribute;org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator");
		irInfo.put("MyID", "attribute;org.omg.CosNotifyChannelAdmin.AdminID");
		irInfo.put("obtain_notification_pull_supplier", "(in:ctype ,out:proxy_id org.omg.CosNotifyChannelAdmin.ProxyID)");
	}
}
