package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface Interceptor
 * @author JacORB IDL compiler.
 */

public abstract class _InterceptorLocalBase
	extends org.omg.CORBA.LocalObject
	implements Interceptor
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/Interceptor:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
