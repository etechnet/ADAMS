package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface PrivateConnectionPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _PrivateConnectionPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements PrivateConnectionPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/PrivateConnectionPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
