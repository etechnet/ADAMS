package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "ClientRequestInterceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class ClientRequestInterceptorLocalTie
	extends _ClientRequestInterceptorLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private ClientRequestInterceptorOperations _delegate;

	public ClientRequestInterceptorLocalTie(ClientRequestInterceptorOperations delegate)
	{
		_delegate = delegate;
	}
	public ClientRequestInterceptorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ClientRequestInterceptorOperations delegate)
	{
		_delegate = delegate;
	}
	public java.lang.String name()
	{
		return _delegate.name();
	}

	public void receive_other(org.omg.PortableInterceptor.ClientRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest
	{
_delegate.receive_other(ri);
	}

	public void send_poll(org.omg.PortableInterceptor.ClientRequestInfo ri)
	{
_delegate.send_poll(ri);
	}

	public void receive_reply(org.omg.PortableInterceptor.ClientRequestInfo ri)
	{
_delegate.receive_reply(ri);
	}

	public void send_request(org.omg.PortableInterceptor.ClientRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest
	{
_delegate.send_request(ri);
	}

	public void receive_exception(org.omg.PortableInterceptor.ClientRequestInfo ri) throws org.omg.PortableInterceptor.ForwardRequest
	{
_delegate.receive_exception(ri);
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
