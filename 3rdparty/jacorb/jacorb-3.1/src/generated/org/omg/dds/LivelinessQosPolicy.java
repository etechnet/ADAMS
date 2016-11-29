package org.omg.dds;

/**
 * Generated from IDL struct "LivelinessQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class LivelinessQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LivelinessQosPolicy(){}
	public org.omg.dds.LivelinessQosPolicyKind kind;
	public org.omg.dds.Duration_t lease_duration;
	public LivelinessQosPolicy(org.omg.dds.LivelinessQosPolicyKind kind, org.omg.dds.Duration_t lease_duration)
	{
		this.kind = kind;
		this.lease_duration = lease_duration;
	}
}
