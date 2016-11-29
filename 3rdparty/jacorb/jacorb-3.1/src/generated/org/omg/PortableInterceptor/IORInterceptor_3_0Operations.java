package org.omg.PortableInterceptor;


/**
 * Generated from IDL interface "IORInterceptor_3_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public interface IORInterceptor_3_0Operations
	extends org.omg.PortableInterceptor.IORInterceptorOperations
{
	/* constants */
	/* operations  */
	void components_established(org.omg.PortableInterceptor.IORInfo info);
	void adapter_manager_state_changed(java.lang.String id, short state);
	void adapter_state_changed(org.omg.PortableInterceptor.ObjectReferenceTemplate[] templates, short state);
}
