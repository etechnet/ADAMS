package org.omg.CORBA;


/**
 * Generated from IDL interface "PolicyManager".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class PolicyManagerLocalTie
	extends _PolicyManagerLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private PolicyManagerOperations _delegate;

	public PolicyManagerLocalTie(PolicyManagerOperations delegate)
	{
		_delegate = delegate;
	}
	public PolicyManagerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PolicyManagerOperations delegate)
	{
		_delegate = delegate;
	}
	public void set_policy_overrides(org.omg.CORBA.Policy[] policies, org.omg.CORBA.SetOverrideType set_add) throws org.omg.CORBA.InvalidPolicies
	{
_delegate.set_policy_overrides(policies,set_add);
	}

	public org.omg.CORBA.Policy[] get_policy_overrides(int[] ts)
	{
		return _delegate.get_policy_overrides(ts);
	}

}
