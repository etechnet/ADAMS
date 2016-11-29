package org.omg.Messaging;


/**
 * Generated from IDL interface "RelativeRequestTimeoutPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public class RelativeRequestTimeoutPolicyLocalTie
	extends _RelativeRequestTimeoutPolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private RelativeRequestTimeoutPolicyOperations _delegate;

	public RelativeRequestTimeoutPolicyLocalTie(RelativeRequestTimeoutPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public RelativeRequestTimeoutPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RelativeRequestTimeoutPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public int policy_type()
	{
		return _delegate.policy_type();
	}

	public long relative_expiry()
	{
		return _delegate.relative_expiry();
	}

	public org.omg.CORBA.Policy copy()
	{
		return _delegate.copy();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
