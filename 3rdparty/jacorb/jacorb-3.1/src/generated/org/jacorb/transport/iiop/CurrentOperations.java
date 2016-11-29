package org.jacorb.transport.iiop;


/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.56
 */

public interface CurrentOperations
	extends org.jacorb.transport.CurrentOperations
{
	/* constants */
	/* operations  */
	int remote_port() throws org.jacorb.transport.NoContext;
	java.lang.String remote_host() throws org.jacorb.transport.NoContext;
	int local_port() throws org.jacorb.transport.NoContext;
	java.lang.String local_host() throws org.jacorb.transport.NoContext;
}
