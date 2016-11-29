package org.omg.Messaging;


/**
 * Generated from IDL interface "RequestStartTimePolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public class RequestStartTimePolicyLocalTie
	extends _RequestStartTimePolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private RequestStartTimePolicyOperations _delegate;

	public RequestStartTimePolicyLocalTie(RequestStartTimePolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public RequestStartTimePolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RequestStartTimePolicyOperations delegate)
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
