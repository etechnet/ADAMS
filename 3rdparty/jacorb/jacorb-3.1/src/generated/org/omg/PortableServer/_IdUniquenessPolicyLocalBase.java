package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface IdUniquenessPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _IdUniquenessPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements IdUniquenessPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/IdUniquenessPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
