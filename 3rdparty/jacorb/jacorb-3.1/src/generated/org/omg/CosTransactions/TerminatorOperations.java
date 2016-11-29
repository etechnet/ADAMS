package org.omg.CosTransactions;


/**
 * Generated from IDL interface "Terminator".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface TerminatorOperations
{
	/* constants */
	/* operations  */
	void commit(boolean report_heuristics) throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed;
	void rollback();
}
