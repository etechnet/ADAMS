package org.omg.RTCORBA;

/**
 * Generated from IDL struct "ThreadpoolLane".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class ThreadpoolLane
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ThreadpoolLane(){}
	public short lane_priority;
	public int static_threads;
	public int dynamic_threads;
	public ThreadpoolLane(short lane_priority, int static_threads, int dynamic_threads)
	{
		this.lane_priority = lane_priority;
		this.static_threads = static_threads;
		this.dynamic_threads = dynamic_threads;
	}
}
