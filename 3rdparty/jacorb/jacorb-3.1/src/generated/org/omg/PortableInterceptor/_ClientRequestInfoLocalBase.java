package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface ClientRequestInfo
 * @author JacORB IDL compiler.
 */

public abstract class _ClientRequestInfoLocalBase
	extends org.omg.CORBA.LocalObject
	implements ClientRequestInfo
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/ClientRequestInfo:1.0","IDL:omg.org/PortableInterceptor/RequestInfo:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
