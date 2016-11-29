package org.omg.CSI;

/**
 * Generated from IDL struct "CompleteEstablishContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class CompleteEstablishContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public CompleteEstablishContext(){}
	public long client_context_id;
	public boolean context_stateful;
	public byte[] final_context_token;
	public CompleteEstablishContext(long client_context_id, boolean context_stateful, byte[] final_context_token)
	{
		this.client_context_id = client_context_id;
		this.context_stateful = context_stateful;
		this.final_context_token = final_context_token;
	}
}
