package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "IORInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public class IORInfoLocalTie
	extends _IORInfoLocalBase
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private IORInfoOperations _delegate;

	public IORInfoLocalTie(IORInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public IORInfoOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IORInfoOperations delegate)
	{
		_delegate = delegate;
	}
	public void add_ior_component(org.omg.IOP.TaggedComponent a_component)
	{
_delegate.add_ior_component(a_component);
	}

	public org.omg.CORBA.Policy get_effective_policy(int type)
	{
		return _delegate.get_effective_policy(type);
	}

	public void add_ior_component_to_profile(org.omg.IOP.TaggedComponent a_component, int profile_id)
	{
_delegate.add_ior_component_to_profile(a_component,profile_id);
	}

}
