package org.omg.GIOP;

/**
 * Generated from IDL struct "RequestHeader_1_2".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class RequestHeader_1_2
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public RequestHeader_1_2(){}
	public int request_id;
	public byte response_flags;
	public byte[] reserved;
	public org.omg.GIOP.TargetAddress target;
	public java.lang.String operation = "";
	public org.omg.IOP.ServiceContext[] service_context;
	public RequestHeader_1_2(int request_id, byte response_flags, byte[] reserved, org.omg.GIOP.TargetAddress target, java.lang.String operation, org.omg.IOP.ServiceContext[] service_context)
	{
		this.request_id = request_id;
		this.response_flags = response_flags;
		this.reserved = reserved;
		this.target = target;
		this.operation = operation;
		this.service_context = service_context;
	}
}
