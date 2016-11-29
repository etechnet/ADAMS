package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface PolicyFactory
 * @author JacORB IDL compiler.
 */

public abstract class _PolicyFactoryLocalBase
	extends org.omg.CORBA.LocalObject
	implements PolicyFactory
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/PolicyFactory:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
