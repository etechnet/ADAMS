package org.omg.CosEventChannelAdmin;


/**
 * Generated from IDL interface "EventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface EventChannelOperations
{
	/* constants */
	/* operations  */
	org.omg.CosEventChannelAdmin.ConsumerAdmin for_consumers();
	org.omg.CosEventChannelAdmin.SupplierAdmin for_suppliers();
	void destroy();
}
