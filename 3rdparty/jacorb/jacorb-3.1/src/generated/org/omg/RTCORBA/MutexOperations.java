package org.omg.RTCORBA;


/**
 * Generated from IDL interface "Mutex".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface MutexOperations
{
	/* constants */
	/* operations  */
	void lock();
	void unlock();
	boolean try_lock(long max_wait);
}
