package org.omg.GIOP;

/**
 * Generated from IDL struct "MessageHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class MessageHeader_1_0
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public MessageHeader_1_0(){}
	public char[] magic;
	public org.omg.GIOP.Version GIOP_version;
	public boolean byte_order;
	public byte message_type;
	public int message_size;
	public MessageHeader_1_0(char[] magic, org.omg.GIOP.Version GIOP_version, boolean byte_order, byte message_type, int message_size)
	{
		this.magic = magic;
		this.GIOP_version = GIOP_version;
		this.byte_order = byte_order;
		this.message_type = message_type;
		this.message_size = message_size;
	}
}
