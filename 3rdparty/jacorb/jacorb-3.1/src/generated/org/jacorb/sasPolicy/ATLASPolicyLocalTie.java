package org.jacorb.sasPolicy;


/**
 * Generated from IDL interface "ATLASPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.54
 */

public class ATLASPolicyLocalTie
	extends _ATLASPolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ATLASPolicyOperations _delegate;

	public ATLASPolicyLocalTie(ATLASPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public ATLASPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ATLASPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public org.jacorb.sasPolicy.ATLASPolicyValues value()
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
