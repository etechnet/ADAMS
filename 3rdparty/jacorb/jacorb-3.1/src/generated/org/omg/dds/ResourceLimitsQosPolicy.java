package org.omg.dds;

/**
 * Generated from IDL struct "ResourceLimitsQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ResourceLimitsQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ResourceLimitsQosPolicy(){}
	public int max_samples;
	public int max_instances;
	public int max_samples_per_instance;
	public ResourceLimitsQosPolicy(int max_samples, int max_instances, int max_samples_per_instance)
	{
		this.max_samples = max_samples;
		this.max_instances = max_instances;
		this.max_samples_per_instance = max_samples_per_instance;
	}
}
