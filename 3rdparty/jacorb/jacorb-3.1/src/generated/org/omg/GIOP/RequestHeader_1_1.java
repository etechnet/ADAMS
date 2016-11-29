package org.omg.GIOP;

/**
 * Generated from IDL struct "RequestHeader_1_1".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RequestHeader_1_1
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public RequestHeader_1_1(){}
	public org.omg.IOP.ServiceContext[] service_context;
	public int request_id;
	public boolean response_expected;
	public byte[] reserved;
	public byte[] object_key;
	public java.lang.String operation = "";
	public byte[] requesting_principal;
	public RequestHeader_1_1(org.omg.IOP.ServiceContext[] service_context, int request_id, boolean response_expected, byte[] reserved, byte[] object_key, java.lang.String operation, byte[] requesting_principal)
	{
		this.service_context = service_context;
		this.request_id = request_id;
		this.response_expected = response_expected;
		this.reserved = reserved;
		this.object_key = object_key;
		this.operation = operation;
		this.requesting_principal = requesting_principal;
	}
}
