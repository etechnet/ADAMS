package org.omg.CosTransactions;


/**
 * Generated from IDL interface "TransactionFactory".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface TransactionFactoryOperations
{
	/* constants */
	/* operations  */
	org.omg.CosTransactions.Control create(int time_out);
	org.omg.CosTransactions.Control recreate(org.omg.CosTransactions.PropagationContext ctx);
}
