package org.omg.CosNotifyComm;


/**
 * Generated from IDL interface "SequencePullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public interface SequencePullSupplierOperations
	extends org.omg.CosNotifyComm.NotifySubscribeOperations
{
	/* constants */
	/* operations  */
	org.omg.CosNotification.StructuredEvent[] pull_structured_events(int max_number) throws org.omg.CosEventComm.Disconnected;
	org.omg.CosNotification.StructuredEvent[] try_pull_structured_events(int max_number, org.omg.CORBA.BooleanHolder has_event) throws org.omg.CosEventComm.Disconnected;
	void disconnect_sequence_pull_supplier();
}
