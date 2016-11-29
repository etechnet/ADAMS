package org.jacorb.transport.iiop;


/**
 * Generated from IDL interface "Current".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.56
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
	public int remote_port() throws org.jacorb.transport.NoContext
	{
		return _delegate.remote_port();
	}

	public java.lang.String local_host() throws org.jacorb.transport.NoContext
	{
		return _delegate.local_host();
	}

	public long messages_sent() throws org.jacorb.transport.NoContext
	{
		return _delegate.messages_sent();
	}

	public java.lang.String remote_host() throws org.jacorb.transport.NoContext
	{
		return _delegate.remote_host();
	}

	public long open_since() throws org.jacorb.transport.NoContext
	{
		return _delegate.open_since();
	}

	public long bytes_received() throws org.jacorb.transport.NoContext
	{
		return _delegate.bytes_received();
	}

	public int id() throws org.jacorb.transport.NoContext
	{
		return _delegate.id();
	}

	public long bytes_sent() throws org.jacorb.transport.NoContext
	{
		return _delegate.bytes_sent();
	}

	public int local_port() throws org.jacorb.transport.NoContext
	{
		return _delegate.local_port();
	}

	public long messages_received() throws org.jacorb.transport.NoContext
	{
		return _delegate.messages_received();
	}

}
