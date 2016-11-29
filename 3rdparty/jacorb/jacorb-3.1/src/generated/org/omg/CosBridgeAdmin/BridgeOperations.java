package org.omg.CosBridgeAdmin;


/**
 * Generated from IDL interface "Bridge".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public interface BridgeOperations
{
	/* constants */
	/* operations  */
	org.omg.CosBridgeAdmin.ExternalEndpoint end_point_receiver();
	org.omg.CosBridgeAdmin.ExternalEndpoint end_point_sender();
	void start_bridge() throws org.omg.CosBridgeAdmin.BridgeAlreadyStarted,org.omg.CosBridgeAdmin.InvalidExternalEndPoints;
	void stop_bridge() throws org.omg.CosBridgeAdmin.BridgeInactive;
	void destroy();
}
