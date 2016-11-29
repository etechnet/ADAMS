package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface RequestInfo
 * @author JacORB IDL compiler.
 */

public abstract class _RequestInfoLocalBase
	extends org.omg.CORBA.LocalObject
	implements RequestInfo
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/RequestInfo:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
