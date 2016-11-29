package org.jacorb.imr;


/**
 * Generated from IDL interface "Registration".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public interface RegistrationOperations
{
	/* constants */
	/* operations  */
	void register_host(org.jacorb.imr.HostInfo info) throws org.jacorb.imr.RegistrationPackage.InvalidSSDRef,org.jacorb.imr.RegistrationPackage.IllegalHostName;
	void set_server_down(java.lang.String name) throws org.jacorb.imr.UnknownServerName;
	void register_poa(java.lang.String name, java.lang.String server, java.lang.String host, int port) throws org.jacorb.imr.RegistrationPackage.IllegalPOAName,org.jacorb.imr.RegistrationPackage.DuplicatePOAName,org.jacorb.imr.UnknownServerName;
	org.jacorb.imr.ImRInfo get_imr_info();
}
