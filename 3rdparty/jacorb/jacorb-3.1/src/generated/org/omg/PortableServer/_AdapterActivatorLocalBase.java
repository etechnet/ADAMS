package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface AdapterActivator
 * @author JacORB IDL compiler.
 */

public abstract class _AdapterActivatorLocalBase
	extends org.omg.CORBA.LocalObject
	implements AdapterActivator
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/AdapterActivator:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
