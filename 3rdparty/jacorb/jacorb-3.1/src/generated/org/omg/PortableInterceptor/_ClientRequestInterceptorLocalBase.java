package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface ClientRequestInterceptor
 * @author JacORB IDL compiler.
 */

public abstract class _ClientRequestInterceptorLocalBase
	extends org.omg.CORBA.LocalObject
	implements ClientRequestInterceptor
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/ClientRequestInterceptor:1.0","IDL:omg.org/PortableInterceptor/Interceptor:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
