package org.jacorb.sasPolicy;


/**
 * Abstract base class for implementations of local interface SASPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _SASPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements SASPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:org/jacorb/sasPolicy/SASPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
