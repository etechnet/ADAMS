package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ClientRequestInterceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface ClientRequestInterceptorOperations
	extends org.omg.PortableInterceptor.InterceptorOperations
{
	/* constants */
	/* operations  */
	void send_request(org.omg.PortableInterceptor.ClientRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
	void send_poll(org.omg.PortableInterceptor.ClientRequestInfo ri);
	void receive_reply(org.omg.PortableInterceptor.ClientRequestInfo ri);
	void receive_exception(org.omg.PortableInterceptor.ClientRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
	void receive_other(org.omg.PortableInterceptor.ClientRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest;
}
