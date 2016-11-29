package org.omg.Messaging;


/**
 * Abstract base class for implementations of local interface RelativeRoundtripTimeoutPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _RelativeRoundtripTimeoutPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements RelativeRoundtripTimeoutPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/Messaging/RelativeRoundtripTimeoutPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
