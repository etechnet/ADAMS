package org.omg.ETF;


/**
 * Generated from IDL interface "Handle".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface HandleOperations
{
	/* constants */
	/* operations  */
	boolean add_input(org.omg.ETF.Connection conn);
	void signal_data_available(org.omg.ETF.Connection conn);
	void closed_by_peer(org.omg.ETF.Connection conn);
}
