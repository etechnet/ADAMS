package org.omg.GIOP;

/**
 * Generated from IDL alias "LocateRequestHeader_1_1".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class LocateRequestHeader_1_1Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.GIOP.LocateRequestHeader_1_0 s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.GIOP.LocateRequestHeader_1_0 extract (final org.omg.CORBA.Any any)
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
			synchronized(LocateRequestHeader_1_1Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GIOP.LocateRequestHeader_1_1Helper.id(), "LocateRequestHeader_1_1",org.omg.CORBA.ORB.init().create_struct_tc(org.omg.GIOP.LocateRequestHeader_1_0Helper.id(),"LocateRequestHeader_1_0",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("request_id", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("object_key", org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(10))), null)}));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/GIOP/LocateRequestHeader_1_1:1.0";
	}
	public static org.omg.GIOP.LocateRequestHeader_1_0 read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.GIOP.LocateRequestHeader_1_0 _result;
		_result=org.omg.GIOP.LocateRequestHeader_1_0Helper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.GIOP.LocateRequestHeader_1_0 _s)
	{
		org.omg.GIOP.LocateRequestHeader_1_0Helper.write(_out,_s);
	}
}
