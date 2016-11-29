package org.omg.RTCORBA;


/**
 * Generated from IDL interface "PriorityBandedConnectionPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class PriorityBandedConnectionPolicyLocalTie
	extends _PriorityBandedConnectionPolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private PriorityBandedConnectionPolicyOperations _delegate;

	public PriorityBandedConnectionPolicyLocalTie(PriorityBandedConnectionPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public PriorityBandedConnectionPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PriorityBandedConnectionPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.RTCORBA.PriorityBand[] priority_bands()
	{
		return _delegate.priority_bands();
	}

	public int policy_type()
	{
		return _delegate.policy_type();
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
