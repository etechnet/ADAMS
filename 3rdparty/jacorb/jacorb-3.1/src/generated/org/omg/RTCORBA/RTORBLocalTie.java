package org.omg.RTCORBA;


/**
 * Generated from IDL interface "RTORB".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class RTORBLocalTie
	extends _RTORBLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private RTORBOperations _delegate;

	public RTORBLocalTie(RTORBOperations delegate)
	{
		_delegate = delegate;
	}
	public RTORBOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RTORBOperations delegate)
	{
		_delegate = delegate;
	}
	public org.omg.RTCORBA.ThreadpoolPolicy create_threadpool_policy(int threadpool)
	{
		return _delegate.create_threadpool_policy(threadpool);
	}

	public org.omg.RTCORBA.PriorityModelPolicy create_priority_model_policy(org.omg.RTCORBA.PriorityModel priority_model, short server_priority)
	{
		return _delegate.create_priority_model_policy(priority_model,server_priority);
	}

	public org.omg.RTCORBA.ClientProtocolPolicy create_client_protocol_policy(org.omg.RTCORBA.Protocol[] protocols)
	{
		return _delegate.create_client_protocol_policy(protocols);
	}

	public int create_threadpool_with_lanes(int stacksize, org.omg.RTCORBA.ThreadpoolLane[] lanes, boolean allow_borrowing, boolean allow_request_buffering, int max_buffered_requests, int max_request_buffer_size)
	{
		return _delegate.create_threadpool_with_lanes(stacksize,lanes,allow_borrowing,allow_request_buffering,max_buffered_requests,max_request_buffer_size);
	}

	public org.omg.RTCORBA.Mutex create_mutex()
	{
		return _delegate.create_mutex();
	}

	public org.omg.RTCORBA.PriorityBandedConnectionPolicy create_priority_banded_connection_policy(org.omg.RTCORBA.PriorityBand[] priority_bands)
	{
		return _delegate.create_priority_banded_connection_policy(priority_bands);
	}

	public void destroy_mutex(org.omg.RTCORBA.Mutex the_mutex)
	{
_delegate.destroy_mutex(the_mutex);
	}

	public org.omg.RTCORBA.PrivateConnectionPolicy create_private_connection_policy()
	{
		return _delegate.create_private_connection_policy();
	}

	public int create_threadpool(int stacksize, int static_threads, int dynamic_threads, short default_priority, boolean allow_request_buffering, int max_buffered_requests, int max_request_buffer_size)
	{
		return _delegate.create_threadpool(stacksize,static_threads,dynamic_threads,default_priority,allow_request_buffering,max_buffered_requests,max_request_buffer_size);
	}

	public org.omg.RTCORBA.ServerProtocolPolicy create_server_protocol_policy(org.omg.RTCORBA.Protocol[] protocols)
	{
		return _delegate.create_server_protocol_policy(protocols);
	}

	public org.omg.RTCORBA.TCPProtocolProperties create_tcp_protocol_properties(int send_buffer_size, int recv_buffer_size, boolean keep_alive, boolean dont_route, boolean no_delay)
	{
		return _delegate.create_tcp_protocol_properties(send_buffer_size,recv_buffer_size,keep_alive,dont_route,no_delay);
	}

	public void destroy_threadpool(int threadpool) throws org.omg.RTCORBA.RTORBPackage.InvalidThreadpool
	{
_delegate.destroy_threadpool(threadpool);
	}

}
