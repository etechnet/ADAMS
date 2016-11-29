package org.omg.Messaging;


/**
 * Abstract base class for implementations of local interface ReplyPriorityPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _ReplyPriorityPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements ReplyPriorityPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/Messaging/ReplyPriorityPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
