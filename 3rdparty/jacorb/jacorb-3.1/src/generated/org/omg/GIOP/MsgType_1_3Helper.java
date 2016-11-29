package org.omg.GIOP;

/**
 * Generated from IDL alias "MsgType_1_3".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class MsgType_1_3Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, org.omg.GIOP.MsgType_1_1 s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static org.omg.GIOP.MsgType_1_1 extract (final org.omg.CORBA.Any any)
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
			synchronized(MsgType_1_3Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(org.omg.GIOP.MsgType_1_3Helper.id(), "MsgType_1_3",org.omg.CORBA.ORB.init().create_enum_tc(org.omg.GIOP.MsgType_1_1Helper.id(),"MsgType_1_1",new String[]{"Request","Reply","CancelRequest","LocateRequest","LocateReply","CloseConnection","MessageError","Fragment"}));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:omg.org/GIOP/MsgType_1_3:1.0";
	}
	public static org.omg.GIOP.MsgType_1_1 read (final org.omg.CORBA.portable.InputStream _in)
	{
		org.omg.GIOP.MsgType_1_1 _result;
		_result=org.omg.GIOP.MsgType_1_1Helper.read(_in);
		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, org.omg.GIOP.MsgType_1_1 _s)
	{
		org.omg.GIOP.MsgType_1_1Helper.write(_out,_s);
	}
}
