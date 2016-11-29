package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface IORInterceptor
 * @author JacORB IDL compiler.
 */

public abstract class _IORInterceptorLocalBase
	extends org.omg.CORBA.LocalObject
	implements IORInterceptor
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/IORInterceptor:1.0","IDL:omg.org/PortableInterceptor/Interceptor:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
