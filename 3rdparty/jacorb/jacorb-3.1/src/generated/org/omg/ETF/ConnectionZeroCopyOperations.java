package org.omg.ETF;


/**
 * Generated from IDL interface "ConnectionZeroCopy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface ConnectionZeroCopyOperations
	extends org.omg.ETF.ConnectionOperations
{
	/* constants */
	/* operations  */
	org.omg.ETF.BufferList create_buffer_list();
	void write_zc(org.omg.ETF.BufferListHolder data, long time_out);
	void read_zc(org.omg.ETF.BufferListHolder data, int min_length, long time_out);
}
