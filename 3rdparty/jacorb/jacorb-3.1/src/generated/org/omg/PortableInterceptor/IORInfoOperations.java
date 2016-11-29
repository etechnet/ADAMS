package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "IORInfo".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface IORInfoOperations
{
	/* constants */
	/* operations  */
	org.omg.CORBA.Policy get_effective_policy(int type);
	void add_ior_component(org.omg.IOP.TaggedComponent a_component);
	void add_ior_component_to_profile(org.omg.IOP.TaggedComponent a_component, int profile_id);
}
