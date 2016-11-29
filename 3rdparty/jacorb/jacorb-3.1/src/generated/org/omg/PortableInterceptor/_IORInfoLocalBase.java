package org.omg.PortableInterceptor;


/**
 * Abstract base class for implementations of local interface IORInfo
 * @author JacORB IDL compiler.
 */

public abstract class _IORInfoLocalBase
	extends org.omg.CORBA.LocalObject
	implements IORInfo
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableInterceptor/IORInfo:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
