package org.omg.MIOP;


/**
 * Generated from IDL struct "PacketHeader_1_0".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.32
 */

public final class PacketHeader_1_0Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(PacketHeader_1_0Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(org.omg.MIOP.PacketHeader_1_0Helper.id(),"PacketHeader_1_0",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("magic", org.omg.CORBA.ORB.init().create_array_tc(4,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(9))), null),new org.omg.CORBA.StructMember("hdr_version", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("flags", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("packet_length", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("packet_number", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("number_of_packets", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("Id", org.omg.CORBA.ORB.init().create_alias_tc(org.omg.MIOP.UniqueIdHelper.id(), "UniqueId",org.omg.CORBA.ORB.init().create_sequence_tc(252, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.MIOP.PacketHeader_1_0 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.MIOP.PacketHeader_1_0 extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:omg.org/MIOP/PacketHeader_1_0:1.0";
	}
	public static org.omg.MIOP.PacketHeader_1_0 read (final org.omg.CORBA.portable.InputStream in)
	{
		org.omg.MIOP.PacketHeader_1_0 result = new org.omg.MIOP.PacketHeader_1_0();
		result.magic = new char[4];
		in.read_char_array(result.magic,0,4);
		result.hdr_version=in.read_octet();
		result.flags=in.read_octet();
		result.packet_length=in.read_ushort();
		result.packet_number=in.read_ulong();
		result.number_of_packets=in.read_ulong();
		result.Id = org.omg.MIOP.UniqueIdHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.omg.MIOP.PacketHeader_1_0 s)
	{
				if (s.magic.length<4)
			throw new org.omg.CORBA.MARSHAL("Incorrect array size "+s.magic.length+", expecting 4");
		out.write_char_array(s.magic,0,4);
		out.write_octet(s.hdr_version);
		out.write_octet(s.flags);
		out.write_ushort(s.packet_length);
		out.write_ulong(s.packet_number);
		out.write_ulong(s.number_of_packets);
		org.omg.MIOP.UniqueIdHelper.write(out,s.Id);
	}
}
