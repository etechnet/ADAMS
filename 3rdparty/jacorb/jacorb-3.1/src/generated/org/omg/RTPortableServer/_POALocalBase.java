package org.omg.RTPortableServer;


/**
 * Abstract base class for implementations of local interface POA
 * @author JacORB IDL compiler.
 */

public abstract class _POALocalBase
	extends org.omg.CORBA.LocalObject
	implements POA
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTPortableServer/POA:1.0","IDL:omg.org/PortableServer/POA:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
