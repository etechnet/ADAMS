package org.omg.dds;

/**
 * Generated from IDL struct "HistoryQosPolicy".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class HistoryQosPolicy
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public HistoryQosPolicy(){}
	public org.omg.dds.HistoryQosPolicyKind kind;
	public int depth;
	public HistoryQosPolicy(org.omg.dds.HistoryQosPolicyKind kind, int depth)
	{
		this.kind = kind;
		this.depth = depth;
	}
}
