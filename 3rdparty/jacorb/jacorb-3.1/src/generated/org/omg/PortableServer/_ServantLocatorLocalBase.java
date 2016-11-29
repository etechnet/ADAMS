package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface ServantLocator
 * @author JacORB IDL compiler.
 */

public abstract class _ServantLocatorLocalBase
	extends org.omg.CORBA.LocalObject
	implements ServantLocator
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/ServantLocator:1.0","IDL:omg.org/PortableServer/ServantManager:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
