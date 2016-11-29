package org.omg.CosNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class SupplierAdminIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("get_proxy_consumer", "(in:proxy_id org.omg.CosNotifyChannelAdmin.ProxyID)");
		irInfo.put("obtain_notification_pull_consumer", "(in:ctype ,out:proxy_id org.omg.CosNotifyChannelAdmin.ProxyID)");
		irInfo.put("pull_consumers", "attribute;org.omg.CosNotifyChannelAdmin.ProxyIDSeq");
		irInfo.put("push_consumers", "attribute;org.omg.CosNotifyChannelAdmin.ProxyIDSeq");
		irInfo.put("obtain_notification_push_consumer", "(in:ctype ,out:proxy_id org.omg.CosNotifyChannelAdmin.ProxyID)");
		irInfo.put("MyChannel", "attribute;org.omg.CosNotifyChannelAdmin.EventChannel");
		irInfo.put("destroy", "()");
		irInfo.put("MyOperator", "attribute;org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator");
		irInfo.put("MyID", "attribute;org.omg.CosNotifyChannelAdmin.AdminID");
	}
}
