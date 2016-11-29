package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface POAManager
 * @author JacORB IDL compiler.
 */

public abstract class _POAManagerLocalBase
	extends org.omg.CORBA.LocalObject
	implements POAManager
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/POAManager:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
