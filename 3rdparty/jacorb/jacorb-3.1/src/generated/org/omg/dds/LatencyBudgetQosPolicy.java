package org.omg.dds;

/**
 * Generated from IDL struct "LatencyBudgetQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LatencyBudgetQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LatencyBudgetQosPolicy(){}
	public org.omg.dds.Duration_t duration;
	public LatencyBudgetQosPolicy(org.omg.dds.Duration_t duration)
	{
		this.duration = duration;
	}
}
