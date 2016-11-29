package org.jacorb.sasPolicy;


/**
 * Generated from IDL interface "SASPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public class SASPolicyLocalTie
	extends _SASPolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private SASPolicyOperations _delegate;

	public SASPolicyLocalTie(SASPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public SASPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(SASPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public org.jacorb.sasPolicy.SASPolicyValues value()
	{
		return _delegate.value();
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
