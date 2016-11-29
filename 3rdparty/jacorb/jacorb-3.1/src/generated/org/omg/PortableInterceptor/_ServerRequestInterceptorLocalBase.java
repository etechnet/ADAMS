package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface ServerRequestInterceptor
 * @author JacORB IDL compiler.
 */

public abstract class _ServerRequestInterceptorLocalBase
	extends org.omg.CORBA.LocalObject
	implements ServerRequestInterceptor
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/ServerRequestInterceptor:1.0","IDL:omg.org/PortableInterceptor/Interceptor:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
