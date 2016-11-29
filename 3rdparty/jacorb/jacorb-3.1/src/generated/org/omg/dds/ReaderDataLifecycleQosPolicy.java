package org.omg.dds;

/**
 * Generated from IDL struct "ReaderDataLifecycleQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class ReaderDataLifecycleQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public ReaderDataLifecycleQosPolicy(){}
	public org.omg.dds.Duration_t autopurge_nowriter_samples_delay;
	public ReaderDataLifecycleQosPolicy(org.omg.dds.Duration_t autopurge_nowriter_samples_delay)
	{
		this.autopurge_nowriter_samples_delay = autopurge_nowriter_samples_delay;
	}
}
