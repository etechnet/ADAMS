package org.omg.dds;

/**
 * Generated from IDL struct "WriterDataLifecycleQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class WriterDataLifecycleQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public WriterDataLifecycleQosPolicy(){}
	public boolean autodispose_unregistered_instances;
	public WriterDataLifecycleQosPolicy(boolean autodispose_unregistered_instances)
	{
		this.autodispose_unregistered_instances = autodispose_unregistered_instances;
	}
}
