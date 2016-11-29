package org.omg.CORBA;


/**
 * Generated from IDL interface "PollableSet".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.30
 */

public class PollableSetLocalTie
	extends _PollableSetLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private PollableSetOperations _delegate;

	public PollableSetLocalTie(PollableSetOperations delegate)
	{
		_delegate = delegate;
	}
	public PollableSetOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(PollableSetOperations delegate)
	{
		_delegate = delegate;
	}
	public void add_pollable(org.omg.CORBA.Pollable potential)
	{
_delegate.add_pollable(potential);
	}

	public org.omg.CORBA.Pollable get_ready_pollable(int timeout) throws org.omg.CORBA.PollableSetPackage.NoPossiblePollable
	{
		return _delegate.get_ready_pollable(timeout);
	}

	public void remove(org.omg.CORBA.Pollable potential) throws org.omg.CORBA.PollableSetPackage.UnknownPollable
	{
_delegate.remove(potential);
	}

	public org.omg.CORBA.DIIPollable create_dii_pollable()
	{
		return _delegate.create_dii_pollable();
	}

	public short number_left()
	{
		return _delegate.number_left();
	}

}
