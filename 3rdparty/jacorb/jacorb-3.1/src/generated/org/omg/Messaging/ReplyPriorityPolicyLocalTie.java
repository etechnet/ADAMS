package org.omg.Messaging;


/**
 * Generated from IDL interface "ReplyPriorityPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public class ReplyPriorityPolicyLocalTie
	extends _ReplyPriorityPolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ReplyPriorityPolicyOperations _delegate;

	public ReplyPriorityPolicyLocalTie(ReplyPriorityPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public ReplyPriorityPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ReplyPriorityPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public int policy_type()
	{
		return _delegate.policy_type();
	}

	public org.omg.CORBA.Policy copy()
	{
		return _delegate.copy();
	}

	public org.omg.Messaging.PriorityRange priority_range()
	{
		return _delegate.priority_range();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
