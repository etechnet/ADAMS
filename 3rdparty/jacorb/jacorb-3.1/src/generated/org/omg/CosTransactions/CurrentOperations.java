package org.omg.CosTransactions;


/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface CurrentOperations
	extends org.omg.CORBA.CurrentOperations
{
	/* constants */
	/* operations  */
	void begin() throws org.omg.CosTransactions.SubtransactionsUnavailable;
	void commit(boolean report_heuristics) throws org.omg.CosTransactions.NoTransaction,org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed;
	void rollback() throws org.omg.CosTransactions.NoTransaction;
	void rollback_only() throws org.omg.CosTransactions.NoTransaction;
	org.omg.CosTransactions.Status get_status();
	java.lang.String get_transaction_name();
	void set_timeout(int seconds);
	org.omg.CosTransactions.Control get_control();
	org.omg.CosTransactions.Control suspend();
	void resume(org.omg.CosTransactions.Control which) throws org.omg.CosTransactions.InvalidControl;
}
