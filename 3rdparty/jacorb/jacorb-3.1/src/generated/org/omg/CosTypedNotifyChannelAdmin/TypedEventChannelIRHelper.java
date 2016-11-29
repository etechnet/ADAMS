package org.omg.CosTypedNotifyChannelAdmin;


/**
 * This class contains generated Interface Repository information.
 * @author JacORB IDL compiler.
 */

public class TypedEventChannelIRHelper
{
	public static java.util.Hashtable irInfo = new java.util.Hashtable();
	static
	{
		irInfo.put("default_filter_factory", "attribute;org.omg.CosNotifyFilter.FilterFactory");
		irInfo.put("get_consumeradmin", "(in:id org.omg.CosNotifyChannelAdmin.AdminID)");
		irInfo.put("get_all_consumeradmins", "org.omg.CosNotifyChannelAdmin.AdminIDSeq()");
		irInfo.put("default_consumer_admin", "attribute;org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin");
		irInfo.put("new_for_typed_notification_suppliers", "(in:op ,out:id org.omg.CosNotifyChannelAdmin.AdminID)");
		irInfo.put("MyFactory", "attribute;org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory");
		irInfo.put("get_all_supplieradmins", "org.omg.CosNotifyChannelAdmin.AdminIDSeq()");
		irInfo.put("default_supplier_admin", "attribute;org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin");
		irInfo.put("new_for_typed_notification_consumers", "(in:op ,out:id org.omg.CosNotifyChannelAdmin.AdminID)");
		irInfo.put("get_supplieradmin", "(in:id org.omg.CosNotifyChannelAdmin.AdminID)");
	}
}
