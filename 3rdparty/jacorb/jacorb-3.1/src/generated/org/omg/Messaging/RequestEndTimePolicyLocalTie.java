package org.omg.Messaging;


/**
 * Generated from IDL interface "RequestEndTimePolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public class RequestEndTimePolicyLocalTie
	extends _RequestEndTimePolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private RequestEndTimePolicyOperations _delegate;

	public RequestEndTimePolicyLocalTie(RequestEndTimePolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public RequestEndTimePolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RequestEndTimePolicyOperations delegate)
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

	public org.omg.TimeBase.UtcT end_time()
	{
		return _delegate.end_time();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
