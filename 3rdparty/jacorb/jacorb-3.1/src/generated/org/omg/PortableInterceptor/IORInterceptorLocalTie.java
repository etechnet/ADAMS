package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "IORInterceptor".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class IORInterceptorLocalTie
	extends _IORInterceptorLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private IORInterceptorOperations _delegate;

	public IORInterceptorLocalTie(IORInterceptorOperations delegate)
	{
		_delegate = delegate;
	}
	public IORInterceptorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IORInterceptorOperations delegate)
	{
		_delegate = delegate;
	}
	public java.lang.String name()
	{
		return _delegate.name();
	}

	public void establish_components(org.omg.PortableInterceptor.IORInfo info)
	{
_delegate.establish_components(info);
	}

	public void destroy()
	{
_delegate.destroy();
	}

}
