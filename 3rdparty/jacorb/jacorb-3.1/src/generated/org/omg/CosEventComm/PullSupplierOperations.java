package org.omg.CosEventComm;


/**
 * Generated from IDL interface "PullSupplier".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface PullSupplierOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.Any pull() throws org.omg.CosEventComm.Disconnected;
	org.omg.CORBA.Any try_pull(org.omg.CORBA.BooleanHolder has_event) throws org.omg.CosEventComm.Disconnected;
	void disconnect_pull_supplier();
}
