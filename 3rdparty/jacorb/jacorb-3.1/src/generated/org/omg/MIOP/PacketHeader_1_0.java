package org.omg.MIOP;

/**
 * Generated from IDL struct "PacketHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PacketHeader_1_0
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public PacketHeader_1_0(){}
	public char[] magic;
	public byte hdr_version;
	public byte flags;
	public short packet_length;
	public int packet_number;
	public int number_of_packets;
	public byte[] Id;
	public PacketHeader_1_0(char[] magic, byte hdr_version, byte flags, short packet_length, int packet_number, int number_of_packets, byte[] Id)
	{
		this.magic = magic;
		this.hdr_version = hdr_version;
		this.flags = flags;
		this.packet_length = packet_length;
		this.packet_number = packet_number;
		this.number_of_packets = number_of_packets;
		this.Id = Id;
	}
}
