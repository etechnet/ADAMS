package org.omg.CosTransactions;


/**
 * Generated from IDL interface "SubtransactionAwareResource".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface SubtransactionAwareResourceOperations
	extends org.omg.CosTransactions.ResourceOperations
{
	/* constants */
	/* operations  */
	void commit_subtransaction(org.omg.CosTransactions.Coordinator parent);
	void rollback_subtransaction();
}
