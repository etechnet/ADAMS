package org.omg.ETF;


/**
 * Generated from IDL interface "BufferList".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.33
 */

public interface BufferListOperations
{
	/* constants */
	/* operations  */
	int add_buffer(int size, org.omg.ETF.BufferHolder buf);
	int num_buffers();
	void get_buffer(int index, org.omg.ETF.BufferHolder buf);
	void release_buffers();
}
