package org.jacorb.util.tracing;

/**
 * Generated from IDL struct "Request".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.55
 */

public final class Request
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Request(){}
	public int originator;
	public long rid;
	public Request(int originator, long rid)
	{
		this.originator = originator;
		this.rid = rid;
	}
}
