package org.omg.CSI;

/**
 * Generated from IDL struct "ContextError".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class ContextError
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ContextError(){}
	public long client_context_id;
	public int major_status;
	public int minor_status;
	public byte[] error_token;
	public ContextError(long client_context_id, int major_status, int minor_status, byte[] error_token)
	{
		this.client_context_id = client_context_id;
		this.major_status = major_status;
		this.minor_status = minor_status;
		this.error_token = error_token;
	}
}
