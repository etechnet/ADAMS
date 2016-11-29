package org.omg.CosTransactions;


/**
 * Generated from IDL interface "Synchronization".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface SynchronizationOperations
	extends org.omg.CosTransactions.TransactionalObjectOperations
{
	/* constants */
	/* operations  */
	void before_completion();
	void after_completion(org.omg.CosTransactions.Status status_);
}
