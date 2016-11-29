package org.omg.dds;

/**
 * Generated from IDL struct "QosPolicyCount".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class QosPolicyCount
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public QosPolicyCount(){}
	public int policy_id;
	public int count;
	public QosPolicyCount(int policy_id, int count)
	{
		this.policy_id = policy_id;
		this.count = count;
	}
}
