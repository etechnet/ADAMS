package org.omg.dds;

/**
 * Generated from IDL struct "DeadlineQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DeadlineQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DeadlineQosPolicy(){}
	public org.omg.dds.Duration_t period;
	public DeadlineQosPolicy(org.omg.dds.Duration_t period)
	{
		this.period = period;
	}
}
