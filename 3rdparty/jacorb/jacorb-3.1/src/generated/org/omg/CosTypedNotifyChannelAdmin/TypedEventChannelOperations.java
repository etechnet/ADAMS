package org.omg.CosTypedNotifyChannelAdmin;


/**
 * Generated from IDL interface "TypedEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public interface TypedEventChannelOperations
	extends org.omg.CosNotification.QoSAdminOperations , org.omg.CosNotification.AdminPropertiesAdminOperations , org.omg.CosTypedEventChannelAdmin.TypedEventChannelOperations
{
	/* constants */
	/* operations  */
	org.omg.CosTypedNotifyChannelAdmin.TypedEventChannelFactory MyFactory();
	org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin default_consumer_admin();
	org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin default_supplier_admin();
	org.omg.CosNotifyFilter.FilterFactory default_filter_factory();
	org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin new_for_typed_notification_consumers(org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator op, org.omg.CORBA.IntHolder id);
	org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin new_for_typed_notification_suppliers(org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator op, org.omg.CORBA.IntHolder id);
	org.omg.CosTypedNotifyChannelAdmin.TypedConsumerAdmin get_consumeradmin(int id) throws org.omg.CosNotifyChannelAdmin.AdminNotFound;
	org.omg.CosTypedNotifyChannelAdmin.TypedSupplierAdmin get_supplieradmin(int id) throws org.omg.CosNotifyChannelAdmin.AdminNotFound;
	int[] get_all_consumeradmins();
	int[] get_all_supplieradmins();
}
