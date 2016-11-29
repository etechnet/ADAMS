package org.omg.GIOP;

/**
 * Generated from IDL alias "MessageHeader_1_3".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class MessageHeader_1_3Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.GIOP.MessageHeader_1_1 s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.GIOP.MessageHeader_1_1 extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(MessageHeader_1_3Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GIOP.MessageHeader_1_3Helper.id(), "MessageHeader_1_3",org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.MessageHeader_1_1Helper.id(),"MessageHeader_1_1",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("magic", org.omg.CORBA.ORB.init().create_array_tc(4,org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(9))), null),new org.omg.CORBA.StructMember("GIOP_version", org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.VersionHelper.id(),"Version",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("major", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("minor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null)}), null),new org.omg.CORBA.StructMember("flags", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("message_type", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10)), null),new org.omg.CORBA.StructMember("message_size", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)}));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/GIOP/MessageHeader_1_3:1.0";
	}
	public static org.omg.GIOP.MessageHeader_1_1 read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.GIOP.MessageHeader_1_1 _result;
		_result=org.omg.GIOP.MessageHeader_1_1Helper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.GIOP.MessageHeader_1_1 _s)
	{
		org.omg.GIOP.MessageHeader_1_1Helper.write(_out,_s);
	}
}
