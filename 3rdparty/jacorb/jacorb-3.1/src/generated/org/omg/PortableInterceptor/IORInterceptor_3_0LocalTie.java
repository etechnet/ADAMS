package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "IORInterceptor_3_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class IORInterceptor_3_0LocalTie
	extends _IORInterceptor_3_0LocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private IORInterceptor_3_0Operations _delegate;

	public IORInterceptor_3_0LocalTie(IORInterceptor_3_0Operations delegate)
	{
		_delegate = delegate;
	}
	public IORInterceptor_3_0Operations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IORInterceptor_3_0Operations delegate)
	{
		_delegate = delegate;
	}
	public java.lang.String name()
	{
		return _delegate.name();
	}

	public void adapter_manager_state_changed(java.lang.String id, short state)
	{
_delegate.adapter_manager_state_changed(id,state);
	}

	public void establish_components(org.omg.PortableInterceptor.IORInfo info)
	{
_delegate.establish_components(info);
	}

	public void components_established(org.omg.PortableInterceptor.IORInfo info)
	{
_delegate.components_established(info);
	}

	public void destroy()
	{
_delegate.destroy();
	}

	public void adapter_state_changed(org.omg.PortableInterceptor.ObjectReferenceTemplate[] templates, short state)
	{
_delegate.adapter_state_changed(templates,state);
	}

}
