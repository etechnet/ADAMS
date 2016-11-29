package org.omg.CosNotifyCommAck;


/**
 * Generated from IDL interface "SequencePullSupplierAck".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public interface SequencePullSupplierAckOperations
	extends org.omg.CosNotifyComm.SequencePullSupplierOperations
{
	/* constants */
	/* operations  */
	void acknowledge(int[] sequence_numbers);
}
