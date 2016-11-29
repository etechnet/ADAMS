package org.omg.PortableGroup;


/**
 * Abstract base class for implementations of local interface GOA
 * @author JacORB IDL compiler.
 */

public abstract class _GOALocalBase
	extends org.omg.CORBA.LocalObject
	implements GOA
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableGroup/GOA:1.0","IDL:omg.org/PortableServer/POA:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
