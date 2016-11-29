package org.omg.ETF;


/**
 * Generated from IDL interface "ConnectionZeroCopy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public class ConnectionZeroCopyLocalTie
	extends _ConnectionZeroCopyLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ConnectionZeroCopyOperations _delegate;

	public ConnectionZeroCopyLocalTie(ConnectionZeroCopyOperations delegate)
	{
		_delegate = delegate;
	}
	public ConnectionZeroCopyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ConnectionZeroCopyOperations delegate)
	{
		_delegate = delegate;
	}
	public boolean wait_next_data(long time_out)
	{
		return _delegate.wait_next_data(time_out);
	}

	public void write_zc(org.omg.ETF.BufferListHolder data, long time_out)
	{
_delegate.write_zc(data,time_out);
	}

	public boolean is_data_available()
	{
		return _delegate.is_data_available();
	}

	public boolean is_connected()
	{
		return _delegate.is_connected();
	}

	public void flush()
	{
_delegate.flush();
	}

	public boolean supports_callback()
	{
		return _delegate.supports_callback();
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

	public org.omg.ETF.BufferList create_buffer_list()
	{
		return _delegate.create_buffer_list();
	}

	public void close()
	{
_delegate.close();
	}

	public void read_zc(org.omg.ETF.BufferListHolder data, int min_length, long time_out)
	{
_delegate.read_zc(data,min_length,time_out);
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
