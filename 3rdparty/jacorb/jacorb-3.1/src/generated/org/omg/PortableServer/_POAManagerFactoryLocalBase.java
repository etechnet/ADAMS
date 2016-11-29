package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface POAManagerFactory
 * @author JacORB IDL compiler.
 */

public abstract class _POAManagerFactoryLocalBase
	extends org.omg.CORBA.LocalObject
	implements POAManagerFactory
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/POAManagerFactory:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
