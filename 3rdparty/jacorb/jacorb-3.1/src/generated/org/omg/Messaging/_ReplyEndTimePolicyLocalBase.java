package org.omg.Messaging;


/**
 * Abstract base class for implementations of local interface ReplyEndTimePolicy
 * @author JacORB IDL compiler.
 */

public abstract class _ReplyEndTimePolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements ReplyEndTimePolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/Messaging/ReplyEndTimePolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
