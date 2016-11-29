package org.omg.ETF;


/**
 * Generated from IDL interface "Listener".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface ListenerOperations
{
	/* constants */
	/* operations  */
	void set_handle(org.omg.ETF.Handle up);
	org.omg.ETF.Connection accept();
	void listen();
	void destroy();
	void completed_data(org.omg.ETF.Connection conn);
	org.omg.ETF.Profile endpoint();
}
