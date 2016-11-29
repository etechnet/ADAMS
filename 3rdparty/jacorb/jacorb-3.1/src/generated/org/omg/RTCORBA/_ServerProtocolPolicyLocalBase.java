package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface ServerProtocolPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _ServerProtocolPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements ServerProtocolPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/ServerProtocolPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
