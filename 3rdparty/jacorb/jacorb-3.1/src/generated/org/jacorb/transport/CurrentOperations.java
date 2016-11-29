package org.jacorb.transport;


/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.56
 */

public interface CurrentOperations
{
	/* constants */
	/* operations  */
	int id() throws org.jacorb.transport.NoContext;
	long bytes_sent() throws org.jacorb.transport.NoContext;
	long bytes_received() throws org.jacorb.transport.NoContext;
	long messages_sent() throws org.jacorb.transport.NoContext;
	long messages_received() throws org.jacorb.transport.NoContext;
	long open_since() throws org.jacorb.transport.NoContext;
}
