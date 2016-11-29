package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class CurrentLocalTie
	extends _CurrentLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private CurrentOperations _delegate;

	public CurrentLocalTie(CurrentOperations delegate)
	{
		_delegate = delegate;
	}
	public CurrentOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(CurrentOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.CORBA.Any get_slot(int id) throws org.omg.PortableInterceptor.InvalidSlot
	{
		return _delegate.get_slot(id);
	}

	public void set_slot(int id, org.omg.CORBA.Any data) throws org.omg.PortableInterceptor.InvalidSlot
	{
_delegate.set_slot(id,data);
	}

}
