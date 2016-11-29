package org.omg.GIOP;

/**
 * Generated from IDL struct "LocateReplyHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class LocateReplyHeader_1_0
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LocateReplyHeader_1_0(){}
	public int request_id;
	public org.omg.GIOP.LocateStatusType_1_0 locate_status;
	public LocateReplyHeader_1_0(int request_id, org.omg.GIOP.LocateStatusType_1_0 locate_status)
	{
		this.request_id = request_id;
		this.locate_status = locate_status;
	}
}
