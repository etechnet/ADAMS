package org.omg.ETF;


/**
 * Generated from IDL interface "Connection".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface ConnectionOperations
{
	/* constants */
	/* operations  */
	void write(boolean is_first, boolean is_last, byte[] data, int offset, int length, long time_out);
	int read(org.omg.ETF.BufferHolder data, int offset, int min_length, int max_length, long time_out);
	void flush();
	void connect(org.omg.ETF.Profile server_profile, long time_out);
	void close();
	boolean is_connected();
	org.omg.ETF.Profile get_server_profile();
	boolean is_data_available();
	boolean wait_next_data(long time_out);
	boolean supports_callback();
	boolean use_handle_time_out();
}
