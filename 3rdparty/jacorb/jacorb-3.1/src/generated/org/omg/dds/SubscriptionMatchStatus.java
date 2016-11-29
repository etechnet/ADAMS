package org.omg.dds;

/**
 * Generated from IDL struct "SubscriptionMatchStatus".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.34
 */

public final class SubscriptionMatchStatus
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public SubscriptionMatchStatus(){}
	public int total_count;
	public int total_count_change;
	public int last_publication_handle;
	public SubscriptionMatchStatus(int total_count, int total_count_change, int last_publication_handle)
	{
		this.total_count = total_count;
		this.total_count_change = total_count_change;
		this.last_publication_handle = last_publication_handle;
	}
}
