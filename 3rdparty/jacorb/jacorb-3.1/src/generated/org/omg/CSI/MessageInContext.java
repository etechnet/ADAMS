package org.omg.CSI;

/**
 * Generated from IDL struct "MessageInContext".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.36
 */

public final class MessageInContext
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public MessageInContext(){}
	public long client_context_id;
	public boolean discard_context;
	public MessageInContext(long client_context_id, boolean discard_context)
	{
		this.client_context_id = client_context_id;
		this.discard_context = discard_context;
	}
}
