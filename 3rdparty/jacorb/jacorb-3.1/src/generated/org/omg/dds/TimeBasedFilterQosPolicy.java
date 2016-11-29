package org.omg.dds;

/**
 * Generated from IDL struct "TimeBasedFilterQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class TimeBasedFilterQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TimeBasedFilterQosPolicy(){}
	public org.omg.dds.Duration_t minimum_separation;
	public TimeBasedFilterQosPolicy(org.omg.dds.Duration_t minimum_separation)
	{
		this.minimum_separation = minimum_separation;
	}
}
