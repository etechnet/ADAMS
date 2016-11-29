package org.omg.PortableServer;


/**
 * Generated from IDL interface "IdUniquenessPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class IdUniquenessPolicyLocalTie
	extends _IdUniquenessPolicyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private IdUniquenessPolicyOperations _delegate;

	public IdUniquenessPolicyLocalTie(IdUniquenessPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public IdUniquenessPolicyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IdUniquenessPolicyOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.PortableServer.IdUniquenessPolicyValue value()
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
