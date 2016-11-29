package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "RequestInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface RequestInfoOperations
{
	/* constants */
	/* operations  */
	int request_id();
	java.lang.String operation();
	org.omg.Dynamic.Parameter[] arguments();
	org.omg.CORBA.TypeCode[] exceptions();
	java.lang.String[] contexts();
	java.lang.String[] operation_context();
	org.omg.CORBA.Any result();
	boolean response_expected();
	short sync_scope();
	short reply_status();
	org.omg.CORBA.Object forward_reference();
	org.omg.CORBA.Any get_slot(int id) throws org.omg.PortableInterceptor.InvalidSlot;
	org.omg.IOP.ServiceContext get_request_service_context(int id);
	org.omg.IOP.ServiceContext get_reply_service_context(int id);
}
