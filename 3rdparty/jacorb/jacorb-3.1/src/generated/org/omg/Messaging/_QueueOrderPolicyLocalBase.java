package org.omg.Messaging;


/**
 * Abstract base class for implementations of local interface QueueOrderPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _QueueOrderPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements QueueOrderPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/Messaging/QueueOrderPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
