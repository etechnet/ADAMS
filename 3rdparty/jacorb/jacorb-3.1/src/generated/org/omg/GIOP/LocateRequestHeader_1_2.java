package org.omg.GIOP;

/**
 * Generated from IDL struct "LocateRequestHeader_1_2".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class LocateRequestHeader_1_2
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LocateRequestHeader_1_2(){}
	public int request_id;
	public org.omg.GIOP.TargetAddress target;
	public LocateRequestHeader_1_2(int request_id, org.omg.GIOP.TargetAddress target)
	{
		this.request_id = request_id;
		this.target = target;
	}
}
