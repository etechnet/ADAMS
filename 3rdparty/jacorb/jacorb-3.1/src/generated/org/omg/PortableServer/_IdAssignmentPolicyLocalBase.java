package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface IdAssignmentPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _IdAssignmentPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements IdAssignmentPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/IdAssignmentPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
