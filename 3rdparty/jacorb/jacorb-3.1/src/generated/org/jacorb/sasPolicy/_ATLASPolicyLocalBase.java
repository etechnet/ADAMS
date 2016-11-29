package org.jacorb.sasPolicy;


/**
 * Abstract base class for implementations of local interface ATLASPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _ATLASPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements ATLASPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:org/jacorb/sasPolicy/ATLASPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
