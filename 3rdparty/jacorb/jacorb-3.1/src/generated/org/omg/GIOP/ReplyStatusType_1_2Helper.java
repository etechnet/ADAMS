package org.omg.GIOP;
/**
 * Generated from IDL enum "ReplyStatusType_1_2".
 *
 * @author JacORB IDL compiler V 3.1, 19-Aug-2012
 * @version generated at 27-set-2013 13.02.31
 */

public final class ReplyStatusType_1_2Helper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ReplyStatusType_1_2Helper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_enum_tc(org.omg.GIOP.ReplyStatusType_1_2Helper.id(),"ReplyStatusType_1_2",new String[]{"NO_EXCEPTION","USER_EXCEPTION","SYSTEM_EXCEPTION","LOCATION_FORWARD","LOCATION_FORWARD_PERM","NEEDS_ADDRESSING_MODE"});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.omg.GIOP.ReplyStatusType_1_2 s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.omg.GIOP.ReplyStatusType_1_2 extract (final org.omg.CORBA.Any any)
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
		return "IDL:omg.org/GIOP/ReplyStatusType_1_2:1.0";
	}
	public static ReplyStatusType_1_2 read (final org.omg.CORBA.portable.InputStream in)
	{
		return ReplyStatusType_1_2.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final ReplyStatusType_1_2 s)
	{
		out.write_long(s.value());
	}
}
