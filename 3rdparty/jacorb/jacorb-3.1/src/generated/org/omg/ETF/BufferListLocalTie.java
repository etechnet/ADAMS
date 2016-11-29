package org.omg.ETF;


/**
 * Generated from IDL interface "BufferList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class BufferListLocalTie
	extends _BufferListLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private BufferListOperations _delegate;

	public BufferListLocalTie(BufferListOperations delegate)
	{
		_delegate = delegate;
	}
	public BufferListOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(BufferListOperations delegate)
	{
		_delegate = delegate;
	}
	public void release_buffers()
	{
_delegate.release_buffers();
	}

	public void get_buffer(int index, org.omg.ETF.BufferHolder buf)
	{
_delegate.get_buffer(index,buf);
	}

	public int add_buffer(int size, org.omg.ETF.BufferHolder buf)
	{
		return _delegate.add_buffer(size,buf);
	}

	public int num_buffers()
	{
		return _delegate.num_buffers();
	}

}
