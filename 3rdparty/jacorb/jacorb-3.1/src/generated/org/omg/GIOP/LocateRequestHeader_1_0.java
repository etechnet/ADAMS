package org.omg.GIOP;

/**
 * Generated from IDL struct "LocateRequestHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class LocateRequestHeader_1_0
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public LocateRequestHeader_1_0(){}
	public int request_id;
	public byte[] object_key;
	public LocateRequestHeader_1_0(int request_id, byte[] object_key)
	{
		this.request_id = request_id;
		this.object_key = object_key;
	}
}
