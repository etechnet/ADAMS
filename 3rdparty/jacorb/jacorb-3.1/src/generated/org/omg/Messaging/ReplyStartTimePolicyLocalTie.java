package org.omg.Messaging;


/**
 * Generated from IDL interface "ReplyStartTimePolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public class ReplyStartTimePolicyLocalTie
	extends _ReplyStartTimePolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ReplyStartTimePolicyOperations _delegate;

	public ReplyStartTimePolicyLocalTie(ReplyStartTimePolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public ReplyStartTimePolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ReplyStartTimePolicyOperations delegate)
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

	public org.omg.TimeBase.UtcT start_time()
	{
		return _delegate.start_time();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
