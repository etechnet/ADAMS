package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ServerRequestInterceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface ServerRequestInterceptorOperations
	extends org.omg.PortableInterceptor.InterceptorOperations
{
	/* constants */
	/* operations  */
	void receive_request_service_contexts(org.omg.PortableInterceptor.ServerRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
	void receive_request(org.omg.PortableInterceptor.ServerRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
	void send_reply(org.omg.PortableInterceptor.ServerRequestInfo ri);
	void send_exception(org.omg.PortableInterceptor.ServerRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
	void send_other(org.omg.PortableInterceptor.ServerRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
}
