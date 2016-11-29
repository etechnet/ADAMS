package org.omg.dds;

/**
 * Generated from IDL struct "OwnershipQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class OwnershipQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public OwnershipQosPolicy(){}
	public org.omg.dds.OwnershipQosPolicyKind kind;
	public OwnershipQosPolicy(org.omg.dds.OwnershipQosPolicyKind kind)
	{
		this.kind = kind;
	}
}
