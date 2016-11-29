package org.omg.dds;

/**
 * Generated from IDL struct "ReliabilityQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ReliabilityQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ReliabilityQosPolicy(){}
	public org.omg.dds.ReliabilityQosPolicyKind kind;
	public org.omg.dds.Duration_t max_blocking_time;
	public ReliabilityQosPolicy(org.omg.dds.ReliabilityQosPolicyKind kind, org.omg.dds.Duration_t max_blocking_time)
	{
		this.kind = kind;
		this.max_blocking_time = max_blocking_time;
	}
}
