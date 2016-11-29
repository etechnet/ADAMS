package org.omg.GIOP;

/**
 * Generated from IDL alias "LocateReplyHeader_1_1".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class LocateReplyHeader_1_1Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.GIOP.LocateReplyHeader_1_0 s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.GIOP.LocateReplyHeader_1_0 extract (final org.omg.CORBA.Any any)
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
			synchronized(LocateReplyHeader_1_1Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GIOP.LocateReplyHeader_1_1Helper.id(), "LocateReplyHeader_1_1",org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.LocateReplyHeader_1_0Helper.id(),"LocateReplyHeader_1_0",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("request_id", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("locate_status", org.omg.CORBA.ORB.init().create_enum_tc(org.omg.GIOP.LocateStatusType_1_0Helper.id(),"LocateStatusType_1_0",new String[]{"UNKNOWN_OBJECT","OBJECT_HERE","OBJECT_FORWARD"}), null)}));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/GIOP/LocateReplyHeader_1_1:1.0";
	}
	public static org.omg.GIOP.LocateReplyHeader_1_0 read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.GIOP.LocateReplyHeader_1_0 _result;
		_result=org.omg.GIOP.LocateReplyHeader_1_0Helper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.GIOP.LocateReplyHeader_1_0 _s)
	{
		org.omg.GIOP.LocateReplyHeader_1_0Helper.write(_out,_s);
	}
}
