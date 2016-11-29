package org.omg.PortableServer;


/**
 * Abstract base class for implementations of local interface ServantRetentionPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _ServantRetentionPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements ServantRetentionPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/PortableServer/ServantRetentionPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
