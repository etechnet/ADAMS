package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface IORInterceptor_3_0
 * @author JacORB IDL compiler.
 */

public abstract class _IORInterceptor_3_0LocalBase
	extends org.omg.CORBA.LocalObject
	implements IORInterceptor_3_0
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/IORInterceptor_3_0:1.0","IDL:omg.org/PortableInterceptor/Interceptor:1.0","IDL:omg.org/PortableInterceptor/IORInterceptor:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
