package org.jacorb.imr;


/**
 * Generated from IDL interface "ServerStartupDaemon".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public interface ServerStartupDaemonOperations
{
	/* constants */
	/* operations  */
	int get_system_load();
	void start_server(java.lang.String command) throws org.jacorb.imr.ServerStartupFailed;
}
