package org.omg.ETF;


/**
 * Generated from IDL interface "Connection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class ConnectionLocalTie
	extends _ConnectionLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ConnectionOperations _delegate;

	public ConnectionLocalTie(ConnectionOperations delegate)
	{
		_delegate = delegate;
	}
	public ConnectionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ConnectionOperations delegate)
	{
		_delegate = delegate;
	}
	public boolean wait_next_data(long time_out)
	{
		return _delegate.wait_next_data(time_out);
	}

	public boolean is_data_available()
	{
		return _delegate.is_data_available();
	}

	public boolean is_connected()
	{
		return _delegate.is_connected();
	}

	public boolean supports_callback()
	{
		return _delegate.supports_callback();
	}

	public void flush()
	{
_delegate.flush();
	}

	public void write(boolean is_first, boolean is_last, byte[] data, int offset, int length, long time_out)
	{
_delegate.write(is_first,is_last,data,offset,length,time_out);
	}

	public org.omg.ETF.Profile get_server_profile()
	{
		return _delegate.get_server_profile();
	}

	public boolean use_handle_time_out()
	{
		return _delegate.use_handle_time_out();
	}

	public void close()
	{
_delegate.close();
	}

	public void connect(org.omg.ETF.Profile server_profile, long time_out)
	{
_delegate.connect(server_profile,time_out);
	}

	public int read(org.omg.ETF.BufferHolder data, int offset, int min_length, int max_length, long time_out)
	{
		return _delegate.read(data,offset,min_length,max_length,time_out);
	}

}
