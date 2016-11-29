package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface ORBInitInfo
 * @author JacORB IDL compiler.
 */

public abstract class _ORBInitInfoLocalBase
	extends org.omg.CORBA.LocalObject
	implements ORBInitInfo
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/ORBInitInfo:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
