package org.jacorb.imr;


/**
 * Generated from IDL interface "Admin".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public interface AdminOperations
{
	/* constants */
	/* operations  */
	org.jacorb.imr.HostInfo[] list_hosts();
	org.jacorb.imr.ServerInfo[] list_servers();
	org.jacorb.imr.ServerInfo get_server_info(java.lang.String name) throws org.jacorb.imr.UnknownServerName;
	void shutdown(boolean _wait);
	void save_server_table() throws org.jacorb.imr.AdminPackage.FileOpFailed;
	void register_server(java.lang.String name, java.lang.String command, java.lang.String host) throws org.jacorb.imr.AdminPackage.IllegalServerName,org.jacorb.imr.AdminPackage.DuplicateServerName;
	void unregister_server(java.lang.String name) throws org.jacorb.imr.UnknownServerName;
	void edit_server(java.lang.String name, java.lang.String command, java.lang.String host) throws org.jacorb.imr.UnknownServerName;
	void hold_server(java.lang.String name) throws org.jacorb.imr.UnknownServerName;
	void release_server(java.lang.String name) throws org.jacorb.imr.UnknownServerName;
	void start_server(java.lang.String name) throws org.jacorb.imr.ServerStartupFailed,org.jacorb.imr.UnknownServerName;
	void unregister_host(java.lang.String name) throws org.jacorb.imr.AdminPackage.UnknownHostName;
}
