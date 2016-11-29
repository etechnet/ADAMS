package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "Interceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class InterceptorLocalTie
	extends _InterceptorLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private InterceptorOperations _delegate;

	public InterceptorLocalTie(InterceptorOperations delegate)
	{
		_delegate = delegate;
	}
	public InterceptorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(InterceptorOperations delegate)
	{
		_delegate = delegate;
	}
	public java.lang.String name()
	{
		return _delegate.name();
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
