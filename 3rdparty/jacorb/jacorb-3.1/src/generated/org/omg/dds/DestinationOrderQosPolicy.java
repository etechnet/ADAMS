package org.omg.dds;

/**
 * Generated from IDL struct "DestinationOrderQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class DestinationOrderQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DestinationOrderQosPolicy(){}
	public org.omg.dds.DestinationOrderQosPolicyKind kind;
	public DestinationOrderQosPolicy(org.omg.dds.DestinationOrderQosPolicyKind kind)
	{
		this.kind = kind;
	}
}
