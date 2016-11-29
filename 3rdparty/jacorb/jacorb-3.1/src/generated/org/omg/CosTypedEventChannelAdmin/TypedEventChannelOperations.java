package org.omg.CosTypedEventChannelAdmin;


/**
 * Generated from IDL interface "TypedEventChannel".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.35
 */

public interface TypedEventChannelOperations
{
	/* constants */
	/* operations  */
	org.omg.CosTypedEventChannelAdmin.TypedConsumerAdmin for_consumers();
	org.omg.CosTypedEventChannelAdmin.TypedSupplierAdmin for_suppliers();
	void destroy();
}
