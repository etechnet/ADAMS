package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface ORBInitializer
 * @author JacORB IDL compiler.
 */

public abstract class _ORBInitializerLocalBase
	extends org.omg.CORBA.LocalObject
	implements ORBInitializer
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/ORBInitializer:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
