package org.omg.RTCORBA;


/**
 * Abstract base class for implementations of local interface PriorityBandedConnectionPolicy
 * @author JacORB IDL compiler.
 */

public abstract class _PriorityBandedConnectionPolicyLocalBase
	extends org.omg.CORBA.LocalObject
	implements PriorityBandedConnectionPolicy
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] _type_ids = {"IDL:omg.org/RTCORBA/PriorityBandedConnectionPolicy:1.0","IDL:omg.org/CORBA/Policy:1.0"};
	public String[] _ids()	{
		return(String[])_type_ids.clone();
	}
}
