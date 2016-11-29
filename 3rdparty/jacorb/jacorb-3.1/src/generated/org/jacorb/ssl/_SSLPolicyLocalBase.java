package org.jacorb.ssl;


/**
 * Abstract base class for implementations of local interface SSLPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _SSLPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements SSLPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:org/jacorb/ssl/SSLPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
